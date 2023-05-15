package io.codiga.model.error;


import java.util.List;

public class Fix {
    public String description;
    public List<Edit> edits;
    public boolean isOpenAi = false;
    public FixType type;
    

    public Fix(String description, List<Edit> edits) {
        this.description = description;
        this.edits = edits;
        this.isOpenAi = false;
        this.type = FixType.REGULAR;
    }

    public Fix(String description, List<Edit> edits, boolean isOpenAi, FixType t) {
        this.description = description;
        this.edits = edits;
        this.isOpenAi = isOpenAi;
        this.type = t;
    }

    public void setOpenAi(boolean openAi) {
        isOpenAi = openAi;
    }
}
