package io.codiga.parser.treesitter.python.types;

import java.util.HashMap;
import java.util.Map;

public enum TreeSitterPythonTypes {

    ALIASED_IMPORT("aliased_import"),
    ARGUMENT_LIST("argument_list"),
    AS_PATTERN("as_pattern"),
    AS_PATTERN_TARGET("as_pattern_target"),
    ASSERT("assert_statement"),
    ASSIGNMENT("assignment"),
    ATTRIBUTE("attribute"),
    BINARY_OPERATOR("binary_operator"),
    BLOCK("block"),
    BREAK_STATEMENT("break_statement"),
    CALL("call"),
    CLASS_DEFINITION("class_definition"),
    COMPARISON_OPERATOR("comparison_operator"),
    CONTINUE_STATEMENT("continue_statement"),
    DEFAULT_PARAMETER("default_parameter"),
    DOTTED_NAME("dotted_name"),
    DECORATED_DEFINITION("decorated_definition"),
    DECORATOR("decorator"),
    DICTIONARY("dictionary"),
    ELIF_CLAUSE("elif_clause"),
    ELSE_CLAUSE("else_clause"),
    EXPRESSION_LIST("expression_list"),
    EXPRESSION_STATEMENT("expression_statement"),
    EXCEPT_CLAUSE("except_clause"),
    FALSE("false"),
    FINALLY_CLAUSE("finally_clause"),
    FOR_STATEMENT("for_statement"),
    FUNCTION_DEFINITION("function_definition"),
    IDENTIFIER("identifier"),
    IF_STATEMENT("if_statement"),
    IMPORT_FROM_STATEMENT("import_from_statement"),
    IMPORT_STATEMENT("import_statement"),
    INTEGER("integer"),
    KEYWORD_ARGUMENT("keyword_argument"),
    LIST("list"),
    NONE("none"),
    NOT_OPERATOR("not_operator"),
    PAIR("pair"),
    PATTERN_LIST("pattern_list"),
    PARAMETERS("parameters"),
    PASS_STATEMENT("pass_statement"),
    RAISE_STATEMENT("raise_statement"),
    RETURN_STATEMENT("return_statement"),
    STRING("string"),
    SUBSCRIPT("subscript"),
    TRUE("true"),
    TRY_STATEMENT("try_statement"),
    TUPLE("tuple"),
    TYPE("type"),
    TYPED_PARAMETER("typed_parameter"),
    TYPED_DEFAULT_PARAMETER("typed_default_parameter"),
    UNKNOWN("unknown"),
    WHILE_STATEMENT("while_statement");

    public static Map<String, TreeSitterPythonTypes> NODE_TYPE_TO_ENUMERATION = new HashMap<>();

    static {
        NODE_TYPE_TO_ENUMERATION.put("aliased_import", ALIASED_IMPORT);
        NODE_TYPE_TO_ENUMERATION.put("argument_list", ARGUMENT_LIST);
        NODE_TYPE_TO_ENUMERATION.put("as_pattern", AS_PATTERN);
        NODE_TYPE_TO_ENUMERATION.put("as_pattern_target", AS_PATTERN_TARGET);
        NODE_TYPE_TO_ENUMERATION.put("assert_statement", ASSERT);
        NODE_TYPE_TO_ENUMERATION.put("assignment", ASSIGNMENT);
        NODE_TYPE_TO_ENUMERATION.put("attribute", ATTRIBUTE);
        NODE_TYPE_TO_ENUMERATION.put("binary_operator", BINARY_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("block", BLOCK);
        NODE_TYPE_TO_ENUMERATION.put("break_statement", BREAK_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("call", CALL);
        NODE_TYPE_TO_ENUMERATION.put("class_definition", CLASS_DEFINITION);
        NODE_TYPE_TO_ENUMERATION.put("comparison_operator", COMPARISON_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("continue_statement", CONTINUE_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("decorated_definition", DECORATED_DEFINITION);
        NODE_TYPE_TO_ENUMERATION.put("decorator", DECORATOR);
        NODE_TYPE_TO_ENUMERATION.put("default_parameter", DEFAULT_PARAMETER);
        NODE_TYPE_TO_ENUMERATION.put("dictionary", DICTIONARY);
        NODE_TYPE_TO_ENUMERATION.put("dotted_name", DOTTED_NAME);
        NODE_TYPE_TO_ENUMERATION.put("elif_clause", ELIF_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("else_clause", ELSE_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("expression_list", EXPRESSION_LIST);
        NODE_TYPE_TO_ENUMERATION.put("expression_statement", EXPRESSION_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("except_clause", EXCEPT_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("false", FALSE);
        NODE_TYPE_TO_ENUMERATION.put("for_statement", FOR_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("function_definition", FUNCTION_DEFINITION);
        NODE_TYPE_TO_ENUMERATION.put("finally_clause", FINALLY_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("identifier", IDENTIFIER);
        NODE_TYPE_TO_ENUMERATION.put("if_statement", IF_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("import_from_statement", IMPORT_FROM_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("import_statement", IMPORT_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("integer", INTEGER);
        NODE_TYPE_TO_ENUMERATION.put("keyword_argument", KEYWORD_ARGUMENT);
        NODE_TYPE_TO_ENUMERATION.put("list", LIST);
        NODE_TYPE_TO_ENUMERATION.put("none", NONE);
        NODE_TYPE_TO_ENUMERATION.put("not_operator", NOT_OPERATOR);
        NODE_TYPE_TO_ENUMERATION.put("pair", PAIR);
        NODE_TYPE_TO_ENUMERATION.put("pattern_list", PATTERN_LIST);
        NODE_TYPE_TO_ENUMERATION.put("parameters", PARAMETERS);
        NODE_TYPE_TO_ENUMERATION.put("pass_statement", PASS_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("raise_statement", RAISE_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("return_statement", RETURN_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("string", STRING);
        NODE_TYPE_TO_ENUMERATION.put("subscript", SUBSCRIPT);
        NODE_TYPE_TO_ENUMERATION.put("true", TRUE);
        NODE_TYPE_TO_ENUMERATION.put("try_statement", TRY_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("tuple", TUPLE);
        NODE_TYPE_TO_ENUMERATION.put("type", TYPE);
        NODE_TYPE_TO_ENUMERATION.put("typed_parameter", TYPED_PARAMETER);
        NODE_TYPE_TO_ENUMERATION.put("typed_default_parameter", TYPED_DEFAULT_PARAMETER);
        NODE_TYPE_TO_ENUMERATION.put("unknown", UNKNOWN);
        NODE_TYPE_TO_ENUMERATION.put("while_statement", WHILE_STATEMENT);
    }

    public final String label;

    TreeSitterPythonTypes(String l) {
        this.label = l;
    }
}
