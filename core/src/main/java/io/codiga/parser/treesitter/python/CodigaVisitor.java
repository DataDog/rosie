package io.codiga.parser.treesitter.python;

import ai.serenade.treesitter.Languages;
import ai.serenade.treesitter.Node;
import ai.serenade.treesitter.Parser;
import io.codiga.model.ast.common.Assignment;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.common.FunctionCall;
import io.codiga.model.ast.python.*;
import io.codiga.model.context.PythonNodeContext;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static io.codiga.parser.treesitter.python.transformation.AssignmentTransformation.transformAssignment;
import static io.codiga.parser.treesitter.python.transformation.CallTransformation.transformCall;
import static io.codiga.parser.treesitter.python.transformation.ClassDeclarationTransformation.transformClassDefinition;
import static io.codiga.parser.treesitter.python.transformation.DecoratedDefinitionTransformation.transformDecoratedDefinition;
import static io.codiga.parser.treesitter.python.transformation.ForStatementTransformation.transformForStatement;
import static io.codiga.parser.treesitter.python.transformation.FunctionDefinitionTransformation.transformFunctionDefinition;
import static io.codiga.parser.treesitter.python.transformation.IfStatementTransformation.transformIfStatement;
import static io.codiga.parser.treesitter.python.transformation.ImportFromStatement.transformImportFromStatement;
import static io.codiga.parser.treesitter.python.transformation.ImportStatement.transformImportStatement;
import static io.codiga.parser.treesitter.python.transformation.TryStatementTransformation.transformTryStatement;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.getNodeType;

public class CodigaVisitor {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(CodigaVisitor.class.getName());

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

    private PythonNodeContext buildContext() {
        PythonNodeContext res = PythonNodeContext.buildPythonNodeContext()
            .currentFunction(visitedFunctionDefinitions.isEmpty() ? null : visitedFunctionDefinitions.lastElement())
            .currentTryBlock(visitedTryStatements.size() > 0 ? visitedTryStatements.lastElement() : null)
            .currentClass(visitedClassDefinitions.size() > 0 ? visitedClassDefinitions.lastElement() : null)
            .code(this.code)
            .importsList(visitedImportStatements)
            .assignmentsList(assignments)
            .build();
        return res;
    }

    private void walkChildren(Node node, TreeSitterParsingContext parsingContext) {
        for (int i = 0; i < node.getChildCount(); i++) {
            walk(node.getChild(i), parsingContext);
        }
    }

    private void walk(Node node, TreeSitterParsingContext parsingContext) {
        var nodeType = getNodeType(node);

        switch (nodeType) {

            case ASSIGNMENT: {
                var transformedElementOptional = transformAssignment(node, parsingContext);
                transformedElementOptional.ifPresent(transformedElement -> {
                    transformedElement.setContext(buildContext());
                    this.assignments.add(transformedElement);
                });
                walkChildren(node, parsingContext);
                break;
            }


            case CALL: {
                var functionCallOptional = transformCall(node, parsingContext);
                if (functionCallOptional.isPresent()) {
                    var pythonFunctionCall = functionCallOptional.get();
                    pythonFunctionCall.setContext(buildContext());
                    this.functionCalls.add(pythonFunctionCall);
                }
                walkChildren(node, parsingContext);
                break;
            }

            case CLASS_DEFINITION: {
                var classDefinitionOptional = transformClassDefinition(node, parsingContext);
                if (classDefinitionOptional.isPresent()) {
                    var classDefinition = classDefinitionOptional.get();
                    classDefinition.setContext(buildContext());
                    this.classDefinitions.add(classDefinition);
                    this.visitedClassDefinitions.push(classDefinition);
                    walkChildren(node, parsingContext);
                    this.visitedClassDefinitions.pop();
                } else {
                    walkChildren(node, parsingContext);
                }
                break;
            }


            case DECORATED_DEFINITION: {
                var transformedElementOptional = transformDecoratedDefinition(node, parsingContext);
                if (transformedElementOptional.isPresent()) {
                    var transformedElement = transformedElementOptional.get();
                    transformedElement.setContext(buildContext());

                    if (transformedElement instanceof PythonFunctionDefinition) {
                        PythonFunctionDefinition pythonFunctionDefinition = (PythonFunctionDefinition) transformedElement;
                        this.functionDefinitions.add(pythonFunctionDefinition);
                        this.visitedFunctionDefinitions.push(pythonFunctionDefinition);
                        walkChildren(node, parsingContext);
                        this.visitedFunctionDefinitions.pop();
                    }

                    if (transformedElement instanceof PythonClassDefinition) {
                        PythonClassDefinition pythonClassDefinition = (PythonClassDefinition) transformedElement;
                        this.classDefinitions.add(pythonClassDefinition);
                        this.visitedClassDefinitions.push(pythonClassDefinition);
                        walkChildren(node, parsingContext);
                        this.visitedClassDefinitions.pop();
                    }

                } else {
                    walkChildren(node, parsingContext);
                }
                break;
            }


            case FOR_STATEMENT: {
                var transformedOptional = transformForStatement(node, parsingContext);
                if (transformedOptional.isPresent()) {
                    var res = transformedOptional.get();
                    this.forStatements.add(res);
                }
                walkChildren(node, parsingContext);
                break;
            }

            case FUNCTION_DEFINITION: {
                var transformedOptional = transformFunctionDefinition(node, parsingContext);
                if (transformedOptional.isPresent()) {
                    var res = transformedOptional.get();
                    this.functionDefinitions.add(res);
                    this.visitedFunctionDefinitions.push(res);
                    walkChildren(node, parsingContext);
                    this.visitedFunctionDefinitions.pop();
                } else {
                    walkChildren(node, parsingContext);
                }
                break;
            }

            case IF_STATEMENT: {
                var transformedOptional = transformIfStatement(node, parsingContext);
                if (transformedOptional.isPresent()) {
                    var res = transformedOptional.get();
                    this.ifStatements.add(res);
                    this.visitedIfStatements.push(res);
                    walkChildren(node, parsingContext);
                    this.visitedIfStatements.pop();
                } else {
                    walkChildren(node, parsingContext);
                }
                break;
            }

            case IMPORT_FROM_STATEMENT: {
                var transformedElementOptional = transformImportFromStatement(node, parsingContext);
                transformedElementOptional.ifPresent(res -> {
                    this.fromStatements.add(res);
                    this.visitedImportStatements.add(res);
                });
                walkChildren(node, parsingContext);
                break;
            }

            case IMPORT_STATEMENT: {
                var transformationResUltOptional = transformImportStatement(node, parsingContext);
                transformationResUltOptional.ifPresent(res -> {
                    this.importStatements.add(res);
                    this.visitedImportStatements.add(res);
                });
                walkChildren(node, parsingContext);
                break;
            }

            case TRY_STATEMENT: {
                var tryStatementOptional = transformTryStatement(node, parsingContext);
                tryStatementOptional.ifPresent(tryStatement -> {
                    tryStatement.setContext(buildContext());
                    this.tryStatements.add(tryStatement);
                });
                walkChildren(node, parsingContext);
                break;
            }

            default: {
                walkChildren(node, parsingContext);
                break;
            }

        }
    }
}
