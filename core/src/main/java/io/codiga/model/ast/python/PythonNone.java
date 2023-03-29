package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.common.context.ParserContext;

public class PythonNone extends AstElement {

    public AstElement[] elements;

    public String value = "None";

    public PythonNone(ParserContext parserContext) {

        super(AST_ELEMENT_TYPE_NONE, parserContext);
    }

}
