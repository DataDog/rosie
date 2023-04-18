package io.codiga.model.ast.common;

import ai.serenade.treesitter.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Represents a TreeSitter AST element
 */
public class TreeSitterAstElement {
    public String astType;
    public io.codiga.model.common.Position start;
    public io.codiga.model.common.Position end;
    public String fieldName;
    public List<TreeSitterAstElement> children;
    @JsonIgnore public TreeSitterAstElement parent; // only used while traversing a tree; don't return in API requests

    public TreeSitterAstElement() {
        // leave empty for tests
    }

    public TreeSitterAstElement(String astType, Position start, Position end, String fieldName, List<TreeSitterAstElement> children, TreeSitterAstElement parent) {
        this.astType = astType;
        this.start = new io.codiga.model.common.Position(start.row, start.column);
        this.end = new io.codiga.model.common.Position(end.row, end.column);
        this.fieldName = fieldName;
        this.children = children;
        this.parent = parent;
    }
}
