package io.codiga.model.common;

import org.graalvm.polyglot.HostAccess;

public class Position {

    @HostAccess.Export
    public int line;

    @HostAccess.Export
    public int col;

    public Position() {
        this.line = 0;
        this.col = 0;
    }

    public Position(int line, int col) {
        this.line = line;
        this.col = col;
    }

    public String toString() {
        return String.format("Position [%s %s]", this.line, this.col);
    }


    public int getLine() {
        return this.line;
    }

    public void setLine(int l) {
        this.line = l;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int c) {
        this.col = c;
    }
}
