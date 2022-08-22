package io.codiga.model.utils;


import io.codiga.model.error.EditType;

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
}
