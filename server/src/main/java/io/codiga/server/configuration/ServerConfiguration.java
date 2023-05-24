package io.codiga.server.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.codiga.server.services.InjectorService;
import io.codiga.server.services.InjectorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ServerConfiguration {

    public static final int WARMUP_LOOPS = 10;
    private Logger logger = LoggerFactory.getLogger(ServerConfiguration.class);

    @Bean
    public InjectorService injectorService() {
        logger.info("Creating injector service");
        return new InjectorServiceImpl();
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
