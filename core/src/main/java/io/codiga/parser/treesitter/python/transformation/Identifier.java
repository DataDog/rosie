package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.parser.common.context.ParserContext;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class Identifier {

    private static final Logger LOGGER = Logger.getLogger(Identifier.class.getName());

    public static Optional<AstString> transformIdentifierToAstString(Node node, TreeSitterParsingContext parsingContext) {
        return transformIdentifierToAstString(node, parsingContext, true);
    }

    public static Optional<AstString> transformIdentifierToAstStringWithoutCheck(Node node, TreeSitterParsingContext parsingContext) {
        return transformIdentifierToAstString(node, parsingContext, false);
    }

    public static Optional<AstString> transformIdentifierToAstString(Node node, TreeSitterParsingContext parsingContext, boolean check) {
        if (check && getNodeType(node) != TreeSitterPythonTypes.IDENTIFIER) {
            return Optional.empty();
        }


        String value = parsingContext.getStringForNode(node);
        if (value != null) {
            ParserContext parserContext = parsingContext.getParserContextForNode(node);
            return Optional.of(new AstString(value, parserContext));
        }

        return Optional.empty();
    }


    public static Optional<FunctionCallArgument> transformNodeToFunctionArgument(Node node, TreeSitterParsingContext parsingContext) {

        Optional<AstElement> argumentValue = TreeSitterPythonParser.parse(node, parsingContext);
        if (argumentValue.isPresent()) {
            return Optional.of(new FunctionCallArgument(null, argumentValue.get(), parsingContext.getParserContextForNode(node)));
        }

        return Optional.empty();
    }

}
