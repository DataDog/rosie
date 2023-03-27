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


    @Setter
    @Builder.Default
    Position startPosition = null; // used only if we want to override the position

    @Setter
    @Builder.Default
    Position endPosition = null; // used only if we want to override the position

    @Getter
    @Setter
    String code;

    @Getter
    @Setter
    PositionFinder positionFinder;

    @Override
    public Position getStartPosition() {
        Node n = this.node;
        if (this.startPosition != null) {
            return this.startPosition;
        }
        var treeSitterPosition = n.getStartPosition();
        return new Position(treeSitterPosition.row + 1, treeSitterPosition.column + 1);
    }

    @Override
    public Position getEndPosition() {
        Node n = node;
        if (this.endPosition != null) {
            return endPosition;
        }

        var treeSitterPosition = n.getEndPosition();
        return new Position(treeSitterPosition.row + 1, treeSitterPosition.column + 1);
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
