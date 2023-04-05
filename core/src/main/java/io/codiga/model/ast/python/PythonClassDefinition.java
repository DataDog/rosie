package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstElementTypes;
import io.codiga.model.ast.common.AstString;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class PythonClassDefinition extends AstElement {
    public PythonDecorator[] decorators;
    public AstString name;
    public AstString[] parentClasses;
    public AstElement content;


    public PythonClassDefinition(List<PythonDecorator> decoratorList,
                                 AstString name,
                                 List<AstString> parentClasses,
                                 AstElement content,
                                 ParserRuleContext ruleContext,
                                 ParserRuleContext root) {
        super(AstElementTypes.CLASS_DEFINITION, ruleContext, root);
        this.name = name;
        this.decorators = decoratorList.stream().toArray(PythonDecorator[]::new);
        this.parentClasses = parentClasses.stream().toArray(AstString[]::new);
        this.content = content;
    }

    public PythonClassDefinition(List<PythonDecorator> decoratorList,
                                 AstString name,
                                 List<AstString> parentClasses,
                                 AstElement content,
                                 ParserContext context) {
        super(AstElementTypes.CLASS_DEFINITION, context);
        this.name = name;
        this.decorators = decoratorList.stream().toArray(PythonDecorator[]::new);
        this.parentClasses = parentClasses.stream().toArray(AstString[]::new);
        this.content = content;
    }
}
