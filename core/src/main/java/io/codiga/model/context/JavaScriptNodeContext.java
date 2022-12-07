package io.codiga.model.context;

import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import lombok.Builder;

import java.util.List;

public class JavaScriptNodeContext extends Context {
    public AstElement currentFunction;
    public AstElement currentTryBlock;

    public AstElement currentClass;
    public AstElement[] imports;
    public Assignment[] assignments;

    @Builder(builderMethodName = "buildJavaScriptNodeContext")
    public JavaScriptNodeContext(AstElement currentFunction,
                                 AstElement currentTryBlock,
                                 AstElement currentClass,
                                 List<AstElement> importsList,
                                 List<Assignment> assignmentsList,
                                 String code) {
        super(code);
        this.currentFunction = currentFunction;
        this.currentTryBlock = currentTryBlock;
        this.currentClass = currentClass;
        this.imports = new AstElement[importsList.size()];
        imports = importsList.toArray(this.imports);
        this.assignments = new Assignment[assignmentsList.size()];
        assignments = assignmentsList.toArray(this.assignments);
    }

}
