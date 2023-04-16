package io.codiga.analyzer.treesitterpm;

import ai.serenade.treesitter.Node;
import ai.serenade.treesitter.Parser;
import ai.serenade.treesitter.Tree;
import ai.serenade.treesitter.query.*;
import ai.serenade.treesitter.query.exceptions.QueryException;
import datadog.trace.api.Trace;
import io.codiga.analyzer.AnalysisOptions;
import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.analyzer.ast.common.AnalyzerCommon;
import io.codiga.analyzer.ast.common.AnalyzerContext;
import io.codiga.analyzer.ast.vm.VmContext;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.context.Context;
import io.codiga.model.error.RuleResult;
import io.codiga.model.tsquery.TsPatternMatch;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static io.codiga.model.RuleErrorCode.ERROR_RULE_INVALID_QUERY;
import static io.codiga.utils.TreeSitterUtils.getAstElement;
import static io.codiga.utils.TreeSitterUtils.languageToTreeSitterLanguage;

public class TreeSitterPatternMatching extends AnalyzerCommon {

    private final AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

    private final Logger logger = LoggerFactory.getLogger(TreeSitterPatternMatching.class);


    public TreeSitterPatternMatching(MetricsInterface metrics, ErrorReportingInterface errorReporting, AnalyzerConfiguration configuration) {
        super(metrics, errorReporting, configuration);
    }

    @Trace(operationName = "TreeSitterPatternMatching.execute")
    @Override
    public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {
        long startTimestamp = System.currentTimeMillis();
        Context ruleContext = new io.codiga.model.context.Context(analyzerContext.getCode(), rule.variables());

        Optional<String> error = Optional.empty(); // put the error if any
        Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(rule.language());

        // Language is not supported
        if (treeSitterLanguage.isEmpty()) {
            return new RuleResult(rule.name(), List.of(), List.of(), null, null, 0);
        }

        VmContext vmContext = new VmContext(analyzerContext);
        vmContext.initializeRule(rule);

        try (Parser parser = new Parser()) {
            parser.setLanguage(treeSitterLanguage.get());
            try (Tree tree = parser.parseString(analyzerContext.getCode())) {
                try (Query query = new Query(treeSitterLanguage.get(), rule.treeSitterQuery())) {
                    List<QueryCapture> captures = query.getCaptures();
                    QueryCursor queryCursor = query.execute(tree.getRootNode());
                    QueryMatch queryMatch = queryCursor.nextMatch();
                    List<TsPatternMatch> matches = new ArrayList<>();
                    TreeSitterParsingContext treeSitterParsingContext = new TreeSitterParsingContext(analyzerContext.getCode(), tree.getRootNode());

                    /**
                     * For each match
                     *  - build an object that contains each match for each capture
                     *  - add the match to the list of matches
                     */
                    while (queryMatch != null) {
                        Map<String, AstElement> match = new HashMap<>();
                        for (QueryMatchCapture queryMatchCapture : queryMatch.getCaptures()) {
                            int idx = queryMatchCapture.index;
                            String name = captures.get(idx).getName();
                            Node node = queryMatchCapture.node;
                            Optional<AstElement> astElementOptional = getAstElement(node, rule.language(), treeSitterParsingContext);
                            match.put(name, astElementOptional.orElse(null));
                        }
                        matches.add(new TsPatternMatch(match, ruleContext));
                        queryMatch = queryCursor.nextMatch();
                    }

                    /**
                     * We have the list of matches in the variable matches.
                     * Invoke the JavaScript rule on all these matches.
                     */
                    vmContext.prepareForExecution(analyzerContext, matches);
                    vmContext.execute(rule);
                } catch (QueryException e) {
                    error = Optional.of(ERROR_RULE_INVALID_QUERY);
                }
            } catch (UnsupportedEncodingException e) {
                logger.info("error when decoding the code");
            }
        }

        String output = vmContext.getOutput();
        vmContext.shutdown();

        long endTimestamp = System.currentTimeMillis();
        long executionTimeMs = endTimestamp - startTimestamp;

        // if there is an error, we return an rule with the error and no violation
        if (error.isPresent()) {
            return new RuleResult(rule.name(), List.of(), error.stream().toList(), null, output, executionTimeMs);
        }
        return new RuleResult(rule.name(), vmContext.getViolations(), List.of(), null, output, executionTimeMs);
    }


    @Trace(operationName = "TreeSitterPatternMatching.buildContext")
    @Override
    public AnalyzerContext buildContext(Language language, String filename, String code, List<AnalyzerRule> rules, AnalysisOptions options) {
        return new AnalyzerContext(language, filename, code, rules, options);
    }

}
