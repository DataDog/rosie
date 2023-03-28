package io.codiga.utils;

import java.util.Optional;

public class EnvironmentUtils {

    // List of all environment variables to use in the system
    public final static String ANALYSIS_THREADS = "ANALYSIS_THREADS";
    public final static String ANALYSIS_TIMEOUT = "ANALYSIS_TIMEOUT";

    public final static String DATADOG_HOSTNAME = "localhost";
    public final static String DATADOG_PORT = "8125";

    public final static String PYTHON_FORCE_ANTLR = "PYTHON_FORCE_ANTLR";

    public final static String METRICS_PREFIX = "METRIX_PREFIX";
    public final static String ROLLBAR_ENVIRONMENT = "ROLLBAR_ENVIRONMENT";
    public final static String ROLLBAR_TOKEN = "ROLLBAR_TOKEN";

    /**
     * Get an environment variable. If it does not exits, returns Optional.empty()
     *
     * @param variable
     * @return
     */
    public static Optional<String> getEnvironmentValue(String variable) {
        return Optional.ofNullable(System.getenv(variable));
    }

    public static Optional<Integer> getEnvironmentValueAsNumber(String variable) {
        try {
            return getEnvironmentValue(variable).map(Integer::parseInt);
        } catch (NumberFormatException nbe) {
            return Optional.empty();
        }
    }

    public static Optional<Long> getEnvironmentValueAsLong(String variable) {
        try {
            return getEnvironmentValue(variable).map(Long::parseLong);
        } catch (NumberFormatException nbe) {
            return Optional.empty();
        }
    }
}
