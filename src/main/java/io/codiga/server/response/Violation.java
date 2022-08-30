package io.codiga.server.response;

import java.util.List;

public class Violation {
    public String message;
    public Position start;
    public Position end;
    public String severity;
    public String category;
    public List<ViolationFix> fixes;


    public Violation(Position start, Position end, String message, String severity, String category, List<ViolationFix> fixes) {
        this.message = message;
        this.start = start;
        this.end = end;
        this.severity = severity;
        this.category = category;
        this.fixes = fixes;
    }
}

