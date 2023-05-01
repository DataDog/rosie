package io.codiga;

import static io.codiga.cli.utils.PathUtils.checkIfPathMatches;
import static io.codiga.cli.utils.PathUtils.getPathsFromIgnorePaths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PathUtilsTest {
  ClassLoader classLoader = getClass().getClassLoader();

  private final Logger log = Logger.getLogger("Test");

  @BeforeAll
  public static void init() {}

  @AfterAll
  public static void done() {}

  @Test
  @DisplayName("Parse a list of ignored paths")
  public void testIgnorePathsParsing() throws IOException {
    String file = "src/test/resources/ignore-paths/single.json";
    List<String> ignorePaths = getPathsFromIgnorePaths(file);
    assertEquals(1, ignorePaths.size());
    assertEquals("foo/bar/baz", ignorePaths.get(0));
  }
  
  @Test
  @DisplayName("Check that non glob paths are compared correctly")
  public void testNonGlobPaths() throws IOException {
    Path path = Path.of("foo/bar/baz.py");
    assertTrue(checkIfPathMatches("foo", path));
    assertTrue(checkIfPathMatches("foo/bar", path));
    assertTrue(checkIfPathMatches("foo/bar/baz.py", path));
    assertFalse(checkIfPathMatches("bar/baz.py", path));
    assertFalse(checkIfPathMatches("baz.py", path));
  }

  @Test
  @DisplayName("Check that star (*) glob paths are compared correctly")
  public void testStarGlobPaths() throws IOException {
    Path path = Path.of("foo/bar/baz.py");
    assertTrue(checkIfPathMatches("foo/bar/*.py", path));
    assertTrue(checkIfPathMatches("foo/**/*.py", path));
    assertTrue(checkIfPathMatches("foo/*/*.py", path));
    assertTrue(checkIfPathMatches("**/*.py", path));
    assertTrue(checkIfPathMatches("*/*/*.py", path));
    assertFalse(checkIfPathMatches("*.py", path));
  }

  @Test
  @DisplayName("Check that square bracket ([]) glob paths are compared correctly")
  public void testSquareBracketGlobPaths() throws IOException {
    Path path = Path.of("foo/bar/baz.py");
    assertTrue(checkIfPathMatches("**/[a-z]az.py", path));
    assertFalse(checkIfPathMatches("**/[c-z]az.py", path));
    assertTrue(checkIfPathMatches("**/[!c-z]az.py", path));
    assertFalse(checkIfPathMatches("**/[!ab]az.py", path));
  }

  @Test
  @DisplayName("Check that curly bracket ({}) glob paths are compared correctly")
  public void testCurlyBracketGlobPaths() throws IOException {
    Path path = Path.of("foo/bar/baz.py");
    assertTrue(checkIfPathMatches("**/{foo,bar}/**", path));
    assertFalse(checkIfPathMatches("**/{hello,world}/**", path));
    assertTrue(checkIfPathMatches("**/*.{py,py3}", path));
    assertFalse(checkIfPathMatches("**/*.{js,jsx}", path));
  }

  @Test
  @DisplayName("Check that question marks (?) glob paths are compared correctly")
  public void testQuestionMarkGlobPaths() throws IOException {
    Path path = Path.of("foo/bar/baz.py");
    assertTrue(checkIfPathMatches("foo/?ar/*.py", path));
    assertFalse(checkIfPathMatches("foo/?ar/*.py?", path));
  }
}
