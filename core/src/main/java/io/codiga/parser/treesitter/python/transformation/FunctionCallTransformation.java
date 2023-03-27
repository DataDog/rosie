package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCallArguments;
import io.codiga.model.ast.python.PythonFunctionCall;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.ArgumentList.transformArgumentListToFunctionCallArguments;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.ATTRIBUTE;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.IDENTIFIER;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class FunctionCallTransformation {

    private static final Logger LOGGER = Logger.getLogger(FunctionCallTransformation.class.getName());


    /**
     * Transform a function call
     *
     * @param node
     * @param parsingContext
     * @return
     */
    public static Optional<PythonFunctionCall> transformExprToFunctionCall(Node node, TreeSitterParsingContext parsingContext) {
        if (!node.getType().equalsIgnoreCase(TreeSitterPythonTypes.CALL.label)) {
            return Optional.empty();
        }
        if (node.getChildCount() != 2) {
            return Optional.empty();
        }
        Node functionName = node.getChild(0);
        Node arguments = node.getChildByFieldName("arguments");

        if (getNodeType(arguments) != TreeSitterPythonTypes.ARGUMENT_LIST) {
            return Optional.empty();
        }

        Optional<AstString> functionCallNameOptional = Optional.empty();
        Optional<AstString> moduleOrObjectOptional = Optional.empty();
        if (getNodeType(functionName) == IDENTIFIER) {
            functionCallNameOptional = transformIdentifierToAstString(functionName, parsingContext);
        }
        if (getNodeType(functionName) == ATTRIBUTE && functionName.getChildCount() > 2) {
            moduleOrObjectOptional = transformIdentifierToAstStringWithoutCheck(functionName.getChild(0), parsingContext);
            functionCallNameOptional = transformIdentifierToAstString(functionName.getChild(2), parsingContext);
        }

        Optional<FunctionCallArguments> functionCallArgumentsOptional = transformArgumentListToFunctionCallArguments(arguments, parsingContext);


        if (functionCallNameOptional.isPresent() && functionCallArgumentsOptional.isPresent()) {
            ParserContextTreeSitter parserContext = parsingContext.getParserContextForNode(node);


            return Optional.of(new PythonFunctionCall(moduleOrObjectOptional.orElse(null), functionCallNameOptional.get(), functionCallArgumentsOptional.orElse(null), parserContext));
        }
        return Optional.empty();
    }


}
