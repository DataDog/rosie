package io.codiga.analyzer.rule;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import java.util.Map;

public record AnalyzerRule(String name,
                           String description,
                           Language language,
                           RuleType type,
                           EntityChecked entityChecked, // defined/used only when ruleType is an AST rule
                           String code, // JavaScript code of the rule
                           String regex, // only defined when using a pattern
                           String treeSitterQuery, // the tree-sitter query when we are using pattern matching
                           Map<String, String> variables) {
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
