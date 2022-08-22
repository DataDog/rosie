package io.codiga.model.error;


import java.util.List;

public record Fix(String description, List<Edit> edits) {
}
