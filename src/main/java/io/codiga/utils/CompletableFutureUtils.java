package io.codiga.utils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

/**
 * Utility functions to work with Future.
 */
public class CompletableFutureUtils {


    /**
     * Utility function to convert a list of future into a future with the list.
     * @param com
     * @return
     * @param <T>
     */
    public static<T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> com) {
        return CompletableFuture.allOf(com.toArray(new CompletableFuture[com.size()]))
            .thenApply(v -> com.stream()
                .map(CompletableFuture::join)
                .collect(toList())
            );
    }
}
