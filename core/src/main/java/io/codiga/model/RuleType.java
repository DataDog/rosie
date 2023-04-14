package io.codiga.model;

public enum RuleType {
    AST_CHECK, // check a type of AST element
    PATTERN, // regular pattern (aka regular expression)
    TREE_SITTER_QUERY, // pattern matching in tree-sitter
    UNKNOWN
}
