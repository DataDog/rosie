package io.codiga.server.configuration;

import io.codiga.server.services.InjectorService;
import io.codiga.server.services.InjectorServiceTestImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServerTestConfiguration {

    private Logger logger = LoggerFactory.getLogger(ServerTestConfiguration.class);

    @Bean
    public InjectorService injectorService() {
        logger.info("loading test configuration");
        return new InjectorServiceTestImpl();
    }
}
