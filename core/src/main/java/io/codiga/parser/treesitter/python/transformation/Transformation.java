package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

public interface Transformation {

    public <T extends Object> T transform(Node node, TreeSitterParsingContext parsingContext);
}
