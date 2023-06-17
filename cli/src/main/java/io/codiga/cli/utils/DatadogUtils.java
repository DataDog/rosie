package io.codiga.cli.utils;

import static io.codiga.utils.EnvironmentUtils.DD_SITE;

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
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            var res = mapper.convertValue(node, AnalyzerRule.class);
            if (res == null) {
                System.err.println("Error when decoding the rule");
                return Optional.empty();
            }

            String code = node.get("code").asText();
            JsonNode description = node.get("description");
            JsonNode regex = node.get("regex");
            JsonNode treeSitterQuery = node.get("tree_sitter_query");
            
            if (code != null) {
                String decodedCode = new String(Base64.getDecoder().decode(code));
                String decodedDescription = description != null ? new String(Base64.getDecoder().decode(description.asText())) : null;
                String decodedRegex = regex != null ? new String(Base64.getDecoder().decode(regex.asText())) : null;
                String decodedTreeSitterQuery = treeSitterQuery != null ? new String(Base64.getDecoder().decode(treeSitterQuery.asText())) : null;

                return Optional.of(
                    new AnalyzerRule(String.format("%s/%s", rulesetName, res.name()), decodedDescription, res.language(), res.type(), res.entityChecked(), decodedCode, decodedRegex, decodedTreeSitterQuery, res.variables())
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

        if(attributesNode.get("rules").isNull()) {
            System.err.println(String.format("ruleset %s has no rule", name));
            return List.of();
        }

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
        var apiKey = getCredential(EnvironmentUtils.DATADOG_API_KEY, EnvironmentUtils.DD_API_KEY);
        var appKey = getCredential(EnvironmentUtils.DATADOG_APP_KEY, EnvironmentUtils.DD_APP_KEY);

        var siteOptional = EnvironmentUtils.getEnvironmentValue(DD_SITE);
        final String site = siteOptional.orElse(DEFAULT_SITE);
        List<AnalyzerRule> result = new ArrayList<>();

        for (String ruleset : configuration.getRulesets()) {
            var client = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder(
                    URI.create(String.format("https://api.%s/api/v2/static-analysis/rulesets/%s", site, ruleset)))
                .header("accept", "application/json")
                .header("dd-api-key", apiKey)
                .header("dd-application-key", appKey)
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

    /**
     * Get credential from Environment Variables using a main key and a fallback key.
     * If none variable is set, the process exists with status=2.
     * If both variables are set, the main credential variable is used.
     * @param credentialKey The main environment variable key.
     * @param fallbackCredentialKey The fallback environment variable key.
     * @return The credential
     */
    private static String getCredential(String credentialKey, String fallbackCredentialKey) {
        var credentialVal = EnvironmentUtils.getEnvironmentValue(credentialKey);
        var fallbackCredentialVal = EnvironmentUtils.getEnvironmentValue(fallbackCredentialKey);

        if (credentialVal.isEmpty() && fallbackCredentialVal.isEmpty()) {
            System.err.println(String.format("Variable %s not defined", credentialKey));
            System.exit(2);
            return null;
        } else if(credentialVal.isPresent() && fallbackCredentialVal.isPresent()) {
            System.out.println(String.format("WARNING: both %s and %s environment variables are defined, using %s", credentialKey, fallbackCredentialKey, credentialKey));
            return credentialVal.get();
        } else return credentialVal.orElseGet(fallbackCredentialVal::get);
    }
}
