package io.codiga.metrics;

public interface MetricsInterface {
    public void incrementMetric(String metricName);

    public void histogramValue(String metricName, double value);
}
