package io.codiga.cli.model.sarif;

import io.codiga.cli.model.ViolationWithFilename;
import lombok.Builder;

import java.util.List;

import static io.codiga.cli.utils.SarifUtils.severityToSarifLevel;

/**
 * A result produced by an analysis tool.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L2038">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifResult {
    public String level;

    public SarifResultMessage message;
    public List<SarifResultLocation> locations;
    public String ruleId;
    public List<SarifResultFix> fixes;

    public static SarifResult generate(ViolationWithFilename violation) {
        return SarifResult
            .builder()
            .level(severityToSarifLevel(violation.severity))
            .message(SarifResultMessage.builder().text(violation.message).build())
            .locations(List.of(SarifResultLocation.generate(violation)))
            .ruleId(violation.rule)
            .fixes(violation.fixes == null ? List.of() : violation.fixes.stream().map(f -> SarifResultFix.generate(f, violation.filename)).toList())
            .build();
    }
}
