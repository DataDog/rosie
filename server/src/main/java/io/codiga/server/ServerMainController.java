package io.codiga.server;

import static io.codiga.constants.Languages.SUPPORTED_LANGUAGES;
import static io.codiga.metrics.MetricsName.*;
import static io.codiga.server.configuration.ServerConfiguration.WARMUP_LOOPS;
import static io.codiga.server.response.ResponseErrors.*;
import static io.codiga.utils.EnvironmentUtils.getEnvironmentValue;
import static io.codiga.utils.TreeSitterUtils.getFullAstTree;
import static io.codiga.utils.Version.CURRENT_VERSION;
import static io.codiga.warmup.AnalyzerWarmup.warmupAnalyzer;

import com.fasterxml.jackson.core.JsonParseException;
import io.codiga.analyzer.AnalysisOptions;
import io.codiga.analyzer.Analyzer;
import io.codiga.analyzer.config.AnalyzerConfiguration;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.Language;
import io.codiga.model.error.AnalysisResult;
import io.codiga.server.request.GetTreeSitterAstRequest;
import io.codiga.server.request.Request;
import io.codiga.server.response.*;
import io.codiga.server.services.InjectorService;
import io.codiga.utils.EnvironmentUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerMainController {

    final InjectorService injectorService;
    private final MetricsInterface metrics;
    private final ErrorReportingInterface errorReporting;
    private final boolean PYTHON_FORCE_ANTLR = getEnvironmentValue(EnvironmentUtils.PYTHON_FORCE_ANTLR)
            .map(v -> v.equalsIgnoreCase("true")).orElse(false);
    Logger logger = LoggerFactory.getLogger(ServerMainController.class);
    private Analyzer analyzer = null;

    public ServerMainController(InjectorService injectorService) {
        AnalyzerConfiguration configuration = new AnalyzerConfiguration(5000);
        metrics = injectorService.getInjector().getInstance(MetricsInterface.class);
        errorReporting = injectorService.getInjector().getInstance(ErrorReportingInterface.class);
        this.analyzer = new Analyzer(errorReporting, metrics, configuration);
        this.injectorService = injectorService;

        warmupAnalyzer(this.analyzer, WARMUP_LOOPS);
    }

    private boolean shouldUseTreeSitter(Request request) {
        if (request.language == Language.PYTHON) {
            if (PYTHON_FORCE_ANTLR) {
                return false;
            }
            if (request.options != null) {
                return request.options.useTreeSitter;
            }
            return true;
        }
        return false;
    }

    /**
     * Handle all exception not being handled or caught in this controller.
     *
     * @param req
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception exception) {
        if (exception instanceof JsonParseException || exception instanceof HttpMessageNotReadableException) {
            metrics.incrementMetric(METRIC_EXCEPTION_INVALID_INPUT_JSON);
            return "invalid JSON input";
        }

        if (exception instanceof HttpMediaTypeNotSupportedException) {
            return "invalid Content-Type";
        }

        metrics.incrementMetric(METRIC_EXCEPTION_UNHANDLED);
        logger.error("got exception");
        logger.error(exception.getMessage());
        Arrays.stream(exception.getStackTrace()).forEach(st -> logger.error(st.toString()));
        errorReporting.reportError(exception, "error in handleError");
        return "oh no no no!";
    }

    @GetMapping("/ping")
    @CrossOrigin(allowedHeaders = {"Authorization", "Origin"}, origins = "*")
    public String ping() {
        metrics.incrementMetric(METRIC_PING_REQUEST);
        return "pong";
    }

    @GetMapping("/version")
    public String version() {
        metrics.incrementMetric(METRIC_VERSION_REQUEST);
        return CURRENT_VERSION;
    }

    @PostMapping("/analyze")
    @CrossOrigin(origins = "*")
    @Async
    public CompletableFuture<Response> analyze(@RequestBody Request request) {
        metrics.incrementMetric(METRIC_ANALYSIS_REQUEST);

        if (!request.isValid()) {
            metrics.incrementMetric(METRIC_INVALID_REQUEST);
            return CompletableFuture.completedFuture(
                    new Response(List.of(), List.of(ERROR_INVALID_REQUEST)));
        }

        if (!SUPPORTED_LANGUAGES.contains(request.language)) {
            logger.info("language not supported");
            metrics.incrementMetric(METRIC_INVALID_LANGUAGE);
            return CompletableFuture.completedFuture(
                    new Response(List.of(), List.of(ERROR_LANGUAGE_NOT_SUPPORTED)));
        }

        List<AnalyzerRule> rules = null;
        String decodedCode = null;

        try {
            decodedCode = new String(Base64.getDecoder().decode(request.code.getBytes()));
        } catch (IllegalArgumentException iae) {
            logger.info("code is not base64");
            return CompletableFuture.completedFuture(new Response(List.of(), List.of(ERROR_CODE_NOT_BASE64)));
        }

        try {
            rules = request.rules.stream()
                    .filter(r -> r.code != null && r.language != null)
                    .map(r -> {
                        String decodedRuleCode = new String(Base64.getDecoder().decode(r.code.getBytes()));
                        
                        String decodedTreeSitterQuery = null;
                        if (r.treeSitterQuery != null) {
                            decodedTreeSitterQuery = new String(Base64.getDecoder().decode(r.treeSitterQuery.getBytes()));
                        }
                        
                        String decodedRegex = null;
                        if (r.regex != null) {
                            decodedRegex = new String(Base64.getDecoder().decode(r.regex.getBytes()));
                        }

                        String decodedShortDescription = null;
                        if (r.shortDescription != null) {
                            decodedShortDescription = new String(Base64.getDecoder().decode(r.shortDescription.getBytes()));
                        }
                        
                        String decodedDescription = null;
                        if (r.description != null) {
                            decodedDescription = new String(Base64.getDecoder().decode(r.description.getBytes()));
                        }

                        return new AnalyzerRule(r.id, decodedShortDescription, decodedDescription, r.language, r.type, r.entityChecked, decodedRuleCode, decodedRegex, decodedTreeSitterQuery, r.variables, false);
                    }).toList();
        } catch (IllegalArgumentException iae) {
            logger.error("error decoding a rule field: " + request.rules);
            return CompletableFuture.completedFuture(new Response(List.of(), List.of(ERROR_DECODING_BASE64)));
        }
        AnalysisOptions options = AnalysisOptions.builder()
                .logOutput(request.options != null && request.options.logOutput)
                .useTreeSitter(shouldUseTreeSitter(request))
                .build();
        CompletableFuture<AnalysisResult> violationsFuture = analyzer.analyze(request.language, request.filename, decodedCode, rules, options);


        return violationsFuture.thenApply(analysisResult -> {
                    List<io.codiga.server.response.RuleResponse> rulesReponses = analysisResult.ruleResults().stream().map(ruleResult -> {
                        List<Violation> violations = ruleResult.violations().stream().map(ruleViolation -> {
                            List<ViolationFix> fixes = ruleViolation.fixes.stream().map(fix -> {
                                List<ViolationFixEdit> edits = fix.edits.stream().map(edit -> new ViolationFixEdit(
                                        edit.start,
                                        edit.end,
                                        edit.editType,
                                        edit.content
                                )).toList();
                                return new ViolationFix(fix.description, edits);

                            }).toList();

                            return new Violation(ruleViolation.start, ruleViolation.end, ruleViolation.message,
                                    ruleViolation.severity, ruleViolation.category, fixes);

                        }).toList();
                        return new RuleResponse(ruleResult.identifier(), violations, ruleResult.errors(), ruleResult.executionError(), ruleResult.output(), ruleResult.executionTimeMs());
                    }).toList();
                    return new Response(rulesReponses, List.of());
                })
                .exceptionally(ex -> {
                    metrics.incrementMetric(METRIC_ANALYSIS_EXCEPTION);
                    errorReporting.reportError(ex, "error when analyzing");
                    return new Response(List.of(), List.of(ERROR_ANALYSIS_ERROR));
                });

    }

    @PostMapping("/get-treesitter-ast")
    @CrossOrigin(origins = "*")
    @Async
    public CompletableFuture<GetTreeSitterAstResponse> getTreeSitterAst(@RequestBody GetTreeSitterAstRequest request) {
        metrics.incrementMetric(METRIC_TREESITTER_AST_REQUEST);

        if (!request.isValid()) {
            metrics.incrementMetric(METRIC_INVALID_TREESITTER_AST_REQUEST);
            return CompletableFuture.completedFuture(new GetTreeSitterAstResponse(null, List.of(ERROR_INVALID_REQUEST)));
        }

        if (!SUPPORTED_LANGUAGES.contains(request.language)) {
            metrics.incrementMetric(METRIC_INVALID_TREESITTER_AST_LANGUAGE);
            return CompletableFuture.completedFuture(new GetTreeSitterAstResponse(null, List.of(ERROR_LANGUAGE_NOT_SUPPORTED)));
        }

        String decodedCode = null;

        try {
            decodedCode = new String(Base64.getDecoder().decode(request.code.getBytes()));
        } catch (IllegalArgumentException iae) {
            logger.info("code is not base64");
            return CompletableFuture.completedFuture(new GetTreeSitterAstResponse(null, List.of(ERROR_CODE_NOT_BASE64)));
        }

        var result = getFullAstTree(decodedCode, request.language);

        if (result.isEmpty()) {
            return CompletableFuture.completedFuture(new GetTreeSitterAstResponse(null, List.of(ERROR_NO_ROOT_NODE)));
        }

        return CompletableFuture.completedFuture(new GetTreeSitterAstResponse(result.get(), List.of()));
    }
}
