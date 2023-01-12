package io.codiga.model.ast.javascript;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.AstString;
import io.codiga.model.ast.common.FunctionDefinition;
import io.codiga.model.ast.common.FunctionDefinitionParameters;
import org.antlr.v4.runtime.ParserRuleContext;


public class JavaScriptFunctionExpression extends FunctionDefinition {


    public JavaScriptFunctionExpression(AstString name,
                                        FunctionDefinitionParameters functionDefinitionParameters,
                                        AstElement content,
                                        ParserRuleContext ruleContext,
                                        ParserRuleContext root) {
        super(AST_ELEMENT_TYPE_FUNCTION_EXPRESSION, name, functionDefinitionParameters, content, ruleContext, root);
    }
}
