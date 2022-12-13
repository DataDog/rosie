package io.codiga.server.e2e.javascript.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.codiga.constants.Languages.ENTITY_CHECKED_IMPORT;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ImportTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(ImportTest.class);


    String code = """
        import { export1 } from "module-name";
                
        var bla = 1;""";

    String fixedCode = """
        import { export2 } from "module-name";
                
        var bla = 1;""";
    String ruleCode = """
        function visit(node) {
            if(node.importedNames && node.importedNames.length > 0){
                node.importedNames.forEach( v => {
                    if (v.name.value === "export1"){
                        const edit = buildEditUpdate(v.start.line, v.start.col, v.end.line, v.end.col, "export2");
                        const fix = buildFix("use export2", [edit]);
                
                        const error = buildError(v.start.line, v.start.col, v.end.line, v.end.col, "do not use export1", "CRITICAL", "SAFETY");
                        addError(error.addFix(fix));
                    }
                    
                });
            }
        }
        """;

    @Test
    @DisplayName("do not import export1, always import export2")
    public void testImportRule() throws Exception {
        Response response = executeTest("bla.js", code, Language.JAVASCRIPT, ruleCode, "import-rule", RULE_TYPE_AST, ENTITY_CHECKED_IMPORT, true);
        logger.info(response.toString());
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(10, response.ruleResponses.get(0).violations.get(0).start.col);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(17, response.ruleResponses.get(0).violations.get(0).end.col);

        assertEquals(fixedCode, applyFix(code, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}