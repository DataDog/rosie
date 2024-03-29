package io.codiga.parser.antlr.python.transformations;

import io.codiga.model.ast.python.PythonString;
import io.codiga.parser.antlr.python.gen.PythonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class AtomTransformation {

    private static final Logger logger = LoggerFactory.getLogger(AtomTransformation.class);

    public static Optional<PythonString> transformAtomToPythonString(PythonParser.AtomContext atomContext, PythonParser.RootContext root) {
        if (atomContext.number() != null) {

            return Optional.of(new PythonString(atomContext.number().getText(), atomContext.number(), root));
        }
        if (atomContext.name() != null) {
            return Optional.of(new PythonString(atomContext.name().getText(), atomContext.name(), root));
        }
        if (atomContext.STRING() != null && atomContext.STRING().size() == 1) {
            return Optional.of(new PythonString(atomContext.STRING().get(0).getText(), atomContext, root));
        }
        return Optional.empty();
    }


}
