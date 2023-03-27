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
    @Builder.Default
    Integer startByte = null; // used only if we want to override the position

    @Getter
    @Setter
    @Builder.Default
    Integer endByte = null; // used only if we want to override the position

    @Getter
    @Setter
    String code;

    @Getter
    @Setter
    PositionFinder positionFinder;

    @Override
    public Position getStartPosition() {
        Node n = this.node;
        if (this.startByte != null) {
            return positionFinder.getCodePosition(this.startByte + 1);
        }
        return positionFinder.getCodePosition(n.getStartByte() + 1);
    }

    @Override
    public Position getEndPosition() {
        Node n = node;
        if (this.endByte != null) {
            return positionFinder.getCodePosition(this.endByte + 1);
        }

        return positionFinder.getCodePosition(n.getEndByte() + 1);
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
