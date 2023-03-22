package io.codiga.parser.treesitter.utils;

import java.io.*;
import java.util.logging.Logger;

public final class TreeSitterInit {

    static boolean isSharedLibraryLoaded = false;
    private static Logger LOGGER = Logger.getLogger(TreeSitterInit.class.getName());

    /**
     * Initialize tree-sitter and the shared library for testing purposes only
     */
    public static void init() throws FileNotFoundException {
        if (isSharedLibraryLoaded) {
            return;
        }
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
        if (os.toLowerCase().startsWith("linux")) {
            if (arch.equalsIgnoreCase("amd64")) {
                toLoad = "lib/tree-sitter/linux-amd64/libjava-tree-sitter.so";
            }
            if (arch.equalsIgnoreCase("arm64")) {
                toLoad = "lib/tree-sitter/linux-arm64/libjava-tree-sitter.so";
            }
        }

        if (toLoad == null) {
            throw new FileNotFoundException("shared library not found");
        }

        ClassLoader classLoader = TreeSitterInit.class.getClassLoader();
        File file = new File(classLoader.getResource(toLoad).getFile());
        InputStream is = classLoader.getResourceAsStream(toLoad);
        try {
            byte[] libContent = is.readAllBytes();
            File tmpFile = File.createTempFile("lib", "temp");
            FileOutputStream fileWriter = new FileOutputStream(tmpFile);
            fileWriter.write(libContent);
            fileWriter.close();
            String absolutePath = file.getAbsolutePath();

            LOGGER.info(String.format("Trying to load %s", absolutePath));
            System.load(tmpFile.getAbsolutePath());
            LOGGER.info(String.format("Library loaded from %s", absolutePath));

            // Delete the file
            tmpFile.delete();
            isSharedLibraryLoaded = true;
        } catch (IOException ioException) {
            LOGGER.info("blabla");
        }

    }
}
