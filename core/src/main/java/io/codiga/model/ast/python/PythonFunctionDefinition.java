package io.codiga.model.ast.python;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;


public class PythonFunctionDefinition extends AstElement {

    public AstString name;
    public AstString returnType;
    public FunctionDefinitionParameters parameters;
    public PythonDecorator[] decorators;


    public boolean isAsync;


    public PythonFunctionDefinition(boolean isAsync,
                                    List<PythonDecorator> decoratorList,
                                    AstString name,
                                    FunctionDefinitionParameters functionDefinitionParameters,
                                    AstString returnType,
                                    ParserRuleContext ruleContext,
                                    ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_DEFINITION, ruleContext, root);
        this.isAsync = isAsync;
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.returnType = returnType;
        this.decorators = decoratorList.stream().toArray(PythonDecorator[]::new);

    }
}
