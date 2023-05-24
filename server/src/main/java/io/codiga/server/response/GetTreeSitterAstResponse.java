package io.codiga.server.response;

import io.codiga.model.ast.common.TreeSitterAstElement;

import java.util.List;

/**
 * The response structure for a request to get a TreeSitter AST
 */
public class GetTreeSitterAstResponse {
    public TreeSitterAstElement result;
    public List<String> errors;

    public GetTreeSitterAstResponse() {
        this.result = null;
        this.errors = null;
    }

    public GetTreeSitterAstResponse(TreeSitterAstElement result, List<String> errors) {
        this.result = result;
        this.errors = errors;
    }
}
