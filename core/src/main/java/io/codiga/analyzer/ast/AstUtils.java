package io.codiga.analyzer.ast;

import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AstUtils {


    private static final Logger logger = LoggerFactory.getLogger(AstUtils.class);


    public static Position getStartPosition(ParserRuleContext context) {
        return new Position(context.start.getLine(), context.start.getCharPositionInLine() + 1);
    }

    public static Position getStartPosition(Token token) {
        return new Position(token.getLine(), token.getCharPositionInLine() + 1);
    }

    public static Position getEndPosition(Token token) {
        return new Position(token.getLine(), token.getCharPositionInLine() + 1 + token.getText().length());
    }

    public static Position getEndPosition(ParserRuleContext context) {
        // old code
        // Position endPosition = new Position(context.start.getLine(), context.start.getCharPositionInLine() + context.getText().length() + 1);

        ParseTree tmp = context;


        while (tmp.getChildCount() > 0) {
            tmp = tmp.getChild(tmp.getChildCount() - 1);
        }

        if (tmp instanceof TerminalNode) {
            TerminalNode terminalNode = (TerminalNode) tmp;
//            System.out.println("terminal node: " + terminalNode.getText() + ";size: " + terminalNode.getText() + " start: " + terminalNode.getSymbol().getStartIndex() + " stop: " + terminalNode.getSymbol().getStopIndex());


            return new Position(terminalNode.getSymbol().getLine(), terminalNode.getSymbol().getCharPositionInLine() + terminalNode.getText().length() + 1);
        }
        ParserRuleContext parserRuleContext = (ParserRuleContext) tmp;
        Position endPosition = new Position(parserRuleContext.start.getLine(), parserRuleContext.start.getCharPositionInLine() + context.getText().length() + 1);
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
