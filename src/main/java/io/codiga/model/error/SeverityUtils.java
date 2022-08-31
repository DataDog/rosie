package io.codiga.model.error;

import java.util.Map;

public class SeverityUtils {
    private final static Map<String, Severity> STRING_TO_SEVERITY = Map.of(
        "critical", Severity.CRITICAL,
        "error", Severity.ERROR,
        "warning", Severity.WARNING,
        "informational", Severity.INFORMATIONAL
    );

    public static Severity severityFromString(String severityString) {
        return STRING_TO_SEVERITY.getOrDefault(severityString, Severity.UNKNOWN);
    }
}