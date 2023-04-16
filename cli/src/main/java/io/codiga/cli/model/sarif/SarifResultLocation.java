package io.codiga.cli.model.sarif;

import lombok.Builder;

@Builder
public class SarifResultLocation {
    public SarifResultPhysicalLocation physicalLocation;

    public static SarifResultLocation generate(String uri) {
        return SarifResultLocation
                .builder()
                .physicalLocation(SarifResultPhysicalLocation.generate(uri))
                .build();
    }
}
