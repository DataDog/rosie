package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JavaScriptFunctionBody {


    public static Optional<AstElement> transformFunctionBody(JavaScriptParser.FunctionBodyContext ctx, ParserRuleContext root) {
        Optional<AstElement> elementTransformed = Optional.empty();
        if (ctx.sourceElements() != null) {
            List<AstElement> astElementList = new ArrayList<>();
            for (var element : ctx.sourceElements().sourceElement()) {
                elementTransformed = JavaScriptSourceElement.transformSourceElement(element, root);
                if (elementTransformed.isPresent()) {
                    astElementList.add(elementTransformed.get());
                }
            }

            return Optional.of(new Sequence(astElementList, ctx, root).flatten());

        }
        return Optional.empty();
    }
}
