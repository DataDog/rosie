package io.codiga.model.error;


import java.util.List;


public class Fix {
    public String description;
    public List<Edit> edits;

    public Fix(String description, List<Edit> edits) {
        this.description = description;
        this.edits = edits;
    }
}