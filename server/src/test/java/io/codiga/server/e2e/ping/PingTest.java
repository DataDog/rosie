package io.codiga.server.e2e.ping;

import io.codiga.server.ServerMainController;
import io.codiga.server.configuration.ServerTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class PingTest {
    @Autowired
    private ServerMainController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPing() throws Exception {
        assertThat(this.restTemplate.getForObject(
            "http://localhost:" + port + "/ping",
            String.class))
            .contains("pong");
    }
}
