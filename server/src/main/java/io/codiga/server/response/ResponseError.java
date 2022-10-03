package io.codiga.server.response;

public class ResponseError {
    public String message;
    public int line;

    public ResponseError() {

    }

    public ResponseError(String message, int line) {
        this.message = message;
        this.line = line;
    }
}
