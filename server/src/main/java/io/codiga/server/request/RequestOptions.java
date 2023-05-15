package io.codiga.server.request;

import io.codiga.model.OpenAiSuggestionMode;

/**
 * Represents options you pass to a request. This is totally optional today.
 */
public class RequestOptions {
    public boolean useTreeSitter;
    public boolean logOutput;
    public OpenAiSuggestionMode openAiMode;


    public RequestOptions(boolean logOutput, boolean useTreeSitter, OpenAiSuggestionMode mode) {
        this.logOutput = logOutput;
        this.useTreeSitter = useTreeSitter;
        this.openAiMode = mode;
    }
    
}

