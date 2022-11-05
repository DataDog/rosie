package io.codiga.model.context;

import io.codiga.model.ast.common.AstElement;
import lombok.Builder;

import java.util.List;

public class PythonNodeContext extends Context {
    public AstElement currentFunction;
    public AstElement currentTryBlock;
    public AstElement[] imports;
    private List<AstElement> importsList;

    @Builder(builderMethodName = "buildPythonNodeContext")
    public PythonNodeContext(AstElement currentFunction,
                             AstElement currentTryBlock,
                             List<AstElement> importsList,
                             String code) {
        super(code);
        this.currentFunction = currentFunction;
        this.currentTryBlock = currentTryBlock;
        this.imports = new AstElement[importsList.size()];
        imports = importsList.toArray(this.imports);
    }

}
