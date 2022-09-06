package io.codiga.analyzer.ast;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {


    public List<ParseTree> getNodesFromType(ParseTree parseTree, Class classType) {
        List<ParseTree> result = new ArrayList<>();

        if (parseTree.getClass().isAssignableFrom(classType)) {
            result.add(parseTree);
        }

        for (int y = 0; y < parseTree.getChildCount(); y++) {
            ParseTree child = parseTree.getChild(y);

            result.addAll(getNodesFromType(child, classType));

        }
        return result;
    }


    void printTreeRec(ParseTree node, int nbSpaces) {

        String classString = String.format("%s%s", " ".repeat(nbSpaces), node.getClass());
        int spaces = 200 - classString.length();
        String line = String.format("%s%s%s", classString, " ".repeat(spaces), node.getText());

        System.out.println(line);
        for (int i = 0; i < node.getChildCount(); i++) {
            printTreeRec(node.getChild(i), nbSpaces + 2);
        }

    }

    public void printTree(ParseTree parseTree) {
        printTreeRec(parseTree, 0);
    }


}
