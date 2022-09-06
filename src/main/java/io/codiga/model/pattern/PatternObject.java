package io.codiga.model.pattern;

import io.codiga.model.common.Position;
import org.graalvm.polyglot.proxy.ProxyHashMap;

import java.util.Collections;
import java.util.Map;

public class PatternObject {
    public Position start;
    public Position end;
    public int startIndex;
    public int endIndex;
    public Map<String, PatternVariableValue> javaVariables;
    public ProxyHashMap variables;


    public PatternObject(Position start, Position end, int startIndex, int endIndex, Map<String, PatternVariableValue> javaVariables) {
        this.javaVariables = javaVariables;
        this.start = start;
        this.end = end;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.variables = ProxyHashMap.from(Collections.unmodifiableMap(javaVariables));
    }

    public String toString() {
        return String.format("PatternObject[startPosition=%s, endPosition=%s, variables=%s]", this.start, this.end, this.javaVariables);
    }
}
