package io.codiga.server.request;

/**
 * Represents options you pass to a request. This is totally optional today.
 */
public class RequestOptions {
    public boolean useTreeSitter;
    public boolean logOutput;


    public RequestOptions(boolean logOutput, boolean useTreeSitter) {
        this.logOutput = logOutput;
        this.useTreeSitter = useTreeSitter;
    }


}
