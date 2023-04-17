package io.codiga.server.request;

import java.util.List;

public class GetTreeSitterAstRequest {
    public String language;
    public String fileEncoding;
    public String codeBase64;

    public GetTreeSitterAstRequest(String language, String fileEncoding, String codeBase64) {
        this.language = language;
        this.fileEncoding = fileEncoding;
        this.codeBase64 = codeBase64;
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
