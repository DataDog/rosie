package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isArrayOrDictReference;
import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isFunctionCall;
import static io.codiga.analyzer.ast.languages.python.transformations.AtomTransformation.transformAtomToPythonString;
import static io.codiga.analyzer.ast.languages.python.transformations.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.analyzer.ast.languages.python.transformations.ExprToVariableIndex.transformExprToVariableIndex;

public class PythonTestTransformation {

    private static final Logger logger = LoggerFactory.getLogger(PythonTestTransformation.class);


    public static Optional<? extends AstElement> transformTestToAstElement(PythonParser.TestContext test, PythonParser.RootContext root) {
        if (test == null) {
            return Optional.empty();
        }
        if (test.logical_test() == null || test.logical_test().size() != 1) {
            return Optional.empty();
        }
        PythonParser.Logical_testContext logical_testContext = test.logical_test().get(0);
        if (logical_testContext.comparison() == null) {
            return Optional.empty();
        }
        if (logical_testContext.comparison().expr() == null) {
            return Optional.empty();
        }
        if (isFunctionCall(logical_testContext.comparison().expr())) {
            return transformExprToFunctionCall(logical_testContext.comparison().expr(), root);
        }

        if (isArrayOrDictReference(logical_testContext.comparison().expr())) {
            return transformExprToVariableIndex(logical_testContext.comparison().expr(), root);
        }
        if (logical_testContext.comparison().expr().atom() != null) {
            return transformAtomToPythonString(logical_testContext.comparison().expr().atom(), root);
        }

        return Optional.empty();
    }

}
