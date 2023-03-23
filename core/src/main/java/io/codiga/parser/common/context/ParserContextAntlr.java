package io.codiga.parser.common.context;

import io.codiga.analyzer.ast.AstUtils;
import io.codiga.model.common.Position;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.ParserRuleContext;

public class ParserContextAntlr implements ParserContext {

    @Getter
    @Setter
    ParserRuleContext root;
    

    @Getter
    @Setter
    ParserRuleContext node;

    @Getter
    @Setter
    String code;

    @Override
    public Position getStartPosition() {
        return AstUtils.getStartPosition(this.node);
    }

    @Override
    public Position getEndPosition() {
        return AstUtils.getEndPosition(this.node);
    }

    @Override
    public int getStartIndex() {
        return node.start.getStartIndex();
    }

    @Override
    public int getEndIndex() {
        return node.stop.getStopIndex();
    }
}
