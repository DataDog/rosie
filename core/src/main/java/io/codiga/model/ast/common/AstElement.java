package io.codiga.model.ast.common;


import io.codiga.model.common.Position;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

import static io.codiga.analyzer.ast.AstUtils.getEndPosition;
import static io.codiga.analyzer.ast.AstUtils.getStartPosition;

public class AstElement {
    public static final String AST_ELEMENT_TYPE_STRING = "string";
    public static final String AST_ELEMENT_TYPE_LIST = "list";
    public static final String AST_ELEMENT_TYPE_ASSIGNMENT = "assignment";
    public static final String AST_ELEMENT_TYPE_FUNCTION_CALL = "functioncall";
    public static final String AST_ELEMENT_TYPE_FUNCTION_DEFINITION_PARAMETERS = "functiondefinitionparameters";
    public static final String AST_ELEMENT_TYPE_FUNCTION_DEFINITION_PARAMETER = "functiondefinitionparameter";
    public static final String AST_ELEMENT_TYPE_FUNCTION_EXCEPT_CLAUSE = "exceptclause";
    public static final String AST_ELEMENT_TYPE_FUNCTION_FINALLY_CLAUSE = "finallyclause";
    public static final String AST_ELEMENT_TYPE_FUNCTION_DEFINITION = "functiondefinition";
    public static final String AST_ELEMENT_TYPE_IMPORT_STATEMENT = "importstatement";
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
    public static final String AST_ELEMENT_TYPE_FROM_ELEMENT = "fromelement";
    public static final String AST_ELEMENT_TYPE_IMPORT_PACKAGE = "importpackage";
    public static final String AST_ELEMENT_TYPE_ARGUMENTS = "arguments";
    public Position start;
    public Position end;
    public int startIndex;
    public int endIndex;
    public String astType;
    private ParserRuleContext parserRuleContext;
    private ParserRuleContext root;

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
    }

    private List<ParseTree> getNodesFromType(ParseTree parseTree, Class classType) {
        List<ParseTree> result = new ArrayList<>();

        if (parseTree.getClass().isAssignableFrom(classType)) {
            result.add(parseTree);
        }

        for (int y = 0; y < parseTree.getChildCount(); y++) {
            ParseTree child = parseTree.getChild(y);

            result.addAll(getNodesFromType(child, classType));

        }
        return result;
    }

    public List<ParseTree> getNodes(Class classType) {
        return getNodesFromType(this.root, classType);
    }

    public String getText() {
        return this.parserRuleContext.getText();
    }

}
