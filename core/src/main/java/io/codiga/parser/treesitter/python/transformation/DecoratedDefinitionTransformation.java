package io.codiga.parser.treesitter.python.transformation;

import ai.serenade.treesitter.Node;
import io.codiga.model.ast.common.AstElement;
import io.codiga.model.ast.python.PythonClassDefinition;
import io.codiga.model.ast.python.PythonFunctionDefinition;
import io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes;
import io.codiga.parser.treesitter.utils.TreeSitterParsingContext;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.codiga.parser.treesitter.python.transformation.ClassDeclarationTransformation.transformClassDefinition;
import static io.codiga.parser.treesitter.python.transformation.DecoratorTransformation.transformDecorator;
import static io.codiga.parser.treesitter.python.transformation.FunctionDefinitionTransformation.transformFunctionDefinition;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.DECORATED_DEFINITION;
import static io.codiga.parser.treesitter.python.types.TreeSitterPythonTypes.DECORATOR;
import static io.codiga.parser.treesitter.utils.TreeSitterNodeUtils.*;
import static io.codiga.utils.Conversions.convertToAstElement;

public class DecoratedDefinitionTransformation {

    private static final Logger LOGGER = Logger.getLogger(DecoratedDefinitionTransformation.class.getName());

    public static Optional<PythonFunctionDefinition> transformDecoratedFunctionDefinition(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != DECORATED_DEFINITION) {
            return Optional.empty();
        }

        var functionDefinition = getNodeChild(node, TreeSitterPythonTypes.FUNCTION_DEFINITION);
        if (functionDefinition.isPresent()) {
            var mappedFunctionDefinition = transformFunctionDefinition(functionDefinition.get(), parsingContext);
            var decorators = getNodeChildren(node, DECORATOR)
                .stream().map(n -> transformDecorator(n, parsingContext))
                .filter(n -> n.isPresent())
                .map(n -> n.get())
                .collect(Collectors.toList());
            if (mappedFunctionDefinition.isPresent()) {
                return Optional.of(
                    new PythonFunctionDefinition(
                        mappedFunctionDefinition.get().isAsync,
                        decorators,
                        mappedFunctionDefinition.get().name,
                        mappedFunctionDefinition.get().parameters,
                        mappedFunctionDefinition.get().returnType,
                        mappedFunctionDefinition.get().content,
                        parsingContext.getParserContextForNode(node))
                );
            }
        }
        return Optional.empty();
    }

    public static Optional<PythonClassDefinition> transformDecoratedClassDefinition(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != DECORATED_DEFINITION) {
            return Optional.empty();
        }

        var classDefinition = getNodeChild(node, TreeSitterPythonTypes.CLASS_DEFINITION);
        if (classDefinition.isPresent()) {
            var mappedClassDefinition = transformClassDefinition(classDefinition.get(), parsingContext);
            var decorators = getNodeChildren(node, DECORATOR)
                .stream().map(n -> transformDecorator(n, parsingContext))
                .filter(n -> n.isPresent())
                .map(n -> n.get())
                .collect(Collectors.toList());
            if (mappedClassDefinition.isPresent()) {
                return Optional.of(
                    new PythonClassDefinition(
                        decorators,
                        mappedClassDefinition.get().name,
                        Arrays.stream(mappedClassDefinition.get().parentClasses).toList(),
                        mappedClassDefinition.get().content,
                        parsingContext.getParserContextForNode(node))
                );
            }
        }
        return Optional.empty();
    }

    public static Optional<AstElement> transformDecoratedDefinition(Node node, TreeSitterParsingContext parsingContext) {
        if (node == null || getNodeType(node) != DECORATED_DEFINITION) {
            return Optional.empty();
        }

        if (getNodeChild(node, TreeSitterPythonTypes.FUNCTION_DEFINITION).isPresent()) {
            return convertToAstElement(transformDecoratedFunctionDefinition(node, parsingContext));
        }

        if (getNodeChild(node, TreeSitterPythonTypes.CLASS_DEFINITION).isPresent()) {
            return convertToAstElement(transformDecoratedClassDefinition(node, parsingContext));
        }

        return Optional.empty();
    }


}
