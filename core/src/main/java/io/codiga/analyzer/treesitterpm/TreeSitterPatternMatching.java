package io.codiga.analyzer.treesitterpm;

import static io.codiga.utils.TreeSitterUtils.getTreeFromNode;
import static io.codiga.utils.TreeSitterUtils.languageToTreeSitterLanguage;

import ai.serenade.treesitter.Node;
import ai.serenade.treesitter.query.Query;
import ai.serenade.treesitter.query.QueryCapture;
import ai.serenade.treesitter.query.QueryCursor;
import ai.serenade.treesitter.query.QueryMatch;
import ai.serenade.treesitter.query.QueryMatchCapture;
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
import io.codiga.model.ast.common.TreeSitterAstElement;
import io.codiga.model.context.Context;
import io.codiga.model.error.RuleResult;
import io.codiga.model.tree_sitter.TsPatternMatch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeSitterPatternMatching extends AnalyzerCommon {

  private final AnalyzerFuturePool pool = AnalyzerFuturePool.getInstance();

  private final Logger logger = LoggerFactory.getLogger(TreeSitterPatternMatching.class);

  public TreeSitterPatternMatching(
      MetricsInterface metrics,
      ErrorReportingInterface errorReporting,
      AnalyzerConfiguration configuration) {
    super(metrics, errorReporting, configuration);
  }

  @Trace(operationName = "TreeSitterPatternMatching.execute")
  @Override
  public RuleResult execute(AnalyzerContext analyzerContext, AnalyzerRule rule) {

    TreeSitterPatternAnalyzerContext treeSitterPatternAnalyzerContext =
        (TreeSitterPatternAnalyzerContext) analyzerContext;

    long startTimestamp = System.currentTimeMillis();
    Context ruleContext =
        new io.codiga.model.context.Context(
            treeSitterPatternAnalyzerContext.getCode(), rule.variables);

    Optional<String> error = Optional.empty(); // put the error if any
    Optional<Long> treeSitterLanguage = languageToTreeSitterLanguage(rule.language);

    // Language is not supported
    if (treeSitterLanguage.isEmpty()) {
      return new RuleResult(rule.name, List.of(), List.of(), null, null, 0);
    }

    VmContext vmContext = new VmContext(treeSitterPatternAnalyzerContext);
    vmContext.initializeRule(rule);

    if (treeSitterPatternAnalyzerContext.getTree().isPresent()) {
      Query query = rule.getTreeSitterQueryObject();
      if (query != null) {
        List<QueryCapture> captures = query.getCaptures();
        try (QueryCursor queryCursor =
            query.execute(treeSitterPatternAnalyzerContext.getTree().get().getRootNode())) {
          QueryMatch queryMatch = queryCursor.nextMatch();
          List<TsPatternMatch> matches = new ArrayList<>();

          /**
           * For each match - build an object that contains each match for each capture - add the
           * match to the list of matches
           */
          while (queryMatch != null) {
            Map<String, TreeSitterAstElement> match = new HashMap<>();
            Map<String, List<TreeSitterAstElement>> matchesList = new HashMap<>();
            for (QueryMatchCapture queryMatchCapture : queryMatch.getCaptures()) {
              int idx = queryMatchCapture.index;
              String name = captures.get(idx).getName();
              Node node = queryMatchCapture.node;
              Optional<TreeSitterAstElement> astElementOptional = getTreeFromNode(node);
              if (astElementOptional.isPresent()) {
                match.put(name, astElementOptional.get());
                if (!matchesList.containsKey(name)) {
                  matchesList.put(name, new ArrayList<>());
                }
                matchesList.get(name).add(astElementOptional.get());
              }
            }
            matches.add(new TsPatternMatch(match, matchesList, ruleContext));
            queryMatch = queryCursor.nextMatch();
          }

          /**
           * We have the list of matches in the variable matches. Invoke the JavaScript rule on all
           * these matches.
           */
          vmContext.prepareForExecution(analyzerContext, matches);
          vmContext.execute(rule);
        }

      }
    }

    String output = vmContext.getOutput();
    vmContext.shutdown();

    long endTimestamp = System.currentTimeMillis();
    long executionTimeMs = endTimestamp - startTimestamp;

    // if there is an error, we return an rule with the error and no violation
    if (error.isPresent()) {
      return new RuleResult(
          rule.name, List.of(), error.stream().toList(), null, output, executionTimeMs);
    }
    return new RuleResult(
        rule.name, vmContext.getViolations(), List.of(), null, output, executionTimeMs);
  }

  @Trace(operationName = "TreeSitterPatternMatching.buildContext")
  @Override
  public AnalyzerContext buildContext(
      Language language,
      String filename,
      String code,
      List<AnalyzerRule> rules,
      AnalysisOptions options) {
    return new TreeSitterPatternAnalyzerContext(language, filename, code, rules, options);
  }
}
