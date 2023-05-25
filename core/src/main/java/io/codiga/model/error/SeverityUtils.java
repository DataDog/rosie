package io.codiga.model.error;

import java.util.Map;

public class SeverityUtils {
    private final static Map<String, Severity> STRING_TO_SEVERITY = Map.of(
        "critical", Severity.ERROR,
        "crit", Severity.ERROR,
        "error", Severity.ERROR,
        "warning", Severity.WARNING,
        "warn", Severity.WARNING,
        "informational", Severity.NOTICE,
        "info", Severity.NOTICE,
        "notice", Severity.NOTICE,
        "note", Severity.NOTICE
    );

    public static Severity severityFromString(String severityString) {
        return STRING_TO_SEVERITY.getOrDefault(severityString.toLowerCase(), Severity.UNKNOWN);
    }
}
