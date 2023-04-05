package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.parser.common.context.ParserContext;

import java.util.List;

public class PythonDictionary extends AstElement {

    public PythonDictionaryElement[] elements;


    public PythonDictionary(List<PythonDictionaryElement> elementsList, ParserContext parserContext) {
        super(AstElementTypes.DICTIONARY, parserContext);
        this.elements = elementsList.stream().toArray(PythonDictionaryElement[]::new);
    }


}
