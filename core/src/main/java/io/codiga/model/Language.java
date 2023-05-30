package io.codiga.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Language {
    GO,
    JAVA,
    JAVASCRIPT,
    PYTHON,
    TYPESCRIPT,
    YAML,
    @JsonEnumDefaultValue UNKNOWN
}
