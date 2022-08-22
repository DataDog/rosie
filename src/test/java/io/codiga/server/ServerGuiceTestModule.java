package io.codiga.server;

import com.google.inject.AbstractModule;
import io.codiga.metrics.MetricsDummy;
import io.codiga.metrics.MetricsInterface;

public class ServerGuiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MetricsInterface.class).to(MetricsDummy.class);
    }
}
