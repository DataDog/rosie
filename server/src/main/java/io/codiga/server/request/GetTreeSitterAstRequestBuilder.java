package io.codiga.server.request;

public class GetTreeSitterAstRequestBuilder {
    private String language;
    private String fileEncoding;
    private String codeBase64;

    public GetTreeSitterAstRequestBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public GetTreeSitterAstRequestBuilder setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
        return this;
    }

    public GetTreeSitterAstRequestBuilder setCodeBase64(String codeBase64) {
        this.codeBase64 = codeBase64;
        return this;
    }

    public GetTreeSitterAstRequest createGetTreeSitterAstRequest () {
        return new GetTreeSitterAstRequest(language, fileEncoding, codeBase64);
    }
}