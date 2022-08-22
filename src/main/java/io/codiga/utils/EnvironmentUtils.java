package io.codiga.utils;

import java.util.Optional;

public class EnvironmentUtils {

    // List of all environment variables to use in the system
    public final static String METRICS_PREFIX = "METRIX_PREFIX";
    public final static String DATADOG_HOSTNAME = "localhost";
    public final static String DATADOG_PORT = "8125";

    /**
     * Get an environment variable. If it does not exits, returns Optional.empty()
     * @param variable
     * @return
     */
    public static Optional<String> getEnvironmentValue(String variable) {
        return Optional.ofNullable(System.getenv(variable));
    }
}
