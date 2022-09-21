package io.codiga.server.guice;

import com.google.inject.AbstractModule;
import io.codiga.errorreporting.ErrorReportingDummy;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsDummy;
import io.codiga.metrics.MetricsInterface;

public class ServerGuiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MetricsInterface.class).to(MetricsDummy.class);
        bind(ErrorReportingInterface.class).to(ErrorReportingDummy.class);
    }
}
