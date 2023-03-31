package io.codiga.model.context;

import io.codiga.model.ast.common.AstElement;
import lombok.Builder;

import java.util.List;
import java.util.Map;

public class PythonNodeContext extends Context {
    public AstElement currentFunction;
    public AstElement currentTryBlock;
    public AstElement currentClass;
    public AstElement[] imports;
    public AstElement[] assignments;

    @Builder(builderMethodName = "buildPythonNodeContext")
    public PythonNodeContext(AstElement currentFunction,
                             AstElement currentTryBlock,
                             AstElement currentClass,
                             List<AstElement> importsList,
                             List<? extends AstElement> assignmentsList,
                             String code,
                             Map<String, String> variables) {
        super(code, variables);
        this.currentFunction = currentFunction;
        this.currentTryBlock = currentTryBlock;
        this.currentClass = currentClass;
        this.imports = new AstElement[importsList.size()];
        imports = importsList.toArray(this.imports);
        this.assignments = new AstElement[assignmentsList.size()];
        assignments = assignmentsList.toArray(this.assignments);
    }

}
