package io.codiga.analyzer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.codiga.utils.EnvironmentUtils.ANALYSIS_THREADS;
import static io.codiga.utils.EnvironmentUtils.getEnvironmentValueAsNumber;

public class AnalyzerFuturePool {

    private final static Logger LOGGER = LoggerFactory.getLogger(Analyzer.class);
    private final static int DEFAULT_THREADS = Runtime.getRuntime().availableProcessors() > 2 ? Runtime.getRuntime().availableProcessors() : 1;
    private static AnalyzerFuturePool _INSTANCE;
    public ExecutorService service;

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
        int nbThreads = getEnvironmentValueAsNumber(ANALYSIS_THREADS).orElse(DEFAULT_THREADS);
        LOGGER.info(String.format("[AnalyzerFuturePool] creating a thread pool with %s threads", nbThreads));
        service = Executors.newFixedThreadPool(nbThreads);
    }
}
