package io.codiga.constants;

import io.codiga.model.Language;

import java.util.List;
import java.util.Map;

import static io.codiga.model.Language.PYTHON;

public class Languages {

    public final static String LANGUAGE_PYTHON = "python";
    public final static String LANGUAGE_JAVA = "java";
    public final static String LANGUAGE_JAVASCRIPT = "javascript";
    public final static String RULE_TYPE_AST = "ast";
    public final static String RULE_TYPE_PATTERN = "pattern";
    public final static String ENTITY_CHECKED_FUNCTION_CALL = "functioncall";
    public final static String ENTITY_CHECKED_IF_CONDITION = "ifcondition";
    public final static String ENTITY_CHECKED_IMPORT = "import";
    public final static String ENTITY_CHECKED_ASSIGNMENT = "assign";
    public final static String ENTITY_CHECKED_FOR_LOOP = "forloop";
    public final static String ENTITY_CHECKED_FUNCTION_DEFINITION = "functiondefinition";
    public final static String ENTITY_CHECKED_CLASS_DEFINITION = "classdefinition";
    public final static String ENTITY_CHECKED_HTML_ELEMENT = "htmlelement";
    public final static String ENTITY_CHECKED_TRY_BLOCK = "tryblock";
    public final static List<String> SUPPORTED_LANGUAGES = List.of(LANGUAGE_PYTHON, LANGUAGE_JAVASCRIPT);
    public final static Map<Language, List<String>> LANGUAGE_EXTENSIONS = Map.of(
        PYTHON, List.of("py", "py3")
    );
    public List<Language> allLanguages = List.of(PYTHON);

}
