package io.codiga.server.request;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;

import java.util.Map;

public class RuleBuilder {
    private String id;
    private String description;
    private Language language;
    private RuleType type;
    private EntityChecked entityChecked;
    private String regex;
    private String code;
    private String treeSitterQuery;
    private Map<String, String> variables;

    public RuleBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public RuleBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public RuleBuilder setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public RuleBuilder setType(RuleType type) {
        this.type = type;
        return this;
    }

    public RuleBuilder setEntityChecked(EntityChecked entityChecked) {
        this.entityChecked = entityChecked;
        return this;
    }

    public RuleBuilder setRegex(String regex) {
        this.regex = regex;
        return this;
    }

    public RuleBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public RuleBuilder setTreeSitterQuery(String treeSitterQuery) {
        this.treeSitterQuery = treeSitterQuery;
        return this;
    }

    public RuleBuilder setVariables(Map<String, String> variables) {
        this.variables = variables;
        return this;
    }

    public Rule createRule() {
        return new Rule(id, description, language, type, entityChecked, treeSitterQuery, regex, code, variables);
    }
}
