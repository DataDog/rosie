package io.codiga.server.response;

import java.util.List;

public class Violation {
    public String message;
    public int line;
    public String severity;
    public String category;
    public List<ViolationFix> fixes;


    public Violation(String message, int line, String severity, String category, List<ViolationFix> fixes) {
        this.message = message;
        this.line = line;
        this.severity = severity;
        this.category = category;
        this.fixes = fixes;
    }
}

