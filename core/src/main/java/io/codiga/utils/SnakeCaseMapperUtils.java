package io.codiga.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

/**
 * Used to
 */
public class SnakeCaseMapperUtils {
    public static ObjectMapper getSnakeCaseMapper() {
        return new ObjectMapper()
            // converts Java variables to snake_case JSON; and vice-versa
            .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy())
            // converts unknown values to a default enum value (i.e. UNKNOWN)
            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
    }
}
