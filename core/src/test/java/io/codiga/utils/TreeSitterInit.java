package io.codiga.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

public final class TreeSitterInit {

    private static Logger LOGGER = Logger.getLogger(TreeSitterInit.class.getName());

    /**
     * Initialize tree-sitter and the shared library for testing purposes only
     */
    public static void init() throws FileNotFoundException {
        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String toLoad = null;
        LOGGER.info(String.format("Detected os %s, arch %s", os, arch));

        /**
         * Try to find the file to load for testing purposes.
         * Raise an exception if not found.
         */
        if (os.toLowerCase().startsWith("mac")) {
            if (arch.equalsIgnoreCase("aarch64")) {
                toLoad = "lib/tree-sitter/darwin-aarch64/libjava-tree-sitter.dylib";
            }
        }

        if (toLoad == null) {
            throw new FileNotFoundException("shared library not found");
        }

        ClassLoader classLoader = TreeSitterInit.class.getClassLoader();
        File file = new File(classLoader.getResource(toLoad).getFile());
        String absolutePath = file.getAbsolutePath();

        LOGGER.info(String.format("Trying to load %s", absolutePath));
        System.load(absolutePath);
        LOGGER.info(String.format("Library loaded from %s", absolutePath));
    }
}
