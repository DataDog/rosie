package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.parser.common.context.ParserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PythonAssertStatement extends AstElement {

    private static final Logger logger = LoggerFactory.getLogger(PythonAssertStatement.class);

    public AstElement value;

    public PythonAssertStatement(AstElement value, ParserContext parserContext) {
        super(AST_ELEMENT_TYPE_ASSERT, parserContext);
        this.value = value;
    }
}
