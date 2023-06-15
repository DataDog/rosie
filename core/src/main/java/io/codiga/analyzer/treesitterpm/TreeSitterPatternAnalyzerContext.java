package io.codiga.analyzer.treesitterpm;

import static io.codiga.utils.TreeSitterUtils.languageToTreeSitterLanguage;

import ai.serenade.treesitter.Parser;
import ai.serenade.treesitter.Tree;
import ai.serenade.treesitter.query.internals.ResourceWithPointer;
import io.codiga.analyzer.AnalysisOptions;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import java.io.UnsupportedEncodingException;
import java.lang.ref.Cleaner;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An analyzer context that contains the tree-sitter tree to analyze.
 * It is created once and for all before we do the analysis.
 */
public class TreeSitterPatternAnalyzerContext extends AnalyzerContext {

    @Getter
    private Optional<Tree> tree = Optional.empty();

    private final Logger logger = LoggerFactory.getLogger(TreeSitterPatternAnalyzerContext.class);

    private static final Cleaner CLEANER = Cleaner.create();

    @Override
    public void finalize() {
        this.tree.ifPresent(ResourceWithPointer::close);
    }

    public TreeSitterPatternAnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, AnalysisOptions analysisOptions) {
        super(language, filename, code, rules, analysisOptions);

        Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(language);
        if(treeSitterLanguage.isPresent()) {
            try (Parser parser = new Parser()) {
                parser.setLanguage(treeSitterLanguage.get());
                try {
                    Tree tsTree = parser.parseString(code);
                    this.tree = Optional.ofNullable(tsTree);
                }
                catch (UnsupportedEncodingException exception) {
                    this.tree = Optional.empty();
                }
            }
        }

    }

}


