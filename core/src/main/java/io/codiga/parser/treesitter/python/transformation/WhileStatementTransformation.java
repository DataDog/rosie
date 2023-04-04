package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.WhileStatement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.WHILE_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

/**
 * Transform Python while_statement from Tree-Sitter into a generic WhileStatement node
 */
public class WhileStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(WhileStatementTransformation.class.getName());


    public static Optional<WhileStatement> transformWhileStatement(Node node, TreeSitterParsingContext parsingContext) {
        // Basic checks - its a while statement and has more than one node
        if (node == null || getNodeType(node) != WHILE_STATEMENT || node.getChildCount() == 0) {
            return Optional.empty();
        }

        Optional<AstElement> conditionOptional = Optional.ofNullable(node.getChildByFieldName("condition"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));
        Optional<AstElement> bodyOptional = Optional.ofNullable(node.getChildByFieldName("body"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));

        return Optional.of(
            new WhileStatement(
                conditionOptional.orElse(null),
                bodyOptional.orElse(null),
                parsingContext.getParserContextForNode(node)
            ));
    }


}
