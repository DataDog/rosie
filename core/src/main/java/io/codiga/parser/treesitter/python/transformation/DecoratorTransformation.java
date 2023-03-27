package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.python.PythonArgument;
import io.codiga.model.ast.python.PythonDecorator;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToPythonArguments;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.*;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChild;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class DecoratorTransformation {

    private static final Logger LOGGER = Logger.getLogger(DecoratorTransformation.class.getName());

    public static Optional<PythonDecorator> transformDecorator(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != DECORATOR) {
            return Optional.empty();
        }

        if (node.getChildCount() == 2) {
            var decoratorIdentifier = getNodeChild(node, IDENTIFIER);
            var decoratorCallFunction = getNodeChild(node, CALL);

            Optional<AstString> attribute = Optional.empty();
            List<PythonArgument> arguments = List.of();

            // it's the form like @mydecorator
            if (decoratorIdentifier.isPresent()) {
                attribute = transformIdentifierToAstString(decoratorIdentifier.get(), parsingContext);
            }

            // it's the form @mydecorator.foo(bar=42)
            if (decoratorCallFunction.isPresent()) {
                var function = Optional.ofNullable(decoratorCallFunction.get().getChildByFieldName("function"));
                if (function.isPresent()) {
                    attribute = transformIdentifierToAstStringWithoutCheck(function.get(), parsingContext);
                    arguments = getNodeChild(decoratorCallFunction.get(), TreeSitterPythonTypes.ARGUMENT_LIST)
                        .map(n -> transformArgumentListToPythonArguments(n, parsingContext))
                        .orElse(List.of());
                }

            }

            if (attribute.isPresent()) {
                return Optional.of(new PythonDecorator(attribute.get(), arguments, parsingContext.getParserContextForNode(node)));
            }
        }


        return Optional.empty();
    }


}
