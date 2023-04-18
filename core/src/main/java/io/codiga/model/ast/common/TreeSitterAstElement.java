package io.codiga.model.ast.common;

import ai.serenade.treesitter.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class TreeSitterAstElement {
    public String astType;
    public io.codiga.model.common.Position start;
    public io.codiga.model.common.Position end;
    public String fieldName;
    public List<TreeSitterAstElement> children;
    @JsonIgnore public TreeSitterAstElement parent;

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
