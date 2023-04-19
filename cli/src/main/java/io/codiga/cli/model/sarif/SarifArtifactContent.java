package io.codiga.cli.model.sarif;

import lombok.Builder;

/**
 * Represents the contents of an artifact.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L261">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifArtifactContent {
    public String text;

}
