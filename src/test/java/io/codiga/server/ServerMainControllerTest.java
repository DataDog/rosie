package io.codiga.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerMainControllerTest {
    @Autowired
    private ServerMainController controller;

    @LocalServerPort
    private int port;


    @Test
    public void testPing() throws Exception {
        assertThat(controller).isNotNull();
        assertEquals(controller.ping(), "pong");
    }
}
