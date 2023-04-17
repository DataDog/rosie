package io.codiga.cli.model.sarif;

import io.codiga.cli.model.ViolationWithFilename;
import lombok.Builder;

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
