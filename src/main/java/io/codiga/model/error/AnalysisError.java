package io.codiga.model.error;


import java.util.List;

public record AnalysisError(int line, String message, Severity severity, Category category, List<Fix> fixes) {

}