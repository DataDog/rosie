package io.codiga.analyzer.ast;

import org.antlr.v4.runtime.tree.ParseTree;

public class AstUtils {


    public static void printTreeRec(ParseTree node, int nbSpaces) {

        String classString = String.format("%s%s", " ".repeat(nbSpaces), node.getClass());
        int spaces = 200 - classString.length();
        String line = String.format("%s%s%s", classString, " ".repeat(spaces), node.getText());

        System.out.println(line);
        for (int i = 0; i < node.getChildCount(); i++) {
            printTreeRec(node.getChild(i), nbSpaces + 2);
        }

    }
}
