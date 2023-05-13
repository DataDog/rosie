package io.codiga.analyzer.rule;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import java.util.Map;

public record AnalyzerRule(String name,
                           String description,
                           Language language,
                           @JsonAlias({"ruleType","type"})
                           RuleType ruleType,
                           @JsonAlias({"entityChecked","entity_checked"})
                           EntityChecked entityChecked, // defined/used only when ruleType is an AST rule
                           @JsonAlias({"code","content"})
                           String code, // JavaScript code of the rule
                           @JsonAlias({"pattern", "regex_pattern"})
                           String pattern, // only defined when using a pattern
                           @JsonAlias("tree_sitter_pattern")
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
