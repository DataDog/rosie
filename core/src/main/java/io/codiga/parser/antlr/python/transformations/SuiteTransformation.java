package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.parser.antlr.python.transformations.SimpleStmtTransformation.transformSimpleStmtToAstElement;
import static io.codiga.parser.antlr.python.transformations.StmtTransformation.transformStmtToAstElement;

public class SuiteTransformation {

    private static final Logger logger = LoggerFactory.getLogger(SuiteTransformation.class);


    public static Optional<? extends AstElement> transformSuiteToAstElement(PythonParser.SuiteContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.simple_stmt() != null) {
            return transformSimpleStmtToAstElement(ctx.simple_stmt(), root);
        }
        if (ctx.stmt() != null) {
            List<AstElement> astElementList = new ArrayList<>();
            for (PythonParser.StmtContext stmt : ctx.stmt()) {
                var stmtOpt = transformStmtToAstElement(stmt, root);
                stmtOpt.ifPresent(astElementList::add);
            }
            return Optional.of(new Sequence(astElementList, ctx, root));
        }
        return Optional.empty();
    }
}
