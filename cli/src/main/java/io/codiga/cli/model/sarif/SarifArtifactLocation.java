package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.nio.file.Path;

@Builder
public class SarifArtifactLocation {
    public String uri;


    public static SarifArtifactLocation generate(Path path) {
        return SarifArtifactLocation
                .builder()
                .uri(path.toUri().toString())
                .build();
    }
}
