package io.codiga.analyzer.ast.languages.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.javascript.transformations.JavaScriptSourceElement.transformSourceElement;

public class JavaScriptFunctionBody {


    public static Optional<AstElement> transformFunctionBody(JavaScriptParser.FunctionBodyContext ctx, ParserRuleContext root) {

        if (ctx.sourceElements() != null) {
            List<AstElement> astElementList = new ArrayList<>();
            for (var element : ctx.sourceElements().sourceElement()) {
                var elementTransformed = transformSourceElement(element, root);
                if (elementTransformed.isPresent()) {
                    astElementList.add(elementTransformed.get());
                }
            }

            return Optional.of(new Sequence(astElementList, ctx, root).flatten());

        }
        return Optional.empty();
    }
}
