package io.codiga.analyzer.languages;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static io.codiga.utils.EnvironmentUtils.ANALYSIS_TIMEOUT;
import static io.codiga.utils.EnvironmentUtils.getEnvironmentValueAsLong;

public class AnalyzerCommon {

    public final static String COMMENT_SHARP = "#";
    private final long DEFAULT_TIMEOUT_MS = 1000;

    protected long getTimeout() {
        return getEnvironmentValueAsLong(ANALYSIS_TIMEOUT).orElse(DEFAULT_TIMEOUT_MS);
    }

    public List<Long> getCommentsLine(String code, String commentStart) {
        String commentLine = String.format("%scodiga-disable", commentStart);

        ArrayList<Long> comments = new ArrayList<>();
        Scanner scanner = new Scanner(code);
        long lineNumber = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String lineStripped = line.replaceAll(" ", "");
            if (lineStripped.equalsIgnoreCase(commentLine)) {
                comments.add(lineNumber);
            }
            lineNumber = lineNumber + 1;
        }
        scanner.close();
        return comments;
    }
}
