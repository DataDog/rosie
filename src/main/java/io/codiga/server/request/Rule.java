package io.codiga.server.request;

public class Rule {
    public String identifier;
    public String description;
    public String contentBase64;

    public Rule() {

    }

    public Rule(String identifier, String description, String contentBase64) {
        this.identifier = identifier;
        this.description = description;
        this.contentBase64 = contentBase64;
    }
}
