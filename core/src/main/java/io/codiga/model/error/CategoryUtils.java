package io.codiga.model.error;

import java.util.Map;

public class CategoryUtils {
    private final static Map<String, Category> STRING_TO_CATEGORY = Map.of(
        "error_prone", Category.ERROR_PRONE,
        "errorprone", Category.ERROR_PRONE,
        "security", Category.SECURITY,
        "safety", Category.SAFETY,
        "best_practices", Category.BEST_PRACTICE,
        "bestpractices", Category.BEST_PRACTICE,
        "best_practice", Category.BEST_PRACTICE,
        "code_style", Category.CODE_STYLE,
        "design", Category.DESIGN,
        "deployment", Category.DEPLOYMENT
    );
    

    public static Category categoryFromString(String categoryString) {
        return STRING_TO_CATEGORY.getOrDefault(categoryString.toLowerCase(), Category.UNKNOWN);
    }
}
