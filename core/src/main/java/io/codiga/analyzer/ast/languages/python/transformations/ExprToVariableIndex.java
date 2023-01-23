package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.VariableIndex;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isArrayOrDictReference;
import static io.codiga.analyzer.ast.languages.python.transformations.ArgumentsTransformation.transformArgumentsToSequence;
import static io.codiga.analyzer.ast.languages.python.transformations.AtomToPythonString.transformAtomToPythonString;
import static io.codiga.analyzer.ast.languages.utils.Conversions.*;

public class ExprToVariableIndex {

    private static final Logger logger = LoggerFactory.getLogger(ExprToVariableIndex.class);


    public static Optional<VariableIndex> transformExprToVariableIndex(PythonParser.ExprContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }

        if (!isArrayOrDictReference(ctx)) {
            return Optional.empty();
        }

        var lastTrailer = ctx.trailer().get(ctx.trailer().size() - 1);
        Optional<AstElement> variable = convertToAstElement(transformAtomToPythonString(ctx.atom(), root));
        Optional<AstElement> index = elementOrSequence(flattenAstElement(transformArgumentsToSequence(lastTrailer.arguments(), root)));

        if (variable.isPresent() && index.isPresent()) {
            return Optional.of(new VariableIndex(variable.orElse(null), index.orElse(null), ctx, root));
        }
        return Optional.empty();

    }
}
