package io.codiga.analyzer;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.codiga.utils.EnvironmentUtils.ANALYSIS_THREADS;
import static io.codiga.utils.EnvironmentUtils.getEnvironmentValueAsNumber;

public class AnalyzerFuturePool {
    private static AnalyzerFuturePool _INSTANCE;
    public ExecutorService service;

    private final static int DEFAULT_THREADS = 20;

    private AnalyzerFuturePool() {
    }

    public static AnalyzerFuturePool getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new AnalyzerFuturePool();
            _INSTANCE.initialize();
        }
        return _INSTANCE;
    }

    private void initialize() {
        service = Executors.newFixedThreadPool(
            getEnvironmentValueAsNumber(ANALYSIS_THREADS)
                .orElse(DEFAULT_THREADS));
    }

}
