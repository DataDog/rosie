package io.codiga.server.request;

import java.util.Map;

public class Rule {
    public String id;
    public String contentBase64;
    public String language;
    public String type;
    public String entityChecked;
    public String tsQueryBase64;
    public String pattern;
    public Map<String, String> variables;

    public Rule() {
        // intentionally left blank
    }

    public Rule(String id,
                String language,
                String type,
                String entityChecked,
                String tsQueryBase64,
                String pattern,
                String contentBase64,
                Map<String, String> variables) {
        this.id = id;
        this.contentBase64 = contentBase64;
        this.language = language;
        this.type = type;
        this.entityChecked = entityChecked;
        this.pattern = pattern;
        this.tsQueryBase64 = tsQueryBase64;
        this.variables = variables;
    }

    public String toString() {
        return String.format("%s %s %s %s %s %s", id, language, type, entityChecked, pattern, variables);
    }
}
