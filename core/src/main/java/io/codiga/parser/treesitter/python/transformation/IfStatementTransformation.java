package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonIfStatement;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.IF_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class IfStatementTransformation {

    private static final Logger LOGGER = Logger.getLogger(IfStatementTransformation.class.getName());


    public static Optional<PythonIfStatement> transformIfStatement(Node node, TreeSitterParsingContext parsingContext) {
        // Basic checks - it's an import and has enough nodes.
        if (node == null || getNodeType(node) != IF_STATEMENT) {
            return Optional.empty();
        }


        return Optional.empty();
    }


}
