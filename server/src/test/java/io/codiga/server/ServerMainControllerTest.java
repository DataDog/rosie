package io.codiga.server;

import io.codiga.server.configuration.ServerTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
public class ServerMainControllerTest {
    @Autowired
    private ServerMainController controller;

    @Test
    public void testPing() throws Exception {
        assertThat(controller).isNotNull();
        assertEquals(controller.ping(), "pong");
    }
}
