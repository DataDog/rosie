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
        String finalUri = uri;
        if (uri.startsWith("/")) {
            finalUri = uri.substring(1);
        }

        return SarifResultArtifactLocation
            .builder()
            .uri(finalUri)
            .build();
    }
}
