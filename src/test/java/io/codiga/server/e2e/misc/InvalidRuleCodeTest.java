package io.codiga.server.e2e.misc;

import io.codiga.server.ServerMainController;
import io.codiga.server.configuration.ServerTestConfiguration;
import io.codiga.server.request.Request;
import io.codiga.server.request.RequestBuilder;
import io.codiga.server.request.RuleBuilder;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static io.codiga.server.constants.Languages.LANGUAGE_PYTHON;
import static io.codiga.server.constants.Languages.RULE_TYPE_FUNCTION_CALL;
import static io.codiga.utils.Base64Utils.encodeBase64;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class InvalidRuleCodeTest {
    @Autowired
    private ServerMainController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(InvalidRuleCodeTest.class);

    String pythonCode = """            
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)
                            """;

    String ruleCode = """
        function visit(node) {
            const hasTimeout = node.arguments().filter(a => a.name && a.name == "timeout").length > 0;

            if(!hasTimeout){
                reportError(node.line, 10, node.line, 11, "timeout not defined", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    public void testInvalidRuleCode() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage("python")
            .setFileEncoding("utf-8")
            .setCodeBase64(encodeBase64(pythonCode))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId("python-timeout")
                        .setContentBase64(encodeBase64(ruleCode))
                        .setLanguage(LANGUAGE_PYTHON)
                        .setType(RULE_TYPE_FUNCTION_CALL)
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);

        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.ruleResponses.get(0).violations.size());
        assertEquals("Unknown identifier: arguments", response.ruleResponses.get(0).executionError);
    }
}