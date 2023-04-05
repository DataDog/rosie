package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.common.context.ParserContext;

public class PythonNot extends AstElement {

    public AstElement value;


    public PythonNot(AstElement value, ParserContext parserContext) {
        super(AstElementTypes.NOT, parserContext);
        this.value = value;
    }

}
