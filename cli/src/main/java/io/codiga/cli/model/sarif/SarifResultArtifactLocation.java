package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.nio.file.Path;

import static io.codiga.cli.utils.SarifUtils.stripLeadingSlash;

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
            .uri(stripLeadingSlash(uri))
            .build();
    }
}
