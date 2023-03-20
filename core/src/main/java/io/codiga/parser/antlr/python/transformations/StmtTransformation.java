package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.parser.antlr.python.transformations.CompoundTransformation.transformCompoundStatement;
import static io.codiga.parser.antlr.python.transformations.SimpleStmtTransformation.transformSimpleStmtToAstElement;

public class StmtTransformation {

    private static final Logger logger = LoggerFactory.getLogger(StmtTransformation.class);


    public static Optional<? extends AstElement> transformStmtToAstElement(PythonParser.StmtContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (ctx.simple_stmt() != null) {
            return transformSimpleStmtToAstElement(ctx.simple_stmt(), root);
        }

        if (ctx.compound_stmt() != null) {
            return transformCompoundStatement(ctx.compound_stmt(), root);
        }
        return Optional.empty();
    }
}
