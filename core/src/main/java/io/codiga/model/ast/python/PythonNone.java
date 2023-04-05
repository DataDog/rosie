package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.common.context.ParserContext;

public class PythonNone extends AstElement {


    public String value = "None";

    public PythonNone(ParserContext parserContext) {

        super(AstElementTypes.NONE, parserContext);
    }

}
