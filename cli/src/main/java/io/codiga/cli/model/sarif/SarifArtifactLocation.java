package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.nio.file.Path;

import static io.codiga.cli.utils.SarifUtils.stripLeadingSlash;

@Builder
public class SarifArtifactLocation {
    public String uri;


    public static SarifArtifactLocation generate(Path path) {
        return SarifArtifactLocation
            .builder()
            .uri(path.toUri().toString())
            .build();
    }

    public static SarifArtifactLocation generate(String s) {
        return SarifArtifactLocation
            .builder()
            .uri(stripLeadingSlash(s))
            .build();
    }
}
