package io.codiga.model.utils;


import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.EditType;

import static io.codiga.model.error.EditType.*;
import static io.codiga.server.constants.Languages.*;

public class ModelUtils {

    public static String editTypeToString(EditType editType) {
        switch (editType) {
            case ADD:
                return "add";
            case REMOVE:
                return "remove";
            case UPDATE:
                return "update";
        }
        throw new IllegalArgumentException();
    }


    public static EditType editTypeFromString(String editType) {
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
        return Language.UNKNOWN;
    }

    public static String stringFromLanguage(Language language) {
        switch (language) {
            case PYTHON:
                return "python";
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
        return EntityChecked.UNKNOWN;
    }
}
