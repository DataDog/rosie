package io.codiga.cli.model.sarif;

import io.codiga.model.error.Fix;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

/**
 * A change to a single artifact.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L230">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifArtifactChange {
    public SarifArtifactLocation artifactLocation;
    public List<SarifReplacement> replacements;


    public static SarifArtifactChange generate(Fix fix, String filename) {
        return SarifArtifactChange
            .builder()
            .artifactLocation(SarifArtifactLocation.generate(filename))
            .replacements(fix.edits.stream().map(SarifReplacement::generate).filter(Optional::isPresent).map(Optional::get).toList())
            .build();
    }
}
