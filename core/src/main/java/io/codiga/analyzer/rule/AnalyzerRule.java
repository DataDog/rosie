package io.codiga.analyzer.rule;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;

import java.util.HashMap;
import java.util.Map;

public record AnalyzerRule(String name, Language language, RuleType ruleType, EntityChecked entityChecked, String code,
                           String pattern, Map<String, String> variables) {
    /**
     * Indicate is a rule is valid for a given language (or not)
     *
     * @param codeLanguage
     * @return
     */
    public boolean validForLanguage(Language codeLanguage) {
        // exception for typescript: javascript rules also work there.
        if (codeLanguage == Language.TYPESCRIPT && language == Language.JAVASCRIPT) {
            return true;
        }
        return codeLanguage == language;
    }

    @Override
    public Map<String, String> variables() {
        if(this.variables == null) {
            return new HashMap<>();
        } else {
            return this.variables;
        }
    }
}
