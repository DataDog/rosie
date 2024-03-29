package io.codiga.model.ast.python;

import io.codiga.model.ast.common.*;
import io.codiga.parser.common.context.ParserContext;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;


public class PythonFunctionDefinition extends AstElement {

    private final List<Assignment> assignmentList;
    public AstString name;
    public AstString returnType;
    public FunctionDefinitionParameters parameters;
    public PythonDecorator[] decorators;
    public Assignment[] assignments;
    public boolean isAsync;
    public AstElement content;


    public PythonFunctionDefinition(boolean isAsync,
                                    List<PythonDecorator> decoratorList,
                                    AstString name,
                                    FunctionDefinitionParameters functionDefinitionParameters,
                                    AstString returnType,
                                    AstElement content,
                                    ParserRuleContext ruleContext,
                                    ParserRuleContext root) {
        super(AstElementTypes.FUNCTION_DEFINITION, ruleContext, root);
        this.isAsync = isAsync;
        this.content = content;
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.returnType = returnType;
        this.decorators = decoratorList.stream().toArray(PythonDecorator[]::new);
        this.assignmentList = new ArrayList<>();
        this.assignments = new Assignment[0];
    }

    public PythonFunctionDefinition(boolean isAsync,
                                    List<PythonDecorator> decoratorList,
                                    AstString name,
                                    FunctionDefinitionParameters functionDefinitionParameters,
                                    AstString returnType,
                                    AstElement content,
                                    ParserContext context) {
        super(AstElementTypes.FUNCTION_DEFINITION, context);
        this.isAsync = isAsync;
        this.content = content;
        this.parameters = functionDefinitionParameters;
        this.name = name;
        this.returnType = returnType;
        this.decorators = decoratorList.stream().toArray(PythonDecorator[]::new);
        this.assignmentList = new ArrayList<>();
        this.assignments = new Assignment[0];
    }

    public void addAssignment(Assignment assignment) {
        this.assignmentList.add(assignment);
        this.assignments = this.assignmentList.stream().toArray(Assignment[]::new);
    }
}
