package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.nio.file.Path;

@Builder
public class SarifArtifact {
    public SarifArtifactLocation location;


    public static SarifArtifact generate(Path path) {
        return SarifArtifact
                .builder()
                .location(SarifArtifactLocation.generate(path))
                .build();
    }
}
