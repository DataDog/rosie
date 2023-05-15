package io.codiga.cli.utils;

import static io.codiga.utils.CompletableFutureUtils.sequence;
import static io.codiga.utils.OpenAiUtils.addFixes;

import io.codiga.analyzer.AnalyzerFuturePool;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.model.OpenAiSuggestionMode;
import io.codiga.model.error.Violation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class OpenAiUtils {
  public static final String OPENAI_HOST = "https://api.openai.com";
  public static final String API_PATH_CHAT = "v1/chat/completions";
  public static final String API_PATH_EDIT = "v1/edits";

  public static String getCode(String directory, String path) throws IOException {
    String res = null;
    try (FileInputStream fileInputStream =
        new FileInputStream(new File(Paths.get(directory, path).toUri()))) {
      res = new String(fileInputStream.readAllBytes());
    }
    return res;
  }


  /**
   * curl https://api.openai.com/v1/chat/completions \ -H "Content-Type: application/json" \ -H
   * "Authorization: Bearer $OPENAI_API_KEY" \ -d '{ "model": "gpt-3.5-turbo", "messages": [{"role":
   * "user", "content": "Say this is a test!"}], "temperature": 0.7 }'
   */
  public static ViolationWithFilename addFixesForCli(
      ViolationWithFilename violation, String codeDirectory, OpenAiSuggestionMode genFixesMode)
      throws IOException, InterruptedException {

    String code = getCode(codeDirectory, violation.filename);
    var v = new Violation(violation.start, violation.end, violation.message, violation.severity, violation.category);
    var result = addFixes(v, violation.filename, code, genFixesMode);
    return ViolationWithFilename.builder()
        .rule(violation.rule)
        .fixes(result.fixes)
        .filename(violation.filename)
        .end(result.end)
        .start(result.start)
        .severity(result.severity)
        .category(result.category)
        .message(result.message)
        .build();
  }

  /**
   * generate list of missing fixes for OpenAI. If a violation does not have a fix, we will add it.
   *
   * @param originalList - the original list of violation with fix.
   * @param directory - the directory that contains the code
   * @return a list of violations with more fixes
   */
  public static List<ViolationWithFilename> generateMissingFixesForCli(
      List<ViolationWithFilename> originalList,
      String directory,
      OpenAiSuggestionMode genFixesMode) {
    ExecutorService executorService = AnalyzerFuturePool.getInstance().service;
    var foo =
        originalList.stream()
            .map(
                violation -> {
                  if (violation.fixes.isEmpty()) {
                    return CompletableFuture.supplyAsync(
                        new Supplier<ViolationWithFilename>() {
                          @Override
                          public ViolationWithFilename get() {
                            try {
                              return addFixesForCli(violation, directory, genFixesMode);
                            } catch (IOException | InterruptedException ioException) {
                              ioException.printStackTrace();
                              return violation;
                            }
                          }
                        },
                        executorService);
                  } else {
                    return CompletableFuture.completedFuture(violation);
                  }
                })
            .toList();
    CompletableFuture<List<ViolationWithFilename>> allFutures = sequence(foo);
    try {
        return allFutures.get(30, TimeUnit.SECONDS);
    } catch (TimeoutException | ExecutionException | InterruptedException ie) {
        System.out.println("cannot get the violations");
    }
    return List.of();

  }
}
