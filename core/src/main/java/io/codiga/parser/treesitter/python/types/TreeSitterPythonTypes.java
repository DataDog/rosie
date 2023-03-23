package io.codiga.parser.treesitter.python.types;

import java.util.HashMap;
import java.util.Map;

public enum TreeSitterPythonTypes {

    ALIASED_IMPORT("aliased_import"),
    ARGUMENT_LIST("argument_list"),
    ASSIGNMENT("assignment"),
    ATTRIBUTE("attribute"),
    BLOCK("block"),
    CALL("call"),
    DOTTED_NAME("dotted_name"),
    EXPRESSION_LIST("expression_list"),
    EXCEPT_CLAUSE("except_clause"),
    FALSE("false"),
    IDENTIFIER("identifier"),
    IMPORT_FROM_STATEMENT("import_from_statement"),
    IMPORT_STATEMENT("import_statement"),
    INTEGER("integer"),
    KEYWORD_ARGUMENT("keyword_argument"),
    PATTERN_LIST("pattern_list"),
    PASS_STATEMENT("pass_statement"),
    STRING("string"),
    SUBSCRIPT("subscript"),
    TRUE("true"),
    TRY_STATEMENT("try_statement"),
    TUPLE("tuple"),
    UNKNOWN("unknown");

    public static Map<String, TreeSitterPythonTypes> NODE_TYPE_TO_ENUMERATION = new HashMap<>();

    static {
        NODE_TYPE_TO_ENUMERATION.put("aliased_import", ALIASED_IMPORT);
        NODE_TYPE_TO_ENUMERATION.put("argument_list", ARGUMENT_LIST);
        NODE_TYPE_TO_ENUMERATION.put("assignment", ASSIGNMENT);
        NODE_TYPE_TO_ENUMERATION.put("attribute", ATTRIBUTE);
        NODE_TYPE_TO_ENUMERATION.put("block", BLOCK);
        NODE_TYPE_TO_ENUMERATION.put("call", CALL);
        NODE_TYPE_TO_ENUMERATION.put("dotted_name", DOTTED_NAME);
        NODE_TYPE_TO_ENUMERATION.put("expression_list", EXPRESSION_LIST);
        NODE_TYPE_TO_ENUMERATION.put("except_clause", EXCEPT_CLAUSE);
        NODE_TYPE_TO_ENUMERATION.put("false", FALSE);
        NODE_TYPE_TO_ENUMERATION.put("identifier", IDENTIFIER);
        NODE_TYPE_TO_ENUMERATION.put("import_from_statement", IMPORT_FROM_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("import_statement", IMPORT_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("integer", INTEGER);
        NODE_TYPE_TO_ENUMERATION.put("keyword_argument", KEYWORD_ARGUMENT);
        NODE_TYPE_TO_ENUMERATION.put("pattern_list", PATTERN_LIST);
        NODE_TYPE_TO_ENUMERATION.put("pass_statement", PASS_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("string", TRUE);
        NODE_TYPE_TO_ENUMERATION.put("subscript", SUBSCRIPT);
        NODE_TYPE_TO_ENUMERATION.put("true", TRUE);
        NODE_TYPE_TO_ENUMERATION.put("try_statement", TRY_STATEMENT);
        NODE_TYPE_TO_ENUMERATION.put("tuple", TUPLE);
        NODE_TYPE_TO_ENUMERATION.put("unknown", UNKNOWN);
    }

    public final String label;

    private TreeSitterPythonTypes(String l) {
        this.label = l;
    }
}
