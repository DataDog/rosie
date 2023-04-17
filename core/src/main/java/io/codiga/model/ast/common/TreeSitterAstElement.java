package io.codiga.model.ast.common;

import ai.serenade.treesitter.Position;

import java.util.List;

public class TreeSitterAstElement {
    public String astType;
    public Position start;
    public Position end;
    public String fieldName;
    public List<TreeSitterAstElement> children;

    public TreeSitterAstElement(String astType, Position start, Position end, String fieldName, List<TreeSitterAstElement> children) {
        this.astType = astType;
        this.start = start;
        this.end = end;
        this.fieldName = fieldName;
        this.children = children;
    }
}
