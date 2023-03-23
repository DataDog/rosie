package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.FromElement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Optional;
import java.util.logging.Logger;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.*;

public class AliasedImport {

    private static final Logger LOGGER = Logger.getLogger(AliasedImport.class.getName());

    public static Optional<FromElement> transformAliasedImport(Node node, TreeSitterParsingContext parsingContext) {
        getNodeChildren(node).forEach(v -> System.out.println(v.getType()));
        if (node == null || getNodeType(node) != TreeSitterPythonTypes.ALIASED_IMPORT) {
            return Optional.empty();
        }

        Optional<Node> nameNodeOptional = getNodeChild(node, TreeSitterPythonTypes.DOTTED_NAME);
        Optional<Node> identifierNodeOptional = getNodeChild(node, TreeSitterPythonTypes.IDENTIFIER);


        if (nameNodeOptional.isPresent() && identifierNodeOptional.isPresent()) {
            var nameOptional = transformIdentifierToAstStringWithoutCheck(nameNodeOptional.get(), parsingContext);
            var asOptional = transformIdentifierToAstStringWithoutCheck(identifierNodeOptional.get(), parsingContext);


            if (nameOptional.isPresent() && asOptional.isPresent()) {
                return Optional.of(new FromElement(nameOptional.get(), asOptional.get(), parsingContext.getParserContextForNode(node)));
            }
        }

        return Optional.empty();
    }


}
