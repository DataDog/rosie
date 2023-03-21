package io.codiga.model.ast.common;

import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Optional;

import static io.codiga.analyzer.ast.AstUtils.getEndPosition;
import static io.codiga.analyzer.ast.AstUtils.getStartPosition;


public class AstString extends AstElement {

    public String value;
    public String str;

    public AstString(String value,
                     ParserRuleContext ruleContext,
                     ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_STRING, ruleContext, root);
        this.value = value;
        this.str = value;
    }

    public AstString(String value,
                     ParserContext parserContext) {
        super(AST_ELEMENT_TYPE_STRING, parserContext);
        this.value = value;
        this.str = value;
    }

    public AstString(String value,
                     Token token,
                     ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_STRING, token, root);
        this.value = value;
        this.str = value;
    }


    public AstString(String value,
                     ParserRuleContext startingContext,
                     ParserRuleContext endingContext,
                     ParserRuleContext ruleContext,
                     ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_STRING, getStartPosition(startingContext), getEndPosition(endingContext), ruleContext, root);
        this.value = value;
        this.str = value;
    }

    public static Optional<AstString> fromTerminalNode(TerminalNode terminalNode, ParserRuleContext root) {
        if (terminalNode != null && terminalNode.getText() != null) {
            return Optional.of(new AstString(terminalNode.getText(), terminalNode.getSymbol(), root));
        }
        return Optional.empty();
    }
}
