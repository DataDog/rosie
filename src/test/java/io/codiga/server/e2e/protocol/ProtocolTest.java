package io.codiga.server.e2e.protocol;

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

import static io.codiga.model.ErrorCode.ERROR_LANGUAGE_MISMATCH;
import static io.codiga.server.constants.Languages.*;
import static io.codiga.server.response.ResponseErrors.CODE_NOT_BASE64;
import static io.codiga.server.response.ResponseErrors.LANGUAGE_NOT_SUPPORTED;
import static io.codiga.utils.Base64Utils.encodeBase64;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class ProtocolTest {
    @Autowired
    private ServerMainController controller;

    Logger logger = LoggerFactory.getLogger(ProtocolTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    String pythonCode = """            
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)
                            """;

    String ruleCode = """
        function visit(node) {
            var hasTimeout = false;
            for (var i = 0 ; i < node.arguments().size() ; i++){
                const argument = node.arguments().get(i);
                if(argument.name().isPresent() && argument.name().get() == "timeout") {
                    hasTimeout = true;
                }
            }
            if(!hasTimeout){
                reportError(node.line(), 10, node.line(), 11, "timeout not defined", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    public void testLanguageNotSupported() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_JAVA)
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
        logger.info("response: " + response.errors);
        assertEquals(0, response.ruleResponses.size());
        assertEquals(1, response.errors.size());
        assertEquals(LANGUAGE_NOT_SUPPORTED, response.errors.get(0));
    }

    @Test
    public void testLanguageMismatch() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_PYTHON)
            .setFileEncoding("utf-8")
            .setCodeBase64(encodeBase64(pythonCode))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId("python-timeout")
                        .setContentBase64(encodeBase64(ruleCode))
                        .setLanguage(LANGUAGE_JAVA)
                        .setType(RULE_TYPE_FUNCTION_CALL)
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        logger.info("response: " + response.errors);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.errors.size());
        assertEquals(ERROR_LANGUAGE_MISMATCH, response.ruleResponses.get(0).errors.get(0));
        assertEquals("python-timeout", response.ruleResponses.get(0).identifier);
    }

    @Test
    public void testInvalidCode() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_PYTHON)
            .setFileEncoding("utf-8")
            .setCodeBase64("][^#@#")
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
        logger.info("response: " + response.errors);
        assertEquals(0, response.ruleResponses.size());
        assertEquals(1, response.errors.size());
        assertEquals(CODE_NOT_BASE64, response.errors.get(0));
    }
}
