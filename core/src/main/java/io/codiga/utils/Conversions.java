package io.codiga.utils;

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

    public static Optional<AstElement> elementOrSequence(Optional<? extends AstElement> el) {
        if (el.isPresent()) {
            AstElement astElement = el.get();
            if (astElement instanceof Sequence) {
                Sequence sequence = (Sequence) astElement;
                if (sequence.elements.length == 1) {
                    return Optional.of(sequence.elements[0]);
                }
            }
            return Optional.of(astElement);
        }
        return Optional.empty();
    }
}
