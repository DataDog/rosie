package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.common.context.ParserContext;

import java.util.List;

public class PythonTuple extends AstElement {

    public AstElement[] elements;

    public PythonTuple(List<? extends AstElement> listElements, ParserContext parserContext) {

        super(AST_ELEMENT_TYPE_TUPLE, parserContext);
        this.elements = new AstElement[listElements.size()];
        this.elements = listElements.toArray(elements);
    }

}
