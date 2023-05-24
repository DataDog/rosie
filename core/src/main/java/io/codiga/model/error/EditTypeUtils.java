package io.codiga.model.error;

import java.util.Map;

public class EditTypeUtils {
    private final static Map<Object, EditType> STRING_TO_EDIT_TYPE = Map.of(
        "add", EditType.ADD,
        "remove", EditType.REMOVE,
        "update", EditType.UPDATE,
        "replace", EditType.UPDATE
    );

    public static EditType editTypeFromString(String editTypeString) {
        return STRING_TO_EDIT_TYPE.getOrDefault(editTypeString.toLowerCase(), EditType.UNKNOWN);
    }
}
