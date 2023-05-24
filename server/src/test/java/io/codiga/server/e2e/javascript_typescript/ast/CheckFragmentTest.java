package io.codiga.server.e2e.javascript_typescript.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.model.EntityChecked;
import io.codiga.model.RuleType;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckFragmentTest extends E2EBase {


    private static final Logger logger = LoggerFactory.getLogger(CheckFragmentTest.class);

    String codeToCheck = """
        const Index = () => {
          return (
            <>
              <b>World</b>
              {true &&\s
                <div>
                  <Box w="full" as={CodeMirror} value={decodeBase64(rule.content)} />
                </div>
              }
            </>
          );
        };

        const Index = () => {
          return (
            <>
              <b>World</b>
              {true && (
                <div>
                  <Box w="full" as={CodeMirror} value={decodeBase64(rule.content)} />
                </div>
              )}
            </>
          );
        };
                            """;


    String ruleCode = """
        function checkIfFragment(tag) {
            if (tag === null) return true;
            if (tag.value === "Fragment") return true;
            if (tag.value === "React.Fragment") return true;
            return false;
        }

        function visit(node, filename, code) {
            if (!node) return;

            // we won't check tags that aren't fragments
            const isFragment = checkIfFragment(node.tag);

            // if it's not a fragment exit
            if (!isFragment) return;
        console.log("we got a fragment");
            // if it's a fragment but has a key prop, skip it
            if (node.tag && node.attributes.some(attribute => attribute.name.value === "key")) return;
        console.log(node.content.length);
            // if there's more than one child, then the fragment is needed
            if (node.content.length > 1) return;
        node.content.forEach(c => {
        	console.log(c.astType);
        });
            const error = buildError(
                node.start.line,
                node.start.col,
                node.end.line,
                node.end.col,
                `useless React Fragment`,
                "INFORMATIONAL",
                "BEST_PRACTICE"
            );

            addError(error);
        }
                        """;

  @Test
  @DisplayName("ensure that we correctly implement the no fragment rule")
  public void testNoSingleFragment() throws Exception {
    JAVASCRIPT_TYPESCRIPT.forEach(
        l -> {
          logger.info("Running test with language: " + l);
          Response responseOneError =
              executeTest(
                  "bla.js",
                  codeToCheck,
                  l,
                  ruleCode,
                  "no-child-as-prop",
                  RuleType.AST_CHECK,
                  EntityChecked.HTML_ELEMENT,
                  null,
                  true);
          logger.info(responseOneError.toString());
          assertEquals(1, responseOneError.ruleResponses.size());
          assertEquals(0, responseOneError.ruleResponses.get(0).violations.size());
        });
  }
}
