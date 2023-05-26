package io.codiga.server.response;

import io.codiga.model.common.Position;
import io.codiga.model.error.Category;
import io.codiga.model.error.Severity;

import java.util.List;

public class Violation {
    public String message;
    public Position start;
    public Position end;
    public Severity severity;
    public Category category;
    public List<ViolationFix> fixes;

    public Violation() {}

    public Violation(Position start, Position end, String message, Severity severity, Category category, List<ViolationFix> fixes) {
        this.message = message;
        this.start = start;
        this.end = end;
        this.severity = severity;
        this.category = category;
        this.fixes = fixes;
    }

    @Override
    public String toString() {
        return "Violation{" +
            "message='" + message + '\'' +
            ", start=" + start +
            ", end=" + end +
            ", severity='" + severity + '\'' +
            ", category='" + category + '\'' +
            ", fixes=" + fixes +
            '}';
    }
}

