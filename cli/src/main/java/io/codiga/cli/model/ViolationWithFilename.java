package io.codiga.cli.model;

import io.codiga.model.common.Position;
import io.codiga.model.error.Category;
import io.codiga.model.error.Fix;
import io.codiga.model.error.Severity;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViolationWithFilename {
    public Position start;
    public Position end;
    public String message;
    public Severity severity;
    public Category category;
    public String filename;
    public String rule;

    @Builder.Default
    public List<Fix> fixes = List.of();
}
