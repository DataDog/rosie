package io.codiga.server.e2e.yaml.tsquery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiVersionTest extends E2EBase {

  private static final Logger logger = LoggerFactory.getLogger(ApiVersionTest.class);

  String code = """
schema-version: v3
service: super-service
team: awesome-team
contacts:
    - type: slack
      contact: https://org.slack.com/AWESOME_CHANNEL""";

  String fixedCode = """
schema-version: v2
service: super-service
team: awesome-team
contacts:
    - type: slack
      contact: https://org.slack.com/AWESOME_CHANNEL""";

  String tsQuery =
      """
(block_mapping_pair
  (flow_node) @name
  (flow_node) @value
  (#eq? @name "schema-version")
)
            """;

  String ruleCodeUpdate =
      """
            
            function visit(node, filename, code) {

                const n = node.captures["name"];
                const v = node.captures["value"];
                console.log(n);
                console.log(v);
                console.log(getCode(v.start, v.end, code));
                
                if(n && v && getCode(v.start, v.end, code) === "v3") {
                    const error = buildError(v.start.line, v.start.col, v.end.line, v.end.col,
                                             "invalid version", "CRITICAL", "BEST_PRACTICE");

                    const edit = buildEdit(v.start.line, v.start.col, v.end.line, v.end.col, "update", "v2");
                    const fix = buildFix("use v2", [edit]);
                    addError(error.addFix(fix));
                }
            }
            """;

  @Test
  @DisplayName("Replace function name foobar by baz")
  public void testBasicTreeSitterQuery() throws Exception {
    Response response =
        executeTestTsQuery(
            "bla.js", code, Language.YAML, ruleCodeUpdate, "ts-query-test", tsQuery, true);
    logger.info(response.toString());

    assertEquals(1, response.ruleResponses.size());
    assertEquals(
        fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
  }
}
