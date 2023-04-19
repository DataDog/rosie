package io.codiga.cli.model.sarif;

import io.codiga.cli.model.ViolationWithFilename;
import lombok.Builder;

/**
 * A location within a programming artifact.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1291">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifResultLocation {
    public SarifResultPhysicalLocation physicalLocation;


    public static SarifResultLocation generate(ViolationWithFilename violation) {
        return SarifResultLocation
            .builder()
            .physicalLocation(SarifResultPhysicalLocation.generate(violation))
            .build();
    }

}