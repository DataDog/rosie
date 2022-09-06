package io.codiga.model.pattern;

import io.codiga.model.common.Position;

import java.util.Map;

public class PatternObject {
    public Position startPosition;
    public Position endPosition;
    public int startIndex;
    public int endIndex;
    public Map<String, PatternVariableValue> variables;

    public PatternObject(Position startPosition, Position endPosition, int startIndex, int endIndex, Map<String, PatternVariableValue> variables) {
        this.variables = variables;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public String toString(){
        return String.format("PatternObject[startPosition=%s, endPosition=%s, variables=%s]", this.startPosition, this.endPosition, this.variables);
    }
}
