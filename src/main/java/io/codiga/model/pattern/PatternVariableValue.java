package io.codiga.model.pattern;

import io.codiga.model.common.Position;

public class PatternVariableValue {
    public Position startPosition;
    public Position endPosition;
    public int startIndex;
    public int endIndex;

    public String value;

    public PatternVariableValue(String value, Position startPosition, Position endPosition, int startIndex, int endIndex) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String toString() {
        return String.format("PatternVariableValue[value=%s start=%s end=%s]", this.value, this.startPosition, this.endPosition);
    }
}
