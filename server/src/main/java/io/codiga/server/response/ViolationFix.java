package io.codiga.server.response;

import java.util.List;

public class ViolationFix {
    public String description;
    public List<ViolationFixEdit> edits;

    public ViolationFix() {
        this.description = null;
        this.edits = null;
    }

    public ViolationFix(String description, List<ViolationFixEdit> edits) {
        this.description = description;
        this.edits = edits;
    }

    @Override
    public String toString() {
        return "ViolationFix{" +
            "description='" + description + '\'' +
            ", edits=" + edits +
            '}';
    }
}
