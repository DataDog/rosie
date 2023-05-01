package io.codiga.model.error;

import io.codiga.model.common.Position;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Edit {
    public Position start;
    public Position end;
    public EditType editType;
    public String content;

    public Edit(Position start, Position end, EditType editType, String content) {
        this.start = start;
        this.end = end;
        this.editType = editType;
        this.content = content;
    }
}
