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
            String decodedCode = new String(Base64.getDecoder().decode(r.code));
            String decodedDescription = r.description != null ? new String(Base64.getDecoder().decode(r.description)):"";
            String decodedRegex = r.regex != null ? new String(Base64.getDecoder().decode(r.regex)):null;
            String decodedTreeSitterQuery = r.treeSitterQuery != null ? new String(Base64.getDecoder().decode(r.treeSitterQuery)):null;
            return AnalyzerRule
                .builder()
                .name(r.name)
                .description(decodedDescription)
                .language(r.language)
                .type(r.type)
                .entityChecked(r.entityChecked)
                .code(decodedCode)
                .regex(decodedRegex)
                .treeSitterQuery(decodedTreeSitterQuery)
                .variables(r.variables)
                .build();
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
