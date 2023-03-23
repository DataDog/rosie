package io.codiga.parser.antlr.typescript.transformations;

import io.codiga.model.ast.common.*;
import io.codiga.parser.antlr.typescript.CodigaVisitor;
import io.codiga.parser.antlr.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.typescript.transformations.TypeScriptIdentifierOrKeywordTransformation.transformIdentifierNameToAstString;
import static io.codiga.parser.antlr.typescript.transformations.TypeScriptParenthesizedExpression.typescriptParenthesizedExpresssionToFunctionCallArguments;
import static io.codiga.utils.Conversions.convertToAstElement;

public class TypeScriptVariableStatement {
    private final Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);


    public static List<VariableDeclaration> transformVariableStatementToVariableDeclaration(TypeScriptParser.VariableStatementContext ctx, ParserRuleContext root) {
        List<VariableDeclaration> result = new ArrayList<>();
        Optional<AstString> modifierOptional = Optional.empty();


        if (ctx.varModifier() != null) {
            modifierOptional = Optional.of(new AstString(ctx.varModifier().getText(), ctx.varModifier(), root));
        }

        if (ctx.variableDeclarationList() != null) {
            TypeScriptParser.VariableDeclarationListContext variableDeclarationListContext = ctx.variableDeclarationList();
            if (variableDeclarationListContext.variableDeclaration() != null) {
                for (TypeScriptParser.VariableDeclarationContext variableDeclarationContext : variableDeclarationListContext.variableDeclaration()) {
                    Optional<VariableDeclaration> variableDeclarationOptional = TypeScriptVariableDeclaration.transformVariableDeclarationToVariableDeclaration(modifierOptional.orElse(null), variableDeclarationContext, root);
                    if (variableDeclarationOptional.isPresent()) {
                        result.add(variableDeclarationOptional.get());
                    }
                }
            }
        }
        return result;
    }

    public static Optional<Sequence> transformVariableStatementToSequence(TypeScriptParser.VariableStatementContext ctx, ParserRuleContext root) {
        List<AstElement> result = new ArrayList<>();
        Optional<AstString> modifierOptional = Optional.empty();

        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.varModifier() != null) {
            modifierOptional = Optional.of(new AstString(ctx.varModifier().getText(), ctx.varModifier(), root));
        }

        if (ctx.variableDeclarationList() != null) {
            TypeScriptParser.VariableDeclarationListContext variableDeclarationListContext = ctx.variableDeclarationList();
            if (variableDeclarationListContext.variableDeclaration() != null) {
                for (TypeScriptParser.VariableDeclarationContext variableDeclarationContext : variableDeclarationListContext.variableDeclaration()) {


                    // variable
                    if (modifierOptional.isPresent()) {
                        Optional<VariableDeclaration> variableDeclarationOptional = TypeScriptVariableDeclaration.transformVariableDeclarationToVariableDeclaration(modifierOptional.orElse(null), variableDeclarationContext, root);
                        if (variableDeclarationOptional.isPresent()) {
                            result.add(variableDeclarationOptional.get());
                        }
                    }

                    // function call
                    if (variableDeclarationContext.identifierOrKeyWord() != null && modifierOptional.isEmpty() && variableDeclarationContext.singleExpression() != null && variableDeclarationContext.singleExpression().size() > 0 && variableDeclarationContext.singleExpression().get(0) instanceof TypeScriptParser.ParenthesizedExpressionContext parenthesizedExpressionContext) {
                        var parameters = typescriptParenthesizedExpresssionToFunctionCallArguments(parenthesizedExpressionContext, root);
                        if (parameters.isPresent()) {
                            result.add(new FunctionCall(convertToAstElement(transformIdentifierNameToAstString(variableDeclarationContext.identifierOrKeyWord(), root)).orElse(null), parameters.get(), variableDeclarationContext, root));
                        }
                    }

                }
            }


        }
        return Optional.of(new Sequence(result, ctx, root));
    }
}
