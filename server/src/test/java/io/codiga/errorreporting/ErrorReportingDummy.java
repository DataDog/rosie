package io.codiga.errorreporting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorReportingDummy implements ErrorReportingInterface {
    private static final Logger logger = LoggerFactory.getLogger(ErrorReportingDummy.class);

    public ErrorReportingDummy() {
        logger.info("[ErrorReportingDummy] initialize dummy exceptions");
    }

    @Override
    public void reportError(Throwable t) {
        logger.info(String.format("[ErrorReportingDummy] reporting exception %s", t.getMessage()));
    }

    @Override
    public void reportError(Throwable t, String description) {
        logger.info(String.format("[ErrorReportingDummy] reporting exception %s: %s", t.getMessage(), description));
    }

    @Override
    public void reportMessage(String message) {
        logger.info(String.format("[ErrorReportingDummy] message %s", message));
    }
}
