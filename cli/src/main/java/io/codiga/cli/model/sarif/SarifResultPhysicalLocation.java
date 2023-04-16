package io.codiga.cli.model.sarif;

import lombok.Builder;

@Builder
public class SarifResultPhysicalLocation {
    public SarifResultArtifactLocation artifactLocation;

    public static SarifResultPhysicalLocation generate(String uri) {
        return SarifResultPhysicalLocation
                .builder()
                .artifactLocation(SarifResultArtifactLocation.generate(uri))
                .build();
    }
}
