package io.codiga.parser.python;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.ast.FunctionCall;
import io.codiga.model.error.AnalysisError;
import io.codiga.parser.common.ErrorReporting;
import io.codiga.parser.python.gen.PythonParser;
import io.codiga.parser.python.gen.PythonParserBaseVisitor;
import org.graalvm.polyglot.Context;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import static io.codiga.ast.AstUtils.isFunctionCall;
import static io.codiga.ast.ExprToFunctionCall.transformExprToFunctionCall;

public class CodigaVisitor extends PythonParserBaseVisitor<List<AnalysisError>> {

    public ErrorReporting errorReporting;
    private AnalyzerRule analyzerRule;

    public CodigaVisitor(AnalyzerRule rule) {
        this.errorReporting = new ErrorReporting();
        this.analyzerRule = rule;
    }


    @Override
    public List<AnalysisError> visitFuncdef(PythonParser.FuncdefContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public List<AnalysisError> visitExpr(PythonParser.ExprContext ctx) {

        if(isFunctionCall(ctx)){
            Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall(ctx);
            if (functionCallOptional.isPresent()) {
                Context context = Context
                    .newBuilder()
                    .allowAllAccess(true)
                    .logHandler(OutputStream.nullOutputStream())
                    .build();
                context.getBindings("js").putMember("root", functionCallOptional.get());
                context.getBindings("js").putMember("addError", errorReporting);
                String finalCode = " reportError = addError.addError; "  + this.analyzerRule.code() + " visit(root);";
                context.eval("js", finalCode);
            }
        }

        return visitChildren(ctx);
    }

}
