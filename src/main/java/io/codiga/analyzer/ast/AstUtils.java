package io.codiga.analyzer.ast;

import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstUtils {


    private static final Logger logger = LoggerFactory.getLogger(AstUtils.class);


    public static Position getStartPosition(ParserRuleContext context) {
        return new Position(context.start.getLine(), context.start.getCharPositionInLine());
    }

    public static Position getEndPosition(ParserRuleContext context) {
        Position endPosition = new Position(context.start.getLine(), context.start.getCharPositionInLine() + context.getText().length());
        return endPosition;
    }

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
