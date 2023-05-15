package io.codiga.server.e2e;

import static io.codiga.constants.Languages.RULE_TYPE_TREE_SITTER_QUERY;
import static io.codiga.model.utils.ModelUtils.stringFromLanguage;
import static io.codiga.utils.Base64Utils.encodeBase64;

import io.codiga.model.Language;
import io.codiga.server.ServerMainController;
import io.codiga.server.configuration.ServerTestConfiguration;
import io.codiga.server.request.*;
import io.codiga.server.response.GetTreeSitterAstResponse;
import io.codiga.server.response.Response;
import io.codiga.server.response.ViolationFix;
import io.codiga.server.response.ViolationFixEdit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 * Base class for all tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
@ContextConfiguration
public class E2EBase {
    public final static List<Language> JAVASCRIPT_TYPESCRIPT = List.of(Language.JAVASCRIPT, Language.TYPESCRIPT);
    public final static List<Language> TYPESCRIPT_ONLY = List.of(Language.TYPESCRIPT);
    public final static List<Language> JAVASCRIPT_ONLY = List.of(Language.JAVASCRIPT);


    protected final Logger logger = LoggerFactory.getLogger(E2EBase.class);
    @Autowired
    private ServerMainController controller;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;


    public Response executeTest(String filename,
                                String code,
                                Language language,
                                String ruleCode,
                                String ruleName,
                                String ruleType,
                                String entityChecked,
                                String pattern,
                                Map<String, String> variables,
                                boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, false, null);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(stringFromLanguage(language))
                .setFileEncoding("utf-8")
                .setCodeBase64(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(
                        List.of(
                                new RuleBuilder()
                                        .setId(ruleName)
                                        .setContentBase64(encodeBase64(ruleCode))
                                        .setLanguage(stringFromLanguage(language))
                                        .setType(ruleType)
                                        .setEntityChecked(entityChecked)
                                        .setPattern(pattern)
                                        .setVariables(variables)
                                        .createRule()
                        )
                ).createRequest();
        Response response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/analyze", request,
                Response.class);
        return response;
    }

    public Response executeTestWithTreeSitter(String filename,
                                              String code,
                                              Language language,
                                              String ruleCode,
                                              String ruleName,
                                              String ruleType,
                                              String entityChecked,
                                              String pattern,
                                              String tsQueryBase64,
                                              Map<String, String> variables,
                                              boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, true, null);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(stringFromLanguage(language))
                .setFileEncoding("utf-8")
                .setCodeBase64(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(
                        List.of(
                                new RuleBuilder()
                                        .setId(ruleName)
                                        .setContentBase64(encodeBase64(ruleCode))
                                        .setLanguage(stringFromLanguage(language))
                                        .setType(ruleType)
                                        .setEntityChecked(entityChecked)
                                        .setPattern(pattern)
                                        .setTsQueryBase64(tsQueryBase64)
                                        .setVariables(variables)
                                        .createRule()
                        )
                ).createRequest();
        Response response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/analyze", request,
                Response.class);
        return response;
    }

    public Response executeTestWithTreeSitter(String filename,
                                              String code,
                                              Language language,
                                              String ruleCode,
                                              String ruleName,
                                              String ruleType,
                                              String entityChecked,
                                              String pattern,
                                              Map<String, String> variables,
                                              boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, ruleType, entityChecked, pattern, null, variables, logOutput);
    }

    public Response executeTestWithTreeSitter(String filename,
                                              String code,
                                              Language language,
                                              String ruleCode,
                                              String ruleName,
                                              String ruleType,
                                              String entityChecked,
                                              String pattern,
                                              boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, ruleType, entityChecked, pattern, null, null, logOutput);
    }

    public Response executeTest(String filename,
                                String code,
                                Language language,
                                String ruleCode,
                                String ruleName,
                                String ruleType,
                                String entityChecked,
                                String pattern,
                                boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, ruleType, entityChecked, pattern, null, null, logOutput);
    }

    public Response executeTestTsQuery(String filename,
                                       String code,
                                       Language language,
                                       String ruleCode,
                                       String ruleName,
                                       String tsQuery,
                                       boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, RULE_TYPE_TREE_SITTER_QUERY,
                null, null, encodeBase64(tsQuery), null, logOutput);
    }

    public Response executeTestTsQuery(String filename,
                                       String code,
                                       Language language,
                                       String ruleCode,
                                       String ruleName,
                                       String tsQuery,
                                       Map<String, String> variables,
                                       boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, RULE_TYPE_TREE_SITTER_QUERY,
                null, null, encodeBase64(tsQuery), variables, logOutput);
    }

    public Response executeTest(String filename,
                                String code,
                                Language codeLanguage,
                                Language ruleLanguage,
                                String ruleCode,
                                String ruleName,
                                String ruleType,
                                String entityChecked,
                                String pattern,
                                Map<String, String> variables,
                                boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, false, null);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(stringFromLanguage(codeLanguage))
                .setFileEncoding("utf-8")
                .setCodeBase64(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(
                        List.of(
                                new RuleBuilder()
                                        .setId(ruleName)
                                        .setContentBase64(encodeBase64(ruleCode))
                                        .setLanguage(stringFromLanguage(ruleLanguage))
                                        .setType(ruleType)
                                        .setEntityChecked(entityChecked)
                                        .setPattern(pattern)
                                        .setVariables(variables)
                                        .createRule()
                        )
                ).createRequest();
        Response response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/analyze", request,
                Response.class);
        return response;
    }

    public Response executeTest(String filename,
                                String code,
                                Language codeLanguage,
                                Language ruleLanguage,
                                String ruleCode,
                                String ruleName,
                                String ruleType,
                                String entityChecked,
                                String pattern,
                                boolean logOutput) {
        return executeTest(filename, code, codeLanguage, ruleLanguage, ruleCode, ruleName, ruleType, entityChecked, pattern, null, logOutput);
    }


    public Response executeTestWithRules(String filename,
                                         String code,
                                         Language language,
                                         List<Rule> rules,
                                         boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, false, null);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(stringFromLanguage(language))
                .setFileEncoding("utf-8")
                .setCodeBase64(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(rules)
                .createRequest();
        Response response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/analyze", request,
                Response.class);
        return response;
    }

    public GetTreeSitterAstResponse executeTreesitterAstTest(String decodedCode,
                                             Language language,
                                             String fileEncoding) {
        GetTreeSitterAstRequest request = GetTreeSitterAstRequest
                .builder()
                .language(stringFromLanguage(language))
                .fileEncoding(fileEncoding)
                .codeBase64(encodeBase64(decodedCode))
                .build();

        GetTreeSitterAstResponse response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/get-treesitter-ast", request,
                GetTreeSitterAstResponse.class);
        return response;
    }

    protected String applyFix(String originalCode, ViolationFix violationFix) {
        logger.info("====== START OF ORIGINAL CODE =======");
        logger.info(originalCode);
        logger.info("======= END OF ORIGINAL CODE ========");
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(originalCode);
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        for (ViolationFixEdit violationFixEdit : violationFix.edits) {
            if (violationFixEdit.editType.equalsIgnoreCase("update")) {

                if (violationFixEdit.start.line == violationFixEdit.end.line) {
                    int lineIndex = violationFixEdit.start.line - 1;
                    String line = lines.get(lineIndex);
                    logger.info(String.format("updating line %s:%s-%s:%s -> %s", violationFixEdit.start.line, violationFixEdit.start.col, violationFixEdit.end.line, violationFixEdit.end.col, line));
                    line = line.substring(0, violationFixEdit.start.col - 1) + violationFixEdit.content + line.substring(violationFixEdit.end.col - 1);
                    lines.set(lineIndex, line);
                }
            }
            if (violationFixEdit.editType.equalsIgnoreCase("add")) {
                int lineIndex = violationFixEdit.start.line - 1;
                String line = lines.get(lineIndex);
                line = line.substring(0, violationFixEdit.start.col - 1) + violationFixEdit.content + line.substring(violationFixEdit.start.col - 1);
                lines.set(lineIndex, line);
            }
            if (violationFixEdit.editType.equalsIgnoreCase("remove")) {
                int lineIndex = violationFixEdit.start.line - 1;
                String line = lines.get(lineIndex);
                line = line.substring(0, violationFixEdit.start.col - 1) + line.substring(violationFixEdit.end.col - 1);
                lines.set(lineIndex, line);
            }
        }
        String newCode = String.join("\n", lines);
        logger.info("====== START OF UPDATED CODE =======");
        logger.info(newCode);
        logger.info("======= END OF UPDATED CODE ========");
        return newCode;
    }
}
