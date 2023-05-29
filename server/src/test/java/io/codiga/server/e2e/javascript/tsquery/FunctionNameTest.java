package io.codiga.server.e2e.javascript.tsquery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionNameTest extends E2EBase {

  private static final Logger logger = LoggerFactory.getLogger(FunctionNameTest.class);

  String code = """
function foobar() {
	console.log("efweF");
}""";

  String fixedCode = """
function baz() {
	console.log("efweF");
}""";

  String tsQuery =
      """
(function_declaration
  name: (identifier) @functionname
  (#eq? @functionname "foobar")
)
            """;

  String ruleCodeUpdate =
      """
            function getCode(start, end, code) {
              const lines = code.split("\\n");
              const startLine = start.line - 1;
              const startCol = start.col - 1;
              const endLine = end.line - 1;
              const endCol = end.col - 1;
              
              var startChar = 0;
              for (var i = 0 ; i < startLine ; i++) {
                startChar = startChar + len(lines[i]) + 1;
              }
              startChar = startChar + startCol;
              
              var endChar = 0;
              for (var i = 0 ; i < startLine ; i++) {
                endChar = endChar + len(lines[i]) + 1;
              }
              endChar = endChar + endCol;
              
              return code.substring(startChar, endChar);
              
            }
            
            function visit(node, filename, code) {

                const functionName = node.captures["functionname"];
                
                if(functionName && getCode(functionName.start, functionName.end, code) === "foobar") {
                    const error = buildError(functionName.start.line, functionName.start.col, functionName.end.line, functionName.end.col,
                                             "invalid name", "CRITICAL", "security");

                    const edit = buildEdit(functionName.start.line, functionName.start.col, functionName.end.line, functionName.end.col, "update", "baz");
                    const fix = buildFix("use bar", [edit]);
                    addError(error.addFix(fix));
                }
            }
            """;

  @Test
  @DisplayName("Replace function name foobar by baz")
  public void testBasicTreeSitterQuery() throws Exception {
    Response response =
        executeTestTsQuery(
            "bla.js", code, Language.JAVASCRIPT, ruleCodeUpdate, "ts-query-test", tsQuery, true);
    logger.info(response.toString());

    assertEquals(1, response.ruleResponses.size());
    assertEquals(
        fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
  }
}
