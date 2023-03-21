package io.codiga.parser.treesitter.python.types;

import java.util.HashMap;
import java.util.Map;

public enum TreeSitterPythonTypes {
    ARGUMENT_LIST("argument_list"),
    ATTRIBUTE("attribute"),
    CALL("call"),
    FALSE("false"),
    IDENTIFIER("identifier"),
    INTEGER("integer"),
    KEYWORD_ARGUMENT("keyword_argument"),
    STRING("string"),
    TRUE("true"),
    UNKNOWN("unknown");

    public static Map<String, TreeSitterPythonTypes> NODE_TYPE_TO_ENUMERATION = new HashMap<>();

    static {
        NODE_TYPE_TO_ENUMERATION.put("argument_list", ARGUMENT_LIST);
        NODE_TYPE_TO_ENUMERATION.put("attribute", ATTRIBUTE);
        NODE_TYPE_TO_ENUMERATION.put("call", CALL);
        NODE_TYPE_TO_ENUMERATION.put("false", FALSE);
        NODE_TYPE_TO_ENUMERATION.put("identifier", IDENTIFIER);
        NODE_TYPE_TO_ENUMERATION.put("integer", INTEGER);
        NODE_TYPE_TO_ENUMERATION.put("keyword_argument", KEYWORD_ARGUMENT);
        NODE_TYPE_TO_ENUMERATION.put("string", TRUE);
        NODE_TYPE_TO_ENUMERATION.put("true", TRUE);
        NODE_TYPE_TO_ENUMERATION.put("unknown", UNKNOWN);
    }

    public final String label;

    private TreeSitterPythonTypes(String l) {
        this.label = l;
    }
}
