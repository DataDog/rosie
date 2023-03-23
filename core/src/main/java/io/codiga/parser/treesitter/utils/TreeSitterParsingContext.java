package io.codiga.parser.treesitter.utils;

import ai.serenade.treesitter.Node;
import io.codiga.parser.common.context.ParserContextTreeSitter;
import io.codiga.utils.PositionFinder;
import lombok.Getter;
import lombok.Setter;


public class TreeSitterParsingContext {
    @Getter
    @Setter
    private String code;

    @Getter
    private Node rootNode;

    @Getter
    private PositionFinder positionFinder;


    public TreeSitterParsingContext(String code, Node root) {
        this.code = code;
        this.rootNode = root;
        this.positionFinder = new PositionFinder(code);
    }

    public String getStringForNode(Node node) {
        return code.substring(node.getStartByte(), node.getEndByte());
    }

    public ParserContextTreeSitter getParserContextForNode(Node node) {
        return ParserContextTreeSitter
            .builder()
            .node(node)
            .root(this.rootNode)
            .code(this.code)
            .positionFinder(this.positionFinder)
            .build();
    }
}
