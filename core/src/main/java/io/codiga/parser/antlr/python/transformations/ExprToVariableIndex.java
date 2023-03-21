package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.VariableIndex;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.codiga.analyzer.ast.languages.python.PythonAstUtils.isArrayOrDictReference;
import static io.codiga.parser.antlr.python.transformations.ArgumentsTransformation.transformArgumentsToSequence;
import static io.codiga.parser.antlr.python.transformations.AtomTransformation.transformAtomToPythonString;
import static io.codiga.utils.Conversions.*;

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


        // there is a member
        if (lastTrailer.name() != null) {
            List<String> allNames = new ArrayList<>();
            allNames.add(ctx.atom().getText());
            allNames.addAll(ctx.trailer().stream().filter(c -> c.name() != null).map(c -> c.name().getText()).collect(Collectors.toList()));
            String name = String.join(".", allNames);
            variable = convertToAstElement(Optional.of(new AstString(name, ctx.atom(), lastTrailer.name(), ctx, root)));
        } else {
            variable = convertToAstElement(transformAtomToPythonString(ctx.atom(), root));
        }

        if (variable.isPresent() && index.isPresent()) {
            return Optional.of(new VariableIndex(variable.orElse(null), index.orElse(null), ctx, root));
        }
        return Optional.empty();

    }
}
