package io.codiga.server.e2e;

import io.codiga.model.Language;
import io.codiga.server.ServerMainController;
import io.codiga.server.configuration.ServerTestConfiguration;
import io.codiga.server.request.Request;
import io.codiga.server.request.RequestBuilder;
import io.codiga.server.request.Rule;
import io.codiga.server.request.RuleBuilder;
import io.codiga.server.response.Response;
import io.codiga.server.response.ViolationFix;
import io.codiga.server.response.ViolationFixEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static io.codiga.model.utils.ModelUtils.stringFromLanguage;
import static io.codiga.utils.Base64Utils.encodeBase64;

/**
 * Base class for all tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class E2EBase {

    protected final Logger logger = LoggerFactory.getLogger(E2EBase.class);
    @Autowired
    private ServerMainController controller;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    public Response executeTestWithPattern(String filename,
                                           String code,
                                           Language language,
                                           String ruleCode,
                                           String ruleName,
                                           String ruleType,
                                           String entityChecked,
                                           String pattern,
                                           boolean logOutput) {
        Request request = new RequestBuilder()
            .setFilename(filename)
            .setLanguage(stringFromLanguage(language))
            .setFileEncoding("utf-8")
            .setCodeBase64(encodeBase64(code))
            .setLogOutput(logOutput)
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId(ruleName)
                        .setContentBase64(encodeBase64(ruleCode))
                        .setLanguage(stringFromLanguage(language))
                        .setType(ruleType)
                        .setEntityChecked(entityChecked)
                        .setPattern(pattern)
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
                                Language language,
                                String ruleCode,
                                String ruleName,
                                String ruleType,
                                String entityChecked,
                                boolean logOutput) {
        return executeTestWithPattern(filename, code, language, ruleCode, ruleName, ruleType, entityChecked, null, logOutput);
    }

    public Response executeTestWithRules(String filename,
                                         String code,
                                         Language language,
                                         List<Rule> rules,
                                         boolean logOutput) {
        Request request = new RequestBuilder()
            .setFilename(filename)
            .setLanguage(stringFromLanguage(language))
            .setFileEncoding("utf-8")
            .setCodeBase64(encodeBase64(code))
            .setLogOutput(logOutput)
            .setRules(rules)
            .createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
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
                    line = line.substring(0, violationFixEdit.start.col - 1) + violationFixEdit.content + line.substring(violationFixEdit.end.col - 1, line.length());
                    lines.set(lineIndex, line);
                }
            }
            if (violationFixEdit.editType.equalsIgnoreCase("add")) {
                int lineIndex = violationFixEdit.start.line - 1;
                String line = lines.get(lineIndex);
                line = line.substring(0, violationFixEdit.start.col - 1) + violationFixEdit.content + line.substring(violationFixEdit.start.col - 1, line.length());
                lines.set(lineIndex, line);
            }
            if (violationFixEdit.editType.equalsIgnoreCase("remove")) {
                int lineIndex = violationFixEdit.start.line - 1;
                String line = lines.get(lineIndex);
                line = line.substring(0, violationFixEdit.start.col - 1) + line.substring(violationFixEdit.end.col - 1, line.length());
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
