package io.codiga.ast.python;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.ast.common.ErrorReporting;
import io.codiga.model.RuleType;
import io.codiga.model.ast.FunctionCall;
import io.codiga.model.error.Violation;
import io.codiga.parser.python.gen.PythonParser;
import io.codiga.parser.python.gen.PythonParserBaseVisitor;
import org.graalvm.polyglot.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static io.codiga.ast.python.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.ast.python.PythonAstUtils.isFunctionCall;
import static io.codiga.ast.vm.VmUtils.createContext;

public class CodigaVisitor extends PythonParserBaseVisitor<List<Violation>> {

    public ErrorReporting errorReporting;
    private AnalyzerRule analyzerRule;
    private PythonParser.RootContext root;
    private Logger logger = LoggerFactory.getLogger(CodigaVisitor.class);

    public CodigaVisitor(AnalyzerRule rule) {
        this.errorReporting = new ErrorReporting();
        this.analyzerRule = rule;
        this.root = null;
    }

    @Override
    public List<Violation> visitRoot(PythonParser.RootContext ctx) {
        this.root = ctx;
        return visitChildren(ctx);
    }


    @Override
    public List<Violation> visitFuncdef(PythonParser.FuncdefContext ctx) {

        return visitChildren(ctx);
    }

    @Override
    public List<Violation> visitExpr(PythonParser.ExprContext ctx) {
        if (analyzerRule.ruleType() == RuleType.FUNCTION_CALL && isFunctionCall(ctx)) {
            Optional<FunctionCall> functionCallOptional = transformExprToFunctionCall(ctx, this.root);
            if (functionCallOptional.isPresent()) {
                Context context = createContext(functionCallOptional.get(), errorReporting);
                String finalCode = " reportError = addError.addError; " + this.analyzerRule.code() + " visit(root);";
                context.eval("js", finalCode);
            }
        }

        return visitChildren(ctx);
    }

}
