package io.codiga.server.request;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import java.util.Map;

public class Rule {
    public String id;
    public String shortDescription;
    public String description;
    public String code;
    public Language language;
    public RuleType type;
    public EntityChecked entityChecked;
    public String treeSitterQuery;
    public String regex;
    public Map<String, String> variables;

    public Rule() {
        // intentionally left blank
    }

    public Rule(String id,
                String shortDescription,
                String description,
                Language language,
                RuleType type,
                EntityChecked entityChecked,
                String treeSitterQuery,
                String regex,
                String code,
                Map<String, String> variables) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.code = code;
        this.language = language;
        this.type = type;
        this.entityChecked = entityChecked;
        this.regex = regex;
        this.treeSitterQuery = treeSitterQuery;
        this.variables = variables;
    }

    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s", id, shortDescription, description, language, type, entityChecked, regex, variables);
    }
}
