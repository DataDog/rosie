package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.nio.file.Path;

/**
 * A single artifact. In some cases, this artifact might be nested within another artifact.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L117L2339">See in the SARIF JSON Schema</a>
 */
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
