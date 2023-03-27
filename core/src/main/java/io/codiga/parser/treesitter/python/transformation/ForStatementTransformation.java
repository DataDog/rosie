package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.python.PythonForStatement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.FOR_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ForStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(ForStatementTransformation.class.getName());


    public static Optional<PythonForStatement> transformForStatement(Node node, TreeSitterParsingContext parsingContext) {
        // Basic checks - it's an import and has enough nodes.
        if (node == null || getNodeType(node) != FOR_STATEMENT) {
            return Optional.empty();
        }

        Optional<AstElement> left = Optional.ofNullable(node.getChildByFieldName("left"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));
        Optional<AstElement> right = Optional.ofNullable(node.getChildByFieldName("right"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));
        Optional<AstElement> body = Optional.ofNullable(node.getChildByFieldName("body"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));

        return Optional.of(
            new PythonForStatement(
                left.orElse(null),
                right.orElse(null),
                body.orElse(null),
                parsingContext.getParserContextForNode(node)
            ));
    }


}
