package io.codiga.cli.model.sarif;

import io.codiga.cli.model.ViolationWithFilename;
import lombok.Builder;

/**
 * A region within an artifact where a result was detected.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1711">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifRegion {
    public Integer startLine;
    public Integer startColumn;
    public Integer endLine;
    public Integer endColumn;

    public static SarifRegion generate(ViolationWithFilename violation) {
        return SarifRegion
            .builder()
            .startLine(violation.start == null ? null : violation.start.line)
            .startColumn(violation.start == null ? null : violation.start.col)
            .endLine(violation.end == null ? null : violation.end.line)
            .endColumn(violation.end == null ? null : violation.end.col)
            .build();
    }
}
