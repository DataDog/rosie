package io.codiga.metrics;


import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.codiga.utils.EnvironmentUtils.*;

/**
 * Implement the metrics reporting interface for Datadog.
 */
public class MetricsDatadog implements MetricsInterface {

    private final static String DEFAULT_HOST = "localhost";
    private final static String DEFAULT_PORT = "8125";
    private final Logger logger = LoggerFactory.getLogger(MetricsDatadog.class);
    private final NonBlockingStatsDClient datadogClient;


    public MetricsDatadog() {
        Optional<String> metrixPrefix = getEnvironmentValue(METRICS_PREFIX);
        NonBlockingStatsDClientBuilder builder = new NonBlockingStatsDClientBuilder();

        if (metrixPrefix.isPresent()) {
            logger.info(String.format("Initializing metrics with prefix %s", metrixPrefix.get()));
            builder = builder.prefix(metrixPrefix.get());
        } else {
            logger.info(String.format("No prefix to set for datadog metrics"));
        }
        builder = builder
            .hostname(getEnvironmentValue(DATADOG_HOSTNAME).orElse(DEFAULT_HOST))
            .port(Integer.parseInt(getEnvironmentValue(DATADOG_PORT).orElse(DEFAULT_PORT)));

        datadogClient = builder.build();
    }

    @Override
    public void incrementMetric(String metricName) {
//        logger.info(String.format("incrementing metric %s", metricName));
        this.datadogClient.increment(metricName);
    }

    public void histogramValue(String metricName, double value) {
        this.datadogClient.histogram(metricName, value);
    }

    public void recordDistribution(String metricName, long value) {
        this.datadogClient.distribution(metricName, value);
    }
}
