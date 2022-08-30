package io.codiga.server.e2e.security;

import io.codiga.server.ServerMainController;
import io.codiga.server.configuration.ServerTestConfiguration;
import io.codiga.server.request.Request;
import io.codiga.server.request.RequestBuilder;
import io.codiga.server.request.RuleBuilder;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static io.codiga.model.ErrorCode.RULE_TIMEOUT;
import static io.codiga.server.constants.Languages.LANGUAGE_PYTHON;
import static io.codiga.server.constants.Languages.RULE_TYPE_FUNCTION_CALL;
import static io.codiga.utils.Base64Utils.encodeBase64;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class InfiniteLoopTest {
    @Autowired
    private ServerMainController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    String pythonCode = """            
        r = requests.get(w, verify=False)
                    """;

    String ruleCode = """
        function visit(node) {
            var a = 0;
            while(true) {
                a = a + 1;
                a = a - 1;
            }
        }
        """;

    @Test
    public void testInfiniteLoop() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage("python")
            .setFileEncoding("utf-8")
            .setCodeBase64(encodeBase64(pythonCode))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId("python-infinite")
                        .setLanguage(LANGUAGE_PYTHON)
                        .setType(RULE_TYPE_FUNCTION_CALL)
                        .setContentBase64(encodeBase64(ruleCode))
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(RULE_TIMEOUT, response.ruleResponses.get(0).errors.get(0));
    }
}
