package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.python.PythonElseStatement;
import io.codiga.model.ast.python.PythonIfStatement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.BlockTransformation.transformBlock;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.*;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChild;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class IfStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(IfStatementTransformation.class.getName());


    public static Optional<PythonIfStatement> transformIfStatement(Node node, TreeSitterParsingContext parsingContext) {
        // Basic checks - it's an import and has enough nodes.
        if (node == null || getNodeType(node) != IF_STATEMENT) {
            return Optional.empty();
        }
        Optional<AstElement> condition = Optional.ofNullable(node.getChildByFieldName("condition"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));
        Optional<AstElement> consequence = Optional.ofNullable(node.getChildByFieldName("consequence"))
            .flatMap(n -> transformBlock(n, parsingContext));
        Optional<PythonElseStatement> elseStatements = getNodeChild(node, ELSE_CLAUSE)
            .flatMap(n -> getNodeChild(n, BLOCK)
                .flatMap(v -> transformBlock(v, parsingContext))
                .map(w -> new PythonElseStatement(w, parsingContext.getParserContextForNode(n)))
            );

        return Optional.of(
            new PythonIfStatement(
                condition.orElse(null),
                consequence.orElse(null),
                List.of(),
                elseStatements.orElse(null),
                parsingContext.getParserContextForNode(node)
            ));
    }


}
