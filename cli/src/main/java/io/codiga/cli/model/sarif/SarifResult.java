package io.codiga.cli.model.sarif;

import static io.codiga.cli.utils.SarifUtils.severityToSarifLevel;

import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.error.Fix;
import java.util.List;
import lombok.Builder;

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
    public int ruleIndex;
    public List<SarifResultFix> fixes;
    public SarifPropertyBag properties;

    public static SarifResult generate(ViolationWithFilename violation, int ruleIndex) {
        List<Fix> fixesWithEdits = violation.fixes == null ? List.of() : violation.fixes.stream().filter(f -> !f.edits.isEmpty()).toList();
        List<Fix> fixesWithoutEdits = violation.fixes == null ? List.of() : violation.fixes.stream().filter(f -> f.edits.isEmpty()).toList();
        List<SarifResultFix> fixes = fixesWithEdits.stream().map(f -> SarifResultFix.generate(f, violation.filename)).toList();

        List<String> fixesAsProperties = fixesWithoutEdits.stream().map(f -> String.format("fix:%s", f.description)).toList();

    return SarifResult.builder()
        .level(severityToSarifLevel(violation.severity))
        .message(SarifResultMessage.builder().text(violation.message).build())
        .locations(List.of(SarifResultLocation.generate(violation)))
        .ruleId(violation.rule)
        .ruleIndex(ruleIndex)
        .fixes(fixes)
        .properties(SarifPropertyBag.builder().tags(fixesAsProperties).build())
        .build();
    }
}
