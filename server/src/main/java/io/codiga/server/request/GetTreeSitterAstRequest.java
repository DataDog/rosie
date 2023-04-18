package io.codiga.server.request;

import lombok.Builder;

/**
 * The request structure and utils needed to get a TreeSitter AST
 */
@Builder
public class GetTreeSitterAstRequest {
    public String language;
    public String fileEncoding;
    public String codeBase64;

    public static GetTreeSitterAstRequest create(String language, String fileEncoding, String codeBase64) {
        return GetTreeSitterAstRequest
                .builder()
                .language(language)
                .fileEncoding(fileEncoding)
                .codeBase64(codeBase64)
                .build();
    }

    public boolean isValid() {
        return !(this.fileEncoding == null ||
                this.language == null ||
                this.codeBase64 == null);
    }

    public String toString() {
        return String.format("%s %s", language, fileEncoding);
    }
}
