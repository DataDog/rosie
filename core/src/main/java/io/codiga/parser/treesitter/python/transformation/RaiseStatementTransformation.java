package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.python.RaiseStatement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.RAISE_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class RaiseStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(RaiseStatementTransformation.class.getName());

    public static Optional<RaiseStatement> transformRaiseStatement(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != RAISE_STATEMENT) {
            return Optional.empty();
        }

        Optional<AstElement> causeOptional = Optional.ofNullable(node.getChildByFieldName("cause"))
            .flatMap(n -> TreeSitterPythonParser.parse(n, parsingContext));
        Optional<AstElement> exception = Optional.empty();

        if (node.getChildCount() >= 2) {
            exception = TreeSitterPythonParser.parse(node.getChild(1), parsingContext);
        }

        return Optional.of(new RaiseStatement(exception.orElse(null), causeOptional.orElse(null), parsingContext.getParserContextForNode(node)));
    }
}
