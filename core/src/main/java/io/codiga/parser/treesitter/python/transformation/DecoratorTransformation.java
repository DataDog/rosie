package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonDecorator;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToPythonArguments;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.DECORATOR;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChild;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class DecoratorTransformation {

    private static final Logger LOGGER = Logger.getLogger(DecoratorTransformation.class.getName());

    public static Optional<PythonDecorator> transformDecorator(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != DECORATOR) {
            return Optional.empty();
        }

        if (node.getChildCount() == 2) {
            var attribute = transformIdentifierToAstStringWithoutCheck(node.getChild(1), parsingContext);
            var arguments = getNodeChild(node.getChild(1), TreeSitterPythonTypes.ARGUMENT_LIST)
                .map(n -> transformArgumentListToPythonArguments(n, parsingContext));
            if (attribute.isPresent()) {
                return Optional.of(new PythonDecorator(attribute.get(), arguments.orElse(List.of()), parsingContext.getParserContextForNode(node)));
            }
        }


        return Optional.empty();
    }


}
