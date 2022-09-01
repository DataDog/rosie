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

}
