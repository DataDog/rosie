package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NoFlaskAppInDebugTest extends E2EBase {

    String pythonCodeWithError = """
        from flask import Flask
                
        app = Flask(__name__)
                
        @app.route('/')
                
        def index():
            return 'Flask webapp'
                
        if __name__ == "__main__":
            app.run(debug = True)""";

    String pythonCodeFixed = """
        from flask import Flask
                
        app = Flask(__name__)
                
        @app.route('/')
                
        def index():
            return 'Flask webapp'
                
        if __name__ == "__main__":
            app.run()""";

    String ruleCode = """
        function printArgument(a) {
            if(a.name && a.value){
                return `${a.name.str} = ${a.value.str}`;
            }
            return `${a.value.str}`;
        }
                
        function visit(node) {
        console.log("here2");
            const useFlask = node.context.imports.filter(i => {
                const useFlaskAsImport = i.astType === "importpackage" && i.name.str === "flask";
                 const useFlaskAsFrom = i.astType === "fromstatement" && i.pkg.str === "flask";
                 return useFlaskAsImport || useFlaskAsFrom;
            }).length > 0;
            const useDebug = node.arguments.values.filter(a => a.name && a.name.str === "debug" && a.value && a.value.str === "True").length > 0;


            if (useDebug && useFlask) {
                const lastArgument = node.arguments.values[node.arguments.values.length - 1];
                const lastArgumentPosition = lastArgument.value.end;
                
                console.log("here");
                
                const argumentsWithoutDebug = node.arguments.values.filter(a => (a.name && a.name.str !== "debug") && (a.value && a.value.str !== "True"));
                const newArguments = argumentsWithoutDebug.map(a => printArgument(a)).join(", ");
                const newFunctionCall = `app.run(${argumentsWithoutDebug})`;
                const editRemoveDebugFlag = buildEditUpdate(node.arguments.start.line, node.arguments.start.col, lastArgumentPosition.line, lastArgumentPosition.col, newArguments)
                const fix = buildFix("remove debug flag", [editRemoveDebugFlag]);

                const error = buildError(node.start.line, node.start.col, node.end.line, node.end.col, "do not use debug = True", "CRITICAL", "SAFETY");

                addError(error.addFix(fix));
            }
            
        }
        """;

    @Test
    @DisplayName("Do not use debug=True in flask")
    public void testPythonNoDebugTrueInFlask() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "flask-no-debug", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, true);
        logger.info(String.format("response: %s", response));


        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(11, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals("do not use debug = True", response.ruleResponses.get(0).violations.get(0).message);
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.size());
        assertEquals(1, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.size());


        assertEquals("remove debug flag", response.ruleResponses.get(0).violations.get(0).fixes.get(0).description);
        assertEquals("update", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).editType);
        assertEquals("", response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).content);
//        assertEquals(11, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.line);
//        assertEquals(13, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).start.col);
//        assertEquals(11, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.line);
//        assertEquals(25, response.ruleResponses.get(0).violations.get(0).fixes.get(0).edits.get(0).end.col);
//

        assertEquals(pythonCodeFixed, applyFix(pythonCodeWithError, response.ruleResponses.get(0).violations.get(0).fixes.get(0)));
    }


}
