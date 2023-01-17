package io.codiga.analyzer.ast.languages.utils;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;

import java.util.Optional;

public class Conversions {


    public static Optional<AstElement> convertToAstElement(Optional<? extends AstElement> el) {
        if (el.isPresent()) {
            AstElement astElement = el.get();
            return Optional.of(astElement);
        }
        return Optional.empty();
    }

    public static Optional<AstElement> flattenAstElement(Optional<? extends AstElement> el) {
        if (el.isPresent()) {
            AstElement astElement = el.get();
            if (astElement instanceof Sequence) {
                Sequence sequence = (Sequence) astElement;
                return Optional.of(sequence.flatten());
            }
            return Optional.of(astElement);
        }
        return Optional.empty();
    }
}
