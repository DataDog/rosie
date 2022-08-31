package io.codiga.model.ast;


import io.codiga.model.common.Position;

public class AstElement {
    public Position start;
    public Position end;

    public AstElement(Position start, Position end) {
        this.start = start;
        this.end = end;
    }
}
