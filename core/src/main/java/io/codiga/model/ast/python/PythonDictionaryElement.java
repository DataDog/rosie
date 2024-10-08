package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.common.context.ParserContext;

public class PythonDictionaryElement extends AstElement {

    public AstElement key;
    public AstElement value;


    public PythonDictionaryElement(AstElement key, AstElement value, ParserContext parserContext) {
        super(AstElementTypes.DICTIONARY_ELEMENT, parserContext);
        this.key = key;
        this.value = value;
    }


}
