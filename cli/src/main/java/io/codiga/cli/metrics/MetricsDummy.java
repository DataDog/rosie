package io.codiga.cli.metrics;


import io.codiga.metrics.MetricsInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implement the metrics reporting interface for Datadog.
 */
public class MetricsDummy implements MetricsInterface {

    private final Logger logger = LoggerFactory.getLogger(MetricsDummy.class);

    @Override
    public void incrementMetric(String metricName) {
    }

    @Override
    public void histogramValue(String metricName, double value) {
    }
}
