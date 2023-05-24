package io.codiga.server.response;

import io.codiga.model.common.Position;
import io.codiga.model.error.EditType;

public class ViolationFixEdit {
    public Position start;
    public Position end;
    public String content;
    public EditType editType;

    public ViolationFixEdit() { }

    public ViolationFixEdit(Position start, Position end, EditType editType, String content) {
        this.start = start;
        this.end = end;
        this.content = content;
        this.editType = editType;
    }

    @Override
    public String toString() {
        return "ViolationFixEdit{" +
            "start=" + start +
            ", end=" + end +
            ", content='" + content + '\'' +
            ", editType='" + editType + '\'' +
            '}';
    }
}
