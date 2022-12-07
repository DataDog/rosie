package io.codiga.model.ast.common;


import io.codiga.model.common.Position;
import io.codiga.model.context.Context;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.graalvm.polyglot.HostAccess;

import static io.codiga.analyzer.ast.AstUtils.getEndPosition;
import static io.codiga.analyzer.ast.AstUtils.getStartPosition;

public class AstElement {
    public static final String AST_ELEMENT_TYPE_STRING = "string";
    public static final String AST_ELEMENT_TYPE_LIST = "list";
    public static final String AST_ELEMENT_TYPE_ARRAY = "array";
    public static final String AST_ELEMENT_TYPE_OBJECT = "object";
    public static final String AST_ELEMENT_TYPE_OBJECT_ELEMENT = "object_element";
    public static final String AST_ELEMENT_TYPE_ASSIGNMENT = "assignment";
    public static final String AST_ELEMENT_TYPE_ASSIGNMENTS = "assignments";
    public static final String AST_ELEMENT_TYPE_CONTAINER = "container";
    public static final String AST_ELEMENT_TYPE_SEQUENCE = "sequence";
    public static final String AST_ELEMENT_TYPE_OPERATION = "operation";
    public static final String AST_ELEMENT_TYPE_FUNCTION_CALL = "functioncall";
    public static final String AST_ELEMENT_TYPE_FUNCTION_DEFINITION_PARAMETERS = "functiondefinitionparameters";
    public static final String AST_ELEMENT_TYPE_FUNCTION_DEFINITION_PARAMETER = "functiondefinitionparameter";
    public static final String AST_ELEMENT_TYPE_FUNCTION_EXCEPT_CLAUSE = "exceptclause";
    public static final String AST_ELEMENT_TYPE_FUNCTION_FINALLY_CLAUSE = "finallyclause";
    public static final String AST_ELEMENT_TYPE_FUNCTION_DEFINITION = "functiondefinition";
    public static final String AST_ELEMENT_TYPE_CLASS_DEFINITION = "classdefinition";
    public static final String AST_ELEMENT_TYPE_IMPORT_STATEMENT = "importstatement";
    public static final String AST_ELEMENT_TYPE_IMPORTED_NAME = "importedname";
    public static final String AST_ELEMENT_TYPE_COMPARISON = "comparison";
    public static final String AST_ELEMENT_TYPE_EXPRESSION = "expression";
    public static final String AST_ELEMENT_TYPE_ARGUMENT = "argument";
    public static final String AST_ELEMENT_TYPE_DECORATOR = "decorator";
    public static final String AST_ELEMENT_IF_STATEMENT = "ifstatement";
    public static final String AST_ELEMENT_FOR_STATEMENT = "forstatement";
    public static final String AST_ELEMENT_ELIF_STATEMENT = "elifstatement";
    public static final String AST_ELEMENT_ELSE_STATEMENT = "elifstatement";
    public static final String AST_ELEMENT_TYPE_FROM_STATEMENT = "fromstatement";
    public static final String AST_ELEMENT_TYPE_TRY_STATEMENT = "trystatement";
    public static final String AST_ELEMENT_TYPE_HTML_ELEMENT = "htmlelement";
    public static final String AST_ELEMENT_TYPE_HTML_ATTRIBUTE = "htmlattribute";
    public static final String AST_ELEMENT_TYPE_FROM_ELEMENT = "fromelement";
    public static final String AST_ELEMENT_TYPE_IMPORT_PACKAGE = "importpackage";
    public static final String AST_ELEMENT_TYPE_ARGUMENTS = "arguments";
    private final ParserRuleContext parserRuleContext;
    private final ParserRuleContext root;

    @HostAccess.Export
    public Position start;

    @HostAccess.Export
    public Position end;
    public int startIndex;
    public int endIndex;
    @HostAccess.Export
    public String astType;

    @HostAccess.Export
    public Context context;

    public AstElement(String astType,
                      ParserRuleContext parserRuleContext,
                      ParserRuleContext root) {
        this.astType = astType;
        this.start = getStartPosition(parserRuleContext);
        this.end = getEndPosition(parserRuleContext);
        this.startIndex = parserRuleContext.start.getStartIndex();
        this.endIndex = parserRuleContext.stop.getStopIndex();
        this.parserRuleContext = parserRuleContext;
        this.root = root;
        this.context = null;
    }

    public AstElement(String astType,
                      Token token,
                      ParserRuleContext root) {
        this.astType = astType;
        this.startIndex = token.getStartIndex();
        this.endIndex = token.getStopIndex();
        this.start = getStartPosition(token);
        this.end = getEndPosition(token);
        this.parserRuleContext = null;
        this.root = root;
        this.context = null;
    }

    public String getText() {
        return this.parserRuleContext.getText();
    }

    public void setContext(Context c) {
        this.context = c;
    }
}
