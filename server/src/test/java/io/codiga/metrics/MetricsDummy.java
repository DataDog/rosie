package io.codiga.metrics;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implement the metrics reporting interface for Datadog.
 */
public class MetricsDummy implements MetricsInterface {

    private final Logger logger = LoggerFactory.getLogger(MetricsDummy.class);

    @Override
    public void incrementMetric(String metricName) {
        logger.info(String.format("not incrementing metric %s", metricName));
    }

    @Override
    public void histogramValue(String metricName, double value) {
        logger.info(String.format("not changing histogram %s", metricName));
    }

    @Override
    public void recordDistribution(String metricName, long value) {
            
    }
}
