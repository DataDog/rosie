package io.codiga.server;

import io.codiga.analyzer.Analyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.metrics.MetricsInterface;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.model.error.AnalysisResult;
import io.codiga.server.request.Request;
import io.codiga.server.response.*;
import io.codiga.server.services.InjectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.codiga.metrics.MetricsName.*;
import static io.codiga.model.utils.ModelUtils.*;
import static io.codiga.server.constants.Languages.SUPPORTED_LANGUAGES;
import static io.codiga.server.response.ResponseErrors.*;
import static io.codiga.utils.Version.CURRENT_VERSION;

@RestController
public class ServerMainController {

    final InjectorService injectorService;
    Logger logger = LoggerFactory.getLogger(ServerMainController.class);
    private Analyzer analyzer = new Analyzer();

    private MetricsInterface metrics;

    public ServerMainController(InjectorService injectorService) {
        metrics = injectorService.getInjector().getInstance(MetricsInterface.class);
        this.injectorService = injectorService;
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
        metrics.incrementMetric(METRIC_EXCEPTION_UNHANDLED);
        logger.error("got exception");
        logger.error(exception.getMessage());
        Arrays.stream(exception.getStackTrace()).forEach(st -> logger.error(st.toString()));
        return "oh no no no!";
    }

    @GetMapping("/ping")
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
    @Async
    public CompletableFuture<Response> analyze(@RequestBody Request request) {
        logger.info("receiving request: " + request);
        metrics.incrementMetric(METRIC_ANALYSIS_REQUEST);

        if (request.isValid()) {
            return CompletableFuture.completedFuture(
                new Response(List.of(), List.of(INVALID_REQUEST)));
        }

        logger.info(String.format("request: %s", request));

        if (!SUPPORTED_LANGUAGES.contains(request.language)) {
            return CompletableFuture.completedFuture(
                new Response(List.of(), List.of(LANGUAGE_NOT_SUPPORTED)));
        }

        List<AnalyzerRule> rules = null;
        String decodedCode = null;

        try {
            decodedCode = new String(Base64.getDecoder().decode(request.codeBase64.getBytes()));
        } catch (IllegalArgumentException iae) {
            logger.info("code is not base64");
            return CompletableFuture.completedFuture(new Response(List.of(), List.of(CODE_NOT_BASE64)));
        }

        try {
            rules = request.rules.stream().map(r -> {
                String decodedRuleCode = new String(Base64.getDecoder().decode(r.contentBase64.getBytes()));
                Language language = languageFromString(r.language);
                EntityChecked entityChecked = entityCheckedFromString(r.entityChecked);
                RuleType ruleType = ruleTypeFromString(r.type);
                AnalyzerRule analyzerRule = new AnalyzerRule(r.id, language, ruleType, entityChecked, decodedRuleCode, r.pattern);
                return analyzerRule;
            }).toList();
        } catch (IllegalArgumentException iae) {
            logger.error("rule is not base64: " + rules);
            return CompletableFuture.completedFuture(new Response(List.of(), List.of(RULE_NOT_BASE64)));
        }
        CompletableFuture<AnalysisResult> violationsFuture = analyzer.analyze(languageFromString(request.language),
            request.filename, decodedCode, rules, request.logOutput);

        return violationsFuture.thenApply(analysisResult -> {
            List<io.codiga.server.response.RuleResponse> rulesReponses = analysisResult.ruleResults().stream().map(ruleResult -> {
                List<Violation> violations = ruleResult.violations().stream().map(ruleViolation -> {
                    List<ViolationFix> fixes = ruleViolation.fixes.stream().map(fix -> {
                        List<ViolationFixEdit> edits = fix.edits.stream().map(edit -> new ViolationFixEdit(
                            edit.start,
                            edit.end,
                            editTypeToString(edit.editType),
                            edit.content
                        )).toList();
                        return new ViolationFix(fix.description, edits);

                    }).toList();

                    return new Violation(ruleViolation.start, ruleViolation.end, ruleViolation.message,
                        ruleViolation.severity.toString(), ruleViolation.category.toString(), fixes);

                }).toList();
                return new RuleResponse(ruleResult.identifier(), violations, ruleResult.errors(), ruleResult.executionError(), ruleResult.output());
            }).toList();
            return new Response(rulesReponses, List.of());
        });
    }
}
