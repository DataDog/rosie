package io.codiga.analyzer.ast.languages.python.treesitter;

import ai.serenade.treesitter.Languages;
import ai.serenade.treesitter.Node;
import ai.serenade.treesitter.Parser;
import io.codiga.analyzer.ast.utils.AntlrUtils;
import io.codiga.parser.treesitter.utils.TreeSitterInit;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class PythonTestUtils extends AntlrUtils {

    private static Logger LOGGER = Logger.getLogger(TreeSitterInit.class.getName());

    public Node parseCode(String code) {
        try {
            TreeSitterInit.init();
            Parser parser = new Parser();
            parser.setLanguage(Languages.python());
            return parser.parseString(code).getRootNode();
        } catch (FileNotFoundException fe) {
            LOGGER.severe("Current architecture is not handled at this time");
            return null;
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            LOGGER.severe("cannot decode and parse the code");
        }

        return null;
    }
}
