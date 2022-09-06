package io.codiga.model.common;

public class Position {
    public int line;
    public int col;

    public Position(int line, int col) {
        this.line = line;
        this.col = col;
    }

    public String toString() {
        return String.format("Position [%s %s]", this.line, this.col);
    }
}
