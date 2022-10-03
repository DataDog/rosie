package io.codiga.model.pattern;

import io.codiga.model.common.Position;

public class PatternVariableValue {
    public Position start;
    public Position end;
    public int startIndex;
    public int endIndex;

    public String value;

    public PatternVariableValue(String value, Position start, Position end, int startIndex, int endIndex) {
        this.start = start;
        this.end = end;
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String toString() {
        return String.format("PatternVariableValue[value=%s start=%s end=%s]", this.value, this.start, this.end);
    }
}
