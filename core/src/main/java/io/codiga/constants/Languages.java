package io.codiga.constants;

import static io.codiga.model.Language.*;

import io.codiga.model.Language;
import java.util.List;
import java.util.Map;

public class Languages {
    public final static List<Language> SUPPORTED_LANGUAGES = List.of(PYTHON, JAVASCRIPT, TYPESCRIPT, GO, YAML, JAVA);
    public final static Map<Language, List<String>> LANGUAGE_EXTENSIONS = Map.of(
        PYTHON, List.of("py", "py3"),
        JAVASCRIPT, List.of("js", "jsx"),
        TYPESCRIPT, List.of("ts", "tsx"),
        GO, List.of("go"),
        YAML, List.of("yaml", "yml"),
        JAVA, List.of("java")
    );
}
