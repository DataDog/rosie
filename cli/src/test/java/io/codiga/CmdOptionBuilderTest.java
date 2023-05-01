package io.codiga;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.codiga.cli.model.OutputFormat;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CmdOptionBuilderTest {

  /**
   * Joins a list of commands into a single string to make comparing test output cleaner
   *
   * @param cmd
   * @return all the options joined together into a single string
   */
  String getCmdAsString(String[] cmd) {
    return String.join(" ", cmd);
  }

  @Test
  @DisplayName("Test that the builder commands are correct")
  public void testCmdOptionBuilder() throws IOException {
    var cmd =
        new CmdOptionBuilder()
            .directoryPath("/")
            .outputPath("/output.sarif")
            .outputFormat(OutputFormat.SARIF)
            .rulesPath("/rules.json")
            .treeSitter(true)
            .ignorePaths("foo/bar")
            .build();
    assertEquals(
        "--directory / --format SARIF --ignore-paths foo/bar --output /output.sarif --rules /rules.json --tree-sitter true",
        getCmdAsString(cmd));

    var cmd2 =
        new CmdOptionBuilder()
            .directoryPath("/foo")
            .outputPath("/output.json")
            .rulesPath("/rules.json")
            .treeSitter(false)
            .ignorePaths("bar")
            .build();
    assertEquals(
        "--directory /foo --ignore-paths bar --output /output.json --rules /rules.json --tree-sitter false",
        getCmdAsString(cmd2));
  }

  @Test
  @DisplayName("Test that the directory shows up correctly")
  public void testCmdOptionBuilderDirectory() throws IOException {
    var cmd = new CmdOptionBuilder().directoryPath("/a/path").build();
    assertEquals("--directory /a/path", getCmdAsString(cmd));

    var cmdEmptyString = new CmdOptionBuilder().directoryPath("").build();
    assertEquals("", getCmdAsString(cmdEmptyString));

    var cmdNull = new CmdOptionBuilder().directoryPath(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }

  @Test
  @DisplayName("Test that the rules path shows up correctly")
  public void testCmdOptionBuilderRulesPath() throws IOException {
    var cmd = new CmdOptionBuilder().rulesPath("/rules.json").build();
    assertEquals("--rules /rules.json", getCmdAsString(cmd));

    var cmdEmptyString = new CmdOptionBuilder().rulesPath("").build();
    assertEquals("", getCmdAsString(cmdEmptyString));

    var cmdNull = new CmdOptionBuilder().rulesPath(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }

  @Test
  @DisplayName("Test that the debug path shows up correctly")
  public void testCmdOptionBuilderDebug() throws IOException {
    var cmdTrue = new CmdOptionBuilder().debug(true).build();
    assertEquals("--debug true", getCmdAsString(cmdTrue));

    var cmdFalse = new CmdOptionBuilder().debug(false).build();
    assertEquals("--debug false", getCmdAsString(cmdFalse));

    var cmdNull = new CmdOptionBuilder().debug(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }

  @Test
  @DisplayName("Test that the treesitter boolean shows up correctly")
  public void testCmdOptionBuilderTreeSitter() throws IOException {
    var cmdTrue = new CmdOptionBuilder().treeSitter(true).build();
    assertEquals("--tree-sitter true", getCmdAsString(cmdTrue));

    var cmdFalse = new CmdOptionBuilder().treeSitter(false).build();
    assertEquals("--tree-sitter false", getCmdAsString(cmdFalse));

    var cmdNull = new CmdOptionBuilder().treeSitter(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }

  @Test
  @DisplayName("Test that the output path shows up correctly")
  public void testCmdOptionBuilderOutputPath() throws IOException {
    var cmd = new CmdOptionBuilder().outputPath("/output.sarif").build();
    assertEquals("--output /output.sarif", getCmdAsString(cmd));

    var cmdEmptyString = new CmdOptionBuilder().outputPath("").build();
    assertEquals("", getCmdAsString(cmdEmptyString));

    var cmdNull = new CmdOptionBuilder().outputPath(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }

  @Test
  @DisplayName("Test that the output format shows up correctly")
  public void testCmdOptionBuilderOutputFormat() throws IOException {
    var cmdSarif = new CmdOptionBuilder().outputFormat(OutputFormat.SARIF).build();
    assertEquals("--format SARIF", getCmdAsString(cmdSarif));

    var cmdJson = new CmdOptionBuilder().outputFormat(OutputFormat.JSON).build();
    assertEquals("--format JSON", getCmdAsString(cmdJson));

    var cmdNull = new CmdOptionBuilder().outputFormat(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }

  @Test
  @DisplayName("Test that the ignore paths path show up correctly")
  public void testCmdOptionBuilderIgnorePaths() throws IOException {
    var cmd = new CmdOptionBuilder().ignorePaths("/ignore-paths.json").build();
    assertEquals("--ignore-paths /ignore-paths.json", getCmdAsString(cmd));
    
    var cmdEmptyString = new CmdOptionBuilder().ignorePaths("").build();
    assertEquals("", getCmdAsString(cmdEmptyString));

    var cmdNull = new CmdOptionBuilder().ignorePaths(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }

  @Test
  @DisplayName("Test that the test mode boolean show up correctly")
  public void testCmdOptionBuilderTestMode() throws IOException {
    var cmdTrue = new CmdOptionBuilder().testMode(true).build();
    assertEquals("--test-mode true", getCmdAsString(cmdTrue));

    var cmdFalse = new CmdOptionBuilder().testMode(false).build();
    assertEquals("--test-mode false", getCmdAsString(cmdFalse));

    var cmdNull = new CmdOptionBuilder().testMode(null).build();
    assertEquals("", getCmdAsString(cmdNull));
  }
}
