package io.codiga.parser.common.visitor;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.python.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CodigaVisitor {

    protected final String code;
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
    protected Stack<PythonFunctionDefinition> visitedFunctionDefinitions;
    protected Stack<PythonClassDefinition> visitedClassDefinitions;
    protected Stack<PythonIfStatement> visitedIfStatements;
    protected Stack<TryStatement> visitedTryStatements;
    protected List<AstElement> visitedImportStatements;
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

}
