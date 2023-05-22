package io.codiga.cli.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.Rules;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RulesUtils {

    public static List<AnalyzerRule> getRulesFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Rules.class).rules.stream().map(r -> {
            String decodedCode = new String(Base64.getDecoder().decode(r.code()));
            String decodedDescription = r.description() != null ? new String(Base64.getDecoder().decode(r.description())):"";
            return new AnalyzerRule(r.name(), decodedDescription, r.language(), r.ruleType(), r.entityChecked(), decodedCode, r.regex(), r.treeSitterQuery(), r.variables());
        }).collect(Collectors.toList());
    }

    public static List<List<AnalyzerRule>> separateRules(List<AnalyzerRule> analyzerRules, int nbSubList) {
        List<List<AnalyzerRule>> result = new ArrayList<>(nbSubList);
        for (int i = 0; i < nbSubList; i++) {
            result.add(new ArrayList<>());
        }
        AtomicInteger index = new AtomicInteger();
        analyzerRules.forEach(rule -> {
            result.get(index.get()).add(rule);
            index.set(index.get() + 1);
            index.set(index.get() % nbSubList);
        });
        return result;
    }
}
