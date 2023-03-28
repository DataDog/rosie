package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionCallArgument;
import io.codiga.model.ast.python.PythonArgument;
import io.codiga.parser.common.context.ParserContext;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class KeywordArgument {

    private static final Logger LOGGER = Logger.getLogger(KeywordArgument.class.getName());

    public static Optional<FunctionCallArgument> keywordArgumentToFunctionCallArgument(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.KEYWORD_ARGUMENT || node.getChildCount() != 3) {
            return Optional.empty();
        }

        Optional<AstString> nameOptional = Optional.ofNullable(node.getChildByFieldName("name"))
            .flatMap(n -> transformIdentifierToAstString(n, parsingContext));

        Optional<AstElement> valueOptional = Optional.ofNullable(node.getChildByFieldName("value"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));


        if (nameOptional.isPresent() && valueOptional.isPresent()) {
            ParserContext parserContext = parsingContext.getParserContextForNode(node);

            return Optional.of(new FunctionCallArgument(nameOptional.orElse(null), valueOptional.orElse(null), parserContext));
        }
        return Optional.empty();
    }

    public static Optional<PythonArgument> keywordArgumentToPythonArgument(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.KEYWORD_ARGUMENT || node.getChildCount() != 3) {
            return Optional.empty();
        }

        Node name = node.getChild(0);
        Node value = node.getChild(2);

        Optional<AstString> nameOptional = transformIdentifierToAstString(name, parsingContext);
        Optional<AstString> valueOptional = transformIdentifierToAstStringWithoutCheck(value, parsingContext);

        if (nameOptional.isPresent() && valueOptional.isPresent()) {
            ParserContext parserContext = parsingContext.getParserContextForNode(node);

            return Optional.of(new PythonArgument(nameOptional.orElse(null), valueOptional.orElse(null), parserContext));
        }
        return Optional.empty();
    }
}
