package io.codiga.parser.common.context;

import ai.serenade.treesitter.Node;
import io.codiga.model.common.Position;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@EqualsAndHashCode
public class ParserContextTreeSitter implements ParserContext {
    @Getter
    @Setter
    Node root;

    @Getter
    @Setter
    Node node;

    @Getter
    @Setter
    String code;

    @Override
    public Position getStartPosition() {
        return new Position(0, 0);
    }

    @Override
    public Position getEndPosition() {
        return new Position(0, 0);
    }

    @Override
    public int getStartIndex() {
        return node.getStartByte();
    }

    @Override
    public int getEndIndex() {
        return node.getStartByte();
    }
}
