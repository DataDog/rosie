package io.codiga.server.response;

import io.codiga.model.error.FixType;
import java.util.List;

public class ViolationFix {
    public String description;
    public List<ViolationFixEdit> edits;
    public boolean isOpenAi = false;
    public FixType type;

    public ViolationFix(String description, List<ViolationFixEdit> edits, boolean isOpenAi, FixType type) {
        this.description = description;
        this.edits = edits;
        this.isOpenAi = isOpenAi;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ViolationFix{" +
            "description='" + description + '\'' +
            ", edits=" + edits +
            '}';
    }
}
