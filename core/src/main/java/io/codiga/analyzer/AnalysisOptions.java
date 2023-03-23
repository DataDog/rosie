package io.codiga.analyzer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class AnalysisOptions {

    @Getter
    @Setter
    private boolean logOutput = false;

    @Setter
    @Getter
    private boolean useTreeSitter = false;
}
