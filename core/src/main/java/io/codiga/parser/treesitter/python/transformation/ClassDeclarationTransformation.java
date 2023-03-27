package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.PythonClassDefinition;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.transformation.BlockTransformation.transformBlock;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstString;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.CLASS_DEFINITION;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.IDENTIFIER;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ClassDeclarationTransformation {

    private static final Logger LOGGER = Logger.getLogger(ClassDeclarationTransformation.class.getName());

    public static Optional<PythonClassDefinition> transformClassDefinition(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != CLASS_DEFINITION || node.getChildCount() < 3) {
            return Optional.empty();
        }

        var nameOptional = Optional.ofNullable(node.getChildByFieldName("name"))
            .flatMap(n -> transformIdentifierToAstString(n, parsingContext));
        var superClasses = Optional.ofNullable(node.getChildByFieldName("superclasses"))
            .map(n -> getNodeChildren(n, IDENTIFIER)
                .stream()
                .map(c -> transformIdentifierToAstString(c, parsingContext))
                .filter(v -> v.isPresent())
                .map(v -> v.get())
                .collect(Collectors.toList())
            ).orElse(List.of());
        var contentOptional = Optional.ofNullable(node.getChildByFieldName("body"))
            .flatMap(n -> transformBlock(n, parsingContext));

        return Optional.of(
            new PythonClassDefinition(
                List.of(),
                nameOptional.orElse(null), // name
                superClasses, // parent class
                contentOptional.orElse(null), // content
                parsingContext.getParserContextForNode(node)
            ));
    }


}
