package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.python.ImportStatementPackage;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.transformation.Identifier.transformIdentifierToAstStringWithoutCheck;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.IMPORT_STATEMENT;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.*;

public class ImportStatement {

    private static final Logger LOGGER = Logger.getLogger(ImportStatement.class.getName());

    /**
     * Transform the following code construct
     * `from foo import bar1, bar2`
     *
     * @param node
     * @param parsingContext
     * @return
     */
    public static Optional<io.codiga.model.ast.python.ImportStatement> transformImportStatement(Node node, TreeSitterParsingContext parsingContext) {
        // Basic checks - it's an import and has enough nodes.
        if (node == null || getNodeType(node) != IMPORT_STATEMENT || node.getChildCount() < 2) {
            return Optional.empty();
        }

        // Get the dotted name inside
        List<Node> dottedNames = getNodeChildren(node).stream()
            .filter(v -> getNodeType(v) == TreeSitterPythonTypes.DOTTED_NAME || getNodeType(v) == TreeSitterPythonTypes.ALIASED_IMPORT)
            .collect(Collectors.toList());

        if (dottedNames.size() < 1) {
            return Optional.empty();
        }


        var packageList = dottedNames
            .stream()
            .map(v -> transformFromElement(v, parsingContext))
            .filter(v -> v.isPresent())
            .map(v -> v.get())
            .collect(Collectors.toList());


        return Optional.of(new io.codiga.model.ast.python.ImportStatement(packageList, parsingContext.getParserContextForNode(node)));

    }

    private static Optional<io.codiga.model.ast.python.ImportStatementPackage> transformFromElement(Node node, TreeSitterParsingContext parsingContext) {
        if (getNodeType(node) == TreeSitterPythonTypes.DOTTED_NAME) {
            var str = transformIdentifierToAstStringWithoutCheck(node, parsingContext);
            if (str.isPresent()) {
                return Optional.of(new ImportStatementPackage(str.get(), null, parsingContext.getParserContextForNode(node)));
            }

        }

        if (getNodeType(node) == TreeSitterPythonTypes.ALIASED_IMPORT) {
            Optional<Node> nameNodeOptional = getNodeChild(node, TreeSitterPythonTypes.DOTTED_NAME);
            Optional<Node> identifierNodeOptional = getNodeChild(node, TreeSitterPythonTypes.IDENTIFIER);
            if (nameNodeOptional.isPresent() && identifierNodeOptional.isPresent()) {
                var nameOptional = transformIdentifierToAstStringWithoutCheck(nameNodeOptional.get(), parsingContext);
                var asOptional = transformIdentifierToAstStringWithoutCheck(identifierNodeOptional.get(), parsingContext);


                if (nameOptional.isPresent() && asOptional.isPresent()) {
                    return Optional.of(new ImportStatementPackage(nameOptional.get(), asOptional.get(), parsingContext.getParserContextForNode(node)));
                }
            }
        }

        return Optional.empty();
    }
}
