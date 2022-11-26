package io.codiga.warmup;

import io.codiga.analyzer.Analyzer;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.error.AnalysisResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.codiga.warmup.AnalyzerWarmupCode.WARMUP_CODE;

public class AnalyzerWarmup {


    private final static Logger LOGGER = LoggerFactory.getLogger(AnalyzerWarmup.class);

    public static void warmupAnalyzer(Analyzer analyzer, int nbLoops) {
        LOGGER.info("Warmup starting");

        IntStream.range(1, nbLoops).forEach(i -> {
//            LOGGER.info(String.format("Warmup: loop %s of %s", i, nbLoops));
            WARMUP_CODE.forEach(analyzerWarmupCodeData -> {
                boolean passed = false;

                while (!passed) {
//                    String rules = analyzerWarmupCodeData.analyzerRuleList.stream().map(AnalyzerRule::name).collect(Collectors.joining(","));
//                    LOGGER.info(String.format("Warming up rules: %s", rules));

                    String decodedCode = new String(Base64.getDecoder().decode(analyzerWarmupCodeData.codeBase64.getBytes()));
                    CompletableFuture<AnalysisResult> futureResult = analyzer.analyze(analyzerWarmupCodeData.language, analyzerWarmupCodeData.filename,
                        decodedCode, analyzerWarmupCodeData.analyzerRuleList.stream().map(analyzerRule -> new AnalyzerRule(analyzerRule.name(), analyzerRule.language(), analyzerRule.ruleType(), analyzerRule.entityChecked(), new String(Base64.getDecoder().decode(analyzerRule.code())), analyzerRule.pattern())).collect(Collectors.toList()), false);
                    AnalysisResult analysisResult = futureResult.join();
                    int nbViolations = analysisResult.ruleResults().stream().flatMap(r -> r.violations().stream()).toList().size();
//                    int nbErrors = analysisResult.ruleResults().stream().flatMap(r -> r.errors().stream()).toList().size();

                    if (nbViolations > 0) {
//                        LOGGER.info(String.format("Warmup for rules: %s completed, returned %s violations and %s errors", rules, nbViolations, nbErrors));
                        passed = true;
                    }
                }

            });
        });
        LOGGER.info("Warmup done");

    }
}
