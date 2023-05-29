package io.codiga.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Language {
    GO,
    PYTHON,
    JAVASCRIPT,
    TYPESCRIPT,
    YAML,
    @JsonEnumDefaultValue UNKNOWN
}
