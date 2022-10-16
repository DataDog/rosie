package io.codiga.warmup;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;

import java.util.List;

public class AnalyzerWarmupCodeDataBuilder {
    private List<AnalyzerRule> analyzerRuleList;
    private String codeBase64;
    private Language language;
    private String filename;

    public AnalyzerWarmupCodeDataBuilder setAnalyzerRuleList(List<AnalyzerRule> analyzerRuleList) {
        this.analyzerRuleList = analyzerRuleList;
        return this;
    }

    public AnalyzerWarmupCodeDataBuilder setCodeBase64(String codeBase64) {
        this.codeBase64 = codeBase64;
        return this;
    }

    public AnalyzerWarmupCodeDataBuilder setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public AnalyzerWarmupCodeDataBuilder setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public AnalyzerWarmupCodeData createAnalyzerWarmupCodeData() {
        return new AnalyzerWarmupCodeData(analyzerRuleList, codeBase64, language, filename);
    }
}