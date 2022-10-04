package io.codiga.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import io.codiga.cli.model.Result;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {


    public static List<Path> filterFilesByExtensions(List<Path> paths, List<String> extensions) {
        return paths.stream().filter(p -> {
            String extension = Files.getFileExtension(p.getFileName().toString());
            return extensions.contains(extension);
        }).collect(Collectors.toList());
    }

    public static String getFileContent(Path path) throws IOException {
        return new String(java.nio.file.Files.readAllBytes(path));
    }


    public static void writeViolationsToFile(Path path, Result result) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(path.toFile(), result);
    }

}
