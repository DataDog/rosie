package io.codiga.server.e2e.yaml.tsast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.GetTreeSitterAstResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetTsAstTest extends E2EBase {

    private static final Logger logger = LoggerFactory.getLogger(GetTsAstTest.class);


    String validCode = """
schema-version: v2
service: super-service
team: awesome-team
contacts:
    - type: slack
      contact: https://org.slack.com/AWESOME_CHANNEL""";




    @Test
    @DisplayName("Test getting a TreeSitter AST for a valid file")
    public void testFileAst() throws Exception {
        GetTreeSitterAstResponse response = executeTreesitterAstTest(validCode, Language.YAML, "utf-8");
        assertEquals(response.errors.size(), 0);
        assertNotNull(response.result);
        assertNotNull(response.result.astType);
    }

}
