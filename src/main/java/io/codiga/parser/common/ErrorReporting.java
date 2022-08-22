package io.codiga.parser.common;

import io.codiga.model.error.AnalysisError;
import io.codiga.model.error.Category;
import io.codiga.model.error.Severity;
import org.graalvm.polyglot.HostAccess;

import java.util.ArrayList;
import java.util.List;

public class ErrorReporting {


    public List<AnalysisError> errors;



    public ErrorReporting() {
        this.errors = new ArrayList<>();
    }


    public List<AnalysisError> getErrors() {
        return this.errors;
    }

    @HostAccess.Export
    public void addError(int line, String message, String severity, String category) {
//            System.out.println("adding errors on line " + line);
        errors.add(new AnalysisError(line, message, Severity.CRITICAL, Category.BEST_PRACTICE, List.of()));
    }
}
