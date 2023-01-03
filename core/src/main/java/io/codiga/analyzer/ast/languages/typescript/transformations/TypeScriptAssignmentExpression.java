package io.codiga.analyzer.ast.languages.typescript.transformations;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.typescript.gen.TypeScriptParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.typescript.transformations.TypeScriptSingleExpressionTransformation.transformSingleExpressionToAstElement;

public class TypeScriptAssignmentExpression {


    /**
     * Transform
     * - "foo = bar"
     * - const [state, deleteState] = useState("");
     *
     * @param ctx
     * @param root
     * @return
     */
    public static Optional<Assignment> transformAssignmentExpressionToAssignment(TypeScriptParser.AssignmentExpressionContext ctx, ParserRuleContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.singleExpression().size() != 2) {
            return Optional.empty();
        }

        Optional<AstElement> leftSide = transformSingleExpressionToAstElement(ctx.singleExpression().get(0), root);
        Optional<AstElement> rightSide = transformSingleExpressionToAstElement(ctx.singleExpression().get(1), root);

        if (leftSide.isPresent() && rightSide.isPresent()) {
            return Optional.of(new Assignment(leftSide.get(), rightSide.get(), ctx, root));
        }

        return Optional.empty();
    }
}
