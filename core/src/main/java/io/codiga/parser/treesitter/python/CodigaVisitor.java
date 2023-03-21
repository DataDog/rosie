package io.codiga.parser.treesitter.python;

import ai.serenade.treesitter.Languages;
import ai.serenade.treesitter.Node;
import ai.serenade.treesitter.Parser;
import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.python.*;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static io.codiga.parser.treesitter.python.transformation.ExprToFunctionCall.transformExprToFunctionCall;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class CodigaVisitor {
    private static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CodigaVisitor.class.getName());

    private final String code;
    // List of all AST elements
    public List<Assignment> assignments;
    public List<FromStatement> fromStatements;
    public List<ImportStatement> importStatements;
    public List<PythonIfStatement> ifStatements;
    public List<TryStatement> tryStatements;
    public List<PythonForStatement> forStatements;
    public List<PythonFunctionDefinition> functionDefinitions;
    public List<FunctionCall> functionCalls;
    public List<PythonClassDefinition> classDefinitions;
    // To build the context
    Stack<PythonFunctionDefinition> visitedFunctionDefinitions;
    Stack<PythonClassDefinition> visitedClassDefinitions;
    Stack<PythonIfStatement> visitedIfStatements;
    Stack<TryStatement> visitedTryStatements;
    List<AstElement> visitedImportStatements;
    private Node root;

    public CodigaVisitor(String code) {
        this.code = code;

        // Initialize the list of all elements being visited
        assignments = new ArrayList<>();
        fromStatements = new ArrayList<>();
        importStatements = new ArrayList<>();
        ifStatements = new ArrayList<>();
        tryStatements = new ArrayList<>();
        forStatements = new ArrayList<>();
        functionDefinitions = new ArrayList<>();
        functionCalls = new ArrayList<>();
        classDefinitions = new ArrayList<>();

        // Initialize the visited elements
        visitedFunctionDefinitions = new Stack();
        visitedIfStatements = new Stack<>();
        visitedTryStatements = new Stack<>();
        visitedImportStatements = new ArrayList<>();
        visitedClassDefinitions = new Stack();
    }


    public void parse() {
        try {
            Parser parser = new Parser();
            parser.setLanguage(Languages.python());
            Node rootNode = parser.parseString(code).getRootNode();
            TreeSitterParsingContext parsingContext = new TreeSitterParsingContext(code, rootNode);
            walk(rootNode, parsingContext);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            LOGGER.severe("cannot decode and parse the code");
        }
    }


    private void walk(Node node, TreeSitterParsingContext parsingContext) {
        var nodeType = getNodeType(node);

        switch (nodeType) {
            case CALL: {
                var functionCallOptional = transformExprToFunctionCall(node, parsingContext);
                functionCallOptional.ifPresent(pythonFunctionCall -> this.functionCalls.add(pythonFunctionCall));
            }
        }


        for (int i = 0; i < node.getChildCount(); i++) {
            walk(node.getChild(i), parsingContext);
        }
    }
}
