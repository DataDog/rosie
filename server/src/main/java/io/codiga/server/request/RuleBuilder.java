package io.codiga.server.request;

import java.util.Map;

public class RuleBuilder {
    private String id;
    private String language;
    private String type;
    private String entityChecked;
    private String pattern;
    private String contentBase64;
    private Map<String, String> variables;

    public RuleBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public RuleBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public RuleBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public RuleBuilder setEntityChecked(String entityChecked) {
        this.entityChecked = entityChecked;
        return this;
    }

    public RuleBuilder setPattern(String pattern) {
        this.pattern = pattern;
        return this;
    }

    public RuleBuilder setContentBase64(String contentBase64) {
        this.contentBase64 = contentBase64;
        return this;
    }

    public RuleBuilder setVariables(Map<String, String> variables) {
        this.variables = variables;
        return this;
    }

    public Rule createRule() {
        return new Rule(id, language, type, entityChecked, pattern, contentBase64, variables);
    }
}