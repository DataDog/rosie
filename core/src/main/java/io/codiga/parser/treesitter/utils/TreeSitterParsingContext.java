package io.codiga.parser.treesitter.utils;

import ai.serenade.treesitter.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class TreeSitterParsingContext {
    @Getter
    @Setter
    private String code;

    @Getter
    private Node rootNode;


    public String getStringForNode(Node node) {
        return code.substring(node.getStartByte(), node.getEndByte());
    }
}
