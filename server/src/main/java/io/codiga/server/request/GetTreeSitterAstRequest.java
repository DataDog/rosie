package io.codiga.server.request;

import lombok.Builder;

/**
 * The request structure and utils needed to get a TreeSitter AST
 */
@Builder
public class GetTreeSitterAstRequest {
    public String language;
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
