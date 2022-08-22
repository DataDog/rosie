package io.codiga.server.response;

import java.util.List;

public class Response {
    public List<Violation> violations;
    public List<ResponseError> errors;
    public String error;

    public Response(List<Violation> violations, List<ResponseError> responseErrors, String error) {
        this.violations = violations;
        this.errors = responseErrors;
        this.error = error;
    }
}
