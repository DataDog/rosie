package io.codiga.analyzer.rule;

import static io.codiga.utils.TreeSitterUtils.languageToTreeSitterLanguage;

import ai.serenade.treesitter.query.Query;
import ai.serenade.treesitter.query.exceptions.QueryException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Builder
@AllArgsConstructor
@Jacksonized
@ToString
public class AnalyzerRule {

    public String name;
    public String description;
    public Language language;
    public RuleType type;
    @JsonProperty("entity_checked")
    public EntityChecked entityChecked; // defined/used only when ruleType is an AST rule
    public String code; // JavaScript code of the rule
    public String regex; // only defined when using a pattern
    @JsonProperty("tree_sitter_query")
    public String treeSitterQuery; // the tree-sitter query when we are using pattern matching
    public Map<String, String> variables;

    @Builder.Default
    @JsonIgnore
    @ToString.Exclude
    private Query treeSitterQueryObject = null;

    protected void finalize() {
        if(treeSitterQueryObject!= null) {
            treeSitterQueryObject.close();
        }
    }

    public Query getTreeSitterQueryObject() {
        if(treeSitterQueryObject != null) {
            return treeSitterQueryObject;
        }
        Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(language);
        if(treeSitterLanguage.isPresent()) {
            try {
                treeSitterQueryObject = new Query(treeSitterLanguage.get(), this.treeSitterQuery);
                return treeSitterQueryObject;
            } catch (QueryException queryObject) {
                return null;
            }
        }
        return null;
    }

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
