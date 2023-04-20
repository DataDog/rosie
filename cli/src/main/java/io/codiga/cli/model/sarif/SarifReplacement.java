package io.codiga.cli.model.sarif;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.codiga.model.error.Edit;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

import static io.codiga.model.error.EditType.ADD;
import static io.codiga.model.error.EditType.UPDATE;

/**
 * The replacement of a single region of an artifact.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1789">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifReplacement {
    // The region of the artifact to delete.
    public SarifRegion deletedRegion;
    // The content to insert at the location specified by the 'deletedRegion' property.
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public SarifArtifactContent insertedContent;
    public SarifPropertyBag properties;

    public static Optional<SarifReplacement> generate(Edit edit) {
        Optional<SarifRegion> deleteRegion = getRegionForDeleteRegion(edit);
        return deleteRegion.map(sarifRegion -> SarifReplacement
            .builder()
            .deletedRegion(sarifRegion)
            .insertedContent(getContentToInsert(edit))
            .properties(SarifPropertyBag.builder().tags(List.of(generateRosieEditTag(edit))).build())
            .build());
    }

    /**
     * The tag added is to indicate in the SARIF format if the edit is an add, update or remove.
     * This function generates the tag.
     * <p>
     * The tag is in the form rosieEditType:ADD
     *
     * @param edit - the edit for the tag
     * @return a string that represents the tag we are generating
     */
    public static String generateRosieEditTag(Edit edit) {
        return String.format("type:%s", edit.editType.toString().toUpperCase());
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
                /*
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
