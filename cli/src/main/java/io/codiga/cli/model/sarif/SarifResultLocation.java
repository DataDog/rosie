package io.codiga.cli.model.sarif;

import io.codiga.cli.model.ViolationWithFilename;
import lombok.Builder;

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