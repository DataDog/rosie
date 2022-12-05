package io.codiga.model.ast.python;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
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
        this.assignmentList = new ArrayList<>();
        this.assignments = new Assignment[0];
    }

    public void addAssignment(Assignment assignment) {
        this.assignmentList.add(assignment);
        this.assignments = this.assignmentList.stream().toArray(Assignment[]::new);
    }
}
