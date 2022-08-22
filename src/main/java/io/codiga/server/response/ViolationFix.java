package io.codiga.server.response;

import java.util.List;

public class ViolationFix {
    public String description;
    public List<ViolationFixEdit> edits;


    public ViolationFix(String description, List<ViolationFixEdit> edits) {
        this.description = description;
        this.edits = edits;
    }
}
