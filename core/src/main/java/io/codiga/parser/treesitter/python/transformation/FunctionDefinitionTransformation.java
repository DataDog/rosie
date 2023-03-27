package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonFunctionDefinition;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.BlockTransformation.transformBlock;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.ParametersTransformation.transformParameters;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChild;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;

public class FunctionDefinitionTransformation {

    private static final Logger LOGGER = Logger.getLogger(FunctionDefinitionTransformation.class.getName());


    /**
     * Transform a function call
     *
     * @param node
     * @param parsingContext
     * @return
     */
    public static Optional<PythonFunctionDefinition> transformFunctionDefinition(Node node, TreeSitterParsingContext parsingContext) {
        var isAsync = getNodeChildren(node).stream().filter(n -> n.getType().equalsIgnoreCase("async")).count() > 0;
        var nodeNameOptional = getNodeChild(node, TreeSitterPythonTypes.IDENTIFIER)
            .flatMap(n -> transformIdentifierToAstString(n, parsingContext));

        var blockOptional = getNodeChild(node, TreeSitterPythonTypes.BLOCK)
            .flatMap(n -> transformBlock(n, parsingContext));

        var parametersOptional = getNodeChild(node, TreeSitterPythonTypes.PARAMETERS)
            .flatMap(n -> transformParameters(n, parsingContext));

        return Optional.of(
            new PythonFunctionDefinition(
                isAsync, // isAsync
                List.of(), // decorators
                nodeNameOptional.orElse(null), // name
                parametersOptional.orElse(null), // function parameters (FunctionDefinitionParameters)
                null, // return type
                blockOptional.orElse(null), //content
                parsingContext.getParserContextForNode(node)
            ));

    }


}
