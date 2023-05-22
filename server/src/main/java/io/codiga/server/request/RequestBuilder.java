package io.codiga.server.request;

import java.util.List;

public class RequestBuilder {
    private String filename;
    private String language;
    private String fileEncoding;
    private String code;
    private List<Rule> rules;

    private RequestOptions requestOptions = null;

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

    public RequestBuilder setCode(String code) {
        this.code = code;
        return this;
    }



    public RequestBuilder setRules(List<Rule> rules) {
        this.rules = rules;
        return this;
    }

    public RequestBuilder setOptions(RequestOptions options) {
        this.requestOptions = options;
        return this;
    }

    public Request createRequest() {
        return new Request(filename, language, fileEncoding, code, rules, requestOptions);
    }
}
