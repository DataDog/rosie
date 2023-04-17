package io.codiga.utils;

import ai.serenade.treesitter.*;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.TreeSitterAstElement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

import static io.codiga.model.utils.ModelUtils.languageFromString;

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

    public static Optional<TreeSitterAstElement> getFullAstTree(String code, String language) {
        Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(languageFromString(language));
        TreeCursor treeCursor;
        if (treeSitterLanguage.isEmpty()) {
            return Optional.empty();
        }

        try {
            Parser parser = new Parser();
            parser.setLanguage(treeSitterLanguage.get());
            Tree tree = parser.parseString(code);
            treeCursor = tree.getRootNode().walk();
        } catch (UnsupportedEncodingException e) {
            logger.info("error when decoding the code");
            return Optional.empty();
        }

        var currentNode = treeCursor.getCurrentNode();
        var result = new TreeSitterAstElement(currentNode.getType(), currentNode.getStartPosition(), currentNode.getEndPosition(), treeCursor.getCurrentFieldName(), new ArrayList<>());

        return Optional.of(result);
    }
}
