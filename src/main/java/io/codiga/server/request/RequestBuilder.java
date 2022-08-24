package io.codiga.server.request;

import java.util.List;

public class RequestBuilder {
    private String filename;
    private String language;
    private String fileEncoding;
    private String codeBase64;
    private List<Rule> rules;

    public RequestBuilder setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public RequestBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public RequestBuilder setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
        return this;
    }

    public RequestBuilder setCodeBase64(String codeBase64) {
        this.codeBase64 = codeBase64;
        return this;
    }

    public RequestBuilder setRules(List<Rule> rules) {
        this.rules = rules;
        return this;
    }

    public Request createRequest() {
        return new Request(filename, language, fileEncoding, codeBase64, rules);
    }
}