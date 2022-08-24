package io.codiga.server.request;

public class RuleBuilder {
    private String identifier;
    private String description;
    private String contentBase64;

    public RuleBuilder setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public RuleBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public RuleBuilder setContentBase64(String contentBase64) {
        this.contentBase64 = contentBase64;
        return this;
    }

    public Rule createRule() {
        return new Rule(identifier, description, contentBase64);
    }
}