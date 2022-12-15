package io.codiga.analyzer.rule;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;

public record AnalyzerRule(String name, Language language, RuleType ruleType, EntityChecked entityChecked, String code,
                           String pattern) {
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
}
