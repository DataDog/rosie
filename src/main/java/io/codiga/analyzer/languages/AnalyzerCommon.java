package io.codiga.analyzer.languages;

import static io.codiga.utils.EnvironmentUtils.ANALYSIS_TIMEOUT;
import static io.codiga.utils.EnvironmentUtils.getEnvironmentValueAsLong;

public class AnalyzerCommon {

    private final long DEFAULT_TIMEOUT_MS = 1000;

    protected long getTimeout() {
        return getEnvironmentValueAsLong(ANALYSIS_TIMEOUT).orElse(DEFAULT_TIMEOUT_MS);
    }
}
