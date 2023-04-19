package io.codiga.cli.model.sarif;

import io.codiga.cli.model.ViolationWithFilename;
import lombok.Builder;

/**
 * A physical location relevant to a result.
 * Specifies a reference to a programming artifact together with a range of bytes or characters within that artifact.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1613">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifResultPhysicalLocation {
    public SarifResultArtifactLocation artifactLocation;
    public SarifRegion region;

    public static SarifResultPhysicalLocation generate(ViolationWithFilename violation) {
        return SarifResultPhysicalLocation
            .builder()
            .artifactLocation(SarifResultArtifactLocation.generate(violation.filename))
            .region(SarifRegion.generate(violation))
            .build();
    }
}
