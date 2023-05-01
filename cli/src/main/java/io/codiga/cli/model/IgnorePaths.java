package io.codiga.cli.model;

import java.util.List;

/**
 * This class is used only to serialized/deserialized the JSON file being passed as an argument.
 */
public class IgnorePaths {
    public List<String> ignorePaths;

    public IgnorePaths() {
        this.ignorePaths = List.of();
    }

    public IgnorePaths(List ignorePaths) {
        this.ignorePaths = IgnorePaths.this.ignorePaths;
    }
}
