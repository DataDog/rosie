package io.codiga.model.ast;


import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class AstElement {
    public Position start;
    public Position end;
    private ParserRuleContext parserRuleContext;
    private ParserRuleContext root;

    public AstElement(Position start, Position end, ParserRuleContext parserRuleContext, ParserRuleContext root) {
        this.start = start;
        this.end = end;
        this.parserRuleContext = parserRuleContext;
        this.root = root;
    }

    private List<ParseTree> getNodesFromType(ParseTree parseTree, Class classType) {
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

    public List<ParseTree> getNodes(Class classType) {
        return getNodesFromType(this.root, classType);
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
}
