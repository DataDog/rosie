package io.codiga.cli.model;

import java.util.List;

public class Result {
    public List<Violation> violations;

    public Result(List<Violation> violations) {
        this.violations = violations;
    }
}
