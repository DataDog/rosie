package io.codiga.server.request;

import io.codiga.model.Language;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * The request structure and utils needed to get a TreeSitter AST
 */
@Builder
@Jacksonized
public class GetTreeSitterAstRequest {
    public Language language;
    public String fileEncoding;
    public String code;

    public boolean isValid() {
        return !(this.fileEncoding == null ||
                this.language == null ||
                this.code == null);
    }

    public String toString() {
        return String.format("%s %s", language, fileEncoding);
    }
}
