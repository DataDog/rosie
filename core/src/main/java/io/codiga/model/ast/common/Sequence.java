package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Sequence extends AstElement {
    public AstElement[] elements;


    public Sequence(List<AstElement> elementsList, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_SEQUENCE, parserRuleContext, root);
        this.elements = new AstElement[elementsList.size()];
        this.elements = elementsList.toArray(elements);
    }

    public Sequence(List<AstElement> elementsList, ParserContext context) {
        super(AST_ELEMENT_TYPE_SEQUENCE, context);
        this.elements = new AstElement[elementsList.size()];
        this.elements = elementsList.toArray(elements);
    }

    public static Optional<AstElement> flattenOptionalAstElement(Optional<AstElement> potentialAstSequence) {
        if (potentialAstSequence.isPresent()) {
            var astElement = potentialAstSequence.get();
            if (astElement instanceof Sequence) {
                var seq = (Sequence) astElement;
                return Optional.of(seq.flatten());
            }
        }
        return Optional.empty();
    }

    public Sequence flatten() {
        List<AstElement> elementList = new ArrayList<>();


        for (AstElement astElement : elements) {
            if (astElement instanceof Sequence subSequence) {
                elementList.addAll(Arrays.stream(subSequence.flatten().elements).toList());
            } else {
                elementList.add(astElement);
            }
        }
        return new Sequence(elementList, this.parserRuleContext, this.root);
    }
}
