package io.codiga.server.constants;

import java.util.List;

public class Languages {

    public final static String LANGUAGE_PYTHON = "python";
    public final static String LANGUAGE_JAVA = "java";

    public final static String RULE_TYPE_AST = "ast";
    public final static String RULE_TYPE_PATTERN = "pattern";
    public final static String ENTITY_CHECKED_FUNCTION_CALL = "functioncall";
    public final static String ENTITY_CHECKED_IF_CONDITION = "ifcondition";
    public final static String ENTITY_CHECKED_FOR_LOOP = "forloop";
    public final static String ENTITY_CHECKED_FUNCTION_DEFINITION = "functiondefinition";
    public final static String ENTITY_CHECKED_TRY_BLOCK = "tryblock";

    public final static List<String> SUPPORTED_LANGUAGES = List.of(LANGUAGE_PYTHON);

}
