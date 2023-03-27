package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.FromElement;
import io.codiga.model.ast.python.FromStatement;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.transformation.AliasedImport.transformAliasedImport;
import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.IMPORT_FROM_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeChildren;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class ImportFromStatement {

    private static final Logger LOGGER = Logger.getLogger(ImportFromStatement.class.getName());

    /**
     * Transform the following code construct
     * `from foo import bar1, bar2`
     *
     * @param node
     * @param parsingContext
     * @return
     */
    public static Optional<FromStatement> transformImportFromStatement(Node node, TreeSitterParsingContext parsingContext) {
        // Basic checks - it's an import and has enough nodes.
        if (node == null || getNodeType(node) != IMPORT_FROM_STATEMENT || node.getChildCount() < 2) {
            return Optional.empty();
        }

        // Get the dotted name inside
        List<Node> dottedNames = getNodeChildren(node).stream()
            .filter(v -> getNodeType(v) == TreeSitterPythonTypes.DOTTED_NAME || getNodeType(v) == TreeSitterPythonTypes.ALIASED_IMPORT)
            .collect(Collectors.toList());

        if (dottedNames.size() < 2) {
            return Optional.empty();
        }

        Node packageName = dottedNames.get(0);
        List<Node> elements = dottedNames.subList(1, dottedNames.size());


        var packageString = transformIdentifierToAstStringWithoutCheck(packageName, parsingContext);
        var elementList = elements
            .stream()
            .map(v -> transformFromElement(v, parsingContext))
            .filter(v -> v.isPresent())
            .map(v -> v.get())
            .collect(Collectors.toList());


        if (packageString.isPresent()) {
            return Optional.of(new FromStatement(packageString.get(), elementList, parsingContext.getParserContextForNode(node)));
        }


        return Optional.empty();
    }

    /**
     * Transform the from elements coming after the import part
     *
     * @param node
     * @param parsingContext
     * @return
     */
    private static Optional<FromElement> transformFromElement(Node node, TreeSitterParsingContext parsingContext) {
        if (getNodeType(node) == TreeSitterPythonTypes.DOTTED_NAME) {
            var str = transformIdentifierToAstStringWithoutCheck(node, parsingContext);
            if (str.isPresent()) {
                return Optional.of(new FromElement(str.get(), null, parsingContext.getParserContextForNode(node)));
            }

        }

        if (getNodeType(node) == TreeSitterPythonTypes.ALIASED_IMPORT) {
            return transformAliasedImport(node, parsingContext);
        }

        return Optional.empty();
    }


}
