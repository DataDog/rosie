package io.codiga.cli.model.sarif;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.codiga.model.error.Edit;
import lombok.Builder;

import java.util.Optional;

import static io.codiga.model.error.EditType.ADD;
import static io.codiga.model.error.EditType.UPDATE;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SarifReplacement {
    // The region of the artifact to delete.
    public SarifRegion deletedRegion;
    // The content to insert at the location specified by the 'deletedRegion' property.
    public SarifArtifactContent insertedContent;

    public static Optional<SarifReplacement> generate(Edit edit) {
        Optional<SarifRegion> deleteRegion = getRegionForDeleteRegion(edit);
        return deleteRegion.map(sarifRegion -> SarifReplacement
            .builder()
            .deletedRegion(sarifRegion)
            .insertedContent(getContentToInsert(edit))
            .build());
    }

    public static Optional<SarifRegion> getRegionForDeleteRegion(Edit edit) {
        switch (edit.editType) {

            case ADD -> {
                /*
                 * If we add, the delete region is at the position of the add
                 * and we delete nothing. We just add something.
                 */
                return Optional.of(
                    SarifRegion.builder()
                        .startLine(edit.start.line)
                        .endLine(edit.start.line)
                        .startColumn(edit.start.col)
                        .endColumn(edit.start.col)
                        .build());
            }

            case UPDATE, REMOVE -> {
                /**
                 * Update and remove: just put whatever section we have to update
                 * or remove
                 */
                return Optional.of(
                    SarifRegion.builder()
                        .startLine(edit.start.line)
                        .endLine(edit.end.line)
                        .startColumn(edit.start.col)
                        .endColumn(edit.end.col)
                        .build());
            }
            case UNKNOWN -> {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public static SarifArtifactContent getContentToInsert(Edit edit) {
        if (edit.editType == ADD || edit.editType == UPDATE) {
            return SarifArtifactContent.builder().text(edit.content).build();
        }
        return null;
    }
}
