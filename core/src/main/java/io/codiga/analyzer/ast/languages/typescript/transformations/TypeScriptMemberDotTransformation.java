package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.javascript.JavaScriptMember;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptIdentifierNameTransformation.transformIdentifierNameToAstString;
import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;


public class TypeScriptMemberDotTransformation {


    public static Optional<AstElement> transformMemberDotToJavaScriptMember(TypeScriptParser.MemberDotExpressionContext memberDotExpressionContext, ParserRuleContext root) {
        if (memberDotExpressionContext != null &&
            memberDotExpressionContext.identifierName() != null &&
            memberDotExpressionContext.singleExpression() != null) {
            Optional<AstString> member = transformIdentifierNameToAstString(memberDotExpressionContext.identifierName(), root);
            Optional<AstElement> parent = transformSingleExpressionToAstElement(memberDotExpressionContext.singleExpression(), root);
            return Optional.of(new JavaScriptMember(member.orElse(null), parent.orElse(null), memberDotExpressionContext, root));
        }
        return Optional.empty();

    }

}
