package io.codiga.model.ast.common;

import ai.serenade.treesitter.Node;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.codiga.model.common.Position;
import java.util.List;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * Represents a TreeSitter AST element
 */
@Builder
@Jacksonized // allows Jackson to deserialize on the builder class
public class TreeSitterAstElement {
    public String astType;
    public io.codiga.model.common.Position start;
    public io.codiga.model.common.Position end;
    public String fieldName;
    public List<TreeSitterAstElement> children;
    @JsonIgnore public TreeSitterAstElement parent; // only used while traversing a tree; don't return in API requests

    public static TreeSitterAstElement create(Node node, String fieldName, List<TreeSitterAstElement> children, TreeSitterAstElement parent) {
        return TreeSitterAstElement
                .builder()
                .astType(node.getType())
                .start(new Position(node.getStartPosition().row + 1, node.getStartPosition().column + 1))
                .end(new Position(node.getEndPosition().row + 1, node.getEndPosition().column + 1))
                .fieldName(fieldName)
                .children(children)
                .parent(parent)
                .build();
    }
}
