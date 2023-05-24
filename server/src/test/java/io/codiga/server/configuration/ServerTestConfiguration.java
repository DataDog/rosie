package io.codiga.server.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.codiga.server.services.InjectorService;
import io.codiga.server.services.InjectorServiceTestImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class ServerTestConfiguration {

    private Logger logger = LoggerFactory.getLogger(ServerTestConfiguration.class);

    @Bean
    public InjectorService injectorService() {
        logger.info("loading test configuration");
        return new InjectorServiceTestImpl();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
            // converts Java variables to snake_case JSON; and vice-versa
            .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy())
            // converts unknown values to a default enum value (i.e. UNKNOWN)
            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
    }
}
