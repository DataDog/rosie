package io.codiga.parser.treesitter.utils;

import ai.serenade.treesitter.Node;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreeSitterNodeUtils {
    public static TreeSitterPythonTypes getNodeType(Node node) {
        return TreeSitterPythonTypes.NODE_TYPE_TO_ENUMERATION.getOrDefault(node.getType(), TreeSitterPythonTypes.UNKNOWN);
    }


    /**
     * Get all children of a node.
     *
     * @param node
     * @return
     */
    public static List<Node> getNodeChildren(Node node) {
        List<Node> res = new ArrayList<>(node.getChildCount());
        for (int i = 0; i < node.getChildCount(); i++) {
            res.add(node.getChild(i));
        }
        return res;
    }

    /**
     * Get the list of children of a given type
     *
     * @param node
     * @param type
     * @return
     */
    public static List<Node> getNodeChildren(Node node, TreeSitterPythonTypes type) {
        List<Node> res = new ArrayList<>(node.getChildCount());
        for (int i = 0; i < node.getChildCount(); i++) {
            Node child = node.getChild(i);
            if (getNodeType(child) == type) {
                res.add(node.getChild(i));
            }
        }
        return res;
    }

    /**
     * Return the first child that has the given type
     *
     * @param node
     * @param type
     * @return
     */
    public static Optional<Node> getNodeChild(Node node, TreeSitterPythonTypes type) {
        for (int i = 0; i < node.getChildCount(); i++) {
            Node child = node.getChild(i);
            if (getNodeType(child) == type) {
                return Optional.of(node.getChild(i));
            }
        }
        return Optional.empty();
    }

}
