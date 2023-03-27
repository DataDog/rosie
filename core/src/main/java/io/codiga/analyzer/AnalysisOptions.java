package io.codiga.analyzer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class AnalysisOptions {

    @Getter
    @Setter
    @Builder.Default
    private boolean logOutput = false;

    @Setter
    @Getter
    @Builder.Default
    private boolean useTreeSitter = false;
}
