package io.codiga.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Language {
    PYTHON,
    JAVASCRIPT,
    TYPESCRIPT,
    @JsonEnumDefaultValue UNKNOWN
}
