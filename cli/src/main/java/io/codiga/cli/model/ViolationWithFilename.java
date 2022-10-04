package io.codiga.cli.model;

import io.codiga.model.common.Position;
import io.codiga.model.error.Category;
import io.codiga.model.error.Severity;

public class ViolationWithFilename {

    public Position start;
    public Position end;
    public String message;
    public Severity severity;
    public Category category;
    public String filename;


    public ViolationWithFilename(Position start, Position end, String message, Severity severity, Category category, String filename) {
        this.start = start;
        this.end = end;
        this.message = message;
        this.severity = severity;
        this.category = category;
        this.filename = filename;
    }


}
