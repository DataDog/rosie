package io.codiga.analyzer.ast.languages;

import io.codiga.model.ast.common.AstElement;

import java.util.Optional;

public class Conversions {

    public static Optional<AstElement> convertToAstElement(Optional<? extends AstElement> el) {
        if (el.isPresent()) {
            AstElement astElement = (AstElement) el.get();
            return Optional.of(astElement);
        }
        return Optional.empty();
    }
}
