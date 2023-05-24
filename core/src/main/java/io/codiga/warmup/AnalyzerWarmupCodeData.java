package io.codiga.warmup;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;

import java.util.List;

public class AnalyzerWarmupCodeData {

    public List<AnalyzerRule> analyzerRuleList;
    public String code;
    public Language language;
    public String filename;

    public AnalyzerWarmupCodeData(List<AnalyzerRule> analyzerRuleList, String code, Language language, String filename) {
        this.analyzerRuleList = analyzerRuleList;
        this.code = code;
        this.language = language;
        this.filename = filename;
    }
}
