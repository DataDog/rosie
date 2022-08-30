package io.codiga.server.request;

public class Rule {
    public String id;
    public String contentBase64;

    public Rule() {
        // intentionally left blank
    }

    public Rule(String id, String description, String contentBase64) {
        this.id = id;
        this.contentBase64 = contentBase64;
    }
}
