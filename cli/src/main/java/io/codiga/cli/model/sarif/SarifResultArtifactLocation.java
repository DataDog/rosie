package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.nio.file.Path;

@Builder
public class SarifResultArtifactLocation {
    public String uri;


    public static SarifResultArtifactLocation generate(Path path) {
        return SarifResultArtifactLocation
                .builder()
                .uri(path.toUri().toString())
                .build();
    }

    public static SarifResultArtifactLocation generate(String uri) {
        return SarifResultArtifactLocation
                .builder()
                .uri(uri)
                .build();
    }
}
