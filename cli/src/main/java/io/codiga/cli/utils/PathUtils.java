package io.codiga.cli.utils;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class PathUtils {
    /**
     * Determine if a file path is valid within a glob/non-glob string path
     *
     * @param ignorePath - a path (glob or non-glob) string to ignore
     * @param filePath   - the file path you want to compare
     * @return true if the filePath matches the ignorePath
     */
    public static Boolean checkIfPathMatches(String ignorePath, String filePath) {
        if (ignorePath.matches(".*[\\[\\]{}*?].*")) {
            // we don't consider paths with ./ or /. or .. when matching the file path
            if (filePath.contains("..") || filePath.contains("./") || filePath.contains("/.")) {
                return false;
            }
            var path = Path.of(removeLeadingSlash(filePath));
            return FileSystems.getDefault().getPathMatcher("glob:" + ignorePath).matches(path);
        } else {
            return filePath.startsWith(ignorePath);
        }
    }

    /**
     * Determine if a file path is valid against a list of glob/non-glob string paths
     *
     * @param ignorePaths a list of (glob or non-glob) string to ignore
     * @param filePath    - the file path you want to compare
     * @return true if the filePath matches any of the ignorePaths
     */
    public static Boolean checkIfPathMatches(List<String> ignorePaths, String filePath) {
        return ignorePaths.stream().anyMatch(path -> checkIfPathMatches(path, filePath));
    }

    private static String removeLeadingSlash(String path) {
        return path.startsWith("/") ? path.replaceFirst("/", "") : path;
    }
}
