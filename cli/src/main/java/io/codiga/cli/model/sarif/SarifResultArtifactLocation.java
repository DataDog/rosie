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
            .uri(uriReference(uri).toString())
            .build();
    }
}
