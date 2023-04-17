package io.codiga.cli.model.sarif;

import io.codiga.cli.model.ViolationWithFilename;
import lombok.Builder;

import java.util.List;

import static io.codiga.cli.utils.SarifUtils.severityToSarifLevel;

@Builder
public class SarifResult {
    public String level;

    public SarifResultMessage message;
    public List<SarifResultLocation> locations;
    public String ruleId;


    public static SarifResult generate(ViolationWithFilename violation) {
        return SarifResult
            .builder()
            .level(severityToSarifLevel(violation.severity))
            .message(SarifResultMessage.builder().text(violation.message).build())
            .locations(List.of(SarifResultLocation.generate(violation)))
            .ruleId(violation.rule)
            .build();
    }
}
