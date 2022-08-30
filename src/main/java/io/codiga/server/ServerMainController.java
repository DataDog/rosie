package io.codiga.server;

import io.codiga.analyzer.Analyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.metrics.MetricsInterface;
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
import static io.codiga.model.utils.ModelUtils.editTypeToString;
import static io.codiga.server.constants.Languages.SUPPORTED_LANGUAGES;
import static io.codiga.server.response.ResponseErrors.*;
import static io.codiga.utils.Version.CURRENT_VERSION;

@RestController
public class ServerMainController {

    Logger logger = LoggerFactory.getLogger(ServerMainController.class);

    final InjectorService injectorService;

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
                new Response(null, List.of(INVALID_REQUEST)));
        }

        if (!SUPPORTED_LANGUAGES.contains(request.language)) {
            return CompletableFuture.completedFuture(
                new Response(null, List.of(LANGUAGE_NOT_SUPPORTED)));
        }

        List<AnalyzerRule> rules = null;
        String decodedCode = null;

        try {
            decodedCode = new String(Base64.getDecoder().decode(request.codeBase64.getBytes()));
        } catch (IllegalArgumentException iae) {
            logger.info("code is not base64");
            return CompletableFuture.completedFuture(new Response(null, List.of(CODE_NOT_BASE64)));
        }

        try {
            rules = request.rules.stream().map(r -> new AnalyzerRule(r.id, new String(Base64.getDecoder().decode(r.contentBase64.getBytes())))).toList();
        } catch (IllegalArgumentException iae) {
            logger.error("rule is not base64: " + rules);
            return CompletableFuture.completedFuture(new Response(null, List.of(RULE_NOT_BASE64)));
        }

        CompletableFuture<AnalysisResult> violationsFuture = analyzer.analyze(request.language, request.filename, decodedCode, rules);

        return violationsFuture.thenApply(analysisResult -> {
            List<io.codiga.server.response.RuleResponse> rulesReponses = analysisResult.ruleResults().stream().map(ruleResult -> {
                List<Violation> violations = ruleResult.violations().stream().map(ruleViolation -> {
                    List<ViolationFix> fixes = ruleViolation.fixes().stream().map(fix -> {
                        List<ViolationFixEdit> edits = fix.edits().stream().map(edit -> new ViolationFixEdit(
                            new Position(edit.start().line(), edit.start().positionInLine()),
                            new Position(edit.end().line(), edit.end().positionInLine()),
                            editTypeToString(edit.kind()),
                            edit.content().orElse(null)
                        )).toList();
                        return new ViolationFix(fix.description(), edits);

                    }).toList();
                    Position start = null;
                    Position end = null;
                    if (ruleViolation.start() != null) {
                        start = new Position(ruleViolation.start().line(), ruleViolation.start().positionInLine());
                    }
                    if (ruleViolation.end() != null) {
                        end = new Position(ruleViolation.end().line(), ruleViolation.end().positionInLine());
                    }
                    return new Violation(start, end, ruleViolation.message(),
                        ruleViolation.severity().toString(), ruleViolation.category().toString(), fixes);

                }).toList();
                return new RuleResponse(ruleResult.identifier(), violations, ruleResult.errors());
            }).toList();
            return new Response(rulesReponses, null);
        });
    }
}
