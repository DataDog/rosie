package io.codiga.parser.antlr.javascript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.ClassDeclarationOneParent;
import io.codiga.parser.antlr.javascript.gen.JavaScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.parser.antlr.javascript.transformations.JavaScriptIdentifierToAstElement.transformIdentifierToAstString;
import static io.codiga.parser.antlr.javascript.transformations.JavaScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class JavaScriptClassDeclarationToClass {


    public static Optional<ClassDeclarationOneParent> transformClassDeclaration(JavaScriptParser.ClassDeclarationContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        Optional<AstString> ext = Optional.empty();
        Optional<AstString> identifier = transformIdentifierToAstString(ctx.identifier(), root);
        if (ctx.classTail() != null && ctx.classTail().singleExpression() != null) {
            Optional<AstElement> extclass = transformSingleExpressionToAstElement(ctx.classTail().singleExpression(), root);

            if (extclass.isPresent()) {
                if (extclass.get() instanceof AstString) {
                    ext = Optional.of((AstString) extclass.get());
                }
            }
        }

        return Optional.of(new ClassDeclarationOneParent(identifier.orElse(null), ext.orElse(null), ctx, root));
    }
}
