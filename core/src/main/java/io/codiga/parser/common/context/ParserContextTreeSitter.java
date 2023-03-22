package io.codiga.parser.common.context;

import ai.serenade.treesitter.Node;
import io.codiga.model.common.Position;
import io.codiga.utils.PositionFinder;
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

    @Getter
    @Setter
    PositionFinder positionFinder;

    @Override
    public Position getStartPosition() {
        return positionFinder.getCodePosition(node.getStartByte());
    }

    @Override
    public Position getEndPosition() {
        return positionFinder.getCodePosition(node.getEndByte());
    }

    @Override
    public int getStartIndex() {
        return node.getStartByte();
    }

    @Override
    public int getEndIndex() {
        return node.getEndByte();
    }
}
