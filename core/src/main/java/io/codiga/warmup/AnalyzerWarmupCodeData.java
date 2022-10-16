package io.codiga.warmup;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;

import java.util.List;

public class AnalyzerWarmupCodeData {

    public List<AnalyzerRule> analyzerRuleList;
    public String codeBase64;
    public Language language;
    public String filename;

    public AnalyzerWarmupCodeData(List<AnalyzerRule> analyzerRuleList, String codeBase64, Language language, String filename) {
        this.analyzerRuleList = analyzerRuleList;
        this.codeBase64 = codeBase64;
        this.language = language;
        this.filename = filename;
    }
}
