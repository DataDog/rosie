package io.codiga.cli.model;

import java.util.List;

public class Result {
    public List<ViolationWithFilename> violations;

    public Result(List<ViolationWithFilename> violations) {
        this.violations = violations;
    }
}
