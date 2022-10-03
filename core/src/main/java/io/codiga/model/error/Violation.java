package io.codiga.model.error;


import io.codiga.model.common.Position;
import org.graalvm.polyglot.HostAccess;

import java.util.ArrayList;
import java.util.List;

public class Violation {
    public Position start;
    public Position end;
    public String message;
    public Severity severity;
    public Category category;
    public List<Fix> fixes;

    public Violation(Position start, Position end, String message, Severity severity, Category category) {
        this.start = start;
        this.end = end;
        this.message = message;
        this.severity = severity;
        this.category = category;
        this.fixes = new ArrayList<>();
    }

    public Violation(Position start, Position end, String message, Severity severity, Category category, List<Fix> fixes) {
        this(start, end, message, severity, category);
        this.fixes = List.copyOf(fixes);
    }

    @HostAccess.Export
    public Violation addFix(Fix fix) {
        this.fixes.add(fix);
        return new Violation(this.start, this.end, this.message, this.severity, this.category, this.fixes);
    }
}