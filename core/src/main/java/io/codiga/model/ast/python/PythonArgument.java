package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

public class PythonArgument extends AstElement {

    public PythonString name;
    public PythonString value;

    public PythonArgument(PythonString name, PythonString value, PythonParser.ArgumentContext context, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_ARGUMENT, context, root);
        this.name = name;
        this.value = value;
    }

    public static Optional<PythonArgument> fromArgumentContext(PythonParser.ArgumentContext context, ParserRuleContext root) {
        if (context == null) {
            return Optional.empty();
        }
        if (context.ASSIGN() == null) {
            if (context.test().size() != 1) {
                return Optional.empty();
            }

            PythonParser.TestContext nameContext = context.test().get(0);

            if (nameContext == null || nameContext.logical_test() == null || nameContext.logical_test().size() == 0) {
                return Optional.empty();
            }

            PythonParser.Logical_testContext logical_testContext = nameContext.logical_test().get(0);

            if (logical_testContext.comparison() == null || logical_testContext.comparison().expr() == null ||
                logical_testContext.comparison().expr().atom() == null || logical_testContext.comparison().expr().atom().name() == null) {
                return Optional.empty();
            }


            PythonParser.NameContext nameValue = nameContext.logical_test().get(0).comparison().expr().atom().name();

            if (nameValue == null) {
                return Optional.empty();
            }

            PythonString value = new PythonString(nameValue.getText(), nameValue, root);
            return Optional.of(new PythonArgument(null, value, context, root));
        } else {
            if (context.test().size() != 2) {
                return Optional.empty();
            }

            PythonParser.TestContext nameContext = context.test().get(0);
            if (nameContext.logical_test() == null || nameContext.logical_test().size() == 0) {
                return Optional.empty();
            }
            if (nameContext.logical_test().get(0).comparison() == null || nameContext.logical_test().get(0).comparison().expr() == null ||
                nameContext.logical_test().get(0).comparison().expr().atom() == null || nameContext.logical_test().get(0).comparison().expr().atom().name() == null) {
                return Optional.empty();
            }
            PythonParser.NameContext nameValue = nameContext.logical_test().get(0).comparison().expr().atom().name();
            PythonString name = new PythonString(nameValue.getText(), nameValue, root);

            PythonParser.TestContext valueContext = context.test().get(1);
            if (valueContext.logical_test() == null || valueContext.logical_test().size() == 0) {
                return Optional.empty();
            }
            if (valueContext.logical_test().get(0).comparison() == null || valueContext.logical_test().get(0).comparison().expr() == null ||
                valueContext.logical_test().get(0).comparison().expr().atom() == null || valueContext.logical_test().get(0).comparison().expr().atom().name() == null) {
                return Optional.empty();
            }
            PythonParser.NameContext valueValue = valueContext.logical_test().get(0).comparison().expr().atom().name();
            PythonString value = new PythonString(valueValue.getText(), valueValue, root);
            return Optional.of(new PythonArgument(name, value, context, root));
        }
    }
}
