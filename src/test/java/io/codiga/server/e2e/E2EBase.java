package io.codiga.server.e2e;

import io.codiga.model.Language;
import io.codiga.server.ServerMainController;
import io.codiga.server.configuration.ServerTestConfiguration;
import io.codiga.server.e2e.python.RequestTimeoutTest;
import io.codiga.server.request.Request;
import io.codiga.server.request.RequestBuilder;
import io.codiga.server.request.RuleBuilder;
import io.codiga.server.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static io.codiga.model.utils.ModelUtils.stringFromLanguage;
import static io.codiga.utils.Base64Utils.encodeBase64;

/**
 * Base class for all tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class E2EBase {

    @Autowired
    private ServerMainController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    protected final Logger logger = LoggerFactory.getLogger(RequestTimeoutTest.class);


    public Response executeTest(String filename,
                                String code,
                                Language language,
                                String ruleCode,
                                String ruleName,
                                String ruleType,
                                String entityChecked) {
        Request request = new RequestBuilder()
            .setFilename(filename)
            .setLanguage(stringFromLanguage(language))
            .setFileEncoding("utf-8")
            .setCodeBase64(encodeBase64(code))
            .setRules(
                List.of(
                    new RuleBuilder()
                        .setId(ruleName)
                        .setContentBase64(encodeBase64(ruleCode))
                        .setLanguage(stringFromLanguage(language))
                        .setType(ruleType)
                        .setEntityChecked(entityChecked)
                        .createRule()
                )
            ).createRequest();
        Response response = this.restTemplate.postForObject(
            "http://localhost:" + port + "/analyze", request,
            Response.class);
        return response;
    }
}
