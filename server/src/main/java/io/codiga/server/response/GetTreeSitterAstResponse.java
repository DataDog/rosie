package io.codiga.server.response;

import io.codiga.model.ast.common.TreeSitterAstElement;

import java.util.List;

public class GetTreeSitterAstResponse {
    public TreeSitterAstElement result;
    public List<String> errors;


    public GetTreeSitterAstResponse(TreeSitterAstElement result, List<String> errors) {
        this.result = result;
        this.errors = errors;
    }
}
