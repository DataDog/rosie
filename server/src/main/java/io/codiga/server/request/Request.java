package io.codiga.server.request;

import java.util.List;

public class Request {
    public String filename;

    public String language;

    public String fileEncoding;
    public String code;
    public List<Rule> rules;
    public RequestOptions options;


    public Request() {

    }

    public Request(String filename, String language, String fileEncoding, String code, List<Rule> rules, RequestOptions options) {
        this.filename = filename;
        this.language = language;
        this.fileEncoding = fileEncoding;
        this.code = code;
        this.rules = rules;
        this.options = options;
    }

    public boolean isValid() {
        return !(this.fileEncoding == null ||
            this.filename == null ||
            this.language == null ||
            this.code == null ||
            this.rules == null ||
            this.rules.isEmpty());
    }

    public String toString() {
        return String.format("%s %s %s %s", filename, language, fileEncoding, String.join(", ", rules.stream().map(r -> r.id).toList()));
    }
}
