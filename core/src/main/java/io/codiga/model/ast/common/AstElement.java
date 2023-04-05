package io.codiga.model.ast.common;


import io.codiga.model.common.Position;
import io.codiga.model.context.Context;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.graalvm.polyglot.HostAccess;

import static io.codiga.analyzer.ast.AstUtils.getEndPosition;
import static io.codiga.analyzer.ast.AstUtils.getStartPosition;

public class AstElement {
    @HostAccess.Export
    public Position start;
    @HostAccess.Export
    public Position end;
    public int startIndex;
    public int endIndex;
    @HostAccess.Export
    public String astType;
    @HostAccess.Export
    public Context context;

    protected ParserContext parserContext = null;
    protected ParserRuleContext parserRuleContext = null;
    protected ParserRuleContext root = null;

    public AstElement(AstElementTypes astType,
                      ParserRuleContext parserRuleContext,
                      ParserRuleContext root) {
        this.astType = astType.label;
        this.start = getStartPosition(parserRuleContext);
        this.end = getEndPosition(parserRuleContext);
        this.startIndex = parserRuleContext.start.getStartIndex();
        this.endIndex = parserRuleContext.stop.getStopIndex();
        this.parserRuleContext = parserRuleContext;
        this.root = root;
        this.context = null;
    }

    public AstElement(AstElementTypes astType,
                      ParserContext parserContext) {
        this.astType = astType.label;
        this.start = parserContext.getStartPosition();
        this.end = parserContext.getEndPosition();
        this.startIndex = parserContext.getStartIndex();
        this.endIndex = parserContext.getEndIndex();
        this.context = null;
    }

    public AstElement(AstElementTypes astType,
                      Token token,
                      ParserRuleContext root) {
        this.astType = astType.label;
        this.startIndex = token.getStartIndex();
        this.endIndex = token.getStopIndex();
        this.start = getStartPosition(token);
        this.end = getEndPosition(token);
        this.parserRuleContext = null;
        this.root = root;
        this.context = null;
    }

    public AstElement(AstElementTypes astType,
                      Position startPosition,
                      Position endPosition,
                      ParserRuleContext parserRuleContext,
                      ParserRuleContext root) {
        this.astType = astType.label;
        this.start = startPosition;
        this.end = endPosition;
        this.startIndex = parserRuleContext.start.getStartIndex();
        this.endIndex = parserRuleContext.stop.getStopIndex();
        this.parserRuleContext = parserRuleContext;
        this.root = root;
        this.context = null;
    }

    public String getText() {
        return this.parserRuleContext.getText();
    }

    public void setContext(Context c) {
        this.context = c;
    }
}
