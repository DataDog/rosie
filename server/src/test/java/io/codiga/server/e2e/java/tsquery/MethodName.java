package io.codiga.server.e2e.java.tsquery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodName extends E2EBase {

  private static final Logger logger = LoggerFactory.getLogger(MethodName.class);

  String code = """
public class Foo {

	public static void bar() {
    	System.out.println("foobar");
    }

}""";

  String fixedCode = """
public class Foo {

	public static void baz() {
    	System.out.println("foobar");
    }

}""";

  String tsQuery =
      """
(method_declaration
   name: (identifier) @methodname
)
            """;

  String ruleCodeUpdate =
      """
            
            function visit(node, filename, code) {

                const n = node.captures["methodname"];
                console.log(getCode(n.start, n.end, code));
                
                if(n && getCode(n.start, n.end, code) === "bar") {
                    const error = buildError(n.start.line, n.start.col, n.end.line, n.end.col,
                                             "invalid name", "CRITICAL", "BEST_PRACTICE");

                    const edit = buildEdit(n.start.line, n.start.col, n.end.line, n.end.col, "update", "baz");
                    const fix = buildFix("use baz", [edit]);
                    addError(error.addFix(fix));
                }
            }
            """;

  @Test
  @DisplayName("Replace function name bar by baz")
  public void testBasicTreeSitterQuery() throws Exception {
    Response response =
        executeTestTsQuery(
            "bla.java", code, Language.JAVA, ruleCodeUpdate, "ts-query-test", tsQuery, true);
    logger.info(response.toString());

    assertEquals(1, response.ruleResponses.size());
    assertEquals(
        fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
  }
}
