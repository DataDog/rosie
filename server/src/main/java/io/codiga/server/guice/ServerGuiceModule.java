package io.codiga.server.guice;

import com.google.inject.AbstractModule;
import io.codiga.errorreporting.ErrorReportingDatadog;
import io.codiga.errorreporting.ErrorReportingInterface;
import io.codiga.metrics.MetricsDatadog;
import io.codiga.metrics.MetricsInterface;

public class ServerGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MetricsInterface.class).to(MetricsDatadog.class);
        bind(ErrorReportingInterface.class).to(ErrorReportingDatadog.class);
    }

}
