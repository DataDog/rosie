package io.codiga.utils;

import ai.serenade.treesitter.*;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.TreeSitterAstElement;
import io.codiga.parser.treesitter.python.TreeSitterPythonParser;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    public static Optional<AstElement> getAstElement(Node node, Language language, TreeSitterParsingContext treeSitterParsingContexts) {
        if (language == Language.PYTHON) {
            return TreeSitterPythonParser.parse(node, treeSitterParsingContexts);
        }
        return Optional.empty();
    }

    public static Optional<TreeSitterAstElement> getFullAstTree(String code, Language language) {
        Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(language);
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

        var visitedChildren = false;
        var isFinished = false;
        TreeSitterAstElement current = null;
        TreeSitterAstElement parent = null;

        while (!isFinished) {

            if (visitedChildren) {
                if (treeCursor.gotoNextSibling()) {
                    visitedChildren = false;
                } else if (treeCursor.gotoParent() && parent != null && parent.parent != null) {
                    parent = parent.parent;
                    visitedChildren = true;
                } else {
                    if (parent == null && current != null) {
                        parent = current;
                    }
                    isFinished = true;
                    treeCursor.close();
                }
            } else {
                if (treeCursor.getCurrentNode().isNamed()) {
                    current = TreeSitterAstElement.create(treeCursor.getCurrentNode(), treeCursor.getCurrentFieldName(), new ArrayList<>(), parent);
                    if (parent != null) {
                        parent.children.add(current);
                    }
                }

                if (treeCursor.gotoFirstChild()) {
                    parent = current;
                    visitedChildren = false;
                } else {
                    visitedChildren = true;
                }
            }

        }

        return Optional.of(parent);
    }
}
