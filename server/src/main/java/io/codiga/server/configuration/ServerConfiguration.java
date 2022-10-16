package io.codiga.server.configuration;

import io.codiga.server.services.InjectorService;
import io.codiga.server.services.InjectorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    public static final int WARMUP_LOOPS = 10;
    private Logger logger = LoggerFactory.getLogger(ServerConfiguration.class);

    @Bean
    public InjectorService injectorService() {
        logger.info("Creating injector service");
        return new InjectorServiceImpl();
    }
}
