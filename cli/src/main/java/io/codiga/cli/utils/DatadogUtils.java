package io.codiga.cli.utils;

import static io.codiga.utils.EnvironmentUtils.DATADOG_SITE;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.cli.config.Configuration;
import io.codiga.utils.EnvironmentUtils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class DatadogUtils {

    public final static String DEFAULT_SITE = "datadoghq.com";

    /**
     * Decode a rule from JSON. If the mapping does not work, return Optional.empty()
     *
     * @param node
     * @param rulesetName
     * @return
     */
    public static Optional<AnalyzerRule> getRuleFromJson(JsonNode node, String rulesetName) {
        System.out.println(node.toString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            var res = mapper.convertValue(node, AnalyzerRule.class);
            if (res == null) {
                System.err.println("Error when decoding the rule");
                return Optional.empty();
            }

            String code = node.get("content").asText();
            if (code != null) {
                String decodedCode = new String(Base64.getDecoder().decode(code));
                return Optional.of(
                    new AnalyzerRule(String.format("%s/%s", rulesetName, res.name()), res.description(), res.language(), res.ruleType(), res.entityChecked(), decodedCode, res.regex(), res.treeSitterQuery(), res.variables())
                );
            }
        } catch (IllegalArgumentException e) {
            System.err.println(String.format("error when trying to convert rule from ruleset %s", rulesetName));
        }
        return Optional.empty();
    }


    /**
     * Get the [[AnalyzerRule]] from the API response from Datadog
     *
     * @param response - the API response in plain text
     * @return - the list of rules (if any if found)
     * @throws IOException
     */
    public static List<AnalyzerRule> getRulesFromApiResponse(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);
        ArrayList<AnalyzerRule> result = new ArrayList<>();
        var dataNode = node.get("data");
        if (dataNode == null) {
            return List.of();
        }

        var attributesNode = dataNode.get("attributes");

        if (attributesNode == null) {
            return List.of();
        }

        var name = attributesNode.get("name").asText();
        var rules = (ArrayNode) attributesNode.get("rules");


        for (JsonNode rule : rules) {
            var r = getRuleFromJson(rule, name);
            r.ifPresent(result::add);
        }

        return result;
    }


    /**
     * Get the rules from Datadog. Initiate a request to Datadog to get the rules
     *
     * @param configuration - the configuration file previously read that contains the rulesets
     * @return the list of rules to use
     */
    public static List<AnalyzerRule> getRulesFromDatadog(Configuration configuration) {
        var appKey = EnvironmentUtils.getEnvironmentValue(EnvironmentUtils.DATADOG_APP_KEY);
        var apiKey = EnvironmentUtils.getEnvironmentValue(EnvironmentUtils.DATADOG_API_KEY);
        var siteOptional = EnvironmentUtils.getEnvironmentValue(DATADOG_SITE);
        final String site = siteOptional.orElse(DEFAULT_SITE);
        List<AnalyzerRule> result = new ArrayList<>();


        if (apiKey.isEmpty()) {
            System.err.println(String.format("Variable %s not defined", EnvironmentUtils.DATADOG_API_KEY));
            System.exit(2);
        }
 
        if (appKey.isEmpty()) {
            System.err.println(String.format("Variable %s not defined", EnvironmentUtils.DATADOG_APP_KEY));
            System.exit(2);
        }

        for (String ruleset : configuration.getRulesets()) {
            var client = HttpClient.newHttpClient();


            var request = HttpRequest.newBuilder(
                    URI.create(String.format("https://api.%s/api/v2/static-analysis/rulesets/%s", site, ruleset)))
                .header("accept", "application/json")
                .header("dd-api-key", apiKey.get())
                .header("dd-application-key", appKey.get())
                .build();

            try {
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() == 200) {
                    result.addAll(getRulesFromApiResponse(response.body()));
                } else {
                    System.err.println(String.format("Error when fetching rule %s", ruleset));
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
