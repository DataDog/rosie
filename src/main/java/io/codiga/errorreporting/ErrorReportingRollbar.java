package io.codiga.errorreporting;

import com.rollbar.notifier.Rollbar;
import com.rollbar.notifier.config.Config;
import com.rollbar.notifier.config.ConfigBuilder;
import io.codiga.utils.Version;

import static io.codiga.utils.EnvironmentUtils.*;

public class ErrorReportingRollbar implements ErrorReportingInterface {
    Rollbar rollbar;

    public ErrorReportingRollbar() {
        Config config = ConfigBuilder.withAccessToken(getEnvironmentValue(ROLLBAR_TOKEN).orElse(null))
            .environment(getEnvironmentValue(ROLLBAR_ENVIRONMENT).orElse("unknown"))
            .codeVersion(Version.CURRENT_VERSION)
            .build();
        rollbar = new Rollbar(config);
    }

    @Override
    public void reportError(Throwable t) {
        rollbar.error(t);
    }

    @Override
    public void reportError(Throwable t, String description) {
        rollbar.error(t, description);
    }

    @Override
    public void reportMessage(String message) {
        rollbar.info(message);
    }
}
