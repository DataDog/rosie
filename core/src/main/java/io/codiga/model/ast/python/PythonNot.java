package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.common.context.ParserContext;

public class PythonNot extends AstElement {

    public AstElement value;


    public PythonNot(AstElement value, ParserContext parserContext) {
        super(AST_ELEMENT_TYPE_NOT, parserContext);
        this.value = value;
    }

}
