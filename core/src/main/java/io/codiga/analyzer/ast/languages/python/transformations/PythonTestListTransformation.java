package io.codiga.analyzer.ast.languages.python.transformations;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.Sequence;
import io.codiga.parser.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.codiga.analyzer.ast.languages.python.transformations.PythonTestTransformation.transformTestToAstElement;

public class PythonTestListTransformation {

    private static final Logger logger = LoggerFactory.getLogger(PythonTestListTransformation.class);


    public static Optional<? extends AstElement> transformTestlistToAstElement(PythonParser.TestlistContext ctx, PythonParser.RootContext root) {
        if (ctx == null) {
            return Optional.empty();
        }
        if (ctx.test() != null) {
            if (ctx.test().size() == 1) {
                return transformTestToAstElement(ctx.test().get(0), root);
            }
            if (ctx.test().size() > 1) {
                List<AstElement> astElementList = new ArrayList<>();
                for (PythonParser.TestContext testContext : ctx.test()) {
                    var opt = transformTestToAstElement(testContext, root);
                    if (opt.isPresent()) {
                        astElementList.add(opt.get());
                    }
                }

                return Optional.of(new Sequence(astElementList, ctx, root));
            }
        }

        return Optional.empty();
    }

}
