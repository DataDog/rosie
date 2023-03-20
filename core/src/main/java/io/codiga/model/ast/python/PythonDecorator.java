package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PythonDecorator extends AstElement {

    public PythonArgument[] arguments;
    public PythonString name;

    public PythonDecorator(PythonString name, List<PythonArgument> argumentList, PythonParser.DecoratorContext context, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_DECORATOR, context, root);
        this.name = name;
        this.arguments = argumentList.stream().toArray(PythonArgument[]::new);

    }

    public static Optional<PythonDecorator> fromArgumentContext(PythonParser.DecoratorContext context, ParserRuleContext root) {
        if (context == null) {
            return Optional.empty();
        }
        return Optional.of(new PythonDecorator(PythonString.fromDottedName(context.dotted_name(), root),
            context.arglist() == null ? List.of() : context.arglist().argument().stream().map(a -> PythonArgument.fromArgumentContext(a, root).orElse(null)).collect(Collectors.toList()),
            context,
            root
        ));
    }
}
