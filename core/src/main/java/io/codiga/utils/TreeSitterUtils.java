package io.codiga.utils;

import ai.serenade.treesitter.Languages;
import ai.serenade.treesitter.Node;
import ai.serenade.treesitter.Parser;
import ai.serenade.treesitter.Tree;
import ai.serenade.treesitter.TreeCursor;
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
  private static final Logger logger = LoggerFactory.getLogger(TreeSitterUtils.class);

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
    if (language == Language.JAVASCRIPT) {
      return Optional.of(Languages.javascript());
    }
    if (language == Language.TYPESCRIPT) {
      return Optional.of(Languages.tsx());
    }
    if (language == Language.GO) {
      return Optional.of(Languages.go());
    }
    if (language == Language.YAML) {
      return Optional.of(Languages.yaml());
    }
    if (language == Language.JAVA) {
      return Optional.of(Languages.java());
    }
    return Optional.empty();
  }

  public static Optional<AstElement> getAstElement(
      Node node, Language language, TreeSitterParsingContext treeSitterParsingContexts) {
    if (language == Language.PYTHON) {
      return TreeSitterPythonParser.parse(node, treeSitterParsingContexts);
    }
    return Optional.empty();
  }

  public static Optional<TreeSitterAstElement> getFullAstTree(String code, Language language) {
    Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(language);
    if (treeSitterLanguage.isEmpty()) {
      return Optional.empty();
    }

    try (Parser parser = new Parser()) {
      parser.setLanguage(treeSitterLanguage.get());
      try (Tree tree = parser.parseString(code)) {
        return getTreeFromNode(tree.getRootNode());
      }
    } catch (UnsupportedEncodingException e) {
      logger.info("error when decoding the code");
      return Optional.empty();
    }
  }

  public static Optional<TreeSitterAstElement> getTreeFromNode(Node node) {
    var visitedChildren = false;
    var isFinished = false;
    TreeSitterAstElement current = null;
    TreeSitterAstElement parent = null;
    try (TreeCursor treeCursor = node.walk()) {
      while (!isFinished) {

        if (visitedChildren) {
          if (treeCursor.gotoNextSibling()) { 
            visitedChildren = false;
          } else if (treeCursor.gotoParent() && parent != null && parent.parent != null) {
            parent = parent.parent;
          } else {
            if (parent == null && current != null) {
              parent = current;
            }
            isFinished = true;
          }
        } else {
          if (treeCursor.getCurrentNode().isNamed()) {
            current =
                TreeSitterAstElement.create(
                    treeCursor.getCurrentNode(),
                    treeCursor.getCurrentFieldName(),
                    new ArrayList<>(),
                    parent);
            if (parent != null) {
              parent.children.add(current);
            }
          }
          if (treeCursor.gotoFirstChild()) {
            parent = current;
          } else {
            visitedChildren = true;
          }
        }
      }
    }
    return Optional.ofNullable(parent);
  }
}
