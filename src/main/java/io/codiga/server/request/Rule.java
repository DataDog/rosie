package io.codiga.server.request;

public class Rule {
    public String id;
    public String contentBase64;
    public String language;
    public String type;

    public Rule() {
        // intentionally left blank
    }

    public Rule(String id, String language, String type, String contentBase64) {
        this.id = id;
        this.contentBase64 = contentBase64;
        this.language = language;
        this.type = type;
    }
}
