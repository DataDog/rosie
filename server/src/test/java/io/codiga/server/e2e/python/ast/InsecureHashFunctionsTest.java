package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class InsecureHashFunctionsTest extends E2EBase {

    String pythonCodeWithError = """
        import hashlib
                
        hashlib.new('md5')
        """;


    String ruleCode = """
        function checkAlgorithm(node, hashMethod) {
            if (!(node.functionName.value === "new" && node.moduleOrObject.value === "hashlib")) {
                return;
            }

            const useOutdatedHashMethod = node.arguments.values.filter(a => a.value && a.value.str.toLowerCase() == `'${hashMethod}'`).length > 0;
            
            const allPackages = node.context.imports.filter(i => i.packages).flatMap(i => i.packages.map(p => p.name.str));
            const useHashlib = allPackages.filter(i => i === "hashlib").length > 0;

            if(useOutdatedHashMethod && useHashlib){
                const error = buildError(node.start.line, node.start.col, node.end.line, node.end.col, `Use of insecure hashing method ${hashMethod}`, "CRITICAL", "SECURITY");
                addError(error);
            } 
        }
                
        function visit(node) {
            const methods = ["md4", "md5", "sha1"];
            methods.forEach(method => {
                checkAlgorithm(node, method);
            });
        }
        """;

    @Test
    @DisplayName("Detect insecure protocol for hashing")
    public void testInsecureProtocol() throws Exception {
        Response response = executeTest("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "insecure-hashing-protocol", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, true);
        logger.info("response:" + response);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(3, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.col);

        assertEquals(3, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(19, response.ruleResponses.get(0).violations.get(0).end.col);
    }
}
