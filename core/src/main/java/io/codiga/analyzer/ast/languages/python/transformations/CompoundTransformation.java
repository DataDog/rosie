package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.transformations.ClassOrFuncDefTransformation.transformClassOrFuncDefToAstElement;
import static io.codiga.analyzer.ast.languages.python.transformations.ForStmtToForStatement.transformForStatement;
import static io.codiga.analyzer.ast.languages.python.transformations.IfStmtToIfStatement.transformIfStatement;
import static io.codiga.analyzer.ast.languages.python.transformations.TryStmtToTryStatement.transformStmtToTryStatement;
import static io.codiga.analyzer.ast.languages.utils.Conversions.convertToAstElement;

public class CompoundTransformation {

    private static final Logger logger = LoggerFactory.getLogger(CompoundTransformation.class);


    public static Optional<AstElement> transformCompoundStatement(PythonParser.Compound_stmtContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }


        if (ctx instanceof PythonParser.If_stmtContext if_stmtContext) {
            return convertToAstElement(transformIfStatement(if_stmtContext, root));
        }

        if (ctx instanceof PythonParser.While_stmtContext) {
            return Optional.empty();
        }

        if (ctx instanceof PythonParser.For_stmtContext for_stmtContext) {
            return convertToAstElement(transformForStatement(for_stmtContext, root));
        }

        if (ctx instanceof PythonParser.Try_stmtContext try_stmtContext) {
            return convertToAstElement(transformStmtToTryStatement(try_stmtContext, root));

        }

        if (ctx instanceof PythonParser.Class_or_func_def_stmtContext class_or_func_def_stmtContext) {
            return transformClassOrFuncDefToAstElement(class_or_func_def_stmtContext, root);
        }

        return Optional.empty();
    }
}
