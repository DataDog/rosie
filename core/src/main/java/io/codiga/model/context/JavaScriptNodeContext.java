package io.codiga.model.context;

import io.codiga.model.ast.common.AstElement;
import lombok.Builder;

import java.util.List;

public class JavaScriptNodeContext extends Context {
    public AstElement currentFunction;
    public AstElement currentTryBlock;

    public AstElement currentClass;
    public AstElement[] imports;
    private List<AstElement> importsList;

    @Builder(builderMethodName = "buildJavaScriptNodeContext")
    public JavaScriptNodeContext(AstElement currentFunction,
                                 AstElement currentTryBlock,
                                 AstElement currentClass,
                                 List<AstElement> importsList,
                                 String code) {
        super(code);
        this.currentFunction = currentFunction;
        this.currentTryBlock = currentTryBlock;
        this.currentClass = currentClass;
        this.imports = new AstElement[importsList.size()];
        imports = importsList.toArray(this.imports);
    }

}
