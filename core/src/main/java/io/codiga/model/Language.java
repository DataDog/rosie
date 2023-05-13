package io.codiga.model;

import static io.codiga.model.utils.ModelUtils.languageFromString;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Language {
    PYTHON,
    JAVASCRIPT,
    TYPESCRIPT,
    UNKNOWN,
    ;

    /**
     * When this Language enum is used within a JSON deserialization, this 
     * helper will run and convert the language value into the correct enum value 
     * 
     * @param value - the JSON value
     * @return Language enum value
     */
    @JsonCreator
    public static Language fromValue(String value){
        for(Language language: Language.values()) {
            return languageFromString(value);
        }
        return Language.UNKNOWN;
    }
}
