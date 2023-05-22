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

import static io.codiga.constants.Languages.*;
import static io.codiga.model.RuleErrorCode.ERROR_RULE_INVALID_RULE_TYPE;
import static io.codiga.model.RuleErrorCode.ERROR_RULE_LANGUAGE_MISMATCH;
import static io.codiga.server.response.ResponseErrors.*;
import static io.codiga.utils.Base64Utils.encodeBase64;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class ProtocolTest {
    Logger logger = LoggerFactory.getLogger(ProtocolTest.class);
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
    @Autowired
    private ServerMainController controller;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLanguageNotSupported() throws Exception {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.setFilename("bla.py");
        requestBuilder.setLanguage(LANGUAGE_JAVA);
        requestBuilder.setFileEncoding("utf-8");
        requestBuilder.setCode(encodeBase64(pythonCode));
        requestBuilder.setRules(
            List.of(
                new RuleBuilder()
                    .setId("python-timeout")
                    .setContentBase64(encodeBase64(ruleCode))
                    .setLanguage(LANGUAGE_PYTHON)
                    .setType(RULE_TYPE_AST)
                    .setEntityChecked(ENTITY_CHECKED_FUNCTION_CALL)
                    .createRule()
            )
        );
        Request request = requestBuilder.createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        logger.info("response: " + response.errors);
        assertEquals(0, response.ruleResponses.size());
        assertEquals(1, response.errors.size());
        assertEquals(ERROR_LANGUAGE_NOT_SUPPORTED, response.errors.get(0));
    }

    @Test
    public void testLanguageMismatch() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_PYTHON)
            .setFileEncoding("utf-8")
            .setCode(encodeBase64(pythonCode))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId("python-timeout")
                        .setContentBase64(encodeBase64(ruleCode))
                        .setLanguage(LANGUAGE_JAVA)
                        .setType(RULE_TYPE_AST)
                        .setEntityChecked(ENTITY_CHECKED_FUNCTION_CALL)
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        logger.info("response: " + response.errors);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.errors.size());
        assertEquals(ERROR_RULE_LANGUAGE_MISMATCH, response.ruleResponses.get(0).errors.get(0));
        assertEquals("python-timeout", response.ruleResponses.get(0).identifier);
    }

    @Test
    public void testInvalidRuleType() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_PYTHON)
            .setFileEncoding("utf-8")
            .setCode(encodeBase64(pythonCode))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId("python-timeout")
                        .setContentBase64(encodeBase64(ruleCode))
                        .setLanguage(LANGUAGE_PYTHON)
                        .setType("foobar")
                        .setEntityChecked(ENTITY_CHECKED_FUNCTION_CALL)
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        logger.info("response: " + response.errors);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(0, response.errors.size());
        assertEquals(ERROR_RULE_INVALID_RULE_TYPE, response.ruleResponses.get(0).errors.get(0));
        assertEquals("python-timeout", response.ruleResponses.get(0).identifier);
    }

    @Test
    public void testNoRules() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_PYTHON)
            .setFileEncoding("utf-8")
            .setCode(encodeBase64(pythonCode))
            .setRules(null)
            .createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        logger.info("response: " + response.errors);
        assertEquals(1, response.errors.size());
        assertEquals(ERROR_INVALID_REQUEST, response.errors.get(0));
    }

    @Test
    public void testInvalidCode() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_PYTHON)
            .setFileEncoding("utf-8")
            .setCode("][^#@#")
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId("python-timeout")
                        .setContentBase64(encodeBase64(ruleCode))
                        .setLanguage(LANGUAGE_PYTHON)
                        .setType(RULE_TYPE_AST)
                        .setEntityChecked(ENTITY_CHECKED_FUNCTION_CALL)
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        logger.info("response: " + response.errors);
        assertEquals(0, response.ruleResponses.size());
        assertEquals(1, response.errors.size());
        assertEquals(ERROR_CODE_NOT_BASE64, response.errors.get(0));
    }

    @Test
    public void testInvalidRuleCode() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage(LANGUAGE_PYTHON)
            .setFileEncoding("utf-8")
            .setCode(encodeBase64(pythonCode))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId("python-timeout")
                        .setContentBase64("22#@#@#232@@#%%")
                        .setLanguage(LANGUAGE_PYTHON)
                        .setType(RULE_TYPE_AST)
                        .setEntityChecked(ENTITY_CHECKED_FUNCTION_CALL)
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        logger.info("response: " + response.errors);
        assertEquals(0, response.ruleResponses.size());
        assertEquals(1, response.errors.size());
        assertEquals(ERROR_RULE_NOT_BASE64, response.errors.get(0));
    }
}
