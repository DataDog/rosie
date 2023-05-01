package io.codiga.cli.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.cli.model.IgnorePaths;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

public class PathUtils {
    /**
     * Get a list of paths to ignore
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static List<String> getPathsFromIgnorePaths(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), IgnorePaths.class).ignorePaths.stream().toList();
    }

    /**
     * Determine if a file path is valid within a glob/non-glob string path
     *
     * @param ignorePath - a path (glob or non-glob) string to ignore
     * @param filePath - the file path you want to compare
     * @return true if the filePath matches the ignorePath
     */
    public static Boolean checkIfPathMatches(String ignorePath, Path filePath) {
        if (ignorePath.matches(".*[\\[\\]{}*?].*")) {
            return FileSystems.getDefault().getPathMatcher("glob:" + ignorePath).matches(filePath);
        } else {
            return filePath.startsWith(ignorePath);
        }
    }

    /**
     * Determine if a file path is valid against a list of glob/non-glob string paths
     *
     * @param ignorePaths a list of (glob or non-glob) string to ignore
     * @param filePath - the file path you want to compare
     * @return true if the filePath matches any of the ignorePaths
     */
    public static Boolean checkIfPathMatches(List<String> ignorePaths, Path filePath) {
        return ignorePaths.stream().anyMatch(path -> checkIfPathMatches(path, filePath));
    }
}
