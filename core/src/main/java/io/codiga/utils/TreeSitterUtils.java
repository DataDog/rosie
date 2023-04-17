package io.codiga.utils;

import ai.serenade.treesitter.Languages;
import ai.serenade.treesitter.Node;
import ai.serenade.treesitter.Parser;
import ai.serenade.treesitter.Tree;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public class TreeSitterUtils {
    private final static Logger logger = LoggerFactory.getLogger(TreeSitterUtils.class);

    /**
     * Convert a language from our model into a Language we can use with tree-sitter.
     *
     * @param language
     * @return - the language value for tree-sitter. Optional.empty() otherwise.
     */
    public static Optional<Long> languageToTreeSitterLanguage(Language language) {
        if (language == Language.PYTHON) {
            return Optional.of(Languages.python());
        }
        return Optional.empty();
    }

    public static Optional<Node> getRootNode(String code, Language language) {
        Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(language);
        Optional<Node> rootNode = Optional.empty();
        if (treeSitterLanguage.isEmpty()) {
            return Optional.empty();
        }

        try (Parser parser = new Parser()) {
            parser.setLanguage(treeSitterLanguage.get());
            try (Tree tree = parser.parseString(code)) {
                rootNode = Optional.of(tree.getRootNode());
            } catch (UnsupportedEncodingException e) {
                logger.info("error when decoding the code");
            }
        }
        return rootNode;
    }

    public static Optional<AstElement> getAstElement(Node node, Language language, TreeSitterParsingContext treeSitterParsingContexts) {
        if (language == Language.PYTHON) {
            return TreeSitterPythonParser.parse(node, treeSitterParsingContexts);
        }
        return Optional.empty();
    }
}
