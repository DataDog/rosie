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
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChild;

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

        var nodeNameOptional = getNodeChild(node, TreeSitterPythonTypes.IDENTIFIER)
            .flatMap(n -> transformIdentifierToAstString(n, parsingContext));

        var blockOptional = getNodeChild(node, TreeSitterPythonTypes.BLOCK)
            .flatMap(n -> transformBlock(n, parsingContext));

        return Optional.of(
            new PythonFunctionDefinition(
                false, // isAsync
                List.of(), // decorators
                nodeNameOptional.orElse(null), // name
                null, // function parameters (FunctionDefinitionParameters)
                null, // return type
                blockOptional.orElse(null), //content
                parsingContext.getParserContextForNode(node)
            ));

    }


}
