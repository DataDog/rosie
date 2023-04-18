package io.codiga.parser.treesitter.python.types;

import java.util.HashMap;
import java.util.Map;

public enum TreeSitterPythonTypes {

    ERROR("ERROR"),
    ALIASED_IMPORT("aliased_import"),
    ARGUMENT_LIST("argument_list"),
    ASSERT_STATEMENT("assert_statement"),
    ASSIGNMENT("assignment"),
    AS_PATTERN("as_pattern"),
    AS_PATTERN_TARGET("as_pattern_target"),
    ATTRIBUTE("attribute"),
    AUGMENTED_ASSIGNMENT("augmented_assignment"),
    AWAIT("await"),
    BINARY_OPERATOR("binary_operator"),
    BLOCK("block"),
    BOOLEAN_OPERATOR("boolean_operator"),
    BREAK_STATEMENT("break_statement"),
    CALL("call"),
    CASE_CLAUSE("case_clause"),
    CASE_PATTERN("case_pattern"),
    CHEVRON("chevron"),
    CLASS_DEFINITION("class_definition"),
    COMMENT("comment"),
    COMPARISON_OPERATOR("comparison_operator"),
    CONCATENATED_STRING("concatenated_string"),
    CONDITIONAL_EXPRESSION("conditional_expression"),
    CONTINUE_STATEMENT("continue_statement"),
    DECORATED_DEFINITION("decorated_definition"),
    DECORATOR("decorator"),
    DEFAULT_PARAMETER("default_parameter"),
    DELETE_STATEMENT("delete_statement"),
    DICTIONARY("dictionary"),
    DICTIONARY_COMPREHENSION("dictionary_comprehension"),
    DICTIONARY_SPLAT("dictionary_splat"),
    DICTIONARY_SPLAT_PATTERN("dictionary_splat_pattern"),
    DOTTED_NAME("dotted_name"),
    ELIF_CLAUSE("elif_clause"),
    ELLIPSIS("ellipsis"),
    ELSE_CLAUSE("else_clause"),
    ESCAPE_SEQUENCE("escape_sequence"),
    EXCEPT_CLAUSE("except_clause"),
    EXCEPT_GROUP_CLAUSE("except_group_clause"),
    EXEC_STATEMENT("exec_statement"),
    EXPRESSION("expression"),
    EXPRESSION_LIST("expression_list"),
    EXPRESSION_STATEMENT("expression_statement"),
    FALSE("false"),
    FINALLY_CLAUSE("finally_clause"),
    FLOAT("float"),
    FORMAT_EXPRESSION("format_expression"),
    FORMAT_SPECIFIER("format_specifier"),
    FOR_IN_CLAUSE("for_in_clause"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DEFINITION("function_definition"),
    FUTURE_IMPORT_STATEMENT("future_import_statement"),
    GENERATOR_EXPRESSION("generator_expression"),
    GLOBAL_STATEMENT("global_statement"),
    IDENTIFIER("identifier"),
    IF_CLAUSE("if_clause"),
    IF_STATEMENT("if_statement"),
    IMPORT_FROM_STATEMENT("import_from_statement"),
    IMPORT_PREFIX("import_prefix"),
    IMPORT_STATEMENT("import_statement"),
    INTEGER("integer"),
    INTERPOLATION("interpolation"),
    KEYWORD_ARGUMENT("keyword_argument"),
    KEYWORD_SEPARATOR("keyword_separator"),
    LAMBDA("lambda"),
    LAMBDA_PARAMETERS("lambda_parameters"),
    LIST("list"),
    LIST_COMPREHENSION("list_comprehension"),
    LIST_PATTERN("list_pattern"),
    LIST_SPLAT("list_splat"),
    LIST_SPLAT_PATTERN("list_splat_pattern"),
    MATCH_STATEMENT("match_statement"),
    MODULE("module"),
    NAMED_EXPRESSION("named_expression"),
    NONE("none"),
    NONLOCAL_STATEMENT("nonlocal_statement"),
    NOT_OPERATOR("not_operator"),
    PAIR("pair"),
    PARAMETER("parameter"),
    PARAMETERS("parameters"),
    PARENTHESIZED_EXPRESSION("parenthesized_expression"),
    PARENTHESIZED_LIST_SPLAT("parenthesized_list_splat"),
    PASS_STATEMENT("pass_statement"),
    PATTERN("pattern"),
    PATTERN_LIST("pattern_list"),
    POSITIONAL_SEPARATOR("positional_separator"),
    PRIMARY_EXPRESSION("primary_expression"),
    PRINT_STATEMENT("print_statement"),
    RAISE_STATEMENT("raise_statement"),
    RELATIVE_IMPORT("relative_import"),
    RETURN_STATEMENT("return_statement"),
    SET("set"),
    SET_COMPREHENSION("set_comprehension"),
    SLICE("slice"),
    STRING("string"),
    STRING_CONTENT("string_content"),
    SUBSCRIPT("subscript"),
    TRUE("true"),
    TRY_STATEMENT("try_statement"),
    TUPLE("tuple"),
    TUPLE_PATTERN("tuple_pattern"),
    TYPE("type"),
    TYPED_DEFAULT_PARAMETER("typed_default_parameter"),
    TYPED_PARAMETER("typed_parameter"),
    TYPE_CONVERSION("type_conversion"),
    UNARY_OPERATOR("unary_operator"),
    UNKNOWN("unknown"),
    WHILE_STATEMENT("while_statement"),
    WILDCARD_IMPORT("wildcard_import"),
    WITH_CLAUSE("with_clause"),
    WITH_ITEM("with_item"),
    WITH_STATEMENT("with_statement"),
    YIELD("yield"),
    _COMPOUND_STATEMENT("_compound_statement"),
    _SIMPLE_STATEMENT("_simple_statement");

    public static Map<String, TreeSitterPythonTypes> NODE_TYPE_TO_ENUMERATION = new HashMap<>();

    static {
        NODE_TYPE_TO_ENUMERATION.put("ERROR", ERROR);
        NODE_TYPE_TO_ENUMERATION.put("aliased_import", ALIASED_IMPORT);
        NODE_TYPE_TO_ENUMERATION.put("argument_list", ARGUMENT_LIST);
        NODE_TYPE_TO_ENUMERATION.put("assert_statement", ASSERT_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("assignment", ASSIGNMENT);
        NODE_TYPE_TO_ENUMERATION.put("as_pattern", AS_PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("as_pattern_target", AS_PATTERN_TARGET);
        NODE_TYPE_TO_ENUMERATION.put("attribute", ATTRIBUTE);
        NODE_TYPE_TO_ENUMERATION.put("augmented_assignment", AUGMENTED_ASSIGNMENT);
        NODE_TYPE_TO_ENUMERATION.put("await", AWAIT);
        NODE_TYPE_TO_ENUMERATION.put("binary_operator", BINARY_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("block", BLOCK);
        NODE_TYPE_TO_ENUMERATION.put("boolean_operator", BOOLEAN_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("break_statement", BREAK_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("call", CALL);
        NODE_TYPE_TO_ENUMERATION.put("case_clause", CASE_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("case_pattern", CASE_PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("chevron", CHEVRON);
        NODE_TYPE_TO_ENUMERATION.put("class_definition", CLASS_DEFINITION);
        NODE_TYPE_TO_ENUMERATION.put("comment", COMMENT);
        NODE_TYPE_TO_ENUMERATION.put("comparison_operator", COMPARISON_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("concatenated_string", CONCATENATED_STRING);
        NODE_TYPE_TO_ENUMERATION.put("conditional_expression", CONDITIONAL_EXPRESSION);
        NODE_TYPE_TO_ENUMERATION.put("continue_statement", CONTINUE_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("decorated_definition", DECORATED_DEFINITION);
        NODE_TYPE_TO_ENUMERATION.put("decorator", DECORATOR);
        NODE_TYPE_TO_ENUMERATION.put("default_parameter", DEFAULT_PARAMETER);
        NODE_TYPE_TO_ENUMERATION.put("delete_statement", DELETE_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("dictionary", DICTIONARY);
        NODE_TYPE_TO_ENUMERATION.put("dictionary_comprehension", DICTIONARY_COMPREHENSION);
        NODE_TYPE_TO_ENUMERATION.put("dictionary_splat", DICTIONARY_SPLAT);
        NODE_TYPE_TO_ENUMERATION.put("dictionary_splat_pattern", DICTIONARY_SPLAT_PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("dotted_name", DOTTED_NAME);
        NODE_TYPE_TO_ENUMERATION.put("elif_clause", ELIF_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("ellipsis", ELLIPSIS);
        NODE_TYPE_TO_ENUMERATION.put("else_clause", ELSE_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("escape_sequence", ESCAPE_SEQUENCE);
        NODE_TYPE_TO_ENUMERATION.put("except_clause", EXCEPT_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("except_group_clause", EXCEPT_GROUP_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("exec_statement", EXEC_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("expression", EXPRESSION);
        NODE_TYPE_TO_ENUMERATION.put("expression_list", EXPRESSION_LIST);
        NODE_TYPE_TO_ENUMERATION.put("expression_statement", EXPRESSION_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("false", FALSE);
        NODE_TYPE_TO_ENUMERATION.put("finally_clause", FINALLY_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("float", FLOAT);
        NODE_TYPE_TO_ENUMERATION.put("format_expression", FORMAT_EXPRESSION);
        NODE_TYPE_TO_ENUMERATION.put("format_specifier", FORMAT_SPECIFIER);
        NODE_TYPE_TO_ENUMERATION.put("for_in_clause", FOR_IN_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("for_statement", FOR_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("function_definition", FUNCTION_DEFINITION);
        NODE_TYPE_TO_ENUMERATION.put("future_import_statement", FUTURE_IMPORT_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("generator_expression", GENERATOR_EXPRESSION);
        NODE_TYPE_TO_ENUMERATION.put("global_statement", GLOBAL_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("identifier", IDENTIFIER);
        NODE_TYPE_TO_ENUMERATION.put("if_clause", IF_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("if_statement", IF_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("import_from_statement", IMPORT_FROM_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("import_prefix", IMPORT_PREFIX);
        NODE_TYPE_TO_ENUMERATION.put("import_statement", IMPORT_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("integer", INTEGER);
        NODE_TYPE_TO_ENUMERATION.put("interpolation", INTERPOLATION);
        NODE_TYPE_TO_ENUMERATION.put("keyword_argument", KEYWORD_ARGUMENT);
        NODE_TYPE_TO_ENUMERATION.put("keyword_separator", KEYWORD_SEPARATOR);
        NODE_TYPE_TO_ENUMERATION.put("lambda", LAMBDA);
        NODE_TYPE_TO_ENUMERATION.put("lambda_parameters", LAMBDA_PARAMETERS);
        NODE_TYPE_TO_ENUMERATION.put("list", LIST);
        NODE_TYPE_TO_ENUMERATION.put("list_comprehension", LIST_COMPREHENSION);
        NODE_TYPE_TO_ENUMERATION.put("list_pattern", LIST_PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("list_splat", LIST_SPLAT);
        NODE_TYPE_TO_ENUMERATION.put("list_splat_pattern", LIST_SPLAT_PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("match_statement", MATCH_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("module", MODULE);
        NODE_TYPE_TO_ENUMERATION.put("named_expression", NAMED_EXPRESSION);
        NODE_TYPE_TO_ENUMERATION.put("none", NONE);
        NODE_TYPE_TO_ENUMERATION.put("nonlocal_statement", NONLOCAL_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("not_operator", NOT_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("pair", PAIR);
        NODE_TYPE_TO_ENUMERATION.put("parameter", PARAMETER);
        NODE_TYPE_TO_ENUMERATION.put("parameters", PARAMETERS);
        NODE_TYPE_TO_ENUMERATION.put("parenthesized_expression", PARENTHESIZED_EXPRESSION);
        NODE_TYPE_TO_ENUMERATION.put("parenthesized_list_splat", PARENTHESIZED_LIST_SPLAT);
        NODE_TYPE_TO_ENUMERATION.put("pass_statement", PASS_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("pattern", PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("pattern_list", PATTERN_LIST);
        NODE_TYPE_TO_ENUMERATION.put("positional_separator", POSITIONAL_SEPARATOR);
        NODE_TYPE_TO_ENUMERATION.put("primary_expression", PRIMARY_EXPRESSION);
        NODE_TYPE_TO_ENUMERATION.put("print_statement", PRINT_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("raise_statement", RAISE_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("relative_import", RELATIVE_IMPORT);
        NODE_TYPE_TO_ENUMERATION.put("return_statement", RETURN_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("set", SET);
        NODE_TYPE_TO_ENUMERATION.put("set_comprehension", SET_COMPREHENSION);
        NODE_TYPE_TO_ENUMERATION.put("slice", SLICE);
        NODE_TYPE_TO_ENUMERATION.put("string", STRING);
        NODE_TYPE_TO_ENUMERATION.put("string_content", STRING_CONTENT);
        NODE_TYPE_TO_ENUMERATION.put("subscript", SUBSCRIPT);
        NODE_TYPE_TO_ENUMERATION.put("true", TRUE);
        NODE_TYPE_TO_ENUMERATION.put("try_statement", TRY_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("tuple", TUPLE);
        NODE_TYPE_TO_ENUMERATION.put("tuple_pattern", TUPLE_PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("type", TYPE);
        NODE_TYPE_TO_ENUMERATION.put("typed_default_parameter", TYPED_DEFAULT_PARAMETER);
        NODE_TYPE_TO_ENUMERATION.put("typed_parameter", TYPED_PARAMETER);
        NODE_TYPE_TO_ENUMERATION.put("type_conversion", TYPE_CONVERSION);
        NODE_TYPE_TO_ENUMERATION.put("unary_operator", UNARY_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("unknown", UNKNOWN);
        NODE_TYPE_TO_ENUMERATION.put("while_statement", WHILE_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("wildcard_import", WILDCARD_IMPORT);
        NODE_TYPE_TO_ENUMERATION.put("with_clause", WITH_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("with_item", WITH_ITEM);
        NODE_TYPE_TO_ENUMERATION.put("with_statement", WITH_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("yield", YIELD);
        NODE_TYPE_TO_ENUMERATION.put("_compound_statement", _COMPOUND_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("_simple_statement", _SIMPLE_STATEMENT);
    }

    public final String label;

    TreeSitterPythonTypes(String l) {
        this.label = l;
    }
}
