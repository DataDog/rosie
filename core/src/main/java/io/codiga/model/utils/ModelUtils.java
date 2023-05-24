package io.codiga.model.utils;


import static io.codiga.constants.Languages.*;
import static io.codiga.model.error.EditType.*;

import io.codiga.model.error.EditType;

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
}
