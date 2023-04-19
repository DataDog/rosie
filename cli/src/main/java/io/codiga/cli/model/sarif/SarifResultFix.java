package io.codiga.cli.model.sarif;

import io.codiga.model.error.Fix;
import lombok.Builder;

import java.util.List;

/**
 * A proposed fix for the problem represented by a result object.
 * A fix specifies a set of artifacts to modify.
 * For each artifact, it specifies a set of bytes to remove, edit, or add and provides a set of new bytes to replace them.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L978">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifResultFix {
    public SarifResultMessage description;
    public List<SarifArtifactChange> artifactChanges;


    public static SarifResultFix generate(Fix fix, String filename) {
        return SarifResultFix
            .builder()
            .description(new SarifResultMessage(fix.description))
//            .description(SarifResultMessage.builder().text(fix.description).build())
            .artifactChanges(fix.edits == null ? List.of() : List.of(SarifArtifactChange.generate(fix, filename)))
            .build();
    }
}
