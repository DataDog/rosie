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


            PythonParser.NameContext nameValue = context.test().get(0).logical_test().get(0).comparison().expr().atom().name();

            if (nameValue == null) {
                return Optional.empty();
            }

            PythonString value = new PythonString(nameValue.getText(), nameValue, root);
            return Optional.of(new PythonArgument(null, value, context, root));
        } else {
            if (context.test().size() != 2) {
                return Optional.empty();
            }

            PythonParser.NameContext nameValue = context.test().get(0).logical_test().get(0).comparison().expr().atom().name();
            PythonString name = new PythonString(nameValue.getText(), nameValue, root);

            PythonParser.NameContext valueValue = context.test().get(1).logical_test().get(0).comparison().expr().atom().name();
            PythonString value = new PythonString(valueValue.getText(), valueValue, root);
            return Optional.of(new PythonArgument(name, value, context, root));
        }
    }
}
