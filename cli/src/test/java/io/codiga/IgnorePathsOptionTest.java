package io.codiga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.cli.Main;
import io.codiga.cli.model.Result;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class IgnorePathsOptionTest {

  ClassLoader classLoader = getClass().getClassLoader();

  private final Logger log = Logger.getLogger("Test");

  public static Result getResults(String path) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(new File(path), Result.class);
  }

  @TempDir static Path tempDir;

  @BeforeAll
  public static void init() {}

  @AfterAll
  public static void done() {}

  @Test
  @DisplayName("Check the total number of violations for the sample project with no ignored paths")
  public void testSampleProjectViolations() throws IOException {
    String outputLocation = tempDir.toAbsolutePath() + "/output.json";
    Main.main(
        new CmdOptionBuilder()
            .directoryPath("src/test/resources/python-project")
            .rulesPath("src/test/resources/rules.json")
            .outputPath(outputLocation)
            .treeSitter(true)
            .testMode(true)
            .build());
    var results = getResults(outputLocation);

    assertEquals(6, results.violations.size());
    assertEquals(0, results.ruleResultsWithError.size());
  }

  @Test
  @DisplayName("Test ignoring a single directory")
  public void testIgnorePathOptionSingle() throws IOException {
    String outputLocation = tempDir.toAbsolutePath() + "/output.json";
    Main.main(
        new CmdOptionBuilder()
            .directoryPath("src/test/resources/python-project")
            .rulesPath("src/test/resources/rules.json")
            .outputPath(outputLocation)
            .ignorePaths("src/test/resources/ignore-paths/single.json")
            .treeSitter(true)
            .testMode(true)
            .build());
    var results = getResults(outputLocation);

    assertEquals(5, results.violations.size());
    assertEquals(0, results.ruleResultsWithError.size());
    results.violations.forEach(
        v -> {
          assertTrue(v.filename != "foo/bar/baz/format-string.py");
        });
  }

  @Test
  @DisplayName("Test ignoring a multiple directories")
  public void testIgnorePathOptionMultiple() throws IOException {
    String outputLocation = tempDir.toAbsolutePath() + "/output.json";
    Main.main(
        new CmdOptionBuilder()
            .directoryPath("src/test/resources/python-project")
            .rulesPath("src/test/resources/rules.json")
            .outputPath(outputLocation)
            .ignorePaths("src/test/resources/ignore-paths/multiple.json")
            .treeSitter(true)
            .testMode(true)
            .build());
    var results = getResults(outputLocation);

    assertEquals(4, results.violations.size());
    assertEquals(0, results.ruleResultsWithError.size());
    results.violations.forEach(
        v -> {
          assertTrue(v.filename != "foo/bar/baz/format-string.py");
          assertTrue(v.filename != "food/use-import-from.py");
        });
  }

  @Test
  @DisplayName("Test ignoring a directory with a question mark glob")
  public void testIgnorePathOptionQuestionMark() throws IOException {
    String outputLocation = tempDir.toAbsolutePath() + "/output.json";
    Main.main(
        new CmdOptionBuilder()
            .directoryPath("src/test/resources/python-project")
            .rulesPath("src/test/resources/rules.json")
            .outputPath(outputLocation)
            .ignorePaths("src/test/resources/ignore-paths/question-mark-glob.json")
            .treeSitter(true)
            .testMode(true)
            .build());
    var results = getResults(outputLocation);

    assertEquals(5, results.violations.size());
    assertEquals(0, results.ruleResultsWithError.size());
    results.violations.forEach(
        v -> {
          assertTrue(v.filename != "food/use-import-from.py");
        });
  }

  @Test
  @DisplayName("Test ignoring a directory with a star glob")
  public void testIgnorePathOptionStar() throws IOException {
    String outputLocation = tempDir.toAbsolutePath() + "/output.json";
    Main.main(
        new CmdOptionBuilder()
            .directoryPath("src/test/resources/python-project")
            .rulesPath("src/test/resources/rules.json")
            .outputPath(outputLocation)
            .ignorePaths("src/test/resources/ignore-paths/star.json")
            .treeSitter(true)
            .testMode(true)
            .build());
    var results = getResults(outputLocation);

    assertEquals(4, results.violations.size());
    assertEquals(0, results.ruleResultsWithError.size());
    results.violations.forEach(
        v -> {
          assertTrue(v.filename != "foo/bar/baz/format-string.py");
          assertTrue(v.filename != "food/use-import-from.py");
        });
  }

  @Test
  @DisplayName("Test ignoring a directory with a star glob")
  public void testIgnorePathOptionSquareBracket() throws IOException {
    String outputLocation = tempDir.toAbsolutePath() + "/output.json";
    Main.main(
        new CmdOptionBuilder()
            .directoryPath("src/test/resources/python-project")
            .rulesPath("src/test/resources/rules.json")
            .outputPath(outputLocation)
            .ignorePaths("src/test/resources/ignore-paths/square-brackets.json")
            .treeSitter(true)
            .testMode(true)
            .build());
    var results = getResults(outputLocation);

    assertEquals(2, results.violations.size());
    assertEquals(0, results.ruleResultsWithError.size());
    results.violations.forEach(
        v -> {
          assertTrue(v.filename != "foo/subprocess.py");
          assertTrue(v.filename != "food/use-import-from.py");
        });
  }

  @Test
  @DisplayName("Test ignoring a directory with a star glob")
  public void testIgnorePathOptionCurlyBracket() throws IOException {
    String outputLocation = tempDir.toAbsolutePath() + "/output.json";
    Main.main(
        new CmdOptionBuilder()
            .directoryPath("src/test/resources/python-project")
            .rulesPath("src/test/resources/rules.json")
            .outputPath(outputLocation)
            .ignorePaths("src/test/resources/ignore-paths/curly-brackets.json")
            .treeSitter(true)
            .testMode(true)
            .build());
    var results = getResults(outputLocation);

    assertEquals(2, results.violations.size());
    assertEquals(0, results.ruleResultsWithError.size());
    results.violations.forEach(
        v -> {
          assertTrue(v.filename != "foo/subprocess.py");
          assertTrue(v.filename != "food/use-import-from.py");
        });
  }
}
