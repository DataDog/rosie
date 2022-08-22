package io.codiga.server.configuration;

import io.codiga.server.services.InjectorService;
import io.codiga.server.services.InjectorServiceTestImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ServerTestConfiguration {

    @Bean
    public InjectorService testInjectorService() {
        return new InjectorServiceTestImpl();
    }
}
