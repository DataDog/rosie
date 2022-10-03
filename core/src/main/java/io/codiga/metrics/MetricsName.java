package io.codiga.metrics;

public final class MetricsName {

    public static String METRIC_PAGE_NOT_FOUND = "page-not-found";
    public static String METRIC_ANALYSIS_REQUEST = "analysis-request";
    public static String METRIC_RULE_EXECUTION_UNKNOWN_ERROR = "analysis-rule-unknown-error";
    public static String METRIC_EXCEPTION_UNHANDLED = "exception-unhandled";
    public static String METRIC_EXCEPTION_INVALID_INPUT_JSON = "exception-invalid-input-json";
    public static String METRIC_ANALYSIS_EXCEPTION = "exception-analysis-exception";
    public static String METRIC_INVALID_REQUEST = "invalid-request";
    public static String METRIC_INVALID_LANGUAGE = "invalid-language";
    public static String METRIC_PING_REQUEST = "ping-request";
    public static String METRIC_VERSION_REQUEST = "version-request";
    public static String METRIC_HISTOGRAM_REQUEST_ANALYSIS_TIME_TOTAL = "histogram-analysis-time-total";
    public static String METRIC_HISTOGRAM_REQUEST_ANALYSIS_TIME_PREFIX = "histogram-analysis-time";

    private MetricsName() {
    }


}