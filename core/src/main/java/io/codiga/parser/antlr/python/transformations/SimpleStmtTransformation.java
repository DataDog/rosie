package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.python.transformations.SmallStmtToAstElement.transformSmallStatementToAstElement;

public class SimpleStmtTransformation {

    private static final Logger logger = LoggerFactory.getLogger(SimpleStmtTransformation.class);


    public static Optional<? extends AstElement> transformSimpleStmtToAstElement(PythonParser.Simple_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx.small_stmt() == null || ctx.small_stmt().size() == 0) {
            return Optional.empty();
        }
        List<AstElement> astElementList = new ArrayList<>();


        if (ctx.small_stmt().size() == 1) {
            return transformSmallStatementToAstElement(ctx.small_stmt().get(0), root);
        }

        for (PythonParser.Small_stmtContext small_stmtContext : ctx.small_stmt()) {
            var astElementOptional = transformSmallStatementToAstElement(small_stmtContext, root);
            astElementOptional.ifPresent(astElementList::add);
        }


        return Optional.of(new Sequence(astElementList, ctx, root));
    }
}
