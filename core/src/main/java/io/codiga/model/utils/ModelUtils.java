package io.codiga.model.utils;

import static io.codiga.constants.Languages.*;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.EditType;

public class ModelUtils {

    public static Language languageFromString(String languageString) {
        if (languageString == null) {
            return Language.UNKNOWN;
        }
        return switch(languageString.toLowerCase()) {
            case "python" -> Language.PYTHON;
            case "javascript" -> Language.JAVASCRIPT;
            case "typescript" -> Language.TYPESCRIPT;
            default -> Language.UNKNOWN;
        };
    }

    public static String stringFromLanguage(Language language) {
        return switch(language) {
            case PYTHON -> "python";
            case JAVASCRIPT -> "javascript";
            case TYPESCRIPT -> "typescript";
            default -> "unknown";
        };
    }

    public static RuleType ruleTypeFromString(String ruleTypeString) {
        if (ruleTypeString == null) {
            return RuleType.UNKNOWN;
        }
        return switch(ruleTypeString.toLowerCase()) {
            case RULE_TYPE_AST -> RuleType.AST_CHECK;
            case RULE_TYPE_PATTERN, "regex_pattern" -> RuleType.PATTERN;
            case RULE_TYPE_TREE_SITTER_QUERY, "tree_sitter_pattern" -> RuleType.TREE_SITTER_QUERY;
            default -> RuleType.UNKNOWN;
        };
    }

    public static EntityChecked entityCheckedFromString(String entityCheckedString) {
        if (entityCheckedString == null) {
            return EntityChecked.UNKNOWN;
        }
        return switch (entityCheckedString.toLowerCase()) {
            case ENTITY_CHECKED_ANY -> EntityChecked.ANY;
            case ENTITY_CHECKED_ASSIGNMENT, "assignment" -> EntityChecked.ASSIGNMENT;  // note: different naming
            case ENTITY_CHECKED_CLASS_DEFINITION, "class_definition" -> EntityChecked.CLASS_DEFINITION;
            case ENTITY_CHECKED_FOR_LOOP, "for_loop" -> EntityChecked.FOR_LOOP;
            case ENTITY_CHECKED_FUNCTION_CALL, "function_call" -> EntityChecked.FUNCTION_CALL;
            case ENTITY_CHECKED_FUNCTION_DEFINITION, "function_definition" -> EntityChecked.FUNCTION_DEFINITION;
            case ENTITY_CHECKED_FUNCTION_EXPRESSION, "function_expression" -> EntityChecked.FUNCTION_EXPRESSION;
            case ENTITY_CHECKED_HTML_ELEMENT, "html_element" -> EntityChecked.HTML_ELEMENT;
            case ENTITY_CHECKED_IF_CONDITION, "if_condition" -> EntityChecked.IF_STATEMENT; // note: different naming
            case ENTITY_CHECKED_IMPORT -> EntityChecked.IMPORT_STATEMENT; // note: different naming
            case ENTITY_CHECKED_INTERFACE -> EntityChecked.INTERFACE;
            case ENTITY_CHECKED_TRY_BLOCK, "try_block" -> EntityChecked.TRY_BLOCK;
            case ENTITY_CHECKED_TYPE -> EntityChecked.TYPE;
            case ENTITY_CHECKED_VARIABLE_DECLARATION, "variable_declaration" -> EntityChecked.VARIABLE_DECLARATION;
            default -> EntityChecked.UNKNOWN;
        };
    }

    public static EditType editTypeFromString(String editTypeString) {
        if (editTypeString == null) {
            return EditType.UNKNOWN;
        }
        return switch (editTypeString.toLowerCase()) {
            case "add" -> EditType.ADD;
            case "remove" -> EditType.REMOVE;
            case "update", "replace" -> EditType.UPDATE; // replace is used in old rule, but is deprecated now
            default -> EditType.UNKNOWN;
        };
    }

    public static String editTypeToString(EditType editType) {
        return switch (editType) {
            case ADD -> "add";
            case REMOVE -> "remove";
            case UPDATE -> "update";
            default -> "unknown";
        };
    }
}

