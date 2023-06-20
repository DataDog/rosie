package io.codiga.analyzer.rule;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import java.util.Map;

public record AnalyzerRule(String name,
                           @JsonProperty("short_description")
                           String shortDescription,
                           String description,
                           Language language,
                           RuleType type,
                           @JsonProperty("entity_checked")
                           EntityChecked entityChecked, // defined/used only when ruleType is an AST rule
                           String code, // JavaScript code of the rule
                           String regex, // only defined when using a pattern
                           @JsonProperty("tree_sitter_query")
                           String treeSitterQuery, // the tree-sitter query when we are using pattern matching
                           Map<String, String> variables,
                           @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "should_use_ai_fix")
                           Boolean shouldUseAiFix
) {
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
