package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import org.antlr.v4.runtime.ParserRuleContext;


public class JavaScriptFunctionDefinition extends FunctionDefinition {

    public boolean isAsync;

    public JavaScriptFunctionDefinition(boolean isAsync,
                                        AstString name,
                                        FunctionDefinitionParameters functionDefinitionParameters,
                                        AstElement content,
                                        ParserRuleContext ruleContext,
                                        ParserRuleContext root) {
        super(name, functionDefinitionParameters, content, ruleContext, root);
        this.isAsync = isAsync;
    }
}
