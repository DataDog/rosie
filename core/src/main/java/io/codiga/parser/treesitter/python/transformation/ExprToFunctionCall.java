package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.TreeSitterPythonTypes.ARGUMENT_LIST;
import static io.codiga.parser.treesitter.python.TreeSitterPythonTypes.CALL;
import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToFunctionCallArguments;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;

public class ExprToFunctionCall {

    private static final Logger LOGGER = Logger.getLogger(ExprToFunctionCall.class.getName());


    /**
     * Transform a function call
     *
     * @param node
     * @param parsingContext
     * @return
     */
    public static Optional<PythonFunctionCall> transformExprToFunctionCall(Node node, TreeSitterParsingContext parsingContext) {
        if (!node.getType().equalsIgnoreCase(CALL)) {
            return Optional.empty();
        }
        if (node.getChildCount() != 2) {
            return Optional.empty();
        }
        Node functionName = node.getChild(0);
        Node arguments = node.getChild(1);

        if (!arguments.getType().equalsIgnoreCase(ARGUMENT_LIST)) {
            return Optional.empty();
        }

        Optional<AstString> functionCallNameOptional = transformIdentifierToAstString(functionName, parsingContext);
        Optional<FunctionCallArguments> functionCallArgumentsOptional = transformArgumentListToFunctionCallArguments(arguments, parsingContext);


        LOGGER.info(functionName.getType());
        LOGGER.info(arguments.getType());


        if (functionCallNameOptional.isPresent() && functionCallArgumentsOptional.isPresent()) {
            ParserContextTreeSitter parserContext = ParserContextTreeSitter.builder()
                .node(node)
                .root(parsingContext.getRootNode())
                .code(parsingContext.getCode()).build();


            return Optional.of(new PythonFunctionCall(null, functionCallNameOptional.get(), functionCallArgumentsOptional.orElse(null), parserContext));
        }
        return Optional.empty();
    }


}
