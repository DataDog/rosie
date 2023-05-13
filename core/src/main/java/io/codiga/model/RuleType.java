package io.codiga.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

import static io.codiga.model.utils.ModelUtils.ruleTypeFromString;

public enum RuleType {
    AST_CHECK, // check a type of AST element
    PATTERN, // regular pattern (aka regular expression)
    TREE_SITTER_QUERY, // pattern matching in tree-sitter
    UNKNOWN,
    ;

    /**
     * When this RuleType enum is used within a JSON deserialization, this
     * helper will run and convert the ruleType value into the correct enum value
     *
     * @param value - the JSON value
     * @return RuleType enum value
     */
    @JsonCreator
    public static RuleType fromValue(String value){
        for(RuleType ruleType: RuleType.values()) {
            // Allows for backward compatability for when rule types like 'AST_CHECK' are given
            if (Objects.equals(ruleType.toString(), value)) {
                return ruleType;
            }
            return ruleTypeFromString(value);
        }
        return RuleType.UNKNOWN;
    }
}
