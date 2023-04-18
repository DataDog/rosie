package io.codiga.errorreporting;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.utils.EnvironmentUtils.*;

/**
 * This is an error reporting mechanism for Datadog. What is does is emit
 * an error indicating there was a mistake and log the line. This way, engineer
 * can look at the error.
 */
public class ErrorReportingDatadog implements ErrorReportingInterface {

    private final static String DEFAULT_HOST = "localhost";
    private final static String DEFAULT_PORT = "8125";
    private final static String ERROR_RAISED = "error-raised";
    private final Logger logger = LoggerFactory.getLogger(ErrorReportingDatadog.class);
    private final NonBlockingStatsDClient datadogClient;

    public ErrorReportingDatadog() {
        Optional<String> metrixPrefix = getEnvironmentValue(METRICS_PREFIX);
        NonBlockingStatsDClientBuilder builder = new NonBlockingStatsDClientBuilder();

        if (metrixPrefix.isPresent()) {
            logger.info(String.format("Initializing error reporting with prefix %s", metrixPrefix.get()));
            builder = builder.prefix(metrixPrefix.get());
        } else {
            logger.info("No prefix to set for datadog error reporting");
        }
        builder = builder
            .hostname(getEnvironmentValue(DATADOG_HOSTNAME).orElse(DEFAULT_HOST))
            .port(Integer.parseInt(getEnvironmentValue(DATADOG_PORT).orElse(DEFAULT_PORT)));

        datadogClient = builder.build();
    }

    @Override
    public void reportError(Throwable t) {
        this.datadogClient.increment(ERROR_RAISED);
        logger.error("[ErrorReportingDatadog]", t);
    }

    @Override
    public void reportError(Throwable t, String description) {
        this.datadogClient.increment(ERROR_RAISED);
        logger.error(String.format("[ErrorReportingDatadog] %s", description), t);
    }

    @Override
    public void reportMessage(String message) {
        this.datadogClient.increment(ERROR_RAISED);
        logger.error(String.format("[ErrorReportingDatadog] %s", message));
    }
}
