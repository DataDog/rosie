package io.codiga.model.error;


import io.codiga.model.common.Position;

import java.util.List;

public record Violation(Position start,
                        Position end,
                        String message,
                        Severity severity,
                        Category category,
                        List<Fix> fixes) {

}