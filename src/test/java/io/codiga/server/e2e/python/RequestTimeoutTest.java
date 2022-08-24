package io.codiga.server.e2e.python;

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

import static io.codiga.utils.Base64Utils.encodeBase64;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class RequestTimeoutTest {
    @Autowired
    private ServerMainController controller;

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
                reportError(node.line(), "timeout not defined", "CRITICAL", "SAFETY");
            }
        }
        """;

    @Test
    public void testPythonRequestTimeout() throws Exception {
        Request request = new RequestBuilder()
            .setFilename("bla.py")
            .setLanguage("python")
            .setFileEncoding("utf-8")
            .setCodeBase64(encodeBase64(pythonCode))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setDescription("new python rule")
                        .setIdentifier("python-timeout")
                        .setContentBase64(encodeBase64(ruleCode))
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        assertEquals(1, response.violations.size());
        assertEquals(1, response.violations.get(0).line);
        assertEquals("timeout not defined", response.violations.get(0).message);
    }
}
