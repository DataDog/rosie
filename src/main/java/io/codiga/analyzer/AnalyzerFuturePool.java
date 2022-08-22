package io.codiga.analyzer;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnalyzerFuturePool {
    private static AnalyzerFuturePool _INSTANCE;
    public ExecutorService service;

    private AnalyzerFuturePool() {
    }

    public static AnalyzerFuturePool getInstance() {
        if (_INSTANCE == null) {
            _INSTANCE = new AnalyzerFuturePool();
        }
        return _INSTANCE;
    }

    public void initialize() {
        service = Executors.newFixedThreadPool(10);
    }

}
