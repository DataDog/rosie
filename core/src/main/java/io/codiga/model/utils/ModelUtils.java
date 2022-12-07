package io.codiga.model.utils;


import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.EditType;

import static io.codiga.constants.Languages.*;
import static io.codiga.model.error.EditType.*;

public class ModelUtils {

    public static String editTypeToString(EditType editType) {
        if (editType == null) {
            return "unknown";
        }
        switch (editType) {
            case ADD:
                return "add";
            case REMOVE:
                return "remove";
            case UPDATE:
                return "update";
        }
        return "unknown";
    }


    public static EditType editTypeFromString(String editType) {
        if (editType == null) {
            return UNKNOWN;
        }
        if (editType.equalsIgnoreCase("add")) {
            return ADD;
        }
        if (editType.equalsIgnoreCase("remove")) {
            return REMOVE;
        }
        if (editType.equalsIgnoreCase("update")) {
            return UPDATE;
        }
        if (editType.equalsIgnoreCase("replace")) {
            return UPDATE;
        }
        return UNKNOWN;
    }

    public static Language languageFromString(String language) {
        if (language.equalsIgnoreCase(LANGUAGE_PYTHON)) {
            return Language.PYTHON;
        }
        if (language.equalsIgnoreCase(LANGUAGE_JAVASCRIPT)) {
            return Language.JAVASCRIPT;
        }
        return Language.UNKNOWN;
    }

    public static String stringFromLanguage(Language language) {
        switch (language) {
            case PYTHON:
                return LANGUAGE_PYTHON;
            case JAVASCRIPT:
                return LANGUAGE_JAVASCRIPT;
            default:
                return "unknown";
        }
    }

    public static RuleType ruleTypeFromString(String ruleType) {
        if (ruleType == null) {
            return RuleType.UNKNOWN;
        }
        if (ruleType.equalsIgnoreCase(RULE_TYPE_AST)) {
            return RuleType.AST_CHECK;
        }
        if (ruleType.equalsIgnoreCase(RULE_TYPE_PATTERN)) {
            return RuleType.PATTERN;
        }
        return RuleType.UNKNOWN;
    }

    public static EntityChecked entityCheckedFromString(String entityChecked) {
        if (entityChecked == null) {
            return EntityChecked.UNKNOWN;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_FUNCTION_CALL)) {
            return EntityChecked.FUNCTION_CALL;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_FUNCTION_DEFINITION)) {
            return EntityChecked.FUNCTION_DEFINITION;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_IF_CONDITION)) {
            return EntityChecked.IF_STATEMENT;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_IF_CONDITION)) {
            return EntityChecked.IF_STATEMENT;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_TRY_BLOCK)) {
            return EntityChecked.TRY_BLOCK;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_FOR_LOOP)) {
            return EntityChecked.FOR_LOOP;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_CLASS_DEFINITION)) {
            return EntityChecked.CLASS_DEFINITION;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_HTML_ELEMENT)) {
            return EntityChecked.HTML_ELEMENT;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_IMPORT)) {
            return EntityChecked.IMPORT_STATEMENT;
        }
        if (entityChecked.equalsIgnoreCase(ENTITY_CHECKED_ASSIGNMENT)) {
            return EntityChecked.ASSIGNMENT;
        }

        return EntityChecked.UNKNOWN;
    }
}
