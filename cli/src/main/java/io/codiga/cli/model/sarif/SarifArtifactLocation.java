package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.nio.file.Path;

import static io.codiga.cli.utils.SarifUtils.uriReference;

/**
 * Specifies the location of an artifact.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L289">See in the SARIF JSON Schema</a>
 */
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
            .uri(uriReference(s).toString())
            .build();
    }
}
