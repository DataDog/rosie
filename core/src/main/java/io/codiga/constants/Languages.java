package io.codiga.constants;

import io.codiga.model.Language;

import java.util.List;
import java.util.Map;

import static io.codiga.model.Language.*;

public class Languages {
    public final static List<Language> SUPPORTED_LANGUAGES = List.of(PYTHON, JAVASCRIPT, TYPESCRIPT);
    public final static Map<Language, List<String>> LANGUAGE_EXTENSIONS = Map.of(
        PYTHON, List.of("py", "py3"),
        JAVASCRIPT, List.of("js", "jsx"),
        TYPESCRIPT, List.of("ts", "tsx")
    );
}
