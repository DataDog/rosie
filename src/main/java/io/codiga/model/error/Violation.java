package io.codiga.model.error;


import java.util.List;

public record Violation(Position start,
                        Position end,
                        String message,
                        Severity severity,
                        Category category,
                        List<Fix> fixes) {

}