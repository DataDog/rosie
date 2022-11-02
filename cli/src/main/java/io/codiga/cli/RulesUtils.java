package io.codiga.cli;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.model.Rules;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class RulesUtils {

    public static List<AnalyzerRule> getRulesFromFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), Rules.class).rules.stream().map(r -> {
            System.out.println(r);
            String decodedCode = new String(Base64.getDecoder().decode(r.code()));
            return new AnalyzerRule(r.name(), r.language(), r.ruleType(), r.entityChecked(), decodedCode, r.pattern());
        }).collect(Collectors.toList());
    }
}
