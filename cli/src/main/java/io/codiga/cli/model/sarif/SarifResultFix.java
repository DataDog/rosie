package io.codiga.cli.model.sarif;

import io.codiga.model.error.Fix;
import lombok.Builder;

import java.util.List;

@Builder
public class SarifResultFix {
    public SarifResultMessage description;
    public List<SarifArtifactChange> artifactChanges;


    public static SarifResultFix generate(Fix fix, String filename) {
        return SarifResultFix
            .builder()
            .description(SarifResultMessage.builder().text(fix.description).build())
            .artifactChanges(fix.edits == null ? List.of() : List.of(SarifArtifactChange.generate(fix, filename)))
            .build();
    }
}
