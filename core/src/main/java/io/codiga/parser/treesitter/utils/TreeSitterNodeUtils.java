package io.codiga.parser.treesitter.utils;

import ai.serenade.treesitter.Node;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;

public class TreeSitterNodeUtils {
    public static TreeSitterPythonTypes getNodeType(Node node) {
        return TreeSitterPythonTypes.NODE_TYPE_TO_ENUMERATION.getOrDefault(node.getType(), TreeSitterPythonTypes.UNKNOWN);
    }
}
