package io.codiga.model.utils;


import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.EditType;

import static io.codiga.server.constants.Languages.LANGUAGE_PYTHON;
import static io.codiga.server.constants.Languages.RULE_TYPE_FUNCTION_CALL;

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

    public static Language languageFromString(String language) {
        if (language.equalsIgnoreCase(LANGUAGE_PYTHON)) {
            return Language.PYTHON;
        }
        return Language.UNKNOWN;
    }

    public static RuleType ruleTypeFromString(String ruleType) {
        if (ruleType.equalsIgnoreCase(RULE_TYPE_FUNCTION_CALL)) {
            return RuleType.FUNCTION_CALL;
        }
        return RuleType.UNKNOWN;
    }
}
