package io.codiga.analyzer.ast.utils;

import ai.serenade.treesitter.Node;

import java.util.ArrayList;
import java.util.List;

public class TreeSitterUtils {

    public static void printTree(Node rootNode) {
        System.out.println(rootNode.getNodeString());
    }

    static void getNodesFromTypeRec(Node node, String nodeType, List<Node> result) {
        if (node != null && node.getType() != null && node.getType().equalsIgnoreCase(nodeType)) {
            result.add(node);
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            getNodesFromTypeRec(node.getChild(i), nodeType, result);
        }
    }

    public static List<Node> getNodesFromType(Node rootNode, String nodeType) {
        List<Node> result = new ArrayList<>();

        getNodesFromTypeRec(rootNode, nodeType, result);
        return result;
    }


}
