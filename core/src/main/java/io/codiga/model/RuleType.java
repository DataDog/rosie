package io.codiga.model;

public enum RuleType {
    AST_CHECK, // check a type of AST element
    REGEX, // regular expression pattern
    TREE_SITTER_QUERY, // pattern matching in tree-sitter
    UNKNOWN
}
