package io.codiga.model;

import static io.codiga.model.utils.ModelUtils.entityCheckedFromString;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EntityChecked {
    ANY,
    ASSIGNMENT,
    CLASS_DEFINITION,
    FOR_LOOP,
    FUNCTION_CALL,
    FUNCTION_DEFINITION,
    FUNCTION_EXPRESSION,
    HTML_ELEMENT,
    IF_STATEMENT,
    INTERFACE,
    IMPORT_STATEMENT,
    VARIABLE_DECLARATION,
    TRY_BLOCK,
    TYPE,
    UNKNOWN,
    ;


    /**
     * When this EntityChecked enum is used within a JSON deserialization, this
     * helper will run and convert the entityChecked value into the correct enum value
     *
     * @param value - the JSON value
     * @return EntityChecked enum value
     */
    @JsonCreator
    public static EntityChecked fromValue(String value){
        for(EntityChecked entityChecked: EntityChecked.values()) {
            return entityCheckedFromString(value);
        }
        return EntityChecked.UNKNOWN;
    }
}
