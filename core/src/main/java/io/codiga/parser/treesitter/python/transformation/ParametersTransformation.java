package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.FunctionDefinitionParameter;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.transformation.TypeTransformation.transformType;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.*;

public class ParametersTransformation {

    private static final Logger LOGGER = Logger.getLogger(ParametersTransformation.class.getName());

    private static Optional<FunctionDefinitionParameter> transformParameter(Node node, TreeSitterParsingContext parsingContext) {

        switch (getNodeType(node)) {
            case TYPED_PARAMETER: {
                var identifierOptional = getNodeChild(node, TreeSitterPythonTypes.IDENTIFIER)
                    .flatMap(i -> transformIdentifierToAstString(i, parsingContext));
                var typeOptional = getNodeChild(node, TreeSitterPythonTypes.TYPE)
                    .flatMap(i -> transformType(i, parsingContext));

                if (identifierOptional.isPresent() && typeOptional.isPresent()) {
                    return Optional.of(new FunctionDefinitionParameter(
                        identifierOptional.get(),
                        typeOptional.get(),
                        null,
                        parsingContext.getParserContextForNode(node)));
                }
                break;
            }
            case TYPED_DEFAULT_PARAMETER: {
                if (node.getChildCount() < 5) {
                    return Optional.empty();
                }
                var identifierOptional = transformIdentifierToAstString(node.getChild(0), parsingContext);
                var typeOptional = transformType(node.getChild(2), parsingContext);
                var defaultValue = transformIdentifierToAstStringWithoutCheck(node.getChild(4), parsingContext);

                if (identifierOptional.isPresent() && typeOptional.isPresent()) {
                    return Optional.of(new FunctionDefinitionParameter(
                        identifierOptional.get(),
                        typeOptional.get(),
                        defaultValue.orElse(null),
                        parsingContext.getParserContextForNode(node)));
                }
                break;
            }
            case IDENTIFIER: {
                var identifierOptional = transformIdentifierToAstString(node, parsingContext);
                return Optional.of(new FunctionDefinitionParameter(
                    identifierOptional.get(),
                    null,
                    null,
                    parsingContext.getParserContextForNode(node)));
            }
            default:
                break;
        }


        return Optional.empty();
    }

    public static Optional<FunctionDefinitionParameters> transformParameters(Node node, TreeSitterParsingContext parsingContext) {
        List<FunctionDefinitionParameter> parameters =
            getNodeChildren(node)
                .stream()
                .map(n -> transformParameter(n, parsingContext))
                .filter(n -> n.isPresent())
                .map(n -> n.get())
                .collect(Collectors.toList());

        return Optional.of(new FunctionDefinitionParameters(parameters, parsingContext.getParserContextForNode(node)));

    }


}
