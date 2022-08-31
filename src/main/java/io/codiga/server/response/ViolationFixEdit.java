package io.codiga.server.response;

import io.codiga.model.common.Position;

public class ViolationFixEdit {
    public Position start;
    public Position end;
    public String content;
    public String editType;


    public ViolationFixEdit(Position start, Position end, String editType, String content) {
        this.start = start;
        this.end = end;
        this.content = content;
        this.editType = editType;
    }
}
