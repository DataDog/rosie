// Generated from java-escape by ANTLR 4.11.1
package io.codiga.parser.antlr.typescript.gen;

import io.codiga.parser.antlr.typescript.java.TypeScriptParserBase;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TypeScriptParser extends TypeScriptParserBase {
    public static final int
        MultiLineComment = 1, SingleLineComment = 2, RegularExpressionLiteral = 3, OpenBracket = 4,
        CloseBracket = 5, OpenParen = 6, CloseParen = 7, OpenBrace = 8, TemplateCloseBrace = 9,
        CloseBrace = 10, SemiColon = 11, Comma = 12, Assign = 13, QuestionMark = 14, Colon = 15,
        Ellipsis = 16, Dot = 17, PlusPlus = 18, MinusMinus = 19, Plus = 20, Minus = 21, BitNot = 22,
        Not = 23, Multiply = 24, Divide = 25, Modulus = 26, RightShiftArithmetic = 27, LeftShiftArithmetic = 28,
        RightShiftLogical = 29, LessThan = 30, MoreThan = 31, LessThanEquals = 32, GreaterThanEquals = 33,
        Equals_ = 34, NotEquals = 35, IdentityEquals = 36, IdentityNotEquals = 37, BitAnd = 38,
        BitXOr = 39, BitOr = 40, And = 41, Or = 42, MultiplyAssign = 43, DivideAssign = 44,
        ModulusAssign = 45, PlusAssign = 46, MinusAssign = 47, LeftShiftArithmeticAssign = 48,
        RightShiftArithmeticAssign = 49, RightShiftLogicalAssign = 50, BitAndAssign = 51,
        BitXorAssign = 52, BitOrAssign = 53, ARROW = 54, NullLiteral = 55, BooleanLiteral = 56,
        DecimalLiteral = 57, HexIntegerLiteral = 58, OctalIntegerLiteral = 59, OctalIntegerLiteral2 = 60,
        BinaryIntegerLiteral = 61, Break = 62, Do = 63, Instanceof = 64, Typeof = 65, Case = 66,
        Else = 67, New = 68, Var = 69, Catch = 70, Finally = 71, Return = 72, Void = 73, Continue = 74,
        For = 75, Switch = 76, While = 77, Debugger = 78, Function_ = 79, This = 80, With = 81,
        Default = 82, If = 83, Throw = 84, Delete = 85, In = 86, Try = 87, As = 88, From = 89,
        ReadOnly = 90, Async = 91, Class = 92, Enum = 93, Extends = 94, Super = 95, Const = 96,
        Export = 97, Import = 98, Implements = 99, Let = 100, Private = 101, Public = 102,
        Interface = 103, Package = 104, Protected = 105, Static = 106, Yield = 107, Any = 108,
        Number = 109, Boolean = 110, String = 111, Symbol = 112, TypeAlias = 113, Get = 114,
        Set = 115, Constructor = 116, Namespace = 117, Require = 118, Module = 119, Declare = 120,
        Abstract = 121, Is = 122, At = 123, Identifier = 124, StringLiteral = 125, BackTick = 126,
        WhiteSpaces = 127, LineTerminator = 128, JsxComment = 129, HtmlComment = 130,
        CDataComment = 131, UnexpectedCharacter = 132, TemplateStringStartExpression = 133,
        TemplateStringAtom = 134, TagOpen = 135, TagClose = 136, TagSlashClose = 137,
        TagSlash = 138, TagName = 139, AttributeValue = 140, Attribute = 141, TagEquals = 142;
    public static final int
        RULE_htmlElements = 0, RULE_htmlElement = 1, RULE_htmlContent = 2, RULE_htmlTagStartName = 3,
        RULE_htmlTagClosingName = 4, RULE_htmlTagName = 5, RULE_htmlAttribute = 6,
        RULE_htmlAttributeName = 7, RULE_identifierOrKeyWordForHtmlAttribute = 8,
        RULE_htmlChardata = 9, RULE_htmlAttributeValue = 10, RULE_objectExpressionSequence = 11,
        RULE_initializer = 12, RULE_bindingPattern = 13, RULE_typeParameters = 14,
        RULE_typeParameterList = 15, RULE_typeParameter = 16, RULE_constraint = 17,
        RULE_typeArguments = 18, RULE_typeArgumentList = 19, RULE_typeArgument = 20,
        RULE_type_ = 21, RULE_unionOrIntersectionOrPrimaryType = 22, RULE_primaryType = 23,
        RULE_predefinedType = 24, RULE_typeReference = 25, RULE_nestedTypeGeneric = 26,
        RULE_typeGeneric = 27, RULE_typeIncludeGeneric = 28, RULE_typeName = 29,
        RULE_objectType = 30, RULE_typeBody = 31, RULE_typeMemberList = 32, RULE_typeMember = 33,
        RULE_arrayType = 34, RULE_tupleType = 35, RULE_tupleElementTypes = 36,
        RULE_functionType = 37, RULE_constructorType = 38, RULE_typeQuery = 39,
        RULE_typeQueryExpression = 40, RULE_propertySignatur = 41, RULE_typeAnnotation = 42,
        RULE_callSignature = 43, RULE_parameterList = 44, RULE_requiredParameterList = 45,
        RULE_parameter = 46, RULE_optionalParameter = 47, RULE_restParameter = 48,
        RULE_requiredParameter = 49, RULE_accessibilityModifier = 50, RULE_identifierOrPattern = 51,
        RULE_constructSignature = 52, RULE_indexSignature = 53, RULE_methodSignature = 54,
        RULE_typeAliasDeclaration = 55, RULE_constructorDeclaration = 56, RULE_interfaceDeclaration = 57,
        RULE_interfaceExtendsClause = 58, RULE_classOrInterfaceTypeList = 59,
        RULE_enumDeclaration = 60, RULE_enumBody = 61, RULE_enumMemberList = 62,
        RULE_enumMember = 63, RULE_namespaceDeclaration = 64, RULE_namespaceName = 65,
        RULE_importAliasDeclaration = 66, RULE_decoratorList = 67, RULE_decorator = 68,
        RULE_decoratorMemberExpression = 69, RULE_decoratorCallExpression = 70,
        RULE_program = 71, RULE_sourceElement = 72, RULE_statement = 73, RULE_block = 74,
        RULE_statementList = 75, RULE_abstractDeclaration = 76, RULE_importStatement = 77,
        RULE_fromBlock = 78, RULE_multipleImportStatement = 79, RULE_exportStatement = 80,
        RULE_variableStatement = 81, RULE_variableDeclarationList = 82, RULE_variableDeclaration = 83,
        RULE_emptyStatement_ = 84, RULE_expressionStatement = 85, RULE_ifStatement = 86,
        RULE_iterationStatement = 87, RULE_varModifier = 88, RULE_continueStatement = 89,
        RULE_breakStatement = 90, RULE_returnStatement = 91, RULE_yieldStatement = 92,
        RULE_withStatement = 93, RULE_switchStatement = 94, RULE_caseBlock = 95,
        RULE_caseClauses = 96, RULE_caseClause = 97, RULE_defaultClause = 98,
        RULE_labelledStatement = 99, RULE_throwStatement = 100, RULE_tryStatement = 101,
        RULE_catchProduction = 102, RULE_finallyProduction = 103, RULE_debuggerStatement = 104,
        RULE_functionDeclaration = 105, RULE_classDeclaration = 106, RULE_classHeritage = 107,
        RULE_classTail = 108, RULE_classExtendsClause = 109, RULE_implementsClause = 110,
        RULE_classElement = 111, RULE_propertyMemberDeclaration = 112, RULE_propertyMemberBase = 113,
        RULE_indexMemberDeclaration = 114, RULE_generatorMethod = 115, RULE_generatorFunctionDeclaration = 116,
        RULE_generatorBlock = 117, RULE_generatorDefinition = 118, RULE_iteratorBlock = 119,
        RULE_iteratorDefinition = 120, RULE_formalParameterList = 121, RULE_formalParameterArg = 122,
        RULE_lastFormalParameterArg = 123, RULE_functionBody = 124, RULE_sourceElements = 125,
        RULE_arrayLiteral = 126, RULE_elementList = 127, RULE_arrayElement = 128,
        RULE_objectLiteral = 129, RULE_propertyAssignment = 130, RULE_getAccessor = 131,
        RULE_setAccessor = 132, RULE_propertyName = 133, RULE_arguments = 134,
        RULE_argumentList = 135, RULE_argument = 136, RULE_expressionSequence = 137,
        RULE_functionExpressionDeclaration = 138, RULE_singleExpression = 139,
        RULE_asExpression = 140, RULE_arrowFunctionDeclaration = 141, RULE_arrowFunctionParameters = 142,
        RULE_arrowFunctionBody = 143, RULE_assignmentOperator = 144, RULE_literal = 145,
        RULE_templateStringLiteral = 146, RULE_templateStringAtom = 147, RULE_numericLiteral = 148,
        RULE_identifierName = 149, RULE_identifierOrKeyWord = 150, RULE_reservedWord = 151,
        RULE_keyword = 152, RULE_getter = 153, RULE_setter = 154, RULE_eos = 155;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
        "\u0004\u0001\u008e\u0741\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001" +
            "\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004" +
            "\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007" +
            "\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b" +
            "\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007" +
            "\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007" +
            "\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007" +
            "\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007" +
            "\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007" +
            "\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007" +
            "\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007" +
            "\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007" +
            "\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007" +
            ",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007" +
            "1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007" +
            "6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007" +
            ";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007" +
            "@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007" +
            "E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007" +
            "J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007" +
            "O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007" +
            "T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007" +
            "Y\u0002Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007]\u0002^\u0007" +
            "^\u0002_\u0007_\u0002`\u0007`\u0002a\u0007a\u0002b\u0007b\u0002c\u0007" +
            "c\u0002d\u0007d\u0002e\u0007e\u0002f\u0007f\u0002g\u0007g\u0002h\u0007" +
            "h\u0002i\u0007i\u0002j\u0007j\u0002k\u0007k\u0002l\u0007l\u0002m\u0007" +
            "m\u0002n\u0007n\u0002o\u0007o\u0002p\u0007p\u0002q\u0007q\u0002r\u0007" +
            "r\u0002s\u0007s\u0002t\u0007t\u0002u\u0007u\u0002v\u0007v\u0002w\u0007" +
            "w\u0002x\u0007x\u0002y\u0007y\u0002z\u0007z\u0002{\u0007{\u0002|\u0007" +
            "|\u0002}\u0007}\u0002~\u0007~\u0002\u007f\u0007\u007f\u0002\u0080\u0007" +
            "\u0080\u0002\u0081\u0007\u0081\u0002\u0082\u0007\u0082\u0002\u0083\u0007" +
            "\u0083\u0002\u0084\u0007\u0084\u0002\u0085\u0007\u0085\u0002\u0086\u0007" +
            "\u0086\u0002\u0087\u0007\u0087\u0002\u0088\u0007\u0088\u0002\u0089\u0007" +
            "\u0089\u0002\u008a\u0007\u008a\u0002\u008b\u0007\u008b\u0002\u008c\u0007" +
            "\u008c\u0002\u008d\u0007\u008d\u0002\u008e\u0007\u008e\u0002\u008f\u0007" +
            "\u008f\u0002\u0090\u0007\u0090\u0002\u0091\u0007\u0091\u0002\u0092\u0007" +
            "\u0092\u0002\u0093\u0007\u0093\u0002\u0094\u0007\u0094\u0002\u0095\u0007" +
            "\u0095\u0002\u0096\u0007\u0096\u0002\u0097\u0007\u0097\u0002\u0098\u0007" +
            "\u0098\u0002\u0099\u0007\u0099\u0002\u009a\u0007\u009a\u0002\u009b\u0007" +
            "\u009b\u0001\u0000\u0004\u0000\u013a\b\u0000\u000b\u0000\f\u0000\u013b" +
            "\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u0141\b\u0001\n\u0001" +
            "\f\u0001\u0144\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
            "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
            "\u0005\u0001\u0150\b\u0001\n\u0001\f\u0001\u0153\t\u0001\u0001\u0001\u0001" +
            "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005" +
            "\u0001\u015c\b\u0001\n\u0001\f\u0001\u015f\t\u0001\u0001\u0001\u0001\u0001" +
            "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u0167\b\u0001" +
            "\n\u0001\f\u0001\u016a\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001" +
            "\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003" +
            "\u0001\u0175\b\u0001\u0001\u0002\u0003\u0002\u0178\b\u0002\u0001\u0002" +
            "\u0001\u0002\u0003\u0002\u017c\b\u0002\u0001\u0002\u0003\u0002\u017f\b" +
            "\u0002\u0005\u0002\u0181\b\u0002\n\u0002\f\u0002\u0184\t\u0002\u0001\u0003" +
            "\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005" +
            "\u0001\u0005\u0001\u0005\u0003\u0005\u018f\b\u0005\u0001\u0005\u0001\u0005" +
            "\u0001\u0005\u0001\u0005\u0003\u0005\u0195\b\u0005\u0005\u0005\u0197\b" +
            "\u0005\n\u0005\f\u0005\u019a\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006" +
            "\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u01a2\b\u0006\u0001\u0007" +
            "\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u01a8\b\u0007\n\u0007" +
            "\f\u0007\u01ab\t\u0007\u0003\u0007\u01ad\b\u0007\u0001\b\u0001\b\u0001" +
            "\b\u0003\b\u01b2\b\b\u0001\t\u0004\t\u01b5\b\t\u000b\t\f\t\u01b6\u0001" +
            "\n\u0001\n\u0001\n\u0003\n\u01bc\b\n\u0001\u000b\u0001\u000b\u0001\u000b" +
            "\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0003\r\u01c7\b\r" +
            "\u0001\u000e\u0001\u000e\u0003\u000e\u01cb\b\u000e\u0001\u000e\u0001\u000e" +
            "\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u01d2\b\u000f\n\u000f" +
            "\f\u000f\u01d5\t\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u01d9\b\u0010" +
            "\u0001\u0010\u0003\u0010\u01dc\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011" +
            "\u0001\u0012\u0001\u0012\u0003\u0012\u01e3\b\u0012\u0001\u0012\u0001\u0012" +
            "\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u01ea\b\u0013\n\u0013" +
            "\f\u0013\u01ed\t\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015" +
            "\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u01f6\b\u0015\u0001\u0016" +
            "\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016" +
            "\u0001\u0016\u0001\u0016\u0005\u0016\u0201\b\u0016\n\u0016\f\u0016\u0204" +
            "\t\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
            "\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
            "\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
            "\u0017\u0003\u0017\u0218\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001" +
            "\u0017\u0005\u0017\u021e\b\u0017\n\u0017\f\u0017\u0221\t\u0017\u0001\u0018" +
            "\u0001\u0018\u0001\u0019\u0001\u0019\u0003\u0019\u0227\b\u0019\u0001\u001a" +
            "\u0001\u001a\u0003\u001a\u022b\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b" +
            "\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c" +
            "\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u023a\b\u001c" +
            "\u0001\u001d\u0001\u001d\u0003\u001d\u023e\b\u001d\u0001\u001e\u0001\u001e" +
            "\u0003\u001e\u0242\b\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f" +
            "\u0003\u001f\u0248\b\u001f\u0001 \u0001 \u0001 \u0005 \u024d\b \n \f " +
            "\u0250\t \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003!\u0259" +
            "\b!\u0003!\u025b\b!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001#\u0001" +
            "#\u0001#\u0001#\u0001$\u0001$\u0001$\u0005$\u0269\b$\n$\f$\u026c\t$\u0001" +
            "%\u0003%\u026f\b%\u0001%\u0001%\u0003%\u0273\b%\u0001%\u0001%\u0001%\u0001" +
            "%\u0001&\u0001&\u0003&\u027b\b&\u0001&\u0001&\u0003&\u027f\b&\u0001&\u0001" +
            "&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0004" +
            "(\u028c\b(\u000b(\f(\u028d\u0001(\u0001(\u0003(\u0292\b(\u0001)\u0003" +
            ")\u0295\b)\u0001)\u0001)\u0003)\u0299\b)\u0001)\u0003)\u029c\b)\u0001" +
            ")\u0001)\u0003)\u02a0\b)\u0001*\u0001*\u0001*\u0001+\u0003+\u02a6\b+\u0001" +
            "+\u0001+\u0003+\u02aa\b+\u0001+\u0001+\u0003+\u02ae\b+\u0001,\u0001,\u0001" +
            ",\u0001,\u0005,\u02b4\b,\n,\f,\u02b7\t,\u0001,\u0001,\u0003,\u02bb\b," +
            "\u0003,\u02bd\b,\u0001-\u0001-\u0001-\u0005-\u02c2\b-\n-\f-\u02c5\t-\u0001" +
            ".\u0001.\u0003.\u02c9\b.\u0001/\u0003/\u02cc\b/\u0001/\u0003/\u02cf\b" +
            "/\u0001/\u0001/\u0001/\u0003/\u02d4\b/\u0001/\u0003/\u02d7\b/\u0001/\u0003" +
            "/\u02da\b/\u00010\u00010\u00010\u00030\u02df\b0\u00011\u00031\u02e2\b" +
            "1\u00011\u00031\u02e5\b1\u00011\u00011\u00031\u02e9\b1\u00012\u00012\u0001" +
            "3\u00013\u00033\u02ef\b3\u00014\u00014\u00034\u02f3\b4\u00014\u00014\u0003" +
            "4\u02f7\b4\u00014\u00014\u00034\u02fb\b4\u00015\u00015\u00015\u00015\u0001" +
            "5\u00015\u00015\u00016\u00016\u00036\u0306\b6\u00016\u00016\u00017\u0001" +
            "7\u00017\u00037\u030d\b7\u00017\u00017\u00017\u00017\u00018\u00038\u0314" +
            "\b8\u00018\u00018\u00018\u00038\u0319\b8\u00018\u00018\u00018\u00018\u0001" +
            "8\u00018\u00038\u0321\b8\u00019\u00039\u0324\b9\u00019\u00039\u0327\b" +
            "9\u00019\u00019\u00019\u00039\u032c\b9\u00019\u00039\u032f\b9\u00019\u0001" +
            "9\u00039\u0333\b9\u0001:\u0001:\u0001:\u0001;\u0001;\u0001;\u0005;\u033b" +
            "\b;\n;\f;\u033e\t;\u0001<\u0003<\u0341\b<\u0001<\u0001<\u0001<\u0001<" +
            "\u0003<\u0347\b<\u0001<\u0001<\u0001=\u0001=\u0003=\u034d\b=\u0001>\u0001" +
            ">\u0001>\u0005>\u0352\b>\n>\f>\u0355\t>\u0001?\u0001?\u0001?\u0003?\u035a" +
            "\b?\u0001@\u0001@\u0001@\u0001@\u0003@\u0360\b@\u0001@\u0001@\u0001A\u0001" +
            "A\u0004A\u0366\bA\u000bA\fA\u0367\u0001A\u0005A\u036b\bA\nA\fA\u036e\t" +
            "A\u0001B\u0001B\u0001B\u0001B\u0001B\u0001C\u0004C\u0376\bC\u000bC\fC" +
            "\u0377\u0001D\u0001D\u0001D\u0003D\u037d\bD\u0001E\u0001E\u0001E\u0001" +
            "E\u0001E\u0001E\u0003E\u0385\bE\u0001E\u0001E\u0001E\u0005E\u038a\bE\n" +
            "E\fE\u038d\tE\u0001F\u0001F\u0001F\u0001G\u0003G\u0393\bG\u0001G\u0001" +
            "G\u0001H\u0003H\u0398\bH\u0001H\u0001H\u0001I\u0001I\u0001I\u0001I\u0001" +
            "I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001" +
            "I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001" +
            "I\u0001I\u0001I\u0001I\u0001I\u0001I\u0003I\u03ba\bI\u0001J\u0001J\u0003" +
            "J\u03be\bJ\u0001J\u0001J\u0001K\u0004K\u03c3\bK\u000bK\fK\u03c4\u0001" +
            "L\u0001L\u0001L\u0001L\u0003L\u03cb\bL\u0001L\u0001L\u0001M\u0001M\u0001" +
            "M\u0003M\u03d2\bM\u0001N\u0001N\u0001N\u0003N\u03d7\bN\u0001N\u0001N\u0003" +
            "N\u03db\bN\u0001N\u0001N\u0001N\u0001N\u0001O\u0001O\u0001O\u0003O\u03e4" +
            "\bO\u0001O\u0001O\u0001O\u0001O\u0005O\u03ea\bO\nO\fO\u03ed\tO\u0001O" +
            "\u0001O\u0001P\u0001P\u0003P\u03f3\bP\u0001P\u0001P\u0003P\u03f7\bP\u0001" +
            "Q\u0001Q\u0003Q\u03fb\bQ\u0001Q\u0001Q\u0003Q\u03ff\bQ\u0001Q\u0003Q\u0402" +
            "\bQ\u0001Q\u0003Q\u0405\bQ\u0001Q\u0003Q\u0408\bQ\u0001Q\u0001Q\u0003" +
            "Q\u040c\bQ\u0001Q\u0001Q\u0003Q\u0410\bQ\u0001Q\u0001Q\u0003Q\u0414\b" +
            "Q\u0003Q\u0416\bQ\u0001R\u0001R\u0001R\u0005R\u041b\bR\nR\fR\u041e\tR" +
            "\u0001S\u0001S\u0001S\u0003S\u0423\bS\u0001S\u0003S\u0426\bS\u0001S\u0003" +
            "S\u0429\bS\u0001S\u0001S\u0003S\u042d\bS\u0001S\u0003S\u0430\bS\u0001" +
            "T\u0001T\u0001U\u0001U\u0001U\u0003U\u0437\bU\u0001V\u0001V\u0001V\u0001" +
            "V\u0001V\u0001V\u0001V\u0003V\u0440\bV\u0001W\u0001W\u0001W\u0001W\u0001" +
            "W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001" +
            "W\u0001W\u0001W\u0003W\u0453\bW\u0001W\u0001W\u0003W\u0457\bW\u0001W\u0001" +
            "W\u0003W\u045b\bW\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001" +
            "W\u0003W\u0465\bW\u0001W\u0001W\u0003W\u0469\bW\u0001W\u0001W\u0001W\u0001" +
            "W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u0474\bW\u0001W\u0001W\u0001" +
            "W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u0481" +
            "\bW\u0001W\u0001W\u0001W\u0001W\u0003W\u0487\bW\u0001X\u0001X\u0001Y\u0001" +
            "Y\u0001Y\u0003Y\u048e\bY\u0001Y\u0001Y\u0001Z\u0001Z\u0001Z\u0003Z\u0495" +
            "\bZ\u0001Z\u0001Z\u0001[\u0001[\u0001[\u0003[\u049c\b[\u0001[\u0001[\u0001" +
            "[\u0001[\u0001[\u0001[\u0001[\u0003[\u04a5\b[\u0001\\\u0001\\\u0001\\" +
            "\u0003\\\u04aa\b\\\u0001\\\u0001\\\u0001]\u0001]\u0001]\u0001]\u0001]" +
            "\u0001]\u0001^\u0001^\u0001^\u0001^\u0001^\u0001^\u0001_\u0001_\u0003" +
            "_\u04bc\b_\u0001_\u0001_\u0003_\u04c0\b_\u0003_\u04c2\b_\u0001_\u0001" +
            "_\u0001`\u0004`\u04c7\b`\u000b`\f`\u04c8\u0001a\u0001a\u0001a\u0001a\u0003" +
            "a\u04cf\ba\u0001b\u0001b\u0001b\u0003b\u04d4\bb\u0001c\u0001c\u0001c\u0001" +
            "c\u0001d\u0001d\u0001d\u0001d\u0001d\u0001e\u0001e\u0001e\u0001e\u0003" +
            "e\u04e3\be\u0001e\u0003e\u04e6\be\u0001f\u0001f\u0001f\u0001f\u0001f\u0001" +
            "f\u0001g\u0001g\u0001g\u0001h\u0001h\u0001h\u0001i\u0003i\u04f5\bi\u0001" +
            "i\u0001i\u0001i\u0001i\u0001i\u0001i\u0001i\u0001i\u0003i\u04ff\bi\u0001" +
            "j\u0003j\u0502\bj\u0001j\u0001j\u0001j\u0003j\u0507\bj\u0001j\u0001j\u0001" +
            "j\u0001k\u0003k\u050d\bk\u0001k\u0003k\u0510\bk\u0001l\u0001l\u0005l\u0514" +
            "\bl\nl\fl\u0517\tl\u0001l\u0001l\u0001m\u0001m\u0001m\u0001n\u0001n\u0001" +
            "n\u0001o\u0001o\u0003o\u0523\bo\u0001o\u0001o\u0001o\u0003o\u0528\bo\u0001" +
            "p\u0001p\u0001p\u0003p\u052d\bp\u0001p\u0003p\u0530\bp\u0001p\u0003p\u0533" +
            "\bp\u0001p\u0001p\u0001p\u0001p\u0001p\u0001p\u0001p\u0001p\u0001p\u0001" +
            "p\u0003p\u053f\bp\u0001p\u0001p\u0001p\u0003p\u0544\bp\u0001p\u0003p\u0547" +
            "\bp\u0001q\u0003q\u054a\bq\u0001q\u0003q\u054d\bq\u0001q\u0003q\u0550" +
            "\bq\u0001q\u0003q\u0553\bq\u0001r\u0001r\u0001r\u0001s\u0003s\u0559\b" +
            "s\u0001s\u0001s\u0001s\u0003s\u055e\bs\u0001s\u0001s\u0001s\u0001s\u0001" +
            "s\u0001t\u0001t\u0001t\u0003t\u0568\bt\u0001t\u0001t\u0003t\u056c\bt\u0001" +
            "t\u0001t\u0001t\u0001t\u0001t\u0001u\u0001u\u0001u\u0001u\u0005u\u0577" +
            "\bu\nu\fu\u057a\tu\u0001u\u0003u\u057d\bu\u0001u\u0001u\u0001v\u0001v" +
            "\u0001v\u0001w\u0001w\u0001w\u0001w\u0005w\u0588\bw\nw\fw\u058b\tw\u0001" +
            "w\u0003w\u058e\bw\u0001w\u0001w\u0001x\u0001x\u0001x\u0001x\u0001x\u0003" +
            "x\u0597\bx\u0001x\u0001x\u0001x\u0001x\u0001x\u0001y\u0001y\u0001y\u0005" +
            "y\u05a1\by\ny\fy\u05a4\ty\u0001y\u0001y\u0003y\u05a8\by\u0001y\u0001y" +
            "\u0001y\u0001y\u0001y\u0003y\u05af\by\u0003y\u05b1\by\u0001z\u0003z\u05b4" +
            "\bz\u0001z\u0003z\u05b7\bz\u0001z\u0001z\u0003z\u05bb\bz\u0001z\u0003" +
            "z\u05be\bz\u0001z\u0001z\u0003z\u05c2\bz\u0001{\u0001{\u0001{\u0001|\u0003" +
            "|\u05c8\b|\u0001}\u0004}\u05cb\b}\u000b}\f}\u05cc\u0001~\u0001~\u0003" +
            "~\u05d1\b~\u0001~\u0001~\u0001\u007f\u0001\u007f\u0004\u007f\u05d7\b\u007f" +
            "\u000b\u007f\f\u007f\u05d8\u0001\u007f\u0005\u007f\u05dc\b\u007f\n\u007f" +
            "\f\u007f\u05df\t\u007f\u0001\u0080\u0003\u0080\u05e2\b\u0080\u0001\u0080" +
            "\u0001\u0080\u0003\u0080\u05e6\b\u0080\u0001\u0080\u0003\u0080\u05e9\b" +
            "\u0080\u0001\u0081\u0001\u0081\u0001\u0081\u0001\u0081\u0005\u0081\u05ef" +
            "\b\u0081\n\u0081\f\u0081\u05f2\t\u0081\u0001\u0081\u0003\u0081\u05f5\b" +
            "\u0081\u0003\u0081\u05f7\b\u0081\u0001\u0081\u0001\u0081\u0001\u0082\u0001" +
            "\u0082\u0001\u0082\u0001\u0082\u0001\u0082\u0001\u0082\u0001\u0082\u0001" +
            "\u0082\u0001\u0082\u0001\u0082\u0001\u0082\u0001\u0082\u0001\u0082\u0001" +
            "\u0082\u0001\u0082\u0003\u0082\u060a\b\u0082\u0001\u0083\u0001\u0083\u0001" +
            "\u0083\u0001\u0083\u0003\u0083\u0610\b\u0083\u0001\u0083\u0001\u0083\u0001" +
            "\u0083\u0001\u0083\u0001\u0084\u0001\u0084\u0001\u0084\u0001\u0084\u0003" +
            "\u0084\u061a\b\u0084\u0001\u0084\u0003\u0084\u061d\b\u0084\u0001\u0084" +
            "\u0001\u0084\u0001\u0084\u0001\u0084\u0001\u0084\u0001\u0085\u0001\u0085" +
            "\u0001\u0085\u0003\u0085\u0627\b\u0085\u0001\u0086\u0001\u0086\u0001\u0086" +
            "\u0003\u0086\u062c\b\u0086\u0003\u0086\u062e\b\u0086\u0001\u0086\u0001" +
            "\u0086\u0001\u0087\u0001\u0087\u0001\u0087\u0005\u0087\u0635\b\u0087\n" +
            "\u0087\f\u0087\u0638\t\u0087\u0001\u0088\u0003\u0088\u063b\b\u0088\u0001" +
            "\u0088\u0001\u0088\u0003\u0088\u063f\b\u0088\u0001\u0089\u0003\u0089\u0642" +
            "\b\u0089\u0001\u0089\u0001\u0089\u0001\u0089\u0005\u0089\u0647\b\u0089" +
            "\n\u0089\f\u0089\u064a\t\u0089\u0001\u008a\u0001\u008a\u0003\u008a\u064e" +
            "\b\u008a\u0001\u008a\u0001\u008a\u0003\u008a\u0652\b\u008a\u0001\u008a" +
            "\u0001\u008a\u0003\u008a\u0656\b\u008a\u0001\u008a\u0001\u008a\u0001\u008a" +
            "\u0001\u008a\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0003\u008b\u0661\b\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0003\u008b\u0667\b\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0003\u008b\u066e\b\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0003\u008b\u0689\b\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0003\u008b\u0695\b\u008b" +
            "\u0001\u008b\u0003\u008b\u0698\b\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b" +
            "\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0003\u008b" +
            "\u06d2\b\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0003\u008b\u06d7\b" +
            "\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001" +
            "\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001\u008b\u0001" +
            "\u008b\u0001\u008b\u0005\u008b\u06e6\b\u008b\n\u008b\f\u008b\u06e9\t\u008b" +
            "\u0001\u008c\u0001\u008c\u0001\u008c\u0003\u008c\u06ee\b\u008c\u0001\u008c" +
            "\u0003\u008c\u06f1\b\u008c\u0001\u008d\u0003\u008d\u06f4\b\u008d\u0001" +
            "\u008d\u0001\u008d\u0003\u008d\u06f8\b\u008d\u0001\u008d\u0001\u008d\u0001" +
            "\u008d\u0001\u008e\u0001\u008e\u0001\u008e\u0003\u008e\u0700\b\u008e\u0001" +
            "\u008e\u0003\u008e\u0703\b\u008e\u0001\u008f\u0001\u008f\u0001\u008f\u0001" +
            "\u008f\u0001\u008f\u0003\u008f\u070a\b\u008f\u0001\u0090\u0001\u0090\u0001" +
            "\u0091\u0001\u0091\u0001\u0091\u0001\u0091\u0001\u0091\u0001\u0091\u0003" +
            "\u0091\u0714\b\u0091\u0001\u0092\u0001\u0092\u0005\u0092\u0718\b\u0092" +
            "\n\u0092\f\u0092\u071b\t\u0092\u0001\u0092\u0001\u0092\u0001\u0093\u0001" +
            "\u0093\u0001\u0093\u0001\u0093\u0001\u0093\u0003\u0093\u0724\b\u0093\u0001" +
            "\u0094\u0001\u0094\u0001\u0095\u0001\u0095\u0003\u0095\u072a\b\u0095\u0001" +
            "\u0096\u0001\u0096\u0001\u0097\u0001\u0097\u0001\u0097\u0003\u0097\u0731" +
            "\b\u0097\u0001\u0098\u0001\u0098\u0001\u0099\u0001\u0099\u0001\u0099\u0001" +
            "\u009a\u0001\u009a\u0001\u009a\u0001\u009b\u0001\u009b\u0001\u009b\u0001" +
            "\u009b\u0003\u009b\u073f\b\u009b\u0001\u009b\u0000\u0004,.\u008a\u0116" +
            "\u009c\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018" +
            "\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080" +
            "\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098" +
            "\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0" +
            "\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8" +
            "\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0" +
            "\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8" +
            "\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106\u0108\u010a\u010c\u010e\u0110" +
            "\u0112\u0114\u0116\u0118\u011a\u011c\u011e\u0120\u0122\u0124\u0126\u0128" +
            "\u012a\u012c\u012e\u0130\u0132\u0134\u0136\u0000\u0010\u0002\u0000\b\b" +
            "\u001e\u001e\u0002\u0000IIlp\u0001\u0000\u000b\f\u0002\u0000efii\u0002" +
            "\u0000mmoo\u0003\u0000EE``dd\u0002\u0000\r\r\u000f\u000f\u0001\u0000\u0018" +
            "\u001a\u0001\u0000\u0014\u0015\u0001\u0000\u001b\u001d\u0001\u0000\u001e" +
            "!\u0001\u0000\"%\u0001\u0000+5\u0001\u00009=\u0004\u0000XXqqvv||\u0005" +
            "\u0000>WYkooqsvv\u0803\u0000\u0139\u0001\u0000\u0000\u0000\u0002\u0174" +
            "\u0001\u0000\u0000\u0000\u0004\u0177\u0001\u0000\u0000\u0000\u0006\u0185" +
            "\u0001\u0000\u0000\u0000\b\u0188\u0001\u0000\u0000\u0000\n\u018e\u0001" +
            "\u0000\u0000\u0000\f\u01a1\u0001\u0000\u0000\u0000\u000e\u01ac\u0001\u0000" +
            "\u0000\u0000\u0010\u01b1\u0001\u0000\u0000\u0000\u0012\u01b4\u0001\u0000" +
            "\u0000\u0000\u0014\u01bb\u0001\u0000\u0000\u0000\u0016\u01bd\u0001\u0000" +
            "\u0000\u0000\u0018\u01c1\u0001\u0000\u0000\u0000\u001a\u01c6\u0001\u0000" +
            "\u0000\u0000\u001c\u01c8\u0001\u0000\u0000\u0000\u001e\u01ce\u0001\u0000" +
            "\u0000\u0000 \u01db\u0001\u0000\u0000\u0000\"\u01dd\u0001\u0000\u0000" +
            "\u0000$\u01e0\u0001\u0000\u0000\u0000&\u01e6\u0001\u0000\u0000\u0000(" +
            "\u01ee\u0001\u0000\u0000\u0000*\u01f5\u0001\u0000\u0000\u0000,\u01f7\u0001" +
            "\u0000\u0000\u0000.\u0217\u0001\u0000\u0000\u00000\u0222\u0001\u0000\u0000" +
            "\u00002\u0224\u0001\u0000\u0000\u00004\u022a\u0001\u0000\u0000\u00006" +
            "\u022c\u0001\u0000\u0000\u00008\u0230\u0001\u0000\u0000\u0000:\u023d\u0001" +
            "\u0000\u0000\u0000<\u023f\u0001\u0000\u0000\u0000>\u0245\u0001\u0000\u0000" +
            "\u0000@\u0249\u0001\u0000\u0000\u0000B\u025a\u0001\u0000\u0000\u0000D" +
            "\u025c\u0001\u0000\u0000\u0000F\u0261\u0001\u0000\u0000\u0000H\u0265\u0001" +
            "\u0000\u0000\u0000J\u026e\u0001\u0000\u0000\u0000L\u0278\u0001\u0000\u0000" +
            "\u0000N\u0284\u0001\u0000\u0000\u0000P\u0291\u0001\u0000\u0000\u0000R" +
            "\u0294\u0001\u0000\u0000\u0000T\u02a1\u0001\u0000\u0000\u0000V\u02a5\u0001" +
            "\u0000\u0000\u0000X\u02bc\u0001\u0000\u0000\u0000Z\u02be\u0001\u0000\u0000" +
            "\u0000\\\u02c8\u0001\u0000\u0000\u0000^\u02cb\u0001\u0000\u0000\u0000" +
            "`\u02db\u0001\u0000\u0000\u0000b\u02e1\u0001\u0000\u0000\u0000d\u02ea" +
            "\u0001\u0000\u0000\u0000f\u02ee\u0001\u0000\u0000\u0000h\u02f0\u0001\u0000" +
            "\u0000\u0000j\u02fc\u0001\u0000\u0000\u0000l\u0303\u0001\u0000\u0000\u0000" +
            "n\u0309\u0001\u0000\u0000\u0000p\u0313\u0001\u0000\u0000\u0000r\u0323" +
            "\u0001\u0000\u0000\u0000t\u0334\u0001\u0000\u0000\u0000v\u0337\u0001\u0000" +
            "\u0000\u0000x\u0340\u0001\u0000\u0000\u0000z\u034a\u0001\u0000\u0000\u0000" +
            "|\u034e\u0001\u0000\u0000\u0000~\u0356\u0001\u0000\u0000\u0000\u0080\u035b" +
            "\u0001\u0000\u0000\u0000\u0082\u0363\u0001\u0000\u0000\u0000\u0084\u036f" +
            "\u0001\u0000\u0000\u0000\u0086\u0375\u0001\u0000\u0000\u0000\u0088\u0379" +
            "\u0001\u0000\u0000\u0000\u008a\u0384\u0001\u0000\u0000\u0000\u008c\u038e" +
            "\u0001\u0000\u0000\u0000\u008e\u0392\u0001\u0000\u0000\u0000\u0090\u0397" +
            "\u0001\u0000\u0000\u0000\u0092\u03b9\u0001\u0000\u0000\u0000\u0094\u03bb" +
            "\u0001\u0000\u0000\u0000\u0096\u03c2\u0001\u0000\u0000\u0000\u0098\u03c6" +
            "\u0001\u0000\u0000\u0000\u009a\u03ce\u0001\u0000\u0000\u0000\u009c\u03d6" +
            "\u0001\u0000\u0000\u0000\u009e\u03e3\u0001\u0000\u0000\u0000\u00a0\u03f0" +
            "\u0001\u0000\u0000\u0000\u00a2\u0415\u0001\u0000\u0000\u0000\u00a4\u0417" +
            "\u0001\u0000\u0000\u0000\u00a6\u0422\u0001\u0000\u0000\u0000\u00a8\u0431" +
            "\u0001\u0000\u0000\u0000\u00aa\u0433\u0001\u0000\u0000\u0000\u00ac\u0438" +
            "\u0001\u0000\u0000\u0000\u00ae\u0486\u0001\u0000\u0000\u0000\u00b0\u0488" +
            "\u0001\u0000\u0000\u0000\u00b2\u048a\u0001\u0000\u0000\u0000\u00b4\u0491" +
            "\u0001\u0000\u0000\u0000\u00b6\u04a4\u0001\u0000\u0000\u0000\u00b8\u04a6" +
            "\u0001\u0000\u0000\u0000\u00ba\u04ad\u0001\u0000\u0000\u0000\u00bc\u04b3" +
            "\u0001\u0000\u0000\u0000\u00be\u04b9\u0001\u0000\u0000\u0000\u00c0\u04c6" +
            "\u0001\u0000\u0000\u0000\u00c2\u04ca\u0001\u0000\u0000\u0000\u00c4\u04d0" +
            "\u0001\u0000\u0000\u0000\u00c6\u04d5\u0001\u0000\u0000\u0000\u00c8\u04d9" +
            "\u0001\u0000\u0000\u0000\u00ca\u04de\u0001\u0000\u0000\u0000\u00cc\u04e7" +
            "\u0001\u0000\u0000\u0000\u00ce\u04ed\u0001\u0000\u0000\u0000\u00d0\u04f0" +
            "\u0001\u0000\u0000\u0000\u00d2\u04f4\u0001\u0000\u0000\u0000\u00d4\u0501" +
            "\u0001\u0000\u0000\u0000\u00d6\u050c\u0001\u0000\u0000\u0000\u00d8\u0511" +
            "\u0001\u0000\u0000\u0000\u00da\u051a\u0001\u0000\u0000\u0000\u00dc\u051d" +
            "\u0001\u0000\u0000\u0000\u00de\u0527\u0001\u0000\u0000\u0000\u00e0\u0546" +
            "\u0001\u0000\u0000\u0000\u00e2\u0549\u0001\u0000\u0000\u0000\u00e4\u0554" +
            "\u0001\u0000\u0000\u0000\u00e6\u0558\u0001\u0000\u0000\u0000\u00e8\u0564" +
            "\u0001\u0000\u0000\u0000\u00ea\u0572\u0001\u0000\u0000\u0000\u00ec\u0580" +
            "\u0001\u0000\u0000\u0000\u00ee\u0583\u0001\u0000\u0000\u0000\u00f0\u0591" +
            "\u0001\u0000\u0000\u0000\u00f2\u05b0\u0001\u0000\u0000\u0000\u00f4\u05b3" +
            "\u0001\u0000\u0000\u0000\u00f6\u05c3\u0001\u0000\u0000\u0000\u00f8\u05c7" +
            "\u0001\u0000\u0000\u0000\u00fa\u05ca\u0001\u0000\u0000\u0000\u00fc\u05ce" +
            "\u0001\u0000\u0000\u0000\u00fe\u05d4\u0001\u0000\u0000\u0000\u0100\u05e1" +
            "\u0001\u0000\u0000\u0000\u0102\u05ea\u0001\u0000\u0000\u0000\u0104\u0609" +
            "\u0001\u0000\u0000\u0000\u0106\u060b\u0001\u0000\u0000\u0000\u0108\u0615" +
            "\u0001\u0000\u0000\u0000\u010a\u0626\u0001\u0000\u0000\u0000\u010c\u0628" +
            "\u0001\u0000\u0000\u0000\u010e\u0631\u0001\u0000\u0000\u0000\u0110\u063a" +
            "\u0001\u0000\u0000\u0000\u0112\u0641\u0001\u0000\u0000\u0000\u0114\u064b" +
            "\u0001\u0000\u0000\u0000\u0116\u0697\u0001\u0000\u0000\u0000\u0118\u06f0" +
            "\u0001\u0000\u0000\u0000\u011a\u06f3\u0001\u0000\u0000\u0000\u011c\u0702" +
            "\u0001\u0000\u0000\u0000\u011e\u0709\u0001\u0000\u0000\u0000\u0120\u070b" +
            "\u0001\u0000\u0000\u0000\u0122\u0713\u0001\u0000\u0000\u0000\u0124\u0715" +
            "\u0001\u0000\u0000\u0000\u0126\u0723\u0001\u0000\u0000\u0000\u0128\u0725" +
            "\u0001\u0000\u0000\u0000\u012a\u0729\u0001\u0000\u0000\u0000\u012c\u072b" +
            "\u0001\u0000\u0000\u0000\u012e\u0730\u0001\u0000\u0000\u0000\u0130\u0732" +
            "\u0001\u0000\u0000\u0000\u0132\u0734\u0001\u0000\u0000\u0000\u0134\u0737" +
            "\u0001\u0000\u0000\u0000\u0136\u073e\u0001\u0000\u0000\u0000\u0138\u013a" +
            "\u0003\u0002\u0001\u0000\u0139\u0138\u0001\u0000\u0000\u0000\u013a\u013b" +
            "\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013b\u013c" +
            "\u0001\u0000\u0000\u0000\u013c\u0001\u0001\u0000\u0000\u0000\u013d\u013e" +
            "\u0005\u001e\u0000\u0000\u013e\u0142\u0003\u0006\u0003\u0000\u013f\u0141" +
            "\u0003\f\u0006\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u0141\u0144\u0001" +
            "\u0000\u0000\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0142\u0143\u0001" +
            "\u0000\u0000\u0000\u0143\u0145\u0001\u0000\u0000\u0000\u0144\u0142\u0001" +
            "\u0000\u0000\u0000\u0145\u0146\u0005\u001f\u0000\u0000\u0146\u0147\u0003" +
            "\u0004\u0002\u0000\u0147\u0148\u0005\u001e\u0000\u0000\u0148\u0149\u0005" +
            "\u0019\u0000\u0000\u0149\u014a\u0003\b\u0004\u0000\u014a\u014b\u0005\u001f" +
            "\u0000\u0000\u014b\u0175\u0001\u0000\u0000\u0000\u014c\u014d\u0005\u001e" +
            "\u0000\u0000\u014d\u0151\u0003\n\u0005\u0000\u014e\u0150\u0003\f\u0006" +
            "\u0000\u014f\u014e\u0001\u0000\u0000\u0000\u0150\u0153\u0001\u0000\u0000" +
            "\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000" +
            "\u0000\u0152\u0154\u0001\u0000\u0000\u0000\u0153\u0151\u0001\u0000\u0000" +
            "\u0000\u0154\u0155\u0003\u0004\u0002\u0000\u0155\u0156\u0005\u0019\u0000" +
            "\u0000\u0156\u0157\u0005\u001f\u0000\u0000\u0157\u0175\u0001\u0000\u0000" +
            "\u0000\u0158\u0159\u0005\u001e\u0000\u0000\u0159\u015d\u0003\n\u0005\u0000" +
            "\u015a\u015c\u0003\f\u0006\u0000\u015b\u015a\u0001\u0000\u0000\u0000\u015c" +
            "\u015f\u0001\u0000\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015d" +
            "\u015e\u0001\u0000\u0000\u0000\u015e\u0160\u0001\u0000\u0000\u0000\u015f" +
            "\u015d\u0001\u0000\u0000\u0000\u0160\u0161\u0005\u0019\u0000\u0000\u0161" +
            "\u0162\u0005\u001f\u0000\u0000\u0162\u0175\u0001\u0000\u0000\u0000\u0163" +
            "\u0164\u0005\u001e\u0000\u0000\u0164\u0168\u0003\n\u0005\u0000\u0165\u0167" +
            "\u0003\f\u0006\u0000\u0166\u0165\u0001\u0000\u0000\u0000\u0167\u016a\u0001" +
            "\u0000\u0000\u0000\u0168\u0166\u0001\u0000\u0000\u0000\u0168\u0169\u0001" +
            "\u0000\u0000\u0000\u0169\u016b\u0001\u0000\u0000\u0000\u016a\u0168\u0001" +
            "\u0000\u0000\u0000\u016b\u016c\u0005\u001f\u0000\u0000\u016c\u0175\u0001" +
            "\u0000\u0000\u0000\u016d\u016e\u0005\u001e\u0000\u0000\u016e\u016f\u0005" +
            "\u001f\u0000\u0000\u016f\u0170\u0003\u0004\u0002\u0000\u0170\u0171\u0005" +
            "\u001e\u0000\u0000\u0171\u0172\u0005\u0019\u0000\u0000\u0172\u0173\u0005" +
            "\u001f\u0000\u0000\u0173\u0175\u0001\u0000\u0000\u0000\u0174\u013d\u0001" +
            "\u0000\u0000\u0000\u0174\u014c\u0001\u0000\u0000\u0000\u0174\u0158\u0001" +
            "\u0000\u0000\u0000\u0174\u0163\u0001\u0000\u0000\u0000\u0174\u016d\u0001" +
            "\u0000\u0000\u0000\u0175\u0003\u0001\u0000\u0000\u0000\u0176\u0178\u0003" +
            "\u0012\t\u0000\u0177\u0176\u0001\u0000\u0000\u0000\u0177\u0178\u0001\u0000" +
            "\u0000\u0000\u0178\u0182\u0001\u0000\u0000\u0000\u0179\u017c\u0003\u0002" +
            "\u0001\u0000\u017a\u017c\u0003\u0016\u000b\u0000\u017b\u0179\u0001\u0000" +
            "\u0000\u0000\u017b\u017a\u0001\u0000\u0000\u0000\u017c\u017e\u0001\u0000" +
            "\u0000\u0000\u017d\u017f\u0003\u0012\t\u0000\u017e\u017d\u0001\u0000\u0000" +
            "\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0181\u0001\u0000\u0000" +
            "\u0000\u0180\u017b\u0001\u0000\u0000\u0000\u0181\u0184\u0001\u0000\u0000" +
            "\u0000\u0182\u0180\u0001\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000" +
            "\u0000\u0183\u0005\u0001\u0000\u0000\u0000\u0184\u0182\u0001\u0000\u0000" +
            "\u0000\u0185\u0186\u0003\n\u0005\u0000\u0186\u0187\u0006\u0003\uffff\uffff" +
            "\u0000\u0187\u0007\u0001\u0000\u0000\u0000\u0188\u0189\u0003\n\u0005\u0000" +
            "\u0189\u018a\u0004\u0004\u0000\u0001\u018a\t\u0001\u0000\u0000\u0000\u018b" +
            "\u018f\u0005\u008b\u0000\u0000\u018c\u018f\u0003\u0130\u0098\u0000\u018d" +
            "\u018f\u0005|\u0000\u0000\u018e\u018b\u0001\u0000\u0000\u0000\u018e\u018c" +
            "\u0001\u0000\u0000\u0000\u018e\u018d\u0001\u0000\u0000\u0000\u018f\u0198" +
            "\u0001\u0000\u0000\u0000\u0190\u0194\u0005\u0011\u0000\u0000\u0191\u0195" +
            "\u0005\u008b\u0000\u0000\u0192\u0195\u0003\u0130\u0098\u0000\u0193\u0195" +
            "\u0005|\u0000\u0000\u0194\u0191\u0001\u0000\u0000\u0000\u0194\u0192\u0001" +
            "\u0000\u0000\u0000\u0194\u0193\u0001\u0000\u0000\u0000\u0195\u0197\u0001" +
            "\u0000\u0000\u0000\u0196\u0190\u0001\u0000\u0000\u0000\u0197\u019a\u0001" +
            "\u0000\u0000\u0000\u0198\u0196\u0001\u0000\u0000\u0000\u0198\u0199\u0001" +
            "\u0000\u0000\u0000\u0199\u000b\u0001\u0000\u0000\u0000\u019a\u0198\u0001" +
            "\u0000\u0000\u0000\u019b\u019c\u0003\u000e\u0007\u0000\u019c\u019d\u0005" +
            "\r\u0000\u0000\u019d\u019e\u0003\u0014\n\u0000\u019e\u01a2\u0001\u0000" +
            "\u0000\u0000\u019f\u01a2\u0003\u000e\u0007\u0000\u01a0\u01a2\u0003\u0016" +
            "\u000b\u0000\u01a1\u019b\u0001\u0000\u0000\u0000\u01a1\u019f\u0001\u0000" +
            "\u0000\u0000\u01a1\u01a0\u0001\u0000\u0000\u0000\u01a2\r\u0001\u0000\u0000" +
            "\u0000\u01a3\u01ad\u0005\u008b\u0000\u0000\u01a4\u01a9\u0003\u0010\b\u0000" +
            "\u01a5\u01a6\u0005\u0015\u0000\u0000\u01a6\u01a8\u0003\u0010\b\u0000\u01a7" +
            "\u01a5\u0001\u0000\u0000\u0000\u01a8\u01ab\u0001\u0000\u0000\u0000\u01a9" +
            "\u01a7\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000\u01aa" +
            "\u01ad\u0001\u0000\u0000\u0000\u01ab\u01a9\u0001\u0000\u0000\u0000\u01ac" +
            "\u01a3\u0001\u0000\u0000\u0000\u01ac\u01a4\u0001\u0000\u0000\u0000\u01ad" +
            "\u000f\u0001\u0000\u0000\u0000\u01ae\u01b2\u0005|\u0000\u0000\u01af\u01b2" +
            "\u0005X\u0000\u0000\u01b0\u01b2\u0003\u0130\u0098\u0000\u01b1\u01ae\u0001" +
            "\u0000\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b0\u0001" +
            "\u0000\u0000\u0000\u01b2\u0011\u0001\u0000\u0000\u0000\u01b3\u01b5\b\u0000" +
            "\u0000\u0000\u01b4\u01b3\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000" +
            "\u0000\u0000\u01b6\u01b4\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000" +
            "\u0000\u0000\u01b7\u0013\u0001\u0000\u0000\u0000\u01b8\u01bc\u0005\u008c" +
            "\u0000\u0000\u01b9\u01bc\u0005}\u0000\u0000\u01ba\u01bc\u0003\u0016\u000b" +
            "\u0000\u01bb\u01b8\u0001\u0000\u0000\u0000\u01bb\u01b9\u0001\u0000\u0000" +
            "\u0000\u01bb\u01ba\u0001\u0000\u0000\u0000\u01bc\u0015\u0001\u0000\u0000" +
            "\u0000\u01bd\u01be\u0005\b\u0000\u0000\u01be\u01bf\u0003\u0112\u0089\u0000" +
            "\u01bf\u01c0\u0005\n\u0000\u0000\u01c0\u0017\u0001\u0000\u0000\u0000\u01c1" +
            "\u01c2\u0005\r\u0000\u0000\u01c2\u01c3\u0003\u0116\u008b\u0000\u01c3\u0019" +
            "\u0001\u0000\u0000\u0000\u01c4\u01c7\u0003\u00fc~\u0000\u01c5\u01c7\u0003" +
            "\u0102\u0081\u0000\u01c6\u01c4\u0001\u0000\u0000\u0000\u01c6\u01c5\u0001" +
            "\u0000\u0000\u0000\u01c7\u001b\u0001\u0000\u0000\u0000\u01c8\u01ca\u0005" +
            "\u001e\u0000\u0000\u01c9\u01cb\u0003\u001e\u000f\u0000\u01ca\u01c9\u0001" +
            "\u0000\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001" +
            "\u0000\u0000\u0000\u01cc\u01cd\u0005\u001f\u0000\u0000\u01cd\u001d\u0001" +
            "\u0000\u0000\u0000\u01ce\u01d3\u0003 \u0010\u0000\u01cf\u01d0\u0005\f" +
            "\u0000\u0000\u01d0\u01d2\u0003 \u0010\u0000\u01d1\u01cf\u0001\u0000\u0000" +
            "\u0000\u01d2\u01d5\u0001\u0000\u0000\u0000\u01d3\u01d1\u0001\u0000\u0000" +
            "\u0000\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4\u001f\u0001\u0000\u0000" +
            "\u0000\u01d5\u01d3\u0001\u0000\u0000\u0000\u01d6\u01d8\u0005|\u0000\u0000" +
            "\u01d7\u01d9\u0003\"\u0011\u0000\u01d8\u01d7\u0001\u0000\u0000\u0000\u01d8" +
            "\u01d9\u0001\u0000\u0000\u0000\u01d9\u01dc\u0001\u0000\u0000\u0000\u01da" +
            "\u01dc\u0003\u001c\u000e\u0000\u01db\u01d6\u0001\u0000\u0000\u0000\u01db" +
            "\u01da\u0001\u0000\u0000\u0000\u01dc!\u0001\u0000\u0000\u0000\u01dd\u01de" +
            "\u0005^\u0000\u0000\u01de\u01df\u0003*\u0015\u0000\u01df#\u0001\u0000" +
            "\u0000\u0000\u01e0\u01e2\u0005\u001e\u0000\u0000\u01e1\u01e3\u0003&\u0013" +
            "\u0000\u01e2\u01e1\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000" +
            "\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4\u01e5\u0005\u001f\u0000" +
            "\u0000\u01e5%\u0001\u0000\u0000\u0000\u01e6\u01eb\u0003(\u0014\u0000\u01e7" +
            "\u01e8\u0005\f\u0000\u0000\u01e8\u01ea\u0003(\u0014\u0000\u01e9\u01e7" +
            "\u0001\u0000\u0000\u0000\u01ea\u01ed\u0001\u0000\u0000\u0000\u01eb\u01e9" +
            "\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001\u0000\u0000\u0000\u01ec\'\u0001" +
            "\u0000\u0000\u0000\u01ed\u01eb\u0001\u0000\u0000\u0000\u01ee\u01ef\u0003" +
            "*\u0015\u0000\u01ef)\u0001\u0000\u0000\u0000\u01f0\u01f6\u0003,\u0016" +
            "\u0000\u01f1\u01f6\u0003J%\u0000\u01f2\u01f6\u0003L&\u0000\u01f3\u01f6" +
            "\u00036\u001b\u0000\u01f4\u01f6\u0005}\u0000\u0000\u01f5\u01f0\u0001\u0000" +
            "\u0000\u0000\u01f5\u01f1\u0001\u0000\u0000\u0000\u01f5\u01f2\u0001\u0000" +
            "\u0000\u0000\u01f5\u01f3\u0001\u0000\u0000\u0000\u01f5\u01f4\u0001\u0000" +
            "\u0000\u0000\u01f6+\u0001\u0000\u0000\u0000\u01f7\u01f8\u0006\u0016\uffff" +
            "\uffff\u0000\u01f8\u01f9\u0003.\u0017\u0000\u01f9\u0202\u0001\u0000\u0000" +
            "\u0000\u01fa\u01fb\n\u0003\u0000\u0000\u01fb\u01fc\u0005(\u0000\u0000" +
            "\u01fc\u0201\u0003,\u0016\u0004\u01fd\u01fe\n\u0002\u0000\u0000\u01fe" +
            "\u01ff\u0005&\u0000\u0000\u01ff\u0201\u0003,\u0016\u0003\u0200\u01fa\u0001" +
            "\u0000\u0000\u0000\u0200\u01fd\u0001\u0000\u0000\u0000\u0201\u0204\u0001" +
            "\u0000\u0000\u0000\u0202\u0200\u0001\u0000\u0000\u0000\u0202\u0203\u0001" +
            "\u0000\u0000\u0000\u0203-\u0001\u0000\u0000\u0000\u0204\u0202\u0001\u0000" +
            "\u0000\u0000\u0205\u0206\u0006\u0017\uffff\uffff\u0000\u0206\u0207\u0005" +
            "\u0006\u0000\u0000\u0207\u0208\u0003*\u0015\u0000\u0208\u0209\u0005\u0007" +
            "\u0000\u0000\u0209\u0218\u0001\u0000\u0000\u0000\u020a\u0218\u00030\u0018" +
            "\u0000\u020b\u0218\u00032\u0019\u0000\u020c\u0218\u0003<\u001e\u0000\u020d" +
            "\u020e\u0005\u0004\u0000\u0000\u020e\u020f\u0003H$\u0000\u020f\u0210\u0005" +
            "\u0005\u0000\u0000\u0210\u0218\u0001\u0000\u0000\u0000\u0211\u0218\u0003" +
            "N\'\u0000\u0212\u0218\u0005P\u0000\u0000\u0213\u0214\u00032\u0019\u0000" +
            "\u0214\u0215\u0005z\u0000\u0000\u0215\u0216\u0003.\u0017\u0001\u0216\u0218" +
            "\u0001\u0000\u0000\u0000\u0217\u0205\u0001\u0000\u0000\u0000\u0217\u020a" +
            "\u0001\u0000\u0000\u0000\u0217\u020b\u0001\u0000\u0000\u0000\u0217\u020c" +
            "\u0001\u0000\u0000\u0000\u0217\u020d\u0001\u0000\u0000\u0000\u0217\u0211" +
            "\u0001\u0000\u0000\u0000\u0217\u0212\u0001\u0000\u0000\u0000\u0217\u0213" +
            "\u0001\u0000\u0000\u0000\u0218\u021f\u0001\u0000\u0000\u0000\u0219\u021a" +
            "\n\u0005\u0000\u0000\u021a\u021b\u0004\u0017\u0004\u0000\u021b\u021c\u0005" +
            "\u0004\u0000\u0000\u021c\u021e\u0005\u0005\u0000\u0000\u021d\u0219\u0001" +
            "\u0000\u0000\u0000\u021e\u0221\u0001\u0000\u0000\u0000\u021f\u021d\u0001" +
            "\u0000\u0000\u0000\u021f\u0220\u0001\u0000\u0000\u0000\u0220/\u0001\u0000" +
            "\u0000\u0000\u0221\u021f\u0001\u0000\u0000\u0000\u0222\u0223\u0007\u0001" +
            "\u0000\u0000\u02231\u0001\u0000\u0000\u0000\u0224\u0226\u0003:\u001d\u0000" +
            "\u0225\u0227\u00034\u001a\u0000\u0226\u0225\u0001\u0000\u0000\u0000\u0226" +
            "\u0227\u0001\u0000\u0000\u0000\u02273\u0001\u0000\u0000\u0000\u0228\u022b" +
            "\u00038\u001c\u0000\u0229\u022b\u00036\u001b\u0000\u022a\u0228\u0001\u0000" +
            "\u0000\u0000\u022a\u0229\u0001\u0000\u0000\u0000\u022b5\u0001\u0000\u0000" +
            "\u0000\u022c\u022d\u0005\u001e\u0000\u0000\u022d\u022e\u0003&\u0013\u0000" +
            "\u022e\u022f\u0005\u001f\u0000\u0000\u022f7\u0001\u0000\u0000\u0000\u0230" +
            "\u0231\u0005\u001e\u0000\u0000\u0231\u0232\u0003&\u0013\u0000\u0232\u0233" +
            "\u0005\u001e\u0000\u0000\u0233\u0239\u0003&\u0013\u0000\u0234\u0235\u0005" +
            "\u001f\u0000\u0000\u0235\u0236\u0003\u001a\r\u0000\u0236\u0237\u0005\u001f" +
            "\u0000\u0000\u0237\u023a\u0001\u0000\u0000\u0000\u0238\u023a\u0005\u001b" +
            "\u0000\u0000\u0239\u0234\u0001\u0000\u0000\u0000\u0239\u0238\u0001\u0000" +
            "\u0000\u0000\u023a9\u0001\u0000\u0000\u0000\u023b\u023e\u0005|\u0000\u0000" +
            "\u023c\u023e\u0003\u0082A\u0000\u023d\u023b\u0001\u0000\u0000\u0000\u023d" +
            "\u023c\u0001\u0000\u0000\u0000\u023e;\u0001\u0000\u0000\u0000\u023f\u0241" +
            "\u0005\b\u0000\u0000\u0240\u0242\u0003>\u001f\u0000\u0241\u0240\u0001" +
            "\u0000\u0000\u0000\u0241\u0242\u0001\u0000\u0000\u0000\u0242\u0243\u0001" +
            "\u0000\u0000\u0000\u0243\u0244\u0005\n\u0000\u0000\u0244=\u0001\u0000" +
            "\u0000\u0000\u0245\u0247\u0003@ \u0000\u0246\u0248\u0007\u0002\u0000\u0000" +
            "\u0247\u0246\u0001\u0000\u0000\u0000\u0247\u0248\u0001\u0000\u0000\u0000" +
            "\u0248?\u0001\u0000\u0000\u0000\u0249\u024e\u0003B!\u0000\u024a\u024b" +
            "\u0007\u0002\u0000\u0000\u024b\u024d\u0003B!\u0000\u024c\u024a\u0001\u0000" +
            "\u0000\u0000\u024d\u0250\u0001\u0000\u0000\u0000\u024e\u024c\u0001\u0000" +
            "\u0000\u0000\u024e\u024f\u0001\u0000\u0000\u0000\u024fA\u0001\u0000\u0000" +
            "\u0000\u0250\u024e\u0001\u0000\u0000\u0000\u0251\u025b\u0003R)\u0000\u0252" +
            "\u025b\u0003V+\u0000\u0253\u025b\u0003h4\u0000\u0254\u025b\u0003j5\u0000" +
            "\u0255\u0258\u0003l6\u0000\u0256\u0257\u00056\u0000\u0000\u0257\u0259" +
            "\u0003*\u0015\u0000\u0258\u0256\u0001\u0000\u0000\u0000\u0258\u0259\u0001" +
            "\u0000\u0000\u0000\u0259\u025b\u0001\u0000\u0000\u0000\u025a\u0251\u0001" +
            "\u0000\u0000\u0000\u025a\u0252\u0001\u0000\u0000\u0000\u025a\u0253\u0001" +
            "\u0000\u0000\u0000\u025a\u0254\u0001\u0000\u0000\u0000\u025a\u0255\u0001" +
            "\u0000\u0000\u0000\u025bC\u0001\u0000\u0000\u0000\u025c\u025d\u0003.\u0017" +
            "\u0000\u025d\u025e\u0004\"\u0005\u0000\u025e\u025f\u0005\u0004\u0000\u0000" +
            "\u025f\u0260\u0005\u0005\u0000\u0000\u0260E\u0001\u0000\u0000\u0000\u0261" +
            "\u0262\u0005\u0004\u0000\u0000\u0262\u0263\u0003H$\u0000\u0263\u0264\u0005" +
            "\u0005\u0000\u0000\u0264G\u0001\u0000\u0000\u0000\u0265\u026a\u0003*\u0015" +
            "\u0000\u0266\u0267\u0005\f\u0000\u0000\u0267\u0269\u0003*\u0015\u0000" +
            "\u0268\u0266\u0001\u0000\u0000\u0000\u0269\u026c\u0001\u0000\u0000\u0000" +
            "\u026a\u0268\u0001\u0000\u0000\u0000\u026a\u026b\u0001\u0000\u0000\u0000" +
            "\u026bI\u0001\u0000\u0000\u0000\u026c\u026a\u0001\u0000\u0000\u0000\u026d" +
            "\u026f\u0003\u001c\u000e\u0000\u026e\u026d\u0001\u0000\u0000\u0000\u026e" +
            "\u026f\u0001\u0000\u0000\u0000\u026f\u0270\u0001\u0000\u0000\u0000\u0270" +
            "\u0272\u0005\u0006\u0000\u0000\u0271\u0273\u0003X,\u0000\u0272\u0271\u0001" +
            "\u0000\u0000\u0000\u0272\u0273\u0001\u0000\u0000\u0000\u0273\u0274\u0001" +
            "\u0000\u0000\u0000\u0274\u0275\u0005\u0007\u0000\u0000\u0275\u0276\u0005" +
            "6\u0000\u0000\u0276\u0277\u0003*\u0015\u0000\u0277K\u0001\u0000\u0000" +
            "\u0000\u0278\u027a\u0005D\u0000\u0000\u0279\u027b\u0003\u001c\u000e\u0000" +
            "\u027a\u0279\u0001\u0000\u0000\u0000\u027a\u027b\u0001\u0000\u0000\u0000" +
            "\u027b\u027c\u0001\u0000\u0000\u0000\u027c\u027e\u0005\u0006\u0000\u0000" +
            "\u027d\u027f\u0003X,\u0000\u027e\u027d\u0001\u0000\u0000\u0000\u027e\u027f" +
            "\u0001\u0000\u0000\u0000\u027f\u0280\u0001\u0000\u0000\u0000\u0280\u0281" +
            "\u0005\u0007\u0000\u0000\u0281\u0282\u00056\u0000\u0000\u0282\u0283\u0003" +
            "*\u0015\u0000\u0283M\u0001\u0000\u0000\u0000\u0284\u0285\u0005A\u0000" +
            "\u0000\u0285\u0286\u0003P(\u0000\u0286O\u0001\u0000\u0000\u0000\u0287" +
            "\u0292\u0005|\u0000\u0000\u0288\u0289\u0003\u012a\u0095\u0000\u0289\u028a" +
            "\u0005\u0011\u0000\u0000\u028a\u028c\u0001\u0000\u0000\u0000\u028b\u0288" +
            "\u0001\u0000\u0000\u0000\u028c\u028d\u0001\u0000\u0000\u0000\u028d\u028b" +
            "\u0001\u0000\u0000\u0000\u028d\u028e\u0001\u0000\u0000\u0000\u028e\u028f" +
            "\u0001\u0000\u0000\u0000\u028f\u0290\u0003\u012a\u0095\u0000\u0290\u0292" +
            "\u0001\u0000\u0000\u0000\u0291\u0287\u0001\u0000\u0000\u0000\u0291\u028b" +
            "\u0001\u0000\u0000\u0000\u0292Q\u0001\u0000\u0000\u0000\u0293\u0295\u0005" +
            "Z\u0000\u0000\u0294\u0293\u0001\u0000\u0000\u0000\u0294\u0295\u0001\u0000" +
            "\u0000\u0000\u0295\u0296\u0001\u0000\u0000\u0000\u0296\u0298\u0003\u010a" +
            "\u0085\u0000\u0297\u0299\u0005\u000e\u0000\u0000\u0298\u0297\u0001\u0000" +
            "\u0000\u0000\u0298\u0299\u0001\u0000\u0000\u0000\u0299\u029b\u0001\u0000" +
            "\u0000\u0000\u029a\u029c\u0003T*\u0000\u029b\u029a\u0001\u0000\u0000\u0000" +
            "\u029b\u029c\u0001\u0000\u0000\u0000\u029c\u029f\u0001\u0000\u0000\u0000" +
            "\u029d\u029e\u00056\u0000\u0000\u029e\u02a0\u0003*\u0015\u0000\u029f\u029d" +
            "\u0001\u0000\u0000\u0000\u029f\u02a0\u0001\u0000\u0000\u0000\u02a0S\u0001" +
            "\u0000\u0000\u0000\u02a1\u02a2\u0005\u000f\u0000\u0000\u02a2\u02a3\u0003" +
            "*\u0015\u0000\u02a3U\u0001\u0000\u0000\u0000\u02a4\u02a6\u0003\u001c\u000e" +
            "\u0000\u02a5\u02a4\u0001\u0000\u0000\u0000\u02a5\u02a6\u0001\u0000\u0000" +
            "\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7\u02a9\u0005\u0006\u0000" +
            "\u0000\u02a8\u02aa\u0003X,\u0000\u02a9\u02a8\u0001\u0000\u0000\u0000\u02a9" +
            "\u02aa\u0001\u0000\u0000\u0000\u02aa\u02ab\u0001\u0000\u0000\u0000\u02ab" +
            "\u02ad\u0005\u0007\u0000\u0000\u02ac\u02ae\u0003T*\u0000\u02ad\u02ac\u0001" +
            "\u0000\u0000\u0000\u02ad\u02ae\u0001\u0000\u0000\u0000\u02aeW\u0001\u0000" +
            "\u0000\u0000\u02af\u02bd\u0003`0\u0000\u02b0\u02b5\u0003\\.\u0000\u02b1" +
            "\u02b2\u0005\f\u0000\u0000\u02b2\u02b4\u0003\\.\u0000\u02b3\u02b1\u0001" +
            "\u0000\u0000\u0000\u02b4\u02b7\u0001\u0000\u0000\u0000\u02b5\u02b3\u0001" +
            "\u0000\u0000\u0000\u02b5\u02b6\u0001\u0000\u0000\u0000\u02b6\u02ba\u0001" +
            "\u0000\u0000\u0000\u02b7\u02b5\u0001\u0000\u0000\u0000\u02b8\u02b9\u0005" +
            "\f\u0000\u0000\u02b9\u02bb\u0003`0\u0000\u02ba\u02b8\u0001\u0000\u0000" +
            "\u0000\u02ba\u02bb\u0001\u0000\u0000\u0000\u02bb\u02bd\u0001\u0000\u0000" +
            "\u0000\u02bc\u02af\u0001\u0000\u0000\u0000\u02bc\u02b0\u0001\u0000\u0000" +
            "\u0000\u02bdY\u0001\u0000\u0000\u0000\u02be\u02c3\u0003b1\u0000\u02bf" +
            "\u02c0\u0005\f\u0000\u0000\u02c0\u02c2\u0003b1\u0000\u02c1\u02bf\u0001" +
            "\u0000\u0000\u0000\u02c2\u02c5\u0001\u0000\u0000\u0000\u02c3\u02c1\u0001" +
            "\u0000\u0000\u0000\u02c3\u02c4\u0001\u0000\u0000\u0000\u02c4[\u0001\u0000" +
            "\u0000\u0000\u02c5\u02c3\u0001\u0000\u0000\u0000\u02c6\u02c9\u0003b1\u0000" +
            "\u02c7\u02c9\u0003^/\u0000\u02c8\u02c6\u0001\u0000\u0000\u0000\u02c8\u02c7" +
            "\u0001\u0000\u0000\u0000\u02c9]\u0001\u0000\u0000\u0000\u02ca\u02cc\u0003" +
            "\u0086C\u0000\u02cb\u02ca\u0001\u0000\u0000\u0000\u02cb\u02cc\u0001\u0000" +
            "\u0000\u0000\u02cc\u02ce\u0001\u0000\u0000\u0000\u02cd\u02cf\u0003d2\u0000" +
            "\u02ce\u02cd\u0001\u0000\u0000\u0000\u02ce\u02cf\u0001\u0000\u0000\u0000" +
            "\u02cf\u02d0\u0001\u0000\u0000\u0000\u02d0\u02d9\u0003f3\u0000\u02d1\u02d3" +
            "\u0005\u000e\u0000\u0000\u02d2\u02d4\u0003T*\u0000\u02d3\u02d2\u0001\u0000" +
            "\u0000\u0000\u02d3\u02d4\u0001\u0000\u0000\u0000\u02d4\u02da\u0001\u0000" +
            "\u0000\u0000\u02d5\u02d7\u0003T*\u0000\u02d6\u02d5\u0001\u0000\u0000\u0000" +
            "\u02d6\u02d7\u0001\u0000\u0000\u0000\u02d7\u02d8\u0001\u0000\u0000\u0000" +
            "\u02d8\u02da\u0003\u0018\f\u0000\u02d9\u02d1\u0001\u0000\u0000\u0000\u02d9" +
            "\u02d6\u0001\u0000\u0000\u0000\u02da_\u0001\u0000\u0000\u0000\u02db\u02dc" +
            "\u0005\u0010\u0000\u0000\u02dc\u02de\u0003\u0116\u008b\u0000\u02dd\u02df" +
            "\u0003T*\u0000\u02de\u02dd\u0001\u0000\u0000\u0000\u02de\u02df\u0001\u0000" +
            "\u0000\u0000\u02dfa\u0001\u0000\u0000\u0000\u02e0\u02e2\u0003\u0086C\u0000" +
            "\u02e1\u02e0\u0001\u0000\u0000\u0000\u02e1\u02e2\u0001\u0000\u0000\u0000" +
            "\u02e2\u02e4\u0001\u0000\u0000\u0000\u02e3\u02e5\u0003d2\u0000\u02e4\u02e3" +
            "\u0001\u0000\u0000\u0000\u02e4\u02e5\u0001\u0000\u0000\u0000\u02e5\u02e6" +
            "\u0001\u0000\u0000\u0000\u02e6\u02e8\u0003f3\u0000\u02e7\u02e9\u0003T" +
            "*\u0000\u02e8\u02e7\u0001\u0000\u0000\u0000\u02e8\u02e9\u0001\u0000\u0000" +
            "\u0000\u02e9c\u0001\u0000\u0000\u0000\u02ea\u02eb\u0007\u0003\u0000\u0000" +
            "\u02ebe\u0001\u0000\u0000\u0000\u02ec\u02ef\u0003\u012a\u0095\u0000\u02ed" +
            "\u02ef\u0003\u001a\r\u0000\u02ee\u02ec\u0001\u0000\u0000\u0000\u02ee\u02ed" +
            "\u0001\u0000\u0000\u0000\u02efg\u0001\u0000\u0000\u0000\u02f0\u02f2\u0005" +
            "D\u0000\u0000\u02f1\u02f3\u0003\u001c\u000e\u0000\u02f2\u02f1\u0001\u0000" +
            "\u0000\u0000\u02f2\u02f3\u0001\u0000\u0000\u0000\u02f3\u02f4\u0001\u0000" +
            "\u0000\u0000\u02f4\u02f6\u0005\u0006\u0000\u0000\u02f5\u02f7\u0003X,\u0000" +
            "\u02f6\u02f5\u0001\u0000\u0000\u0000\u02f6\u02f7\u0001\u0000\u0000\u0000" +
            "\u02f7\u02f8\u0001\u0000\u0000\u0000\u02f8\u02fa\u0005\u0007\u0000\u0000" +
            "\u02f9\u02fb\u0003T*\u0000\u02fa\u02f9\u0001\u0000\u0000\u0000\u02fa\u02fb" +
            "\u0001\u0000\u0000\u0000\u02fbi\u0001\u0000\u0000\u0000\u02fc\u02fd\u0005" +
            "\u0004\u0000\u0000\u02fd\u02fe\u0005|\u0000\u0000\u02fe\u02ff\u0005\u000f" +
            "\u0000\u0000\u02ff\u0300\u0007\u0004\u0000\u0000\u0300\u0301\u0005\u0005" +
            "\u0000\u0000\u0301\u0302\u0003T*\u0000\u0302k\u0001\u0000\u0000\u0000" +
            "\u0303\u0305\u0003\u010a\u0085\u0000\u0304\u0306\u0005\u000e\u0000\u0000" +
            "\u0305\u0304\u0001\u0000\u0000\u0000\u0305\u0306\u0001\u0000\u0000\u0000" +
            "\u0306\u0307\u0001\u0000\u0000\u0000\u0307\u0308\u0003V+\u0000\u0308m" +
            "\u0001\u0000\u0000\u0000\u0309\u030a\u0005q\u0000\u0000\u030a\u030c\u0005" +
            "|\u0000\u0000\u030b\u030d\u0003\u001c\u000e\u0000\u030c\u030b\u0001\u0000" +
            "\u0000\u0000\u030c\u030d\u0001\u0000\u0000\u0000\u030d\u030e\u0001\u0000" +
            "\u0000\u0000\u030e\u030f\u0005\r\u0000\u0000\u030f\u0310\u0003*\u0015" +
            "\u0000\u0310\u0311\u0005\u000b\u0000\u0000\u0311o\u0001\u0000\u0000\u0000" +
            "\u0312\u0314\u0003d2\u0000\u0313\u0312\u0001\u0000\u0000\u0000\u0313\u0314" +
            "\u0001\u0000\u0000\u0000\u0314\u0315\u0001\u0000\u0000\u0000\u0315\u0316" +
            "\u0005t\u0000\u0000\u0316\u0318\u0005\u0006\u0000\u0000\u0317\u0319\u0003" +
            "\u00f2y\u0000\u0318\u0317\u0001\u0000\u0000\u0000\u0318\u0319\u0001\u0000" +
            "\u0000\u0000\u0319\u031a\u0001\u0000\u0000\u0000\u031a\u0320\u0005\u0007" +
            "\u0000\u0000\u031b\u031c\u0005\b\u0000\u0000\u031c\u031d\u0003\u00f8|" +
            "\u0000\u031d\u031e\u0005\n\u0000\u0000\u031e\u0321\u0001\u0000\u0000\u0000" +
            "\u031f\u0321\u0005\u000b\u0000\u0000\u0320\u031b\u0001\u0000\u0000\u0000" +
            "\u0320\u031f\u0001\u0000\u0000\u0000\u0320\u0321\u0001\u0000\u0000\u0000" +
            "\u0321q\u0001\u0000\u0000\u0000\u0322\u0324\u0005a\u0000\u0000\u0323\u0322" +
            "\u0001\u0000\u0000\u0000\u0323\u0324\u0001\u0000\u0000\u0000\u0324\u0326" +
            "\u0001\u0000\u0000\u0000\u0325\u0327\u0005x\u0000\u0000\u0326\u0325\u0001" +
            "\u0000\u0000\u0000\u0326\u0327\u0001\u0000\u0000\u0000\u0327\u0328\u0001" +
            "\u0000\u0000\u0000\u0328\u0329\u0005g\u0000\u0000\u0329\u032b\u0005|\u0000" +
            "\u0000\u032a\u032c\u0003\u001c\u000e\u0000\u032b\u032a\u0001\u0000\u0000" +
            "\u0000\u032b\u032c\u0001\u0000\u0000\u0000\u032c\u032e\u0001\u0000\u0000" +
            "\u0000\u032d\u032f\u0003t:\u0000\u032e\u032d\u0001\u0000\u0000\u0000\u032e" +
            "\u032f\u0001\u0000\u0000\u0000\u032f\u0330\u0001\u0000\u0000\u0000\u0330" +
            "\u0332\u0003<\u001e\u0000\u0331\u0333\u0005\u000b\u0000\u0000\u0332\u0331" +
            "\u0001\u0000\u0000\u0000\u0332\u0333\u0001\u0000\u0000\u0000\u0333s\u0001" +
            "\u0000\u0000\u0000\u0334\u0335\u0005^\u0000\u0000\u0335\u0336\u0003v;" +
            "\u0000\u0336u\u0001\u0000\u0000\u0000\u0337\u033c\u00032\u0019\u0000\u0338" +
            "\u0339\u0005\f\u0000\u0000\u0339\u033b\u00032\u0019\u0000\u033a\u0338" +
            "\u0001\u0000\u0000\u0000\u033b\u033e\u0001\u0000\u0000\u0000\u033c\u033a" +
            "\u0001\u0000\u0000\u0000\u033c\u033d\u0001\u0000\u0000\u0000\u033dw\u0001" +
            "\u0000\u0000\u0000\u033e\u033c\u0001\u0000\u0000\u0000\u033f\u0341\u0005" +
            "`\u0000\u0000\u0340\u033f\u0001\u0000\u0000\u0000\u0340\u0341\u0001\u0000" +
            "\u0000\u0000\u0341\u0342\u0001\u0000\u0000\u0000\u0342\u0343\u0005]\u0000" +
            "\u0000\u0343\u0344\u0005|\u0000\u0000\u0344\u0346\u0005\b\u0000\u0000" +
            "\u0345\u0347\u0003z=\u0000\u0346\u0345\u0001\u0000\u0000\u0000\u0346\u0347" +
            "\u0001\u0000\u0000\u0000\u0347\u0348\u0001\u0000\u0000\u0000\u0348\u0349" +
            "\u0005\n\u0000\u0000\u0349y\u0001\u0000\u0000\u0000\u034a\u034c\u0003" +
            "|>\u0000\u034b\u034d\u0005\f\u0000\u0000\u034c\u034b\u0001\u0000\u0000" +
            "\u0000\u034c\u034d\u0001\u0000\u0000\u0000\u034d{\u0001\u0000\u0000\u0000" +
            "\u034e\u0353\u0003~?\u0000\u034f\u0350\u0005\f\u0000\u0000\u0350\u0352" +
            "\u0003~?\u0000\u0351\u034f\u0001\u0000\u0000\u0000\u0352\u0355\u0001\u0000" +
            "\u0000\u0000\u0353\u0351\u0001\u0000\u0000\u0000\u0353\u0354\u0001\u0000" +
            "\u0000\u0000\u0354}\u0001\u0000\u0000\u0000\u0355\u0353\u0001\u0000\u0000" +
            "\u0000\u0356\u0359\u0003\u010a\u0085\u0000\u0357\u0358\u0005\r\u0000\u0000" +
            "\u0358\u035a\u0003\u0116\u008b\u0000\u0359\u0357\u0001\u0000\u0000\u0000" +
            "\u0359\u035a\u0001\u0000\u0000\u0000\u035a\u007f\u0001\u0000\u0000\u0000" +
            "\u035b\u035c\u0005u\u0000\u0000\u035c\u035d\u0003\u0082A\u0000\u035d\u035f" +
            "\u0005\b\u0000\u0000\u035e\u0360\u0003\u0096K\u0000\u035f\u035e\u0001" +
            "\u0000\u0000\u0000\u035f\u0360\u0001\u0000\u0000\u0000\u0360\u0361\u0001" +
            "\u0000\u0000\u0000\u0361\u0362\u0005\n\u0000\u0000\u0362\u0081\u0001\u0000" +
            "\u0000\u0000\u0363\u036c\u0005|\u0000\u0000\u0364\u0366\u0005\u0011\u0000" +
            "\u0000\u0365\u0364\u0001\u0000\u0000\u0000\u0366\u0367\u0001\u0000\u0000" +
            "\u0000\u0367\u0365\u0001\u0000\u0000\u0000\u0367\u0368\u0001\u0000\u0000" +
            "\u0000\u0368\u0369\u0001\u0000\u0000\u0000\u0369\u036b\u0005|\u0000\u0000" +
            "\u036a\u0365\u0001\u0000\u0000\u0000\u036b\u036e\u0001\u0000\u0000\u0000" +
            "\u036c\u036a\u0001\u0000\u0000\u0000\u036c\u036d\u0001\u0000\u0000\u0000" +
            "\u036d\u0083\u0001\u0000\u0000\u0000\u036e\u036c\u0001\u0000\u0000\u0000" +
            "\u036f\u0370\u0005|\u0000\u0000\u0370\u0371\u0005\r\u0000\u0000\u0371" +
            "\u0372\u0003\u0082A\u0000\u0372\u0373\u0005\u000b\u0000\u0000\u0373\u0085" +
            "\u0001\u0000\u0000\u0000\u0374\u0376\u0003\u0088D\u0000\u0375\u0374\u0001" +
            "\u0000\u0000\u0000\u0376\u0377\u0001\u0000\u0000\u0000\u0377\u0375\u0001" +
            "\u0000\u0000\u0000\u0377\u0378\u0001\u0000\u0000\u0000\u0378\u0087\u0001" +
            "\u0000\u0000\u0000\u0379\u037c\u0005{\u0000\u0000\u037a\u037d\u0003\u008a" +
            "E\u0000\u037b\u037d\u0003\u008cF\u0000\u037c\u037a\u0001\u0000\u0000\u0000" +
            "\u037c\u037b\u0001\u0000\u0000\u0000\u037d\u0089\u0001\u0000\u0000\u0000" +
            "\u037e\u037f\u0006E\uffff\uffff\u0000\u037f\u0385\u0005|\u0000\u0000\u0380" +
            "\u0381\u0005\u0006\u0000\u0000\u0381\u0382\u0003\u0116\u008b\u0000\u0382" +
            "\u0383\u0005\u0007\u0000\u0000\u0383\u0385\u0001\u0000\u0000\u0000\u0384" +
            "\u037e\u0001\u0000\u0000\u0000\u0384\u0380\u0001\u0000\u0000\u0000\u0385" +
            "\u038b\u0001\u0000\u0000\u0000\u0386\u0387\n\u0002\u0000\u0000\u0387\u0388" +
            "\u0005\u0011\u0000\u0000\u0388\u038a\u0003\u012a\u0095\u0000\u0389\u0386" +
            "\u0001\u0000\u0000\u0000\u038a\u038d\u0001\u0000\u0000\u0000\u038b\u0389" +
            "\u0001\u0000\u0000\u0000\u038b\u038c\u0001\u0000\u0000\u0000\u038c\u008b" +
            "\u0001\u0000\u0000\u0000\u038d\u038b\u0001\u0000\u0000\u0000\u038e\u038f" +
            "\u0003\u008aE\u0000\u038f\u0390\u0003\u010c\u0086\u0000\u0390\u008d\u0001" +
            "\u0000\u0000\u0000\u0391\u0393\u0003\u00fa}\u0000\u0392\u0391\u0001\u0000" +
            "\u0000\u0000\u0392\u0393\u0001\u0000\u0000\u0000\u0393\u0394\u0001\u0000" +
            "\u0000\u0000\u0394\u0395\u0005\u0000\u0000\u0001\u0395\u008f\u0001\u0000" +
            "\u0000\u0000\u0396\u0398\u0005a\u0000\u0000\u0397\u0396\u0001\u0000\u0000" +
            "\u0000\u0397\u0398\u0001\u0000\u0000\u0000\u0398\u0399\u0001\u0000\u0000" +
            "\u0000\u0399\u039a\u0003\u0092I\u0000\u039a\u0091\u0001\u0000\u0000\u0000" +
            "\u039b\u03ba\u0003\u0094J\u0000\u039c\u03ba\u0003\u009aM\u0000\u039d\u03ba" +
            "\u0003\u00a0P\u0000\u039e\u03ba\u0003\u00a8T\u0000\u039f\u03ba\u0003\u0098" +
            "L\u0000\u03a0\u03ba\u0003\u0086C\u0000\u03a1\u03ba\u0003\u00d4j\u0000" +
            "\u03a2\u03ba\u0003r9\u0000\u03a3\u03ba\u0003\u0080@\u0000\u03a4\u03ba" +
            "\u0003\u00acV\u0000\u03a5\u03ba\u0003\u00aeW\u0000\u03a6\u03ba\u0003\u00b2" +
            "Y\u0000\u03a7\u03ba\u0003\u00b4Z\u0000\u03a8\u03ba\u0003\u00b6[\u0000" +
            "\u03a9\u03ba\u0003\u00b8\\\u0000\u03aa\u03ba\u0003\u00ba]\u0000\u03ab" +
            "\u03ba\u0003\u00c6c\u0000\u03ac\u03ba\u0003\u00bc^\u0000\u03ad\u03ba\u0003" +
            "\u00c8d\u0000\u03ae\u03ba\u0003\u00cae\u0000\u03af\u03ba\u0003\u00d0h" +
            "\u0000\u03b0\u03ba\u0003\u00d2i\u0000\u03b1\u03ba\u0003\u011a\u008d\u0000" +
            "\u03b2\u03ba\u0003\u00e8t\u0000\u03b3\u03ba\u0003\u00a2Q\u0000\u03b4\u03ba" +
            "\u0003n7\u0000\u03b5\u03ba\u0003x<\u0000\u03b6\u03ba\u0003\u00aaU\u0000" +
            "\u03b7\u03b8\u0005a\u0000\u0000\u03b8\u03ba\u0003\u0092I\u0000\u03b9\u039b" +
            "\u0001\u0000\u0000\u0000\u03b9\u039c\u0001\u0000\u0000\u0000\u03b9\u039d" +
            "\u0001\u0000\u0000\u0000\u03b9\u039e\u0001\u0000\u0000\u0000\u03b9\u039f" +
            "\u0001\u0000\u0000\u0000\u03b9\u03a0\u0001\u0000\u0000\u0000\u03b9\u03a1" +
            "\u0001\u0000\u0000\u0000\u03b9\u03a2\u0001\u0000\u0000\u0000\u03b9\u03a3" +
            "\u0001\u0000\u0000\u0000\u03b9\u03a4\u0001\u0000\u0000\u0000\u03b9\u03a5" +
            "\u0001\u0000\u0000\u0000\u03b9\u03a6\u0001\u0000\u0000\u0000\u03b9\u03a7" +
            "\u0001\u0000\u0000\u0000\u03b9\u03a8\u0001\u0000\u0000\u0000\u03b9\u03a9" +
            "\u0001\u0000\u0000\u0000\u03b9\u03aa\u0001\u0000\u0000\u0000\u03b9\u03ab" +
            "\u0001\u0000\u0000\u0000\u03b9\u03ac\u0001\u0000\u0000\u0000\u03b9\u03ad" +
            "\u0001\u0000\u0000\u0000\u03b9\u03ae\u0001\u0000\u0000\u0000\u03b9\u03af" +
            "\u0001\u0000\u0000\u0000\u03b9\u03b0\u0001\u0000\u0000\u0000\u03b9\u03b1" +
            "\u0001\u0000\u0000\u0000\u03b9\u03b2\u0001\u0000\u0000\u0000\u03b9\u03b3" +
            "\u0001\u0000\u0000\u0000\u03b9\u03b4\u0001\u0000\u0000\u0000\u03b9\u03b5" +
            "\u0001\u0000\u0000\u0000\u03b9\u03b6\u0001\u0000\u0000\u0000\u03b9\u03b7" +
            "\u0001\u0000\u0000\u0000\u03ba\u0093\u0001\u0000\u0000\u0000\u03bb\u03bd" +
            "\u0005\b\u0000\u0000\u03bc\u03be\u0003\u0096K\u0000\u03bd\u03bc\u0001" +
            "\u0000\u0000\u0000\u03bd\u03be\u0001\u0000\u0000\u0000\u03be\u03bf\u0001" +
            "\u0000\u0000\u0000\u03bf\u03c0\u0005\n\u0000\u0000\u03c0\u0095\u0001\u0000" +
            "\u0000\u0000\u03c1\u03c3\u0003\u0092I\u0000\u03c2\u03c1\u0001\u0000\u0000" +
            "\u0000\u03c3\u03c4\u0001\u0000\u0000\u0000\u03c4\u03c2\u0001\u0000\u0000" +
            "\u0000\u03c4\u03c5\u0001\u0000\u0000\u0000\u03c5\u0097\u0001\u0000\u0000" +
            "\u0000\u03c6\u03ca\u0005y\u0000\u0000\u03c7\u03c8\u0005|\u0000\u0000\u03c8" +
            "\u03cb\u0003V+\u0000\u03c9\u03cb\u0003\u00a2Q\u0000\u03ca\u03c7\u0001" +
            "\u0000\u0000\u0000\u03ca\u03c9\u0001\u0000\u0000\u0000\u03cb\u03cc\u0001" +
            "\u0000\u0000\u0000\u03cc\u03cd\u0003\u0136\u009b\u0000\u03cd\u0099\u0001" +
            "\u0000\u0000\u0000\u03ce\u03d1\u0005b\u0000\u0000\u03cf\u03d2\u0003\u009c" +
            "N\u0000\u03d0\u03d2\u0003\u0084B\u0000\u03d1\u03cf\u0001\u0000\u0000\u0000" +
            "\u03d1\u03d0\u0001\u0000\u0000\u0000\u03d2\u009b\u0001\u0000\u0000\u0000" +
            "\u03d3\u03d7\u0005\u0018\u0000\u0000\u03d4\u03d7\u0003\u009eO\u0000\u03d5" +
            "\u03d7\u0003\u012a\u0095\u0000\u03d6\u03d3\u0001\u0000\u0000\u0000\u03d6" +
            "\u03d4\u0001\u0000\u0000\u0000\u03d6\u03d5\u0001\u0000\u0000\u0000\u03d7" +
            "\u03da\u0001\u0000\u0000\u0000\u03d8\u03d9\u0005X\u0000\u0000\u03d9\u03db" +
            "\u0003\u012a\u0095\u0000\u03da\u03d8\u0001\u0000\u0000\u0000\u03da\u03db" +
            "\u0001\u0000\u0000\u0000\u03db\u03dc\u0001\u0000\u0000\u0000\u03dc\u03dd" +
            "\u0005Y\u0000\u0000\u03dd\u03de\u0005}\u0000\u0000\u03de\u03df\u0003\u0136" +
            "\u009b\u0000\u03df\u009d\u0001\u0000\u0000\u0000\u03e0\u03e1\u0003\u012a" +
            "\u0095\u0000\u03e1\u03e2\u0005\f\u0000\u0000\u03e2\u03e4\u0001\u0000\u0000" +
            "\u0000\u03e3\u03e0\u0001\u0000\u0000\u0000\u03e3\u03e4\u0001\u0000\u0000" +
            "\u0000\u03e4\u03e5\u0001\u0000\u0000\u0000\u03e5\u03e6\u0005\b\u0000\u0000" +
            "\u03e6\u03eb\u0003\u012a\u0095\u0000\u03e7\u03e8\u0005\f\u0000\u0000\u03e8" +
            "\u03ea\u0003\u012a\u0095\u0000\u03e9\u03e7\u0001\u0000\u0000\u0000\u03ea" +
            "\u03ed\u0001\u0000\u0000\u0000\u03eb\u03e9\u0001\u0000\u0000\u0000\u03eb" +
            "\u03ec\u0001\u0000\u0000\u0000\u03ec\u03ee\u0001\u0000\u0000\u0000\u03ed" +
            "\u03eb\u0001\u0000\u0000\u0000\u03ee\u03ef\u0005\n\u0000\u0000\u03ef\u009f" +
            "\u0001\u0000\u0000\u0000\u03f0\u03f2\u0005a\u0000\u0000\u03f1\u03f3\u0005" +
            "R\u0000\u0000\u03f2\u03f1\u0001\u0000\u0000\u0000\u03f2\u03f3\u0001\u0000" +
            "\u0000\u0000\u03f3\u03f6\u0001\u0000\u0000\u0000\u03f4\u03f7\u0003\u009c" +
            "N\u0000\u03f5\u03f7\u0003\u0092I\u0000\u03f6\u03f4\u0001\u0000\u0000\u0000" +
            "\u03f6\u03f5\u0001\u0000\u0000\u0000\u03f7\u00a1\u0001\u0000\u0000\u0000" +
            "\u03f8\u03fa\u0003\u001a\r\u0000\u03f9\u03fb\u0003T*\u0000\u03fa\u03f9" +
            "\u0001\u0000\u0000\u0000\u03fa\u03fb\u0001\u0000\u0000\u0000\u03fb\u03fc" +
            "\u0001\u0000\u0000\u0000\u03fc\u03fe\u0003\u0018\f\u0000\u03fd\u03ff\u0005" +
            "\u000b\u0000\u0000\u03fe\u03fd\u0001\u0000\u0000\u0000\u03fe\u03ff\u0001" +
            "\u0000\u0000\u0000\u03ff\u0416\u0001\u0000\u0000\u0000\u0400\u0402\u0003" +
            "d2\u0000\u0401\u0400\u0001\u0000\u0000\u0000\u0401\u0402\u0001\u0000\u0000" +
            "\u0000\u0402\u0404\u0001\u0000\u0000\u0000\u0403\u0405\u0003\u00b0X\u0000" +
            "\u0404\u0403\u0001\u0000\u0000\u0000\u0404\u0405\u0001\u0000\u0000\u0000" +
            "\u0405\u0407\u0001\u0000\u0000\u0000\u0406\u0408\u0005Z\u0000\u0000\u0407" +
            "\u0406\u0001\u0000\u0000\u0000\u0407\u0408\u0001\u0000\u0000\u0000\u0408" +
            "\u0409\u0001\u0000\u0000\u0000\u0409\u040b\u0003\u00a4R\u0000\u040a\u040c" +
            "\u0005\u000b\u0000\u0000\u040b\u040a\u0001\u0000\u0000\u0000\u040b\u040c" +
            "\u0001\u0000\u0000\u0000\u040c\u0416\u0001\u0000\u0000\u0000\u040d\u040f" +
            "\u0005x\u0000\u0000\u040e\u0410\u0003\u00b0X\u0000\u040f\u040e\u0001\u0000" +
            "\u0000\u0000\u040f\u0410\u0001\u0000\u0000\u0000\u0410\u0411\u0001\u0000" +
            "\u0000\u0000\u0411\u0413\u0003\u00a4R\u0000\u0412\u0414\u0005\u000b\u0000" +
            "\u0000\u0413\u0412\u0001\u0000\u0000\u0000\u0413\u0414\u0001\u0000\u0000" +
            "\u0000\u0414\u0416\u0001\u0000\u0000\u0000\u0415\u03f8\u0001\u0000\u0000" +
            "\u0000\u0415\u0401\u0001\u0000\u0000\u0000\u0415\u040d\u0001\u0000\u0000" +
            "\u0000\u0416\u00a3\u0001\u0000\u0000\u0000\u0417\u041c\u0003\u00a6S\u0000" +
            "\u0418\u0419\u0005\f\u0000\u0000\u0419\u041b\u0003\u00a6S\u0000\u041a" +
            "\u0418\u0001\u0000\u0000\u0000\u041b\u041e\u0001\u0000\u0000\u0000\u041c" +
            "\u041a\u0001\u0000\u0000\u0000\u041c\u041d\u0001\u0000\u0000\u0000\u041d" +
            "\u00a5\u0001\u0000\u0000\u0000\u041e\u041c\u0001\u0000\u0000\u0000\u041f" +
            "\u0423\u0003\u012c\u0096\u0000\u0420\u0423\u0003\u00fc~\u0000\u0421\u0423" +
            "\u0003\u0102\u0081\u0000\u0422\u041f\u0001\u0000\u0000\u0000\u0422\u0420" +
            "\u0001\u0000\u0000\u0000\u0422\u0421\u0001\u0000\u0000\u0000\u0423\u0425" +
            "\u0001\u0000\u0000\u0000\u0424\u0426\u0003T*\u0000\u0425\u0424\u0001\u0000" +
            "\u0000\u0000\u0425\u0426\u0001\u0000\u0000\u0000\u0426\u0428\u0001\u0000" +
            "\u0000\u0000\u0427\u0429\u0003\u0116\u008b\u0000\u0428\u0427\u0001\u0000" +
            "\u0000\u0000\u0428\u0429\u0001\u0000\u0000\u0000\u0429\u042f\u0001\u0000" +
            "\u0000\u0000\u042a\u042c\u0005\r\u0000\u0000\u042b\u042d\u0003\u001c\u000e" +
            "\u0000\u042c\u042b\u0001\u0000\u0000\u0000\u042c\u042d\u0001\u0000\u0000" +
            "\u0000\u042d\u042e\u0001\u0000\u0000\u0000\u042e\u0430\u0003\u0116\u008b" +
            "\u0000\u042f\u042a\u0001\u0000\u0000\u0000\u042f\u0430\u0001\u0000\u0000" +
            "\u0000\u0430\u00a7\u0001\u0000\u0000\u0000\u0431\u0432\u0005\u000b\u0000" +
            "\u0000\u0432\u00a9\u0001\u0000\u0000\u0000\u0433\u0434\u0004U\u0007\u0000" +
            "\u0434\u0436\u0003\u0112\u0089\u0000\u0435\u0437\u0005\u000b\u0000\u0000" +
            "\u0436\u0435\u0001\u0000\u0000\u0000\u0436\u0437\u0001\u0000\u0000\u0000" +
            "\u0437\u00ab\u0001\u0000\u0000\u0000\u0438\u0439\u0005S\u0000\u0000\u0439" +
            "\u043a\u0005\u0006\u0000\u0000\u043a\u043b\u0003\u0112\u0089\u0000\u043b" +
            "\u043c\u0005\u0007\u0000\u0000\u043c\u043f\u0003\u0092I\u0000\u043d\u043e" +
            "\u0005C\u0000\u0000\u043e\u0440\u0003\u0092I\u0000\u043f\u043d\u0001\u0000" +
            "\u0000\u0000\u043f\u0440\u0001\u0000\u0000\u0000\u0440\u00ad\u0001\u0000" +
            "\u0000\u0000\u0441\u0442\u0005?\u0000\u0000\u0442\u0443\u0003\u0092I\u0000" +
            "\u0443\u0444\u0005M\u0000\u0000\u0444\u0445\u0005\u0006\u0000\u0000\u0445" +
            "\u0446\u0003\u0112\u0089\u0000\u0446\u0447\u0005\u0007\u0000\u0000\u0447" +
            "\u0448\u0003\u0136\u009b\u0000\u0448\u0487\u0001\u0000\u0000\u0000\u0449" +
            "\u044a\u0005M\u0000\u0000\u044a\u044b\u0005\u0006\u0000\u0000\u044b\u044c" +
            "\u0003\u0112\u0089\u0000\u044c\u044d\u0005\u0007\u0000\u0000\u044d\u044e" +
            "\u0003\u0092I\u0000\u044e\u0487\u0001\u0000\u0000\u0000\u044f\u0450\u0005" +
            "K\u0000\u0000\u0450\u0452\u0005\u0006\u0000\u0000\u0451\u0453\u0003\u0112" +
            "\u0089\u0000\u0452\u0451\u0001\u0000\u0000\u0000\u0452\u0453\u0001\u0000" +
            "\u0000\u0000\u0453\u0454\u0001\u0000\u0000\u0000\u0454\u0456\u0005\u000b" +
            "\u0000\u0000\u0455\u0457\u0003\u0112\u0089\u0000\u0456\u0455\u0001\u0000" +
            "\u0000\u0000\u0456\u0457\u0001\u0000\u0000\u0000\u0457\u0458\u0001\u0000" +
            "\u0000\u0000\u0458\u045a\u0005\u000b\u0000\u0000\u0459\u045b\u0003\u0112" +
            "\u0089\u0000\u045a\u0459\u0001\u0000\u0000\u0000\u045a\u045b\u0001\u0000" +
            "\u0000\u0000\u045b\u045c\u0001\u0000\u0000\u0000\u045c\u045d\u0005\u0007" +
            "\u0000\u0000\u045d\u0487\u0003\u0092I\u0000\u045e\u045f\u0005K\u0000\u0000" +
            "\u045f\u0460\u0005\u0006\u0000\u0000\u0460\u0461\u0003\u00b0X\u0000\u0461" +
            "\u0462\u0003\u00a4R\u0000\u0462\u0464\u0005\u000b\u0000\u0000\u0463\u0465" +
            "\u0003\u0112\u0089\u0000\u0464\u0463\u0001\u0000\u0000\u0000\u0464\u0465" +
            "\u0001\u0000\u0000\u0000\u0465\u0466\u0001\u0000\u0000\u0000\u0466\u0468" +
            "\u0005\u000b\u0000\u0000\u0467\u0469\u0003\u0112\u0089\u0000\u0468\u0467" +
            "\u0001\u0000\u0000\u0000\u0468\u0469\u0001\u0000\u0000\u0000\u0469\u046a" +
            "\u0001\u0000\u0000\u0000\u046a\u046b\u0005\u0007\u0000\u0000\u046b\u046c" +
            "\u0003\u0092I\u0000\u046c\u0487\u0001\u0000\u0000\u0000\u046d\u046e\u0005" +
            "K\u0000\u0000\u046e\u046f\u0005\u0006\u0000\u0000\u046f\u0473\u0003\u0116" +
            "\u008b\u0000\u0470\u0474\u0005V\u0000\u0000\u0471\u0472\u0005|\u0000\u0000" +
            "\u0472\u0474\u0004W\b\u0000\u0473\u0470\u0001\u0000\u0000\u0000\u0473" +
            "\u0471\u0001\u0000\u0000\u0000\u0474\u0475\u0001\u0000\u0000\u0000\u0475" +
            "\u0476\u0003\u0112\u0089\u0000\u0476\u0477\u0005\u0007\u0000\u0000\u0477" +
            "\u0478\u0003\u0092I\u0000\u0478\u0487\u0001\u0000\u0000\u0000\u0479\u047a" +
            "\u0005K\u0000\u0000\u047a\u047b\u0005\u0006\u0000\u0000\u047b\u047c\u0003" +
            "\u00b0X\u0000\u047c\u0480\u0003\u00a6S\u0000\u047d\u0481\u0005V\u0000" +
            "\u0000\u047e\u047f\u0005|\u0000\u0000\u047f\u0481\u0004W\t\u0000\u0480" +
            "\u047d\u0001\u0000\u0000\u0000\u0480\u047e\u0001\u0000\u0000\u0000\u0481" +
            "\u0482\u0001\u0000\u0000\u0000\u0482\u0483\u0003\u0112\u0089\u0000\u0483" +
            "\u0484\u0005\u0007\u0000\u0000\u0484\u0485\u0003\u0092I\u0000\u0485\u0487" +
            "\u0001\u0000\u0000\u0000\u0486\u0441\u0001\u0000\u0000\u0000\u0486\u0449" +
            "\u0001\u0000\u0000\u0000\u0486\u044f\u0001\u0000\u0000\u0000\u0486\u045e" +
            "\u0001\u0000\u0000\u0000\u0486\u046d\u0001\u0000\u0000\u0000\u0486\u0479" +
            "\u0001\u0000\u0000\u0000\u0487\u00af\u0001\u0000\u0000\u0000\u0488\u0489" +
            "\u0007\u0005\u0000\u0000\u0489\u00b1\u0001\u0000\u0000\u0000\u048a\u048d" +
            "\u0005J\u0000\u0000\u048b\u048c\u0004Y\n\u0000\u048c\u048e\u0005|\u0000" +
            "\u0000\u048d\u048b\u0001\u0000\u0000\u0000\u048d\u048e\u0001\u0000\u0000" +
            "\u0000\u048e\u048f\u0001\u0000\u0000\u0000\u048f\u0490\u0003\u0136\u009b" +
            "\u0000\u0490\u00b3\u0001\u0000\u0000\u0000\u0491\u0494\u0005>\u0000\u0000" +
            "\u0492\u0493\u0004Z\u000b\u0000\u0493\u0495\u0005|\u0000\u0000\u0494\u0492" +
            "\u0001\u0000\u0000\u0000\u0494\u0495\u0001\u0000\u0000\u0000\u0495\u0496" +
            "\u0001\u0000\u0000\u0000\u0496\u0497\u0003\u0136\u009b\u0000\u0497\u00b5" +
            "\u0001\u0000\u0000\u0000\u0498\u049b\u0005H\u0000\u0000\u0499\u049a\u0004" +
            "[\f\u0000\u049a\u049c\u0003\u0112\u0089\u0000\u049b\u0499\u0001\u0000" +
            "\u0000\u0000\u049b\u049c\u0001\u0000\u0000\u0000\u049c\u049d\u0001\u0000" +
            "\u0000\u0000\u049d\u04a5\u0003\u0136\u009b\u0000\u049e\u049f\u0005H\u0000" +
            "\u0000\u049f\u04a0\u0005\u0006\u0000\u0000\u04a0\u04a1\u0003\u0000\u0000" +
            "\u0000\u04a1\u04a2\u0005\u0007\u0000\u0000\u04a2\u04a3\u0003\u0136\u009b" +
            "\u0000\u04a3\u04a5\u0001\u0000\u0000\u0000\u04a4\u0498\u0001\u0000\u0000" +
            "\u0000\u04a4\u049e\u0001\u0000\u0000\u0000\u04a5\u00b7\u0001\u0000\u0000" +
            "\u0000\u04a6\u04a9\u0005k\u0000\u0000\u04a7\u04a8\u0004\\\r\u0000\u04a8" +
            "\u04aa\u0003\u0112\u0089\u0000\u04a9\u04a7\u0001\u0000\u0000\u0000\u04a9" +
            "\u04aa\u0001\u0000\u0000\u0000\u04aa\u04ab\u0001\u0000\u0000\u0000\u04ab" +
            "\u04ac\u0003\u0136\u009b\u0000\u04ac\u00b9\u0001\u0000\u0000\u0000\u04ad" +
            "\u04ae\u0005Q\u0000\u0000\u04ae\u04af\u0005\u0006\u0000\u0000\u04af\u04b0" +
            "\u0003\u0112\u0089\u0000\u04b0\u04b1\u0005\u0007\u0000\u0000\u04b1\u04b2" +
            "\u0003\u0092I\u0000\u04b2\u00bb\u0001\u0000\u0000\u0000\u04b3\u04b4\u0005" +
            "L\u0000\u0000\u04b4\u04b5\u0005\u0006\u0000\u0000\u04b5\u04b6\u0003\u0112" +
            "\u0089\u0000\u04b6\u04b7\u0005\u0007\u0000\u0000\u04b7\u04b8\u0003\u00be" +
            "_\u0000\u04b8\u00bd\u0001\u0000\u0000\u0000\u04b9\u04bb\u0005\b\u0000" +
            "\u0000\u04ba\u04bc\u0003\u00c0`\u0000\u04bb\u04ba\u0001\u0000\u0000\u0000" +
            "\u04bb\u04bc\u0001\u0000\u0000\u0000\u04bc\u04c1\u0001\u0000\u0000\u0000" +
            "\u04bd\u04bf\u0003\u00c4b\u0000\u04be\u04c0\u0003\u00c0`\u0000\u04bf\u04be" +
            "\u0001\u0000\u0000\u0000\u04bf\u04c0\u0001\u0000\u0000\u0000\u04c0\u04c2" +
            "\u0001\u0000\u0000\u0000\u04c1\u04bd\u0001\u0000\u0000\u0000\u04c1\u04c2" +
            "\u0001\u0000\u0000\u0000\u04c2\u04c3\u0001\u0000\u0000\u0000\u04c3\u04c4" +
            "\u0005\n\u0000\u0000\u04c4\u00bf\u0001\u0000\u0000\u0000\u04c5\u04c7\u0003" +
            "\u00c2a\u0000\u04c6\u04c5\u0001\u0000\u0000\u0000\u04c7\u04c8\u0001\u0000" +
            "\u0000\u0000\u04c8\u04c6\u0001\u0000\u0000\u0000\u04c8\u04c9\u0001\u0000" +
            "\u0000\u0000\u04c9\u00c1\u0001\u0000\u0000\u0000\u04ca\u04cb\u0005B\u0000" +
            "\u0000\u04cb\u04cc\u0003\u0112\u0089\u0000\u04cc\u04ce\u0005\u000f\u0000" +
            "\u0000\u04cd\u04cf\u0003\u0096K\u0000\u04ce\u04cd\u0001\u0000\u0000\u0000" +
            "\u04ce\u04cf\u0001\u0000\u0000\u0000\u04cf\u00c3\u0001\u0000\u0000\u0000" +
            "\u04d0\u04d1\u0005R\u0000\u0000\u04d1\u04d3\u0005\u000f\u0000\u0000\u04d2" +
            "\u04d4\u0003\u0096K\u0000\u04d3\u04d2\u0001\u0000\u0000\u0000\u04d3\u04d4" +
            "\u0001\u0000\u0000\u0000\u04d4\u00c5\u0001\u0000\u0000\u0000\u04d5\u04d6" +
            "\u0005|\u0000\u0000\u04d6\u04d7\u0005\u000f\u0000\u0000\u04d7\u04d8\u0003" +
            "\u0092I\u0000\u04d8\u00c7\u0001\u0000\u0000\u0000\u04d9\u04da\u0005T\u0000" +
            "\u0000\u04da\u04db\u0004d\u000e\u0000\u04db\u04dc\u0003\u0112\u0089\u0000" +
            "\u04dc\u04dd\u0003\u0136\u009b\u0000\u04dd\u00c9\u0001\u0000\u0000\u0000" +
            "\u04de\u04df\u0005W\u0000\u0000\u04df\u04e5\u0003\u0094J\u0000\u04e0\u04e2" +
            "\u0003\u00ccf\u0000\u04e1\u04e3\u0003\u00ceg\u0000\u04e2\u04e1\u0001\u0000" +
            "\u0000\u0000\u04e2\u04e3\u0001\u0000\u0000\u0000\u04e3\u04e6\u0001\u0000" +
            "\u0000\u0000\u04e4\u04e6\u0003\u00ceg\u0000\u04e5\u04e0\u0001\u0000\u0000" +
            "\u0000\u04e5\u04e4\u0001\u0000\u0000\u0000\u04e6\u00cb\u0001\u0000\u0000" +
            "\u0000\u04e7\u04e8\u0005F\u0000\u0000\u04e8\u04e9\u0005\u0006\u0000\u0000" +
            "\u04e9\u04ea\u0005|\u0000\u0000\u04ea\u04eb\u0005\u0007\u0000\u0000\u04eb" +
            "\u04ec\u0003\u0094J\u0000\u04ec\u00cd\u0001\u0000\u0000\u0000\u04ed\u04ee" +
            "\u0005G\u0000\u0000\u04ee\u04ef\u0003\u0094J\u0000\u04ef\u00cf\u0001\u0000" +
            "\u0000\u0000\u04f0\u04f1\u0005N\u0000\u0000\u04f1\u04f2\u0003\u0136\u009b" +
            "\u0000\u04f2\u00d1\u0001\u0000\u0000\u0000\u04f3\u04f5\u0005[\u0000\u0000" +
            "\u04f4\u04f3\u0001\u0000\u0000\u0000\u04f4\u04f5\u0001\u0000\u0000\u0000" +
            "\u04f5\u04f6\u0001\u0000\u0000\u0000\u04f6\u04f7\u0005O\u0000\u0000\u04f7" +
            "\u04f8\u0005|\u0000\u0000\u04f8\u04fe\u0003V+\u0000\u04f9\u04fa\u0005" +
            "\b\u0000\u0000\u04fa\u04fb\u0003\u00f8|\u0000\u04fb\u04fc\u0005\n\u0000" +
            "\u0000\u04fc\u04ff\u0001\u0000\u0000\u0000\u04fd\u04ff\u0005\u000b\u0000" +
            "\u0000\u04fe\u04f9\u0001\u0000\u0000\u0000\u04fe\u04fd\u0001\u0000\u0000" +
            "\u0000\u04ff\u00d3\u0001\u0000\u0000\u0000\u0500\u0502\u0005y\u0000\u0000" +
            "\u0501\u0500\u0001\u0000\u0000\u0000\u0501\u0502\u0001\u0000\u0000\u0000" +
            "\u0502\u0503\u0001\u0000\u0000\u0000\u0503\u0504\u0005\\\u0000\u0000\u0504" +
            "\u0506\u0005|\u0000\u0000\u0505\u0507\u0003\u001c\u000e\u0000\u0506\u0505" +
            "\u0001\u0000\u0000\u0000\u0506\u0507\u0001\u0000\u0000\u0000\u0507\u0508" +
            "\u0001\u0000\u0000\u0000\u0508\u0509\u0003\u00d6k\u0000\u0509\u050a\u0003" +
            "\u00d8l\u0000\u050a\u00d5\u0001\u0000\u0000\u0000\u050b\u050d\u0003\u00da" +
            "m\u0000\u050c\u050b\u0001\u0000\u0000\u0000\u050c\u050d\u0001\u0000\u0000" +
            "\u0000\u050d\u050f\u0001\u0000\u0000\u0000\u050e\u0510\u0003\u00dcn\u0000" +
            "\u050f\u050e\u0001\u0000\u0000\u0000\u050f\u0510\u0001\u0000\u0000\u0000" +
            "\u0510\u00d7\u0001\u0000\u0000\u0000\u0511\u0515\u0005\b\u0000\u0000\u0512" +
            "\u0514\u0003\u00deo\u0000\u0513\u0512\u0001\u0000\u0000\u0000\u0514\u0517" +
            "\u0001\u0000\u0000\u0000\u0515\u0513\u0001\u0000\u0000\u0000\u0515\u0516" +
            "\u0001\u0000\u0000\u0000\u0516\u0518\u0001\u0000\u0000\u0000\u0517\u0515" +
            "\u0001\u0000\u0000\u0000\u0518\u0519\u0005\n\u0000\u0000\u0519\u00d9\u0001" +
            "\u0000\u0000\u0000\u051a\u051b\u0005^\u0000\u0000\u051b\u051c\u00032\u0019" +
            "\u0000\u051c\u00db\u0001\u0000\u0000\u0000\u051d\u051e\u0005c\u0000\u0000" +
            "\u051e\u051f\u0003v;\u0000\u051f\u00dd\u0001\u0000\u0000\u0000\u0520\u0528" +
            "\u0003p8\u0000\u0521\u0523\u0003\u0086C\u0000\u0522\u0521\u0001\u0000" +
            "\u0000\u0000\u0522\u0523\u0001\u0000\u0000\u0000\u0523\u0524\u0001\u0000" +
            "\u0000\u0000\u0524\u0528\u0003\u00e0p\u0000\u0525\u0528\u0003\u00e4r\u0000" +
            "\u0526\u0528\u0003\u0092I\u0000\u0527\u0520\u0001\u0000\u0000\u0000\u0527" +
            "\u0522\u0001\u0000\u0000\u0000\u0527\u0525\u0001\u0000\u0000\u0000\u0527" +
            "\u0526\u0001\u0000\u0000\u0000\u0528\u00df\u0001\u0000\u0000\u0000\u0529" +
            "\u052a\u0003\u00e2q\u0000\u052a\u052c\u0003\u010a\u0085\u0000\u052b\u052d" +
            "\u0005\u000e\u0000\u0000\u052c\u052b\u0001\u0000\u0000\u0000\u052c\u052d" +
            "\u0001\u0000\u0000\u0000\u052d\u052f\u0001\u0000\u0000\u0000\u052e\u0530" +
            "\u0003T*\u0000\u052f\u052e\u0001\u0000\u0000\u0000\u052f\u0530\u0001\u0000" +
            "\u0000\u0000\u0530\u0532\u0001\u0000\u0000\u0000\u0531\u0533\u0003\u0018" +
            "\f\u0000\u0532\u0531\u0001\u0000\u0000\u0000\u0532\u0533\u0001\u0000\u0000" +
            "\u0000\u0533\u0534\u0001\u0000\u0000\u0000\u0534\u0535\u0005\u000b\u0000" +
            "\u0000\u0535\u0547\u0001\u0000\u0000\u0000\u0536\u0537\u0003\u00e2q\u0000" +
            "\u0537\u0538\u0003\u010a\u0085\u0000\u0538\u053e\u0003V+\u0000\u0539\u053a" +
            "\u0005\b\u0000\u0000\u053a\u053b\u0003\u00f8|\u0000\u053b\u053c\u0005" +
            "\n\u0000\u0000\u053c\u053f\u0001\u0000\u0000\u0000\u053d\u053f\u0005\u000b" +
            "\u0000\u0000\u053e\u0539\u0001\u0000\u0000\u0000\u053e\u053d\u0001\u0000" +
            "\u0000\u0000\u053f\u0547\u0001\u0000\u0000\u0000\u0540\u0543\u0003\u00e2" +
            "q\u0000\u0541\u0544\u0003\u0106\u0083\u0000\u0542\u0544\u0003\u0108\u0084" +
            "\u0000\u0543\u0541\u0001\u0000\u0000\u0000\u0543\u0542\u0001\u0000\u0000" +
            "\u0000\u0544\u0547\u0001\u0000\u0000\u0000\u0545\u0547\u0003\u0098L\u0000" +
            "\u0546\u0529\u0001\u0000\u0000\u0000\u0546\u0536\u0001\u0000\u0000\u0000" +
            "\u0546\u0540\u0001\u0000\u0000\u0000\u0546\u0545\u0001\u0000\u0000\u0000" +
            "\u0547\u00e1\u0001\u0000\u0000\u0000\u0548\u054a\u0005[\u0000\u0000\u0549" +
            "\u0548\u0001\u0000\u0000\u0000\u0549\u054a\u0001\u0000\u0000\u0000\u054a" +
            "\u054c\u0001\u0000\u0000\u0000\u054b\u054d\u0003d2\u0000\u054c\u054b\u0001" +
            "\u0000\u0000\u0000\u054c\u054d\u0001\u0000\u0000\u0000\u054d\u054f\u0001" +
            "\u0000\u0000\u0000\u054e\u0550\u0005j\u0000\u0000\u054f\u054e\u0001\u0000" +
            "\u0000\u0000\u054f\u0550\u0001\u0000\u0000\u0000\u0550\u0552\u0001\u0000" +
            "\u0000\u0000\u0551\u0553\u0005Z\u0000\u0000\u0552\u0551\u0001\u0000\u0000" +
            "\u0000\u0552\u0553\u0001\u0000\u0000\u0000\u0553\u00e3\u0001\u0000\u0000" +
            "\u0000\u0554\u0555\u0003j5\u0000\u0555\u0556\u0005\u000b\u0000\u0000\u0556" +
            "\u00e5\u0001\u0000\u0000\u0000\u0557\u0559\u0005\u0018\u0000\u0000\u0558" +
            "\u0557\u0001\u0000\u0000\u0000\u0558\u0559\u0001\u0000\u0000\u0000\u0559" +
            "\u055a\u0001\u0000\u0000\u0000\u055a\u055b\u0005|\u0000\u0000\u055b\u055d" +
            "\u0005\u0006\u0000\u0000\u055c\u055e\u0003\u00f2y\u0000\u055d\u055c\u0001" +
            "\u0000\u0000\u0000\u055d\u055e\u0001\u0000\u0000\u0000\u055e\u055f\u0001" +
            "\u0000\u0000\u0000\u055f\u0560\u0005\u0007\u0000\u0000\u0560\u0561\u0005" +
            "\b\u0000\u0000\u0561\u0562\u0003\u00f8|\u0000\u0562\u0563\u0005\n\u0000" +
            "\u0000\u0563\u00e7\u0001\u0000\u0000\u0000\u0564\u0565\u0005O\u0000\u0000" +
            "\u0565\u0567\u0005\u0018\u0000\u0000\u0566\u0568\u0005|\u0000\u0000\u0567" +
            "\u0566\u0001\u0000\u0000\u0000\u0567\u0568\u0001\u0000\u0000\u0000\u0568" +
            "\u0569\u0001\u0000\u0000\u0000\u0569\u056b\u0005\u0006\u0000\u0000\u056a" +
            "\u056c\u0003\u00f2y\u0000\u056b\u056a\u0001\u0000\u0000\u0000\u056b\u056c" +
            "\u0001\u0000\u0000\u0000\u056c\u056d\u0001\u0000\u0000\u0000\u056d\u056e" +
            "\u0005\u0007\u0000\u0000\u056e\u056f\u0005\b\u0000\u0000\u056f\u0570\u0003" +
            "\u00f8|\u0000\u0570\u0571\u0005\n\u0000\u0000\u0571\u00e9\u0001\u0000" +
            "\u0000\u0000\u0572\u0573\u0005\b\u0000\u0000\u0573\u0578\u0003\u00ecv" +
            "\u0000\u0574\u0575\u0005\f\u0000\u0000\u0575\u0577\u0003\u00ecv\u0000" +
            "\u0576\u0574\u0001\u0000\u0000\u0000\u0577\u057a\u0001\u0000\u0000\u0000" +
            "\u0578\u0576\u0001\u0000\u0000\u0000\u0578\u0579\u0001\u0000\u0000\u0000" +
            "\u0579\u057c\u0001\u0000\u0000\u0000\u057a\u0578\u0001\u0000\u0000\u0000" +
            "\u057b\u057d\u0005\f\u0000\u0000\u057c\u057b\u0001\u0000\u0000\u0000\u057c" +
            "\u057d\u0001\u0000\u0000\u0000\u057d\u057e\u0001\u0000\u0000\u0000\u057e" +
            "\u057f\u0005\n\u0000\u0000\u057f\u00eb\u0001\u0000\u0000\u0000\u0580\u0581" +
            "\u0005\u0018\u0000\u0000\u0581\u0582\u0003\u00f0x\u0000\u0582\u00ed\u0001" +
            "\u0000\u0000\u0000\u0583\u0584\u0005\b\u0000\u0000\u0584\u0589\u0003\u00f0" +
            "x\u0000\u0585\u0586\u0005\f\u0000\u0000\u0586\u0588\u0003\u00f0x\u0000" +
            "\u0587\u0585\u0001\u0000\u0000\u0000\u0588\u058b\u0001\u0000\u0000\u0000" +
            "\u0589\u0587\u0001\u0000\u0000\u0000\u0589\u058a\u0001\u0000\u0000\u0000" +
            "\u058a\u058d\u0001\u0000\u0000\u0000\u058b\u0589\u0001\u0000\u0000\u0000" +
            "\u058c\u058e\u0005\f\u0000\u0000\u058d\u058c\u0001\u0000\u0000\u0000\u058d" +
            "\u058e\u0001\u0000\u0000\u0000\u058e\u058f\u0001\u0000\u0000\u0000\u058f" +
            "\u0590\u0005\n\u0000\u0000\u0590\u00ef\u0001\u0000\u0000\u0000\u0591\u0592" +
            "\u0005\u0004\u0000\u0000\u0592\u0593\u0003\u0116\u008b\u0000\u0593\u0594" +
            "\u0005\u0005\u0000\u0000\u0594\u0596\u0005\u0006\u0000\u0000\u0595\u0597" +
            "\u0003\u00f2y\u0000\u0596\u0595\u0001\u0000\u0000\u0000\u0596\u0597\u0001" +
            "\u0000\u0000\u0000\u0597\u0598\u0001\u0000\u0000\u0000\u0598\u0599\u0005" +
            "\u0007\u0000\u0000\u0599\u059a\u0005\b\u0000\u0000\u059a\u059b\u0003\u00f8" +
            "|\u0000\u059b\u059c\u0005\n\u0000\u0000\u059c\u00f1\u0001\u0000\u0000" +
            "\u0000\u059d\u05a2\u0003\u00f4z\u0000\u059e\u059f\u0005\f\u0000\u0000" +
            "\u059f\u05a1\u0003\u00f4z\u0000\u05a0\u059e\u0001\u0000\u0000\u0000\u05a1" +
            "\u05a4\u0001\u0000\u0000\u0000\u05a2\u05a0\u0001\u0000\u0000\u0000\u05a2" +
            "\u05a3\u0001\u0000\u0000\u0000\u05a3\u05a7\u0001\u0000\u0000\u0000\u05a4" +
            "\u05a2\u0001\u0000\u0000\u0000\u05a5\u05a6\u0005\f\u0000\u0000\u05a6\u05a8" +
            "\u0003\u00f6{\u0000\u05a7\u05a5\u0001\u0000\u0000\u0000\u05a7\u05a8\u0001" +
            "\u0000\u0000\u0000\u05a8\u05b1\u0001\u0000\u0000\u0000\u05a9\u05b1\u0003" +
            "\u00f6{\u0000\u05aa\u05b1\u0003\u00fc~\u0000\u05ab\u05ae\u0003\u0102\u0081" +
            "\u0000\u05ac\u05ad\u0005\u000f\u0000\u0000\u05ad\u05af\u0003\u00f2y\u0000" +
            "\u05ae\u05ac\u0001\u0000\u0000\u0000\u05ae\u05af\u0001\u0000\u0000\u0000" +
            "\u05af\u05b1\u0001\u0000\u0000\u0000\u05b0\u059d\u0001\u0000\u0000\u0000" +
            "\u05b0\u05a9\u0001\u0000\u0000\u0000\u05b0\u05aa\u0001\u0000\u0000\u0000" +
            "\u05b0\u05ab\u0001\u0000\u0000\u0000\u05b1\u00f3\u0001\u0000\u0000\u0000" +
            "\u05b2\u05b4\u0003\u0088D\u0000\u05b3\u05b2\u0001\u0000\u0000\u0000\u05b3" +
            "\u05b4\u0001\u0000\u0000\u0000\u05b4\u05b6\u0001\u0000\u0000\u0000\u05b5" +
            "\u05b7\u0003d2\u0000\u05b6\u05b5\u0001\u0000\u0000\u0000\u05b6\u05b7\u0001" +
            "\u0000\u0000\u0000\u05b7\u05b8\u0001\u0000\u0000\u0000\u05b8\u05ba\u0003" +
            "\u012c\u0096\u0000\u05b9\u05bb\u0005\u000e\u0000\u0000\u05ba\u05b9\u0001" +
            "\u0000\u0000\u0000\u05ba\u05bb\u0001\u0000\u0000\u0000\u05bb\u05bd\u0001" +
            "\u0000\u0000\u0000\u05bc\u05be\u0003T*\u0000\u05bd\u05bc\u0001\u0000\u0000" +
            "\u0000\u05bd\u05be\u0001\u0000\u0000\u0000\u05be\u05c1\u0001\u0000\u0000" +
            "\u0000\u05bf\u05c0\u0005\r\u0000\u0000\u05c0\u05c2\u0003\u0116\u008b\u0000" +
            "\u05c1\u05bf\u0001\u0000\u0000\u0000\u05c1\u05c2\u0001\u0000\u0000\u0000" +
            "\u05c2\u00f5\u0001\u0000\u0000\u0000\u05c3\u05c4\u0005\u0010\u0000\u0000" +
            "\u05c4\u05c5\u0005|\u0000\u0000\u05c5\u00f7\u0001\u0000\u0000\u0000\u05c6" +
            "\u05c8\u0003\u00fa}\u0000\u05c7\u05c6\u0001\u0000\u0000\u0000\u05c7\u05c8" +
            "\u0001\u0000\u0000\u0000\u05c8\u00f9\u0001\u0000\u0000\u0000\u05c9\u05cb" +
            "\u0003\u0090H\u0000\u05ca\u05c9\u0001\u0000\u0000\u0000\u05cb\u05cc\u0001" +
            "\u0000\u0000\u0000\u05cc\u05ca\u0001\u0000\u0000\u0000\u05cc\u05cd\u0001" +
            "\u0000\u0000\u0000\u05cd\u00fb\u0001\u0000\u0000\u0000\u05ce\u05d0\u0005" +
            "\u0004\u0000\u0000\u05cf\u05d1\u0003\u00fe\u007f\u0000\u05d0\u05cf\u0001" +
            "\u0000\u0000\u0000\u05d0\u05d1\u0001\u0000\u0000\u0000\u05d1\u05d2\u0001" +
            "\u0000\u0000\u0000\u05d2\u05d3\u0005\u0005\u0000\u0000\u05d3\u00fd\u0001" +
            "\u0000\u0000\u0000\u05d4\u05dd\u0003\u0100\u0080\u0000\u05d5\u05d7\u0005" +
            "\f\u0000\u0000\u05d6\u05d5\u0001\u0000\u0000\u0000\u05d7\u05d8\u0001\u0000" +
            "\u0000\u0000\u05d8\u05d6\u0001\u0000\u0000\u0000\u05d8\u05d9\u0001\u0000" +
            "\u0000\u0000\u05d9\u05da\u0001\u0000\u0000\u0000\u05da\u05dc\u0003\u0100" +
            "\u0080\u0000\u05db\u05d6\u0001\u0000\u0000\u0000\u05dc\u05df\u0001\u0000" +
            "\u0000\u0000\u05dd\u05db\u0001\u0000\u0000\u0000\u05dd\u05de\u0001\u0000" +
            "\u0000\u0000\u05de\u00ff\u0001\u0000\u0000\u0000\u05df\u05dd\u0001\u0000" +
            "\u0000\u0000\u05e0\u05e2\u0005\u0010\u0000\u0000\u05e1\u05e0\u0001\u0000" +
            "\u0000\u0000\u05e1\u05e2\u0001\u0000\u0000\u0000\u05e2\u05e5\u0001\u0000" +
            "\u0000\u0000\u05e3\u05e6\u0003\u0116\u008b\u0000\u05e4\u05e6\u0005|\u0000" +
            "\u0000\u05e5\u05e3\u0001\u0000\u0000\u0000\u05e5\u05e4\u0001\u0000\u0000" +
            "\u0000\u05e6\u05e8\u0001\u0000\u0000\u0000\u05e7\u05e9\u0005\f\u0000\u0000" +
            "\u05e8\u05e7\u0001\u0000\u0000\u0000\u05e8\u05e9\u0001\u0000\u0000\u0000" +
            "\u05e9\u0101\u0001\u0000\u0000\u0000\u05ea\u05f6\u0005\b\u0000\u0000\u05eb" +
            "\u05f0\u0003\u0104\u0082\u0000\u05ec\u05ed\u0005\f\u0000\u0000\u05ed\u05ef" +
            "\u0003\u0104\u0082\u0000\u05ee\u05ec\u0001\u0000\u0000\u0000\u05ef\u05f2" +
            "\u0001\u0000\u0000\u0000\u05f0\u05ee\u0001\u0000\u0000\u0000\u05f0\u05f1" +
            "\u0001\u0000\u0000\u0000\u05f1\u05f4\u0001\u0000\u0000\u0000\u05f2\u05f0" +
            "\u0001\u0000\u0000\u0000\u05f3\u05f5\u0005\f\u0000\u0000\u05f4\u05f3\u0001" +
            "\u0000\u0000\u0000\u05f4\u05f5\u0001\u0000\u0000\u0000\u05f5\u05f7\u0001" +
            "\u0000\u0000\u0000\u05f6\u05eb\u0001\u0000\u0000\u0000\u05f6\u05f7\u0001" +
            "\u0000\u0000\u0000\u05f7\u05f8\u0001\u0000\u0000\u0000\u05f8\u05f9\u0005" +
            "\n\u0000\u0000\u05f9\u0103\u0001\u0000\u0000\u0000\u05fa\u05fb\u0003\u010a" +
            "\u0085\u0000\u05fb\u05fc\u0007\u0006\u0000\u0000\u05fc\u05fd\u0003\u0116" +
            "\u008b\u0000\u05fd\u060a\u0001\u0000\u0000\u0000\u05fe\u05ff\u0005\u0004" +
            "\u0000\u0000\u05ff\u0600\u0003\u0116\u008b\u0000\u0600\u0601\u0005\u0005" +
            "\u0000\u0000\u0601\u0602\u0005\u000f\u0000\u0000\u0602\u0603\u0003\u0116" +
            "\u008b\u0000\u0603\u060a\u0001\u0000\u0000\u0000\u0604\u060a\u0003\u0106" +
            "\u0083\u0000\u0605\u060a\u0003\u0108\u0084\u0000\u0606\u060a\u0003\u00e6" +
            "s\u0000\u0607\u060a\u0003\u012c\u0096\u0000\u0608\u060a\u0003`0\u0000" +
            "\u0609\u05fa\u0001\u0000\u0000\u0000\u0609\u05fe\u0001\u0000\u0000\u0000" +
            "\u0609\u0604\u0001\u0000\u0000\u0000\u0609\u0605\u0001\u0000\u0000\u0000" +
            "\u0609\u0606\u0001\u0000\u0000\u0000\u0609\u0607\u0001\u0000\u0000\u0000" +
            "\u0609\u0608\u0001\u0000\u0000\u0000\u060a\u0105\u0001\u0000\u0000\u0000" +
            "\u060b\u060c\u0003\u0132\u0099\u0000\u060c\u060d\u0005\u0006\u0000\u0000" +
            "\u060d\u060f\u0005\u0007\u0000\u0000\u060e\u0610\u0003T*\u0000\u060f\u060e" +
            "\u0001\u0000\u0000\u0000\u060f\u0610\u0001\u0000\u0000\u0000\u0610\u0611" +
            "\u0001\u0000\u0000\u0000\u0611\u0612\u0005\b\u0000\u0000\u0612\u0613\u0003" +
            "\u00f8|\u0000\u0613\u0614\u0005\n\u0000\u0000\u0614\u0107\u0001\u0000" +
            "\u0000\u0000\u0615\u0616\u0003\u0134\u009a\u0000\u0616\u0619\u0005\u0006" +
            "\u0000\u0000\u0617\u061a\u0005|\u0000\u0000\u0618\u061a\u0003\u001a\r" +
            "\u0000\u0619\u0617\u0001\u0000\u0000\u0000\u0619\u0618\u0001\u0000\u0000" +
            "\u0000\u061a\u061c\u0001\u0000\u0000\u0000\u061b\u061d\u0003T*\u0000\u061c" +
            "\u061b\u0001\u0000\u0000\u0000\u061c\u061d\u0001\u0000\u0000\u0000\u061d" +
            "\u061e\u0001\u0000\u0000\u0000\u061e\u061f\u0005\u0007\u0000\u0000\u061f" +
            "\u0620\u0005\b\u0000\u0000\u0620\u0621\u0003\u00f8|\u0000\u0621\u0622" +
            "\u0005\n\u0000\u0000\u0622\u0109\u0001\u0000\u0000\u0000\u0623\u0627\u0003" +
            "\u012a\u0095\u0000\u0624\u0627\u0005}\u0000\u0000\u0625\u0627\u0003\u0128" +
            "\u0094\u0000\u0626\u0623\u0001\u0000\u0000\u0000\u0626\u0624\u0001\u0000" +
            "\u0000\u0000\u0626\u0625\u0001\u0000\u0000\u0000\u0627\u010b\u0001\u0000" +
            "\u0000\u0000\u0628\u062d\u0005\u0006\u0000\u0000\u0629\u062b\u0003\u010e" +
            "\u0087\u0000\u062a\u062c\u0005\f\u0000\u0000\u062b\u062a\u0001\u0000\u0000" +
            "\u0000\u062b\u062c\u0001\u0000\u0000\u0000\u062c\u062e\u0001\u0000\u0000" +
            "\u0000\u062d\u0629\u0001\u0000\u0000\u0000\u062d\u062e\u0001\u0000\u0000" +
            "\u0000\u062e\u062f\u0001\u0000\u0000\u0000\u062f\u0630\u0005\u0007\u0000" +
            "\u0000\u0630\u010d\u0001\u0000\u0000\u0000\u0631\u0636\u0003\u0110\u0088" +
            "\u0000\u0632\u0633\u0005\f\u0000\u0000\u0633\u0635\u0003\u0110\u0088\u0000" +
            "\u0634\u0632\u0001\u0000\u0000\u0000\u0635\u0638\u0001\u0000\u0000\u0000" +
            "\u0636\u0634\u0001\u0000\u0000\u0000\u0636\u0637\u0001\u0000\u0000\u0000" +
            "\u0637\u010f\u0001\u0000\u0000\u0000\u0638\u0636\u0001\u0000\u0000\u0000" +
            "\u0639\u063b\u0005\u0010\u0000\u0000\u063a\u0639\u0001\u0000\u0000\u0000" +
            "\u063a\u063b\u0001\u0000\u0000\u0000\u063b\u063e\u0001\u0000\u0000\u0000" +
            "\u063c\u063f\u0003\u0116\u008b\u0000\u063d\u063f\u0005|\u0000\u0000\u063e" +
            "\u063c\u0001\u0000\u0000\u0000\u063e\u063d\u0001\u0000\u0000\u0000\u063f" +
            "\u0111\u0001\u0000\u0000\u0000\u0640\u0642\u0005\u0010\u0000\u0000\u0641" +
            "\u0640\u0001\u0000\u0000\u0000\u0641\u0642\u0001\u0000\u0000\u0000\u0642" +
            "\u0643\u0001\u0000\u0000\u0000\u0643\u0648\u0003\u0116\u008b\u0000\u0644" +
            "\u0645\u0005\f\u0000\u0000\u0645\u0647\u0003\u0116\u008b\u0000\u0646\u0644" +
            "\u0001\u0000\u0000\u0000\u0647\u064a\u0001\u0000\u0000\u0000\u0648\u0646" +
            "\u0001\u0000\u0000\u0000\u0648\u0649\u0001\u0000\u0000\u0000\u0649\u0113" +
            "\u0001\u0000\u0000\u0000\u064a\u0648\u0001\u0000\u0000\u0000\u064b\u064d" +
            "\u0005O\u0000\u0000\u064c\u064e\u0005|\u0000\u0000\u064d\u064c\u0001\u0000" +
            "\u0000\u0000\u064d\u064e\u0001\u0000\u0000\u0000\u064e\u064f\u0001\u0000" +
            "\u0000\u0000\u064f\u0651\u0005\u0006\u0000\u0000\u0650\u0652\u0003\u00f2" +
            "y\u0000\u0651\u0650\u0001\u0000\u0000\u0000\u0651\u0652\u0001\u0000\u0000" +
            "\u0000\u0652\u0653\u0001\u0000\u0000\u0000\u0653\u0655\u0005\u0007\u0000" +
            "\u0000\u0654\u0656\u0003T*\u0000\u0655\u0654\u0001\u0000\u0000\u0000\u0655" +
            "\u0656\u0001\u0000\u0000\u0000\u0656\u0657\u0001\u0000\u0000\u0000\u0657" +
            "\u0658\u0005\b\u0000\u0000\u0658\u0659\u0003\u00f8|\u0000\u0659\u065a" +
            "\u0005\n\u0000\u0000\u065a\u0115\u0001\u0000\u0000\u0000\u065b\u065c\u0006" +
            "\u008b\uffff\uffff\u0000\u065c\u0698\u0003\u0114\u008a\u0000\u065d\u0698" +
            "\u0003\u011a\u008d\u0000\u065e\u0660\u0005\\\u0000\u0000\u065f\u0661\u0005" +
            "|\u0000\u0000\u0660\u065f\u0001\u0000\u0000\u0000\u0660\u0661\u0001\u0000" +
            "\u0000\u0000\u0661\u0662\u0001\u0000\u0000\u0000\u0662\u0698\u0003\u00d8" +
            "l\u0000\u0663\u0664\u0005D\u0000\u0000\u0664\u0666\u0003\u0116\u008b\u0000" +
            "\u0665\u0667\u0003$\u0012\u0000\u0666\u0665\u0001\u0000\u0000\u0000\u0666" +
            "\u0667\u0001\u0000\u0000\u0000\u0667\u0668\u0001\u0000\u0000\u0000\u0668" +
            "\u0669\u0003\u010c\u0086\u0000\u0669\u0698\u0001\u0000\u0000\u0000\u066a" +
            "\u066b\u0005D\u0000\u0000\u066b\u066d\u0003\u0116\u008b\u0000\u066c\u066e" +
            "\u0003$\u0012\u0000\u066d\u066c\u0001\u0000\u0000\u0000\u066d\u066e\u0001" +
            "\u0000\u0000\u0000\u066e\u0698\u0001\u0000\u0000\u0000\u066f\u0670\u0005" +
            "U\u0000\u0000\u0670\u0698\u0003\u0116\u008b\'\u0671\u0672\u0005I\u0000" +
            "\u0000\u0672\u0698\u0003\u0116\u008b&\u0673\u0674\u0005A\u0000\u0000\u0674" +
            "\u0698\u0003\u0116\u008b%\u0675\u0676\u0005\u0012\u0000\u0000\u0676\u0698" +
            "\u0003\u0116\u008b$\u0677\u0678\u0005\u0013\u0000\u0000\u0678\u0698\u0003" +
            "\u0116\u008b#\u0679\u067a\u0005\u0014\u0000\u0000\u067a\u0698\u0003\u0116" +
            "\u008b\"\u067b\u067c\u0005\u0015\u0000\u0000\u067c\u0698\u0003\u0116\u008b" +
            "!\u067d\u067e\u0005\u0016\u0000\u0000\u067e\u0698\u0003\u0116\u008b \u067f" +
            "\u0680\u0005\u0017\u0000\u0000\u0680\u0698\u0003\u0116\u008b\u001f\u0681" +
            "\u0698\u0003\u00eew\u0000\u0682\u0698\u0003\u00eau\u0000\u0683\u0698\u0003" +
            "\u00e8t\u0000\u0684\u0698\u0003\u00b8\\\u0000\u0685\u0698\u0005P\u0000" +
            "\u0000\u0686\u0688\u0003\u012a\u0095\u0000\u0687\u0689\u0003\u0116\u008b" +
            "\u0000\u0688\u0687\u0001\u0000\u0000\u0000\u0688\u0689\u0001\u0000\u0000" +
            "\u0000\u0689\u0698\u0001\u0000\u0000\u0000\u068a\u0698\u0005_\u0000\u0000" +
            "\u068b\u0698\u0003\u0122\u0091\u0000\u068c\u0698\u0003\u00fc~\u0000\u068d" +
            "\u0698\u0003\u0102\u0081\u0000\u068e\u068f\u0005\u0006\u0000\u0000\u068f" +
            "\u0690\u0003\u0112\u0089\u0000\u0690\u0691\u0005\u0007\u0000\u0000\u0691" +
            "\u0698\u0001\u0000\u0000\u0000\u0692\u0694\u0003$\u0012\u0000\u0693\u0695" +
            "\u0003\u0112\u0089\u0000\u0694\u0693\u0001\u0000\u0000\u0000\u0694\u0695" +
            "\u0001\u0000\u0000\u0000\u0695\u0698\u0001\u0000\u0000\u0000\u0696\u0698" +
            "\u0003\u0000\u0000\u0000\u0697\u065b\u0001\u0000\u0000\u0000\u0697\u065d" +
            "\u0001\u0000\u0000\u0000\u0697\u065e\u0001\u0000\u0000\u0000\u0697\u0663" +
            "\u0001\u0000\u0000\u0000\u0697\u066a\u0001\u0000\u0000\u0000\u0697\u066f" +
            "\u0001\u0000\u0000\u0000\u0697\u0671\u0001\u0000\u0000\u0000\u0697\u0673" +
            "\u0001\u0000\u0000\u0000\u0697\u0675\u0001\u0000\u0000\u0000\u0697\u0677" +
            "\u0001\u0000\u0000\u0000\u0697\u0679\u0001\u0000\u0000\u0000\u0697\u067b" +
            "\u0001\u0000\u0000\u0000\u0697\u067d\u0001\u0000\u0000\u0000\u0697\u067f" +
            "\u0001\u0000\u0000\u0000\u0697\u0681\u0001\u0000\u0000\u0000\u0697\u0682" +
            "\u0001\u0000\u0000\u0000\u0697\u0683\u0001\u0000\u0000\u0000\u0697\u0684" +
            "\u0001\u0000\u0000\u0000\u0697\u0685\u0001\u0000\u0000\u0000\u0697\u0686" +
            "\u0001\u0000\u0000\u0000\u0697\u068a\u0001\u0000\u0000\u0000\u0697\u068b" +
            "\u0001\u0000\u0000\u0000\u0697\u068c\u0001\u0000\u0000\u0000\u0697\u068d" +
            "\u0001\u0000\u0000\u0000\u0697\u068e\u0001\u0000\u0000\u0000\u0697\u0692" +
            "\u0001\u0000\u0000\u0000\u0697\u0696\u0001\u0000\u0000\u0000\u0698\u06e7" +
            "\u0001\u0000\u0000\u0000\u0699\u069a\n\u001e\u0000\u0000\u069a\u069b\u0007" +
            "\u0007\u0000\u0000\u069b\u06e6\u0003\u0116\u008b\u001f\u069c\u069d\n\u001d" +
            "\u0000\u0000\u069d\u069e\u0007\b\u0000\u0000\u069e\u06e6\u0003\u0116\u008b" +
            "\u001e\u069f\u06a0\n\u001c\u0000\u0000\u06a0\u06a1\u0007\t\u0000\u0000" +
            "\u06a1\u06e6\u0003\u0116\u008b\u001d\u06a2\u06a3\n\u001b\u0000\u0000\u06a3" +
            "\u06a4\u0007\n\u0000\u0000\u06a4\u06e6\u0003\u0116\u008b\u001c\u06a5\u06a6" +
            "\n\u001a\u0000\u0000\u06a6\u06a7\u0005@\u0000\u0000\u06a7\u06e6\u0003" +
            "\u0116\u008b\u001b\u06a8\u06a9\n\u0019\u0000\u0000\u06a9\u06aa\u0005V" +
            "\u0000\u0000\u06aa\u06e6\u0003\u0116\u008b\u001a\u06ab\u06ac\n\u0018\u0000" +
            "\u0000\u06ac\u06ad\u0007\u000b\u0000\u0000\u06ad\u06e6\u0003\u0116\u008b" +
            "\u0019\u06ae\u06af\n\u0017\u0000\u0000\u06af\u06b0\u0005&\u0000\u0000" +
            "\u06b0\u06e6\u0003\u0116\u008b\u0018\u06b1\u06b2\n\u0016\u0000\u0000\u06b2" +
            "\u06b3\u0005\'\u0000\u0000\u06b3\u06e6\u0003\u0116\u008b\u0017\u06b4\u06b5" +
            "\n\u0015\u0000\u0000\u06b5\u06b6\u0005(\u0000\u0000\u06b6\u06e6\u0003" +
            "\u0116\u008b\u0016\u06b7\u06b8\n\u0014\u0000\u0000\u06b8\u06b9\u0005)" +
            "\u0000\u0000\u06b9\u06e6\u0003\u0116\u008b\u0015\u06ba\u06bb\n\u0013\u0000" +
            "\u0000\u06bb\u06bc\u0005*\u0000\u0000\u06bc\u06e6\u0003\u0116\u008b\u0014" +
            "\u06bd\u06be\n\u0012\u0000\u0000\u06be\u06bf\u0005\u000e\u0000\u0000\u06bf" +
            "\u06c0\u0003\u0116\u008b\u0000\u06c0\u06c1\u0005\u000f\u0000\u0000\u06c1" +
            "\u06c2\u0003\u0116\u008b\u0013\u06c2\u06e6\u0001\u0000\u0000\u0000\u06c3" +
            "\u06c4\n\u0011\u0000\u0000\u06c4\u06c5\u0005\r\u0000\u0000\u06c5\u06e6" +
            "\u0003\u0116\u008b\u0012\u06c6\u06c7\n\u0010\u0000\u0000\u06c7\u06c8\u0003" +
            "\u0120\u0090\u0000\u06c8\u06c9\u0003\u0116\u008b\u0011\u06c9\u06e6\u0001" +
            "\u0000\u0000\u0000\u06ca\u06cb\n.\u0000\u0000\u06cb\u06cc\u0005\u0004" +
            "\u0000\u0000\u06cc\u06cd\u0003\u0112\u0089\u0000\u06cd\u06ce\u0005\u0005" +
            "\u0000\u0000\u06ce\u06e6\u0001\u0000\u0000\u0000\u06cf\u06d1\n-\u0000" +
            "\u0000\u06d0\u06d2\u0005\u000e\u0000\u0000\u06d1\u06d0\u0001\u0000\u0000" +
            "\u0000\u06d1\u06d2\u0001\u0000\u0000\u0000\u06d2\u06d3\u0001\u0000\u0000" +
            "\u0000\u06d3\u06d4\u0005\u0011\u0000\u0000\u06d4\u06d6\u0003\u012a\u0095" +
            "\u0000\u06d5\u06d7\u00034\u001a\u0000\u06d6\u06d5\u0001\u0000\u0000\u0000" +
            "\u06d6\u06d7\u0001\u0000\u0000\u0000\u06d7\u06e6\u0001\u0000\u0000\u0000" +
            "\u06d8\u06d9\n*\u0000\u0000\u06d9\u06e6\u0003\u010c\u0086\u0000\u06da" +
            "\u06db\n)\u0000\u0000\u06db\u06dc\u0004\u008b\"\u0000\u06dc\u06e6\u0005" +
            "\u0012\u0000\u0000\u06dd\u06de\n(\u0000\u0000\u06de\u06df\u0004\u008b" +
            "$\u0000\u06df\u06e6\u0005\u0013\u0000\u0000\u06e0\u06e1\n\u000f\u0000" +
            "\u0000\u06e1\u06e6\u0003\u0124\u0092\u0000\u06e2\u06e3\n\u0002\u0000\u0000" +
            "\u06e3\u06e4\u0005X\u0000\u0000\u06e4\u06e6\u0003\u0118\u008c\u0000\u06e5" +
            "\u0699\u0001\u0000\u0000\u0000\u06e5\u069c\u0001\u0000\u0000\u0000\u06e5" +
            "\u069f\u0001\u0000\u0000\u0000\u06e5\u06a2\u0001\u0000\u0000\u0000\u06e5" +
            "\u06a5\u0001\u0000\u0000\u0000\u06e5\u06a8\u0001\u0000\u0000\u0000\u06e5" +
            "\u06ab\u0001\u0000\u0000\u0000\u06e5\u06ae\u0001\u0000\u0000\u0000\u06e5" +
            "\u06b1\u0001\u0000\u0000\u0000\u06e5\u06b4\u0001\u0000\u0000\u0000\u06e5" +
            "\u06b7\u0001\u0000\u0000\u0000\u06e5\u06ba\u0001\u0000\u0000\u0000\u06e5" +
            "\u06bd\u0001\u0000\u0000\u0000\u06e5\u06c3\u0001\u0000\u0000\u0000\u06e5" +
            "\u06c6\u0001\u0000\u0000\u0000\u06e5\u06ca\u0001\u0000\u0000\u0000\u06e5" +
            "\u06cf\u0001\u0000\u0000\u0000\u06e5\u06d8\u0001\u0000\u0000\u0000\u06e5" +
            "\u06da\u0001\u0000\u0000\u0000\u06e5\u06dd\u0001\u0000\u0000\u0000\u06e5" +
            "\u06e0\u0001\u0000\u0000\u0000\u06e5\u06e2\u0001\u0000\u0000\u0000\u06e6" +
            "\u06e9\u0001\u0000\u0000\u0000\u06e7\u06e5\u0001\u0000\u0000\u0000\u06e7" +
            "\u06e8\u0001\u0000\u0000\u0000\u06e8\u0117\u0001\u0000\u0000\u0000\u06e9" +
            "\u06e7\u0001\u0000\u0000\u0000\u06ea\u06ed\u00030\u0018\u0000\u06eb\u06ec" +
            "\u0005\u0004\u0000\u0000\u06ec\u06ee\u0005\u0005\u0000\u0000\u06ed\u06eb" +
            "\u0001\u0000\u0000\u0000\u06ed\u06ee\u0001\u0000\u0000\u0000\u06ee\u06f1" +
            "\u0001\u0000\u0000\u0000\u06ef\u06f1\u0003\u0116\u008b\u0000\u06f0\u06ea" +
            "\u0001\u0000\u0000\u0000\u06f0\u06ef\u0001\u0000\u0000\u0000\u06f1\u0119" +
            "\u0001\u0000\u0000\u0000\u06f2\u06f4\u0005[\u0000\u0000\u06f3\u06f2\u0001" +
            "\u0000\u0000\u0000\u06f3\u06f4\u0001\u0000\u0000\u0000\u06f4\u06f5\u0001" +
            "\u0000\u0000\u0000\u06f5\u06f7\u0003\u011c\u008e\u0000\u06f6\u06f8\u0003" +
            "T*\u0000\u06f7\u06f6\u0001\u0000\u0000\u0000\u06f7\u06f8\u0001\u0000\u0000" +
            "\u0000\u06f8\u06f9\u0001\u0000\u0000\u0000\u06f9\u06fa\u00056\u0000\u0000" +
            "\u06fa\u06fb\u0003\u011e\u008f\u0000\u06fb\u011b\u0001\u0000\u0000\u0000" +
            "\u06fc\u0703\u0005|\u0000\u0000\u06fd\u06ff\u0005\u0006\u0000\u0000\u06fe" +
            "\u0700\u0003\u00f2y\u0000\u06ff\u06fe\u0001\u0000\u0000\u0000\u06ff\u0700" +
            "\u0001\u0000\u0000\u0000\u0700\u0701\u0001\u0000\u0000\u0000\u0701\u0703" +
            "\u0005\u0007\u0000\u0000\u0702\u06fc\u0001\u0000\u0000\u0000\u0702\u06fd" +
            "\u0001\u0000\u0000\u0000\u0703\u011d\u0001\u0000\u0000\u0000\u0704\u070a" +
            "\u0003\u0116\u008b\u0000\u0705\u0706\u0005\b\u0000\u0000\u0706\u0707\u0003" +
            "\u00f8|\u0000\u0707\u0708\u0005\n\u0000\u0000\u0708\u070a\u0001\u0000" +
            "\u0000\u0000\u0709\u0704\u0001\u0000\u0000\u0000\u0709\u0705\u0001\u0000" +
            "\u0000\u0000\u070a\u011f\u0001\u0000\u0000\u0000\u070b\u070c\u0007\f\u0000" +
            "\u0000\u070c\u0121\u0001\u0000\u0000\u0000\u070d\u0714\u00057\u0000\u0000" +
            "\u070e\u0714\u00058\u0000\u0000\u070f\u0714\u0005}\u0000\u0000\u0710\u0714" +
            "\u0003\u0124\u0092\u0000\u0711\u0714\u0005\u0003\u0000\u0000\u0712\u0714" +
            "\u0003\u0128\u0094\u0000\u0713\u070d\u0001\u0000\u0000\u0000\u0713\u070e" +
            "\u0001\u0000\u0000\u0000\u0713\u070f\u0001\u0000\u0000\u0000\u0713\u0710" +
            "\u0001\u0000\u0000\u0000\u0713\u0711\u0001\u0000\u0000\u0000\u0713\u0712" +
            "\u0001\u0000\u0000\u0000\u0714\u0123\u0001\u0000\u0000\u0000\u0715\u0719" +
            "\u0005~\u0000\u0000\u0716\u0718\u0003\u0126\u0093\u0000\u0717\u0716\u0001" +
            "\u0000\u0000\u0000\u0718\u071b\u0001\u0000\u0000\u0000\u0719\u0717\u0001" +
            "\u0000\u0000\u0000\u0719\u071a\u0001\u0000\u0000\u0000\u071a\u071c\u0001" +
            "\u0000\u0000\u0000\u071b\u0719\u0001\u0000\u0000\u0000\u071c\u071d\u0005" +
            "~\u0000\u0000\u071d\u0125\u0001\u0000\u0000\u0000\u071e\u0724\u0005\u0086" +
            "\u0000\u0000\u071f\u0720\u0005\u0085\u0000\u0000\u0720\u0721\u0003\u0116" +
            "\u008b\u0000\u0721\u0722\u0005\t\u0000\u0000\u0722\u0724\u0001\u0000\u0000" +
            "\u0000\u0723\u071e\u0001\u0000\u0000\u0000\u0723\u071f\u0001\u0000\u0000" +
            "\u0000\u0724\u0127\u0001\u0000\u0000\u0000\u0725\u0726\u0007\r\u0000\u0000" +
            "\u0726\u0129\u0001\u0000\u0000\u0000\u0727\u072a\u0005|\u0000\u0000\u0728" +
            "\u072a\u0003\u012e\u0097\u0000\u0729\u0727\u0001\u0000\u0000\u0000\u0729" +
            "\u0728\u0001\u0000\u0000\u0000\u072a\u012b\u0001\u0000\u0000\u0000\u072b" +
            "\u072c\u0007\u000e\u0000\u0000\u072c\u012d\u0001\u0000\u0000\u0000\u072d" +
            "\u0731\u0003\u0130\u0098\u0000\u072e\u0731\u00057\u0000\u0000\u072f\u0731" +
            "\u00058\u0000\u0000\u0730\u072d\u0001\u0000\u0000\u0000\u0730\u072e\u0001" +
            "\u0000\u0000\u0000\u0730\u072f\u0001\u0000\u0000\u0000\u0731\u012f\u0001" +
            "\u0000\u0000\u0000\u0732\u0733\u0007\u000f\u0000\u0000\u0733\u0131\u0001" +
            "\u0000\u0000\u0000\u0734\u0735\u0005r\u0000\u0000\u0735\u0736\u0003\u010a" +
            "\u0085\u0000\u0736\u0133\u0001\u0000\u0000\u0000\u0737\u0738\u0005s\u0000" +
            "\u0000\u0738\u0739\u0003\u010a\u0085\u0000\u0739\u0135\u0001\u0000\u0000" +
            "\u0000\u073a\u073f\u0005\u000b\u0000\u0000\u073b\u073f\u0005\u0000\u0000" +
            "\u0001\u073c\u073f\u0004\u009b\'\u0000\u073d\u073f\u0004\u009b(\u0000" +
            "\u073e\u073a\u0001\u0000\u0000\u0000\u073e\u073b\u0001\u0000\u0000\u0000" +
            "\u073e\u073c\u0001\u0000\u0000\u0000\u073e\u073d\u0001\u0000\u0000\u0000" +
            "\u073f\u0137\u0001\u0000\u0000\u0000\u00e8\u013b\u0142\u0151\u015d\u0168" +
            "\u0174\u0177\u017b\u017e\u0182\u018e\u0194\u0198\u01a1\u01a9\u01ac\u01b1" +
            "\u01b6\u01bb\u01c6\u01ca\u01d3\u01d8\u01db\u01e2\u01eb\u01f5\u0200\u0202" +
            "\u0217\u021f\u0226\u022a\u0239\u023d\u0241\u0247\u024e\u0258\u025a\u026a" +
            "\u026e\u0272\u027a\u027e\u028d\u0291\u0294\u0298\u029b\u029f\u02a5\u02a9" +
            "\u02ad\u02b5\u02ba\u02bc\u02c3\u02c8\u02cb\u02ce\u02d3\u02d6\u02d9\u02de" +
            "\u02e1\u02e4\u02e8\u02ee\u02f2\u02f6\u02fa\u0305\u030c\u0313\u0318\u0320" +
            "\u0323\u0326\u032b\u032e\u0332\u033c\u0340\u0346\u034c\u0353\u0359\u035f" +
            "\u0367\u036c\u0377\u037c\u0384\u038b\u0392\u0397\u03b9\u03bd\u03c4\u03ca" +
            "\u03d1\u03d6\u03da\u03e3\u03eb\u03f2\u03f6\u03fa\u03fe\u0401\u0404\u0407" +
            "\u040b\u040f\u0413\u0415\u041c\u0422\u0425\u0428\u042c\u042f\u0436\u043f" +
            "\u0452\u0456\u045a\u0464\u0468\u0473\u0480\u0486\u048d\u0494\u049b\u04a4" +
            "\u04a9\u04bb\u04bf\u04c1\u04c8\u04ce\u04d3\u04e2\u04e5\u04f4\u04fe\u0501" +
            "\u0506\u050c\u050f\u0515\u0522\u0527\u052c\u052f\u0532\u053e\u0543\u0546" +
            "\u0549\u054c\u054f\u0552\u0558\u055d\u0567\u056b\u0578\u057c\u0589\u058d" +
            "\u0596\u05a2\u05a7\u05ae\u05b0\u05b3\u05b6\u05ba\u05bd\u05c1\u05c7\u05cc" +
            "\u05d0\u05d8\u05dd\u05e1\u05e5\u05e8\u05f0\u05f4\u05f6\u0609\u060f\u0619" +
            "\u061c\u0626\u062b\u062d\u0636\u063a\u063e\u0641\u0648\u064d\u0651\u0655" +
            "\u0660\u0666\u066d\u0688\u0694\u0697\u06d1\u06d6\u06e5\u06e7\u06ed\u06f0" +
            "\u06f3\u06f7\u06ff\u0702\u0709\u0713\u0719\u0723\u0729\u0730\u073e";
    public static final ATN _ATN =
        new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
        new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION);
    }

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public TypeScriptParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
            "htmlElements", "htmlElement", "htmlContent", "htmlTagStartName", "htmlTagClosingName",
            "htmlTagName", "htmlAttribute", "htmlAttributeName", "identifierOrKeyWordForHtmlAttribute",
            "htmlChardata", "htmlAttributeValue", "objectExpressionSequence", "initializer",
            "bindingPattern", "typeParameters", "typeParameterList", "typeParameter",
            "constraint", "typeArguments", "typeArgumentList", "typeArgument", "type_",
            "unionOrIntersectionOrPrimaryType", "primaryType", "predefinedType",
            "typeReference", "nestedTypeGeneric", "typeGeneric", "typeIncludeGeneric",
            "typeName", "objectType", "typeBody", "typeMemberList", "typeMember",
            "arrayType", "tupleType", "tupleElementTypes", "functionType", "constructorType",
            "typeQuery", "typeQueryExpression", "propertySignatur", "typeAnnotation",
            "callSignature", "parameterList", "requiredParameterList", "parameter",
            "optionalParameter", "restParameter", "requiredParameter", "accessibilityModifier",
            "identifierOrPattern", "constructSignature", "indexSignature", "methodSignature",
            "typeAliasDeclaration", "constructorDeclaration", "interfaceDeclaration",
            "interfaceExtendsClause", "classOrInterfaceTypeList", "enumDeclaration",
            "enumBody", "enumMemberList", "enumMember", "namespaceDeclaration", "namespaceName",
            "importAliasDeclaration", "decoratorList", "decorator", "decoratorMemberExpression",
            "decoratorCallExpression", "program", "sourceElement", "statement", "block",
            "statementList", "abstractDeclaration", "importStatement", "fromBlock",
            "multipleImportStatement", "exportStatement", "variableStatement", "variableDeclarationList",
            "variableDeclaration", "emptyStatement_", "expressionStatement", "ifStatement",
            "iterationStatement", "varModifier", "continueStatement", "breakStatement",
            "returnStatement", "yieldStatement", "withStatement", "switchStatement",
            "caseBlock", "caseClauses", "caseClause", "defaultClause", "labelledStatement",
            "throwStatement", "tryStatement", "catchProduction", "finallyProduction",
            "debuggerStatement", "functionDeclaration", "classDeclaration", "classHeritage",
            "classTail", "classExtendsClause", "implementsClause", "classElement",
            "propertyMemberDeclaration", "propertyMemberBase", "indexMemberDeclaration",
            "generatorMethod", "generatorFunctionDeclaration", "generatorBlock",
            "generatorDefinition", "iteratorBlock", "iteratorDefinition", "formalParameterList",
            "formalParameterArg", "lastFormalParameterArg", "functionBody", "sourceElements",
            "arrayLiteral", "elementList", "arrayElement", "objectLiteral", "propertyAssignment",
            "getAccessor", "setAccessor", "propertyName", "arguments", "argumentList",
            "argument", "expressionSequence", "functionExpressionDeclaration", "singleExpression",
            "asExpression", "arrowFunctionDeclaration", "arrowFunctionParameters",
            "arrowFunctionBody", "assignmentOperator", "literal", "templateStringLiteral",
            "templateStringAtom", "numericLiteral", "identifierName", "identifierOrKeyWord",
            "reservedWord", "keyword", "getter", "setter", "eos"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
            null, null, null, null, "'['", "']'", "'('", "')'", "'{'", null, "'}'",
            "';'", "','", "'='", "'?'", "':'", "'...'", "'.'", "'++'", "'--'", "'+'",
            "'-'", "'~'", "'!'", "'*'", "'/'", "'%'", "'>>'", "'<<'", "'>>>'", "'<'",
            "'>'", "'<='", "'>='", "'=='", "'!='", "'==='", "'!=='", "'&'", "'^'",
            "'|'", "'&&'", "'||'", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='",
            "'>>='", "'>>>='", "'&='", "'^='", "'|='", "'=>'", "'null'", null, null,
            null, null, null, null, "'break'", "'do'", "'instanceof'", "'typeof'",
            "'case'", "'else'", "'new'", "'var'", "'catch'", "'finally'", "'return'",
            "'void'", "'continue'", "'for'", "'switch'", "'while'", "'debugger'",
            "'function'", "'this'", "'with'", "'default'", "'if'", "'throw'", "'delete'",
            "'in'", "'try'", "'as'", "'from'", "'readonly'", "'async'", "'class'",
            "'enum'", "'extends'", "'super'", "'const'", "'export'", "'import'",
            "'implements'", "'let'", "'private'", "'public'", "'interface'", "'package'",
            "'protected'", "'static'", "'yield'", "'any'", "'number'", "'boolean'",
            "'string'", "'symbol'", "'type'", "'get'", "'set'", "'constructor'",
            "'namespace'", "'require'", "'module'", "'declare'", "'abstract'", "'is'",
            "'@'", null, null, null, null, null, null, null, null, null, "'${'",
            null, null, null, "'/>'"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
            null, "MultiLineComment", "SingleLineComment", "RegularExpressionLiteral",
            "OpenBracket", "CloseBracket", "OpenParen", "CloseParen", "OpenBrace",
            "TemplateCloseBrace", "CloseBrace", "SemiColon", "Comma", "Assign", "QuestionMark",
            "Colon", "Ellipsis", "Dot", "PlusPlus", "MinusMinus", "Plus", "Minus",
            "BitNot", "Not", "Multiply", "Divide", "Modulus", "RightShiftArithmetic",
            "LeftShiftArithmetic", "RightShiftLogical", "LessThan", "MoreThan", "LessThanEquals",
            "GreaterThanEquals", "Equals_", "NotEquals", "IdentityEquals", "IdentityNotEquals",
            "BitAnd", "BitXOr", "BitOr", "And", "Or", "MultiplyAssign", "DivideAssign",
            "ModulusAssign", "PlusAssign", "MinusAssign", "LeftShiftArithmeticAssign",
            "RightShiftArithmeticAssign", "RightShiftLogicalAssign", "BitAndAssign",
            "BitXorAssign", "BitOrAssign", "ARROW", "NullLiteral", "BooleanLiteral",
            "DecimalLiteral", "HexIntegerLiteral", "OctalIntegerLiteral", "OctalIntegerLiteral2",
            "BinaryIntegerLiteral", "Break", "Do", "Instanceof", "Typeof", "Case",
            "Else", "New", "Var", "Catch", "Finally", "Return", "Void", "Continue",
            "For", "Switch", "While", "Debugger", "Function_", "This", "With", "Default",
            "If", "Throw", "Delete", "In", "Try", "As", "From", "ReadOnly", "Async",
            "Class", "Enum", "Extends", "Super", "Const", "Export", "Import", "Implements",
            "Let", "Private", "Public", "Interface", "Package", "Protected", "Static",
            "Yield", "Any", "Number", "Boolean", "String", "Symbol", "TypeAlias",
            "Get", "Set", "Constructor", "Namespace", "Require", "Module", "Declare",
            "Abstract", "Is", "At", "Identifier", "StringLiteral", "BackTick", "WhiteSpaces",
            "LineTerminator", "JsxComment", "HtmlComment", "CDataComment", "UnexpectedCharacter",
            "TemplateStringStartExpression", "TemplateStringAtom", "TagOpen", "TagClose",
            "TagSlashClose", "TagSlash", "TagName", "AttributeValue", "Attribute",
            "TagEquals"
        };
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }

    @Override
    public String getGrammarFileName() {
        return "java-escape";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final HtmlElementsContext htmlElements() throws RecognitionException {
        HtmlElementsContext _localctx = new HtmlElementsContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_htmlElements);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(313);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(312);
                                htmlElement();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(315);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 0, _ctx);
                } while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlElementContext htmlElement() throws RecognitionException {
        HtmlElementContext _localctx = new HtmlElementContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_htmlElement);
        int _la;
        try {
            int _alt;
            setState(372);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(317);
                    match(LessThan);
                    setState(318);
                    htmlTagStartName();
                    setState(322);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018427387648L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1175034882464677887L) != 0 || _la == TagName) {
                        {
                            {
                                setState(319);
                                htmlAttribute();
                            }
                        }
                        setState(324);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(325);
                    match(MoreThan);
                    setState(326);
                    htmlContent();
                    setState(327);
                    match(LessThan);
                    setState(328);
                    match(Divide);
                    setState(329);
                    htmlTagClosingName();
                    setState(330);
                    match(MoreThan);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(332);
                    match(LessThan);
                    setState(333);
                    htmlTagName();
                    setState(337);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                    while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(334);
                                    htmlAttribute();
                                }
                            }
                        }
                        setState(339);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 2, _ctx);
                    }
                    setState(340);
                    htmlContent();
                    setState(341);
                    match(Divide);
                    setState(342);
                    match(MoreThan);
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(344);
                    match(LessThan);
                    setState(345);
                    htmlTagName();
                    setState(349);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018427387648L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1175034882464677887L) != 0 || _la == TagName) {
                        {
                            {
                                setState(346);
                                htmlAttribute();
                            }
                        }
                        setState(351);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(352);
                    match(Divide);
                    setState(353);
                    match(MoreThan);
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(355);
                    match(LessThan);
                    setState(356);
                    htmlTagName();
                    setState(360);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018427387648L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1175034882464677887L) != 0 || _la == TagName) {
                        {
                            {
                                setState(357);
                                htmlAttribute();
                            }
                        }
                        setState(362);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(363);
                    match(MoreThan);
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(365);
                    match(LessThan);
                    setState(366);
                    match(MoreThan);
                    setState(367);
                    htmlContent();
                    setState(368);
                    match(LessThan);
                    setState(369);
                    match(Divide);
                    setState(370);
                    match(MoreThan);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlContentContext htmlContent() throws RecognitionException {
        HtmlContentContext _localctx = new HtmlContentContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_htmlContent);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(375);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
                    case 1: {
                        setState(374);
                        htmlChardata();
                    }
                    break;
                }
                setState(386);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 9, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(379);
                                _errHandler.sync(this);
                                switch (_input.LA(1)) {
                                    case LessThan: {
                                        setState(377);
                                        htmlElement();
                                    }
                                    break;
                                    case OpenBrace: {
                                        setState(378);
                                        objectExpressionSequence();
                                    }
                                    break;
                                    default:
                                        throw new NoViableAltException(this);
                                }
                                setState(382);
                                _errHandler.sync(this);
                                switch (getInterpreter().adaptivePredict(_input, 8, _ctx)) {
                                    case 1: {
                                        setState(381);
                                        htmlChardata();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    setState(388);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 9, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlTagStartNameContext htmlTagStartName() throws RecognitionException {
        HtmlTagStartNameContext _localctx = new HtmlTagStartNameContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_htmlTagStartName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(389);
                ((HtmlTagStartNameContext) _localctx).htmlTagName = htmlTagName();
                this.pushHtmlTagName((((HtmlTagStartNameContext) _localctx).htmlTagName != null ? _input.getText(((HtmlTagStartNameContext) _localctx).htmlTagName.start, ((HtmlTagStartNameContext) _localctx).htmlTagName.stop) : null));
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlTagClosingNameContext htmlTagClosingName() throws RecognitionException {
        HtmlTagClosingNameContext _localctx = new HtmlTagClosingNameContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_htmlTagClosingName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(392);
                ((HtmlTagClosingNameContext) _localctx).htmlTagName = htmlTagName();
                setState(393);
                if (!(this.popHtmlTagName((((HtmlTagClosingNameContext) _localctx).htmlTagName != null ? _input.getText(((HtmlTagClosingNameContext) _localctx).htmlTagName.start, ((HtmlTagClosingNameContext) _localctx).htmlTagName.stop) : null))))
                    throw new FailedPredicateException(this, "this.popHtmlTagName($htmlTagName.text)");
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlTagNameContext htmlTagName() throws RecognitionException {
        HtmlTagNameContext _localctx = new HtmlTagNameContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_htmlTagName);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(398);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case TagName: {
                        setState(395);
                        match(TagName);
                    }
                    break;
                    case Break:
                    case Do:
                    case Instanceof:
                    case Typeof:
                    case Case:
                    case Else:
                    case New:
                    case Var:
                    case Catch:
                    case Finally:
                    case Return:
                    case Void:
                    case Continue:
                    case For:
                    case Switch:
                    case While:
                    case Debugger:
                    case Function_:
                    case This:
                    case With:
                    case Default:
                    case If:
                    case Throw:
                    case Delete:
                    case In:
                    case Try:
                    case From:
                    case ReadOnly:
                    case Async:
                    case Class:
                    case Enum:
                    case Extends:
                    case Super:
                    case Const:
                    case Export:
                    case Import:
                    case Implements:
                    case Let:
                    case Private:
                    case Public:
                    case Interface:
                    case Package:
                    case Protected:
                    case Static:
                    case Yield:
                    case String:
                    case TypeAlias:
                    case Get:
                    case Set:
                    case Require: {
                        setState(396);
                        keyword();
                    }
                    break;
                    case Identifier: {
                        setState(397);
                        match(Identifier);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                setState(408);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 12, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(400);
                                match(Dot);
                                setState(404);
                                _errHandler.sync(this);
                                switch (_input.LA(1)) {
                                    case TagName: {
                                        setState(401);
                                        match(TagName);
                                    }
                                    break;
                                    case Break:
                                    case Do:
                                    case Instanceof:
                                    case Typeof:
                                    case Case:
                                    case Else:
                                    case New:
                                    case Var:
                                    case Catch:
                                    case Finally:
                                    case Return:
                                    case Void:
                                    case Continue:
                                    case For:
                                    case Switch:
                                    case While:
                                    case Debugger:
                                    case Function_:
                                    case This:
                                    case With:
                                    case Default:
                                    case If:
                                    case Throw:
                                    case Delete:
                                    case In:
                                    case Try:
                                    case From:
                                    case ReadOnly:
                                    case Async:
                                    case Class:
                                    case Enum:
                                    case Extends:
                                    case Super:
                                    case Const:
                                    case Export:
                                    case Import:
                                    case Implements:
                                    case Let:
                                    case Private:
                                    case Public:
                                    case Interface:
                                    case Package:
                                    case Protected:
                                    case Static:
                                    case Yield:
                                    case String:
                                    case TypeAlias:
                                    case Get:
                                    case Set:
                                    case Require: {
                                        setState(402);
                                        keyword();
                                    }
                                    break;
                                    case Identifier: {
                                        setState(403);
                                        match(Identifier);
                                    }
                                    break;
                                    default:
                                        throw new NoViableAltException(this);
                                }
                            }
                        }
                    }
                    setState(410);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 12, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlAttributeContext htmlAttribute() throws RecognitionException {
        HtmlAttributeContext _localctx = new HtmlAttributeContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_htmlAttribute);
        try {
            setState(417);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 13, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(411);
                    htmlAttributeName();
                    setState(412);
                    match(Assign);
                    setState(413);
                    htmlAttributeValue();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(415);
                    htmlAttributeName();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(416);
                    objectExpressionSequence();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlAttributeNameContext htmlAttributeName() throws RecognitionException {
        HtmlAttributeNameContext _localctx = new HtmlAttributeNameContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_htmlAttributeName);
        try {
            int _alt;
            setState(428);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case TagName:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(419);
                    match(TagName);
                }
                break;
                case Break:
                case Do:
                case Instanceof:
                case Typeof:
                case Case:
                case Else:
                case New:
                case Var:
                case Catch:
                case Finally:
                case Return:
                case Void:
                case Continue:
                case For:
                case Switch:
                case While:
                case Debugger:
                case Function_:
                case This:
                case With:
                case Default:
                case If:
                case Throw:
                case Delete:
                case In:
                case Try:
                case As:
                case From:
                case ReadOnly:
                case Async:
                case Class:
                case Enum:
                case Extends:
                case Super:
                case Const:
                case Export:
                case Import:
                case Implements:
                case Let:
                case Private:
                case Public:
                case Interface:
                case Package:
                case Protected:
                case Static:
                case Yield:
                case String:
                case TypeAlias:
                case Get:
                case Set:
                case Require:
                case Identifier:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(420);
                    identifierOrKeyWordForHtmlAttribute();
                    setState(425);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 14, _ctx);
                    while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(421);
                                    match(Minus);
                                    setState(422);
                                    identifierOrKeyWordForHtmlAttribute();
                                }
                            }
                        }
                        setState(427);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 14, _ctx);
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IdentifierOrKeyWordForHtmlAttributeContext identifierOrKeyWordForHtmlAttribute() throws RecognitionException {
        IdentifierOrKeyWordForHtmlAttributeContext _localctx = new IdentifierOrKeyWordForHtmlAttributeContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_identifierOrKeyWordForHtmlAttribute);
        try {
            setState(433);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(430);
                    match(Identifier);
                }
                break;
                case As:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(431);
                    match(As);
                }
                break;
                case Break:
                case Do:
                case Instanceof:
                case Typeof:
                case Case:
                case Else:
                case New:
                case Var:
                case Catch:
                case Finally:
                case Return:
                case Void:
                case Continue:
                case For:
                case Switch:
                case While:
                case Debugger:
                case Function_:
                case This:
                case With:
                case Default:
                case If:
                case Throw:
                case Delete:
                case In:
                case Try:
                case From:
                case ReadOnly:
                case Async:
                case Class:
                case Enum:
                case Extends:
                case Super:
                case Const:
                case Export:
                case Import:
                case Implements:
                case Let:
                case Private:
                case Public:
                case Interface:
                case Package:
                case Protected:
                case Static:
                case Yield:
                case String:
                case TypeAlias:
                case Get:
                case Set:
                case Require:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(432);
                    keyword();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlChardataContext htmlChardata() throws RecognitionException {
        HtmlChardataContext _localctx = new HtmlChardataContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_htmlChardata);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(436);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(435);
                                _la = _input.LA(1);
                                if (_la <= 0 || (_la == OpenBrace || _la == LessThan)) {
                                    _errHandler.recoverInline(this);
                                } else {
                                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                    _errHandler.reportMatch(this);
                                    consume();
                                }
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(438);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 17, _ctx);
                } while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final HtmlAttributeValueContext htmlAttributeValue() throws RecognitionException {
        HtmlAttributeValueContext _localctx = new HtmlAttributeValueContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_htmlAttributeValue);
        try {
            setState(443);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case AttributeValue:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(440);
                    match(AttributeValue);
                }
                break;
                case StringLiteral:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(441);
                    match(StringLiteral);
                }
                break;
                case OpenBrace:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(442);
                    objectExpressionSequence();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ObjectExpressionSequenceContext objectExpressionSequence() throws RecognitionException {
        ObjectExpressionSequenceContext _localctx = new ObjectExpressionSequenceContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_objectExpressionSequence);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(445);
                match(OpenBrace);
                setState(446);
                expressionSequence();
                setState(447);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final InitializerContext initializer() throws RecognitionException {
        InitializerContext _localctx = new InitializerContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_initializer);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(449);
                match(Assign);
                setState(450);
                singleExpression(0);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BindingPatternContext bindingPattern() throws RecognitionException {
        BindingPatternContext _localctx = new BindingPatternContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_bindingPattern);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(454);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case OpenBracket: {
                        setState(452);
                        arrayLiteral();
                    }
                    break;
                    case OpenBrace: {
                        setState(453);
                        objectLiteral();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeParametersContext typeParameters() throws RecognitionException {
        TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_typeParameters);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(456);
                match(LessThan);
                setState(458);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan || _la == Identifier) {
                    {
                        setState(457);
                        typeParameterList();
                    }
                }

                setState(460);
                match(MoreThan);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeParameterListContext typeParameterList() throws RecognitionException {
        TypeParameterListContext _localctx = new TypeParameterListContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_typeParameterList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(462);
                typeParameter();
                setState(467);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Comma) {
                    {
                        {
                            setState(463);
                            match(Comma);
                            setState(464);
                            typeParameter();
                        }
                    }
                    setState(469);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeParameterContext typeParameter() throws RecognitionException {
        TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
        enterRule(_localctx, 32, RULE_typeParameter);
        int _la;
        try {
            setState(475);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(470);
                    match(Identifier);
                    setState(472);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == Extends) {
                        {
                            setState(471);
                            constraint();
                        }
                    }

                }
                break;
                case LessThan:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(474);
                    typeParameters();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ConstraintContext constraint() throws RecognitionException {
        ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_constraint);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(477);
                match(Extends);
                setState(478);
                type_();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeArgumentsContext typeArguments() throws RecognitionException {
        TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_typeArguments);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(480);
                match(LessThan);
                setState(482);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & 1073742160L) != 0 || (((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 1729654935793991945L) != 0) {
                    {
                        setState(481);
                        typeArgumentList();
                    }
                }

                setState(484);
                match(MoreThan);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeArgumentListContext typeArgumentList() throws RecognitionException {
        TypeArgumentListContext _localctx = new TypeArgumentListContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_typeArgumentList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(486);
                typeArgument();
                setState(491);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Comma) {
                    {
                        {
                            setState(487);
                            match(Comma);
                            setState(488);
                            typeArgument();
                        }
                    }
                    setState(493);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeArgumentContext typeArgument() throws RecognitionException {
        TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
        enterRule(_localctx, 40, RULE_typeArgument);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(494);
                type_();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final Type_Context type_() throws RecognitionException {
        Type_Context _localctx = new Type_Context(_ctx, getState());
        enterRule(_localctx, 42, RULE_type_);
        try {
            setState(501);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 26, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(496);
                    unionOrIntersectionOrPrimaryType(0);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(497);
                    functionType();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(498);
                    constructorType();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(499);
                    typeGeneric();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(500);
                    match(StringLiteral);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType() throws RecognitionException {
        return unionOrIntersectionOrPrimaryType(0);
    }

    private UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        UnionOrIntersectionOrPrimaryTypeContext _localctx = new UnionOrIntersectionOrPrimaryTypeContext(_ctx, _parentState);
        UnionOrIntersectionOrPrimaryTypeContext _prevctx = _localctx;
        int _startState = 44;
        enterRecursionRule(_localctx, 44, RULE_unionOrIntersectionOrPrimaryType, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                {
                    _localctx = new PrimaryContext(_localctx);
                    _ctx = _localctx;
                    _prevctx = _localctx;

                    setState(504);
                    primaryType(0);
                }
                _ctx.stop = _input.LT(-1);
                setState(514);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 28, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(512);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
                                case 1: {
                                    _localctx = new UnionContext(new UnionOrIntersectionOrPrimaryTypeContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_unionOrIntersectionOrPrimaryType);
                                    setState(506);
                                    if (!(precpred(_ctx, 3)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 3)");
                                    setState(507);
                                    match(BitOr);
                                    setState(508);
                                    unionOrIntersectionOrPrimaryType(4);
                                }
                                break;
                                case 2: {
                                    _localctx = new IntersectionContext(new UnionOrIntersectionOrPrimaryTypeContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_unionOrIntersectionOrPrimaryType);
                                    setState(509);
                                    if (!(precpred(_ctx, 2)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(510);
                                    match(BitAnd);
                                    setState(511);
                                    unionOrIntersectionOrPrimaryType(3);
                                }
                                break;
                            }
                        }
                    }
                    setState(516);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 28, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public final PrimaryTypeContext primaryType() throws RecognitionException {
        return primaryType(0);
    }

    private PrimaryTypeContext primaryType(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        PrimaryTypeContext _localctx = new PrimaryTypeContext(_ctx, _parentState);
        PrimaryTypeContext _prevctx = _localctx;
        int _startState = 46;
        enterRecursionRule(_localctx, 46, RULE_primaryType, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(535);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 29, _ctx)) {
                    case 1: {
                        _localctx = new ParenthesizedPrimTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(518);
                        match(OpenParen);
                        setState(519);
                        type_();
                        setState(520);
                        match(CloseParen);
                    }
                    break;
                    case 2: {
                        _localctx = new PredefinedPrimTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(522);
                        predefinedType();
                    }
                    break;
                    case 3: {
                        _localctx = new ReferencePrimTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(523);
                        typeReference();
                    }
                    break;
                    case 4: {
                        _localctx = new ObjectPrimTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(524);
                        objectType();
                    }
                    break;
                    case 5: {
                        _localctx = new TuplePrimTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(525);
                        match(OpenBracket);
                        setState(526);
                        tupleElementTypes();
                        setState(527);
                        match(CloseBracket);
                    }
                    break;
                    case 6: {
                        _localctx = new QueryPrimTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(529);
                        typeQuery();
                    }
                    break;
                    case 7: {
                        _localctx = new ThisPrimTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(530);
                        match(This);
                    }
                    break;
                    case 8: {
                        _localctx = new RedefinitionOfTypeContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(531);
                        typeReference();
                        setState(532);
                        match(Is);
                        setState(533);
                        primaryType(1);
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(543);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new ArrayPrimTypeContext(new PrimaryTypeContext(_parentctx, _parentState));
                                pushNewRecursionContext(_localctx, _startState, RULE_primaryType);
                                setState(537);
                                if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
                                setState(538);
                                if (!(notLineTerminator()))
                                    throw new FailedPredicateException(this, "notLineTerminator()");
                                setState(539);
                                match(OpenBracket);
                                setState(540);
                                match(CloseBracket);
                            }
                        }
                    }
                    setState(545);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public final PredefinedTypeContext predefinedType() throws RecognitionException {
        PredefinedTypeContext _localctx = new PredefinedTypeContext(_ctx, getState());
        enterRule(_localctx, 48, RULE_predefinedType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(546);
                _la = _input.LA(1);
                if (!((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 1065151889409L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeReferenceContext typeReference() throws RecognitionException {
        TypeReferenceContext _localctx = new TypeReferenceContext(_ctx, getState());
        enterRule(_localctx, 50, RULE_typeReference);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(548);
                typeName();
                setState(550);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 31, _ctx)) {
                    case 1: {
                        setState(549);
                        nestedTypeGeneric();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final NestedTypeGenericContext nestedTypeGeneric() throws RecognitionException {
        NestedTypeGenericContext _localctx = new NestedTypeGenericContext(_ctx, getState());
        enterRule(_localctx, 52, RULE_nestedTypeGeneric);
        try {
            setState(554);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 32, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(552);
                    typeIncludeGeneric();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(553);
                    typeGeneric();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeGenericContext typeGeneric() throws RecognitionException {
        TypeGenericContext _localctx = new TypeGenericContext(_ctx, getState());
        enterRule(_localctx, 54, RULE_typeGeneric);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(556);
                match(LessThan);
                setState(557);
                typeArgumentList();
                setState(558);
                match(MoreThan);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeIncludeGenericContext typeIncludeGeneric() throws RecognitionException {
        TypeIncludeGenericContext _localctx = new TypeIncludeGenericContext(_ctx, getState());
        enterRule(_localctx, 56, RULE_typeIncludeGeneric);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(560);
                match(LessThan);
                setState(561);
                typeArgumentList();
                setState(562);
                match(LessThan);
                setState(563);
                typeArgumentList();
                setState(569);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case MoreThan: {
                        setState(564);
                        match(MoreThan);
                        setState(565);
                        bindingPattern();
                        setState(566);
                        match(MoreThan);
                    }
                    break;
                    case RightShiftArithmetic: {
                        setState(568);
                        match(RightShiftArithmetic);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeNameContext typeName() throws RecognitionException {
        TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
        enterRule(_localctx, 58, RULE_typeName);
        try {
            setState(573);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(571);
                    match(Identifier);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(572);
                    namespaceName();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ObjectTypeContext objectType() throws RecognitionException {
        ObjectTypeContext _localctx = new ObjectTypeContext(_ctx, getState());
        enterRule(_localctx, 60, RULE_objectType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(575);
                match(OpenBrace);
                setState(577);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795945222064L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 3480877891661594623L) != 0) {
                    {
                        setState(576);
                        typeBody();
                    }
                }

                setState(579);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeBodyContext typeBody() throws RecognitionException {
        TypeBodyContext _localctx = new TypeBodyContext(_ctx, getState());
        enterRule(_localctx, 62, RULE_typeBody);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(581);
                typeMemberList();
                setState(583);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == SemiColon || _la == Comma) {
                    {
                        setState(582);
                        _la = _input.LA(1);
                        if (!(_la == SemiColon || _la == Comma)) {
                            _errHandler.recoverInline(this);
                        } else {
                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                            _errHandler.reportMatch(this);
                            consume();
                        }
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeMemberListContext typeMemberList() throws RecognitionException {
        TypeMemberListContext _localctx = new TypeMemberListContext(_ctx, getState());
        enterRule(_localctx, 64, RULE_typeMemberList);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(585);
                typeMember();
                setState(590);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 37, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(586);
                                _la = _input.LA(1);
                                if (!(_la == SemiColon || _la == Comma)) {
                                    _errHandler.recoverInline(this);
                                } else {
                                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                    _errHandler.reportMatch(this);
                                    consume();
                                }
                                setState(587);
                                typeMember();
                            }
                        }
                    }
                    setState(592);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 37, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeMemberContext typeMember() throws RecognitionException {
        TypeMemberContext _localctx = new TypeMemberContext(_ctx, getState());
        enterRule(_localctx, 66, RULE_typeMember);
        int _la;
        try {
            setState(602);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 39, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(593);
                    propertySignatur();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(594);
                    callSignature();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(595);
                    constructSignature();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(596);
                    indexSignature();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(597);
                    methodSignature();
                    setState(600);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == ARROW) {
                        {
                            setState(598);
                            match(ARROW);
                            setState(599);
                            type_();
                        }
                    }

                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArrayTypeContext arrayType() throws RecognitionException {
        ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
        enterRule(_localctx, 68, RULE_arrayType);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(604);
                primaryType(0);
                setState(605);
                if (!(notLineTerminator())) throw new FailedPredicateException(this, "notLineTerminator()");
                setState(606);
                match(OpenBracket);
                setState(607);
                match(CloseBracket);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TupleTypeContext tupleType() throws RecognitionException {
        TupleTypeContext _localctx = new TupleTypeContext(_ctx, getState());
        enterRule(_localctx, 70, RULE_tupleType);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(609);
                match(OpenBracket);
                setState(610);
                tupleElementTypes();
                setState(611);
                match(CloseBracket);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TupleElementTypesContext tupleElementTypes() throws RecognitionException {
        TupleElementTypesContext _localctx = new TupleElementTypesContext(_ctx, getState());
        enterRule(_localctx, 72, RULE_tupleElementTypes);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(613);
                type_();
                setState(618);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Comma) {
                    {
                        {
                            setState(614);
                            match(Comma);
                            setState(615);
                            type_();
                        }
                    }
                    setState(620);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FunctionTypeContext functionType() throws RecognitionException {
        FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
        enterRule(_localctx, 74, RULE_functionType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(622);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan) {
                    {
                        setState(621);
                        typeParameters();
                    }
                }

                setState(624);
                match(OpenParen);
                setState(626);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -4503599627370430192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1751495634751324159L) != 0) {
                    {
                        setState(625);
                        parameterList();
                    }
                }

                setState(628);
                match(CloseParen);
                setState(629);
                match(ARROW);
                setState(630);
                type_();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ConstructorTypeContext constructorType() throws RecognitionException {
        ConstructorTypeContext _localctx = new ConstructorTypeContext(_ctx, getState());
        enterRule(_localctx, 76, RULE_constructorType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(632);
                match(New);
                setState(634);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan) {
                    {
                        setState(633);
                        typeParameters();
                    }
                }

                setState(636);
                match(OpenParen);
                setState(638);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -4503599627370430192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1751495634751324159L) != 0) {
                    {
                        setState(637);
                        parameterList();
                    }
                }

                setState(640);
                match(CloseParen);
                setState(641);
                match(ARROW);
                setState(642);
                type_();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeQueryContext typeQuery() throws RecognitionException {
        TypeQueryContext _localctx = new TypeQueryContext(_ctx, getState());
        enterRule(_localctx, 78, RULE_typeQuery);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(644);
                match(Typeof);
                setState(645);
                typeQueryExpression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeQueryExpressionContext typeQueryExpression() throws RecognitionException {
        TypeQueryExpressionContext _localctx = new TypeQueryExpressionContext(_ctx, getState());
        enterRule(_localctx, 80, RULE_typeQueryExpression);
        try {
            int _alt;
            setState(657);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 46, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(647);
                    match(Identifier);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(651);
                    _errHandler.sync(this);
                    _alt = 1;
                    do {
                        switch (_alt) {
                            case 1: {
                                {
                                    setState(648);
                                    identifierName();
                                    setState(649);
                                    match(Dot);
                                }
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                        setState(653);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 45, _ctx);
                    } while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER);
                    setState(655);
                    identifierName();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PropertySignaturContext propertySignatur() throws RecognitionException {
        PropertySignaturContext _localctx = new PropertySignaturContext(_ctx, getState());
        enterRule(_localctx, 82, RULE_propertySignatur);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(660);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 47, _ctx)) {
                    case 1: {
                        setState(659);
                        match(ReadOnly);
                    }
                    break;
                }
                setState(662);
                propertyName();
                setState(664);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == QuestionMark) {
                    {
                        setState(663);
                        match(QuestionMark);
                    }
                }

                setState(667);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(666);
                        typeAnnotation();
                    }
                }

                setState(671);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == ARROW) {
                    {
                        setState(669);
                        match(ARROW);
                        setState(670);
                        type_();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeAnnotationContext typeAnnotation() throws RecognitionException {
        TypeAnnotationContext _localctx = new TypeAnnotationContext(_ctx, getState());
        enterRule(_localctx, 84, RULE_typeAnnotation);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(673);
                match(Colon);
                setState(674);
                type_();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CallSignatureContext callSignature() throws RecognitionException {
        CallSignatureContext _localctx = new CallSignatureContext(_ctx, getState());
        enterRule(_localctx, 86, RULE_callSignature);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(677);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan) {
                    {
                        setState(676);
                        typeParameters();
                    }
                }

                setState(679);
                match(OpenParen);
                setState(681);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -4503599627370430192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1751495634751324159L) != 0) {
                    {
                        setState(680);
                        parameterList();
                    }
                }

                setState(683);
                match(CloseParen);
                setState(685);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 53, _ctx)) {
                    case 1: {
                        setState(684);
                        typeAnnotation();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ParameterListContext parameterList() throws RecognitionException {
        ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
        enterRule(_localctx, 88, RULE_parameterList);
        int _la;
        try {
            int _alt;
            setState(700);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case Ellipsis:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(687);
                    restParameter();
                }
                break;
                case OpenBracket:
                case OpenBrace:
                case NullLiteral:
                case BooleanLiteral:
                case Break:
                case Do:
                case Instanceof:
                case Typeof:
                case Case:
                case Else:
                case New:
                case Var:
                case Catch:
                case Finally:
                case Return:
                case Void:
                case Continue:
                case For:
                case Switch:
                case While:
                case Debugger:
                case Function_:
                case This:
                case With:
                case Default:
                case If:
                case Throw:
                case Delete:
                case In:
                case Try:
                case From:
                case ReadOnly:
                case Async:
                case Class:
                case Enum:
                case Extends:
                case Super:
                case Const:
                case Export:
                case Import:
                case Implements:
                case Let:
                case Private:
                case Public:
                case Interface:
                case Package:
                case Protected:
                case Static:
                case Yield:
                case String:
                case TypeAlias:
                case Get:
                case Set:
                case Require:
                case At:
                case Identifier:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(688);
                    parameter();
                    setState(693);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 54, _ctx);
                    while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(689);
                                    match(Comma);
                                    setState(690);
                                    parameter();
                                }
                            }
                        }
                        setState(695);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 54, _ctx);
                    }
                    setState(698);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == Comma) {
                        {
                            setState(696);
                            match(Comma);
                            setState(697);
                            restParameter();
                        }
                    }

                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final RequiredParameterListContext requiredParameterList() throws RecognitionException {
        RequiredParameterListContext _localctx = new RequiredParameterListContext(_ctx, getState());
        enterRule(_localctx, 90, RULE_requiredParameterList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(702);
                requiredParameter();
                setState(707);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Comma) {
                    {
                        {
                            setState(703);
                            match(Comma);
                            setState(704);
                            requiredParameter();
                        }
                    }
                    setState(709);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ParameterContext parameter() throws RecognitionException {
        ParameterContext _localctx = new ParameterContext(_ctx, getState());
        enterRule(_localctx, 92, RULE_parameter);
        try {
            setState(712);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 58, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(710);
                    requiredParameter();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(711);
                    optionalParameter();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final OptionalParameterContext optionalParameter() throws RecognitionException {
        OptionalParameterContext _localctx = new OptionalParameterContext(_ctx, getState());
        enterRule(_localctx, 94, RULE_optionalParameter);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(715);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == At) {
                    {
                        setState(714);
                        decoratorList();
                    }
                }

                {
                    setState(718);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 60, _ctx)) {
                        case 1: {
                            setState(717);
                            accessibilityModifier();
                        }
                        break;
                    }
                    setState(720);
                    identifierOrPattern();
                    setState(729);
                    _errHandler.sync(this);
                    switch (_input.LA(1)) {
                        case QuestionMark: {
                            setState(721);
                            match(QuestionMark);
                            setState(723);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la == Colon) {
                                {
                                    setState(722);
                                    typeAnnotation();
                                }
                            }

                        }
                        break;
                        case Assign:
                        case Colon: {
                            setState(726);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            if (_la == Colon) {
                                {
                                    setState(725);
                                    typeAnnotation();
                                }
                            }

                            setState(728);
                            initializer();
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final RestParameterContext restParameter() throws RecognitionException {
        RestParameterContext _localctx = new RestParameterContext(_ctx, getState());
        enterRule(_localctx, 96, RULE_restParameter);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(731);
                match(Ellipsis);
                setState(732);
                singleExpression(0);
                setState(734);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(733);
                        typeAnnotation();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final RequiredParameterContext requiredParameter() throws RecognitionException {
        RequiredParameterContext _localctx = new RequiredParameterContext(_ctx, getState());
        enterRule(_localctx, 98, RULE_requiredParameter);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(737);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == At) {
                    {
                        setState(736);
                        decoratorList();
                    }
                }

                setState(740);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 66, _ctx)) {
                    case 1: {
                        setState(739);
                        accessibilityModifier();
                    }
                    break;
                }
                setState(742);
                identifierOrPattern();
                setState(744);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(743);
                        typeAnnotation();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AccessibilityModifierContext accessibilityModifier() throws RecognitionException {
        AccessibilityModifierContext _localctx = new AccessibilityModifierContext(_ctx, getState());
        enterRule(_localctx, 100, RULE_accessibilityModifier);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(746);
                _la = _input.LA(1);
                if (!((((_la - 101)) & ~0x3f) == 0 && ((1L << (_la - 101)) & 19L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IdentifierOrPatternContext identifierOrPattern() throws RecognitionException {
        IdentifierOrPatternContext _localctx = new IdentifierOrPatternContext(_ctx, getState());
        enterRule(_localctx, 102, RULE_identifierOrPattern);
        try {
            setState(750);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case NullLiteral:
                case BooleanLiteral:
                case Break:
                case Do:
                case Instanceof:
                case Typeof:
                case Case:
                case Else:
                case New:
                case Var:
                case Catch:
                case Finally:
                case Return:
                case Void:
                case Continue:
                case For:
                case Switch:
                case While:
                case Debugger:
                case Function_:
                case This:
                case With:
                case Default:
                case If:
                case Throw:
                case Delete:
                case In:
                case Try:
                case From:
                case ReadOnly:
                case Async:
                case Class:
                case Enum:
                case Extends:
                case Super:
                case Const:
                case Export:
                case Import:
                case Implements:
                case Let:
                case Private:
                case Public:
                case Interface:
                case Package:
                case Protected:
                case Static:
                case Yield:
                case String:
                case TypeAlias:
                case Get:
                case Set:
                case Require:
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(748);
                    identifierName();
                }
                break;
                case OpenBracket:
                case OpenBrace:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(749);
                    bindingPattern();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ConstructSignatureContext constructSignature() throws RecognitionException {
        ConstructSignatureContext _localctx = new ConstructSignatureContext(_ctx, getState());
        enterRule(_localctx, 104, RULE_constructSignature);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(752);
                match(New);
                setState(754);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan) {
                    {
                        setState(753);
                        typeParameters();
                    }
                }

                setState(756);
                match(OpenParen);
                setState(758);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -4503599627370430192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1751495634751324159L) != 0) {
                    {
                        setState(757);
                        parameterList();
                    }
                }

                setState(760);
                match(CloseParen);
                setState(762);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(761);
                        typeAnnotation();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IndexSignatureContext indexSignature() throws RecognitionException {
        IndexSignatureContext _localctx = new IndexSignatureContext(_ctx, getState());
        enterRule(_localctx, 106, RULE_indexSignature);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(764);
                match(OpenBracket);
                setState(765);
                match(Identifier);
                setState(766);
                match(Colon);
                setState(767);
                _la = _input.LA(1);
                if (!(_la == Number || _la == String)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(768);
                match(CloseBracket);
                setState(769);
                typeAnnotation();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final MethodSignatureContext methodSignature() throws RecognitionException {
        MethodSignatureContext _localctx = new MethodSignatureContext(_ctx, getState());
        enterRule(_localctx, 108, RULE_methodSignature);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(771);
                propertyName();
                setState(773);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == QuestionMark) {
                    {
                        setState(772);
                        match(QuestionMark);
                    }
                }

                setState(775);
                callSignature();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TypeAliasDeclarationContext typeAliasDeclaration() throws RecognitionException {
        TypeAliasDeclarationContext _localctx = new TypeAliasDeclarationContext(_ctx, getState());
        enterRule(_localctx, 110, RULE_typeAliasDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(777);
                match(TypeAlias);
                setState(778);
                match(Identifier);
                setState(780);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan) {
                    {
                        setState(779);
                        typeParameters();
                    }
                }

                setState(782);
                match(Assign);
                setState(783);
                type_();
                setState(784);
                match(SemiColon);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
        ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
        enterRule(_localctx, 112, RULE_constructorDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(787);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la - 101)) & ~0x3f) == 0 && ((1L << (_la - 101)) & 19L) != 0) {
                    {
                        setState(786);
                        accessibilityModifier();
                    }
                }

                setState(789);
                match(Constructor);
                setState(790);
                match(OpenParen);
                setState(792);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & 65808L) != 0 || (((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & 104186667009L) != 0) {
                    {
                        setState(791);
                        formalParameterList();
                    }
                }

                setState(794);
                match(CloseParen);
                setState(800);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 76, _ctx)) {
                    case 1: {
                        {
                            setState(795);
                            match(OpenBrace);
                            setState(796);
                            functionBody();
                            setState(797);
                            match(CloseBrace);
                        }
                    }
                    break;
                    case 2: {
                        setState(799);
                        match(SemiColon);
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
        InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
        enterRule(_localctx, 114, RULE_interfaceDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(803);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Export) {
                    {
                        setState(802);
                        match(Export);
                    }
                }

                setState(806);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Declare) {
                    {
                        setState(805);
                        match(Declare);
                    }
                }

                setState(808);
                match(Interface);
                setState(809);
                match(Identifier);
                setState(811);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan) {
                    {
                        setState(810);
                        typeParameters();
                    }
                }

                setState(814);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Extends) {
                    {
                        setState(813);
                        interfaceExtendsClause();
                    }
                }

                setState(816);
                objectType();
                setState(818);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 81, _ctx)) {
                    case 1: {
                        setState(817);
                        match(SemiColon);
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final InterfaceExtendsClauseContext interfaceExtendsClause() throws RecognitionException {
        InterfaceExtendsClauseContext _localctx = new InterfaceExtendsClauseContext(_ctx, getState());
        enterRule(_localctx, 116, RULE_interfaceExtendsClause);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(820);
                match(Extends);
                setState(821);
                classOrInterfaceTypeList();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ClassOrInterfaceTypeListContext classOrInterfaceTypeList() throws RecognitionException {
        ClassOrInterfaceTypeListContext _localctx = new ClassOrInterfaceTypeListContext(_ctx, getState());
        enterRule(_localctx, 118, RULE_classOrInterfaceTypeList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(823);
                typeReference();
                setState(828);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Comma) {
                    {
                        {
                            setState(824);
                            match(Comma);
                            setState(825);
                            typeReference();
                        }
                    }
                    setState(830);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
        EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
        enterRule(_localctx, 120, RULE_enumDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(832);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Const) {
                    {
                        setState(831);
                        match(Const);
                    }
                }

                setState(834);
                match(Enum);
                setState(835);
                match(Identifier);
                setState(836);
                match(OpenBrace);
                setState(838);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028797018963968L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 3480877891661594623L) != 0) {
                    {
                        setState(837);
                        enumBody();
                    }
                }

                setState(840);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final EnumBodyContext enumBody() throws RecognitionException {
        EnumBodyContext _localctx = new EnumBodyContext(_ctx, getState());
        enterRule(_localctx, 122, RULE_enumBody);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(842);
                enumMemberList();
                setState(844);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Comma) {
                    {
                        setState(843);
                        match(Comma);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final EnumMemberListContext enumMemberList() throws RecognitionException {
        EnumMemberListContext _localctx = new EnumMemberListContext(_ctx, getState());
        enterRule(_localctx, 124, RULE_enumMemberList);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(846);
                enumMember();
                setState(851);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 86, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(847);
                                match(Comma);
                                setState(848);
                                enumMember();
                            }
                        }
                    }
                    setState(853);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 86, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final EnumMemberContext enumMember() throws RecognitionException {
        EnumMemberContext _localctx = new EnumMemberContext(_ctx, getState());
        enterRule(_localctx, 126, RULE_enumMember);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(854);
                propertyName();
                setState(857);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Assign) {
                    {
                        setState(855);
                        match(Assign);
                        setState(856);
                        singleExpression(0);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final NamespaceDeclarationContext namespaceDeclaration() throws RecognitionException {
        NamespaceDeclarationContext _localctx = new NamespaceDeclarationContext(_ctx, getState());
        enterRule(_localctx, 128, RULE_namespaceDeclaration);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(859);
                match(Namespace);
                setState(860);
                namespaceName();
                setState(861);
                match(OpenBrace);
                setState(863);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 88, _ctx)) {
                    case 1: {
                        setState(862);
                        statementList();
                    }
                    break;
                }
                setState(865);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final NamespaceNameContext namespaceName() throws RecognitionException {
        NamespaceNameContext _localctx = new NamespaceNameContext(_ctx, getState());
        enterRule(_localctx, 130, RULE_namespaceName);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(867);
                match(Identifier);
                setState(876);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 90, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(869);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                do {
                                    {
                                        {
                                            setState(868);
                                            match(Dot);
                                        }
                                    }
                                    setState(871);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                } while (_la == Dot);
                                setState(873);
                                match(Identifier);
                            }
                        }
                    }
                    setState(878);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 90, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ImportAliasDeclarationContext importAliasDeclaration() throws RecognitionException {
        ImportAliasDeclarationContext _localctx = new ImportAliasDeclarationContext(_ctx, getState());
        enterRule(_localctx, 132, RULE_importAliasDeclaration);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(879);
                match(Identifier);
                setState(880);
                match(Assign);
                setState(881);
                namespaceName();
                setState(882);
                match(SemiColon);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DecoratorListContext decoratorList() throws RecognitionException {
        DecoratorListContext _localctx = new DecoratorListContext(_ctx, getState());
        enterRule(_localctx, 134, RULE_decoratorList);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(885);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(884);
                                decorator();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(887);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 91, _ctx);
                } while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DecoratorContext decorator() throws RecognitionException {
        DecoratorContext _localctx = new DecoratorContext(_ctx, getState());
        enterRule(_localctx, 136, RULE_decorator);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(889);
                match(At);
                setState(892);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 92, _ctx)) {
                    case 1: {
                        setState(890);
                        decoratorMemberExpression(0);
                    }
                    break;
                    case 2: {
                        setState(891);
                        decoratorCallExpression();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DecoratorMemberExpressionContext decoratorMemberExpression() throws RecognitionException {
        return decoratorMemberExpression(0);
    }

    private DecoratorMemberExpressionContext decoratorMemberExpression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        DecoratorMemberExpressionContext _localctx = new DecoratorMemberExpressionContext(_ctx, _parentState);
        DecoratorMemberExpressionContext _prevctx = _localctx;
        int _startState = 138;
        enterRecursionRule(_localctx, 138, RULE_decoratorMemberExpression, _p);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(900);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case Identifier: {
                        setState(895);
                        match(Identifier);
                    }
                    break;
                    case OpenParen: {
                        setState(896);
                        match(OpenParen);
                        setState(897);
                        singleExpression(0);
                        setState(898);
                        match(CloseParen);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                _ctx.stop = _input.LT(-1);
                setState(907);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 94, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            {
                                _localctx = new DecoratorMemberExpressionContext(_parentctx, _parentState);
                                pushNewRecursionContext(_localctx, _startState, RULE_decoratorMemberExpression);
                                setState(902);
                                if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                setState(903);
                                match(Dot);
                                setState(904);
                                identifierName();
                            }
                        }
                    }
                    setState(909);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 94, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public final DecoratorCallExpressionContext decoratorCallExpression() throws RecognitionException {
        DecoratorCallExpressionContext _localctx = new DecoratorCallExpressionContext(_ctx, getState());
        enterRule(_localctx, 140, RULE_decoratorCallExpression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(910);
                decoratorMemberExpression(0);
                setState(911);
                arguments();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ProgramContext program() throws RecognitionException {
        ProgramContext _localctx = new ProgramContext(_ctx, getState());
        enterRule(_localctx, 142, RULE_program);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(914);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 95, _ctx)) {
                    case 1: {
                        setState(913);
                        sourceElements();
                    }
                    break;
                }
                setState(916);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SourceElementContext sourceElement() throws RecognitionException {
        SourceElementContext _localctx = new SourceElementContext(_ctx, getState());
        enterRule(_localctx, 144, RULE_sourceElement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(919);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 96, _ctx)) {
                    case 1: {
                        setState(918);
                        match(Export);
                    }
                    break;
                }
                setState(921);
                statement();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StatementContext statement() throws RecognitionException {
        StatementContext _localctx = new StatementContext(_ctx, getState());
        enterRule(_localctx, 146, RULE_statement);
        try {
            setState(953);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 97, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(923);
                    block();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(924);
                    importStatement();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(925);
                    exportStatement();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(926);
                    emptyStatement_();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(927);
                    abstractDeclaration();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(928);
                    decoratorList();
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(929);
                    classDeclaration();
                }
                break;
                case 8:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(930);
                    interfaceDeclaration();
                }
                break;
                case 9:
                    enterOuterAlt(_localctx, 9);
                {
                    setState(931);
                    namespaceDeclaration();
                }
                break;
                case 10:
                    enterOuterAlt(_localctx, 10);
                {
                    setState(932);
                    ifStatement();
                }
                break;
                case 11:
                    enterOuterAlt(_localctx, 11);
                {
                    setState(933);
                    iterationStatement();
                }
                break;
                case 12:
                    enterOuterAlt(_localctx, 12);
                {
                    setState(934);
                    continueStatement();
                }
                break;
                case 13:
                    enterOuterAlt(_localctx, 13);
                {
                    setState(935);
                    breakStatement();
                }
                break;
                case 14:
                    enterOuterAlt(_localctx, 14);
                {
                    setState(936);
                    returnStatement();
                }
                break;
                case 15:
                    enterOuterAlt(_localctx, 15);
                {
                    setState(937);
                    yieldStatement();
                }
                break;
                case 16:
                    enterOuterAlt(_localctx, 16);
                {
                    setState(938);
                    withStatement();
                }
                break;
                case 17:
                    enterOuterAlt(_localctx, 17);
                {
                    setState(939);
                    labelledStatement();
                }
                break;
                case 18:
                    enterOuterAlt(_localctx, 18);
                {
                    setState(940);
                    switchStatement();
                }
                break;
                case 19:
                    enterOuterAlt(_localctx, 19);
                {
                    setState(941);
                    throwStatement();
                }
                break;
                case 20:
                    enterOuterAlt(_localctx, 20);
                {
                    setState(942);
                    tryStatement();
                }
                break;
                case 21:
                    enterOuterAlt(_localctx, 21);
                {
                    setState(943);
                    debuggerStatement();
                }
                break;
                case 22:
                    enterOuterAlt(_localctx, 22);
                {
                    setState(944);
                    functionDeclaration();
                }
                break;
                case 23:
                    enterOuterAlt(_localctx, 23);
                {
                    setState(945);
                    arrowFunctionDeclaration();
                }
                break;
                case 24:
                    enterOuterAlt(_localctx, 24);
                {
                    setState(946);
                    generatorFunctionDeclaration();
                }
                break;
                case 25:
                    enterOuterAlt(_localctx, 25);
                {
                    setState(947);
                    variableStatement();
                }
                break;
                case 26:
                    enterOuterAlt(_localctx, 26);
                {
                    setState(948);
                    typeAliasDeclaration();
                }
                break;
                case 27:
                    enterOuterAlt(_localctx, 27);
                {
                    setState(949);
                    enumDeclaration();
                }
                break;
                case 28:
                    enterOuterAlt(_localctx, 28);
                {
                    setState(950);
                    expressionStatement();
                }
                break;
                case 29:
                    enterOuterAlt(_localctx, 29);
                {
                    setState(951);
                    match(Export);
                    setState(952);
                    statement();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BlockContext block() throws RecognitionException {
        BlockContext _localctx = new BlockContext(_ctx, getState());
        enterRule(_localctx, 148, RULE_block);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(955);
                match(OpenBrace);
                setState(957);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 98, _ctx)) {
                    case 1: {
                        setState(956);
                        statementList();
                    }
                    break;
                }
                setState(959);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StatementListContext statementList() throws RecognitionException {
        StatementListContext _localctx = new StatementListContext(_ctx, getState());
        enterRule(_localctx, 150, RULE_statementList);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(962);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(961);
                                statement();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(964);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 99, _ctx);
                } while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AbstractDeclarationContext abstractDeclaration() throws RecognitionException {
        AbstractDeclarationContext _localctx = new AbstractDeclarationContext(_ctx, getState());
        enterRule(_localctx, 152, RULE_abstractDeclaration);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(966);
                match(Abstract);
                setState(970);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 100, _ctx)) {
                    case 1: {
                        setState(967);
                        match(Identifier);
                        setState(968);
                        callSignature();
                    }
                    break;
                    case 2: {
                        setState(969);
                        variableStatement();
                    }
                    break;
                }
                setState(972);
                eos();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ImportStatementContext importStatement() throws RecognitionException {
        ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
        enterRule(_localctx, 154, RULE_importStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(974);
                match(Import);
                setState(977);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 101, _ctx)) {
                    case 1: {
                        setState(975);
                        fromBlock();
                    }
                    break;
                    case 2: {
                        setState(976);
                        importAliasDeclaration();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FromBlockContext fromBlock() throws RecognitionException {
        FromBlockContext _localctx = new FromBlockContext(_ctx, getState());
        enterRule(_localctx, 156, RULE_fromBlock);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(982);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 102, _ctx)) {
                    case 1: {
                        setState(979);
                        match(Multiply);
                    }
                    break;
                    case 2: {
                        setState(980);
                        multipleImportStatement();
                    }
                    break;
                    case 3: {
                        setState(981);
                        identifierName();
                    }
                    break;
                }
                setState(986);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == As) {
                    {
                        setState(984);
                        match(As);
                        setState(985);
                        identifierName();
                    }
                }

                setState(988);
                match(From);
                setState(989);
                match(StringLiteral);
                setState(990);
                eos();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final MultipleImportStatementContext multipleImportStatement() throws RecognitionException {
        MultipleImportStatementContext _localctx = new MultipleImportStatementContext(_ctx, getState());
        enterRule(_localctx, 158, RULE_multipleImportStatement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(995);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -4503599627370496000L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 1175034882447900671L) != 0) {
                    {
                        setState(992);
                        identifierName();
                        setState(993);
                        match(Comma);
                    }
                }

                setState(997);
                match(OpenBrace);
                setState(998);
                identifierName();
                setState(1003);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Comma) {
                    {
                        {
                            setState(999);
                            match(Comma);
                            setState(1000);
                            identifierName();
                        }
                    }
                    setState(1005);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(1006);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ExportStatementContext exportStatement() throws RecognitionException {
        ExportStatementContext _localctx = new ExportStatementContext(_ctx, getState());
        enterRule(_localctx, 160, RULE_exportStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1008);
                match(Export);
                setState(1010);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 106, _ctx)) {
                    case 1: {
                        setState(1009);
                        match(Default);
                    }
                    break;
                }
                setState(1014);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 107, _ctx)) {
                    case 1: {
                        setState(1012);
                        fromBlock();
                    }
                    break;
                    case 2: {
                        setState(1013);
                        statement();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final VariableStatementContext variableStatement() throws RecognitionException {
        VariableStatementContext _localctx = new VariableStatementContext(_ctx, getState());
        enterRule(_localctx, 162, RULE_variableStatement);
        int _la;
        try {
            setState(1045);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 116, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1016);
                    bindingPattern();
                    setState(1018);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == Colon) {
                        {
                            setState(1017);
                            typeAnnotation();
                        }
                    }

                    setState(1020);
                    initializer();
                    setState(1022);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 109, _ctx)) {
                        case 1: {
                            setState(1021);
                            match(SemiColon);
                        }
                        break;
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1025);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la - 101)) & ~0x3f) == 0 && ((1L << (_la - 101)) & 19L) != 0) {
                        {
                            setState(1024);
                            accessibilityModifier();
                        }
                    }

                    setState(1028);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 2281701377L) != 0) {
                        {
                            setState(1027);
                            varModifier();
                        }
                    }

                    setState(1031);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == ReadOnly) {
                        {
                            setState(1030);
                            match(ReadOnly);
                        }
                    }

                    setState(1033);
                    variableDeclarationList();
                    setState(1035);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 113, _ctx)) {
                        case 1: {
                            setState(1034);
                            match(SemiColon);
                        }
                        break;
                    }
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1037);
                    match(Declare);
                    setState(1039);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 2281701377L) != 0) {
                        {
                            setState(1038);
                            varModifier();
                        }
                    }

                    setState(1041);
                    variableDeclarationList();
                    setState(1043);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 115, _ctx)) {
                        case 1: {
                            setState(1042);
                            match(SemiColon);
                        }
                        break;
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final VariableDeclarationListContext variableDeclarationList() throws RecognitionException {
        VariableDeclarationListContext _localctx = new VariableDeclarationListContext(_ctx, getState());
        enterRule(_localctx, 164, RULE_variableDeclarationList);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1047);
                variableDeclaration();
                setState(1052);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 117, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(1048);
                                match(Comma);
                                setState(1049);
                                variableDeclaration();
                            }
                        }
                    }
                    setState(1054);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 117, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
        VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
        enterRule(_localctx, 166, RULE_variableDeclaration);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1058);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case As:
                    case TypeAlias:
                    case Require:
                    case Identifier: {
                        setState(1055);
                        identifierOrKeyWord();
                    }
                    break;
                    case OpenBracket: {
                        setState(1056);
                        arrayLiteral();
                    }
                    break;
                    case OpenBrace: {
                        setState(1057);
                        objectLiteral();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                setState(1061);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 119, _ctx)) {
                    case 1: {
                        setState(1060);
                        typeAnnotation();
                    }
                    break;
                }
                setState(1064);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 120, _ctx)) {
                    case 1: {
                        setState(1063);
                        singleExpression(0);
                    }
                    break;
                }
                setState(1071);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 122, _ctx)) {
                    case 1: {
                        setState(1066);
                        match(Assign);
                        setState(1068);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 121, _ctx)) {
                            case 1: {
                                setState(1067);
                                typeParameters();
                            }
                            break;
                        }
                        setState(1070);
                        singleExpression(0);
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final EmptyStatement_Context emptyStatement_() throws RecognitionException {
        EmptyStatement_Context _localctx = new EmptyStatement_Context(_ctx, getState());
        enterRule(_localctx, 168, RULE_emptyStatement_);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1073);
                match(SemiColon);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ExpressionStatementContext expressionStatement() throws RecognitionException {
        ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
        enterRule(_localctx, 170, RULE_expressionStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1075);
                if (!(this.notOpenBraceAndNotFunction()))
                    throw new FailedPredicateException(this, "this.notOpenBraceAndNotFunction()");
                setState(1076);
                expressionSequence();
                setState(1078);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 123, _ctx)) {
                    case 1: {
                        setState(1077);
                        match(SemiColon);
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IfStatementContext ifStatement() throws RecognitionException {
        IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
        enterRule(_localctx, 172, RULE_ifStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1080);
                match(If);
                setState(1081);
                match(OpenParen);
                setState(1082);
                expressionSequence();
                setState(1083);
                match(CloseParen);
                setState(1084);
                statement();
                setState(1087);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 124, _ctx)) {
                    case 1: {
                        setState(1085);
                        match(Else);
                        setState(1086);
                        statement();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IterationStatementContext iterationStatement() throws RecognitionException {
        IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
        enterRule(_localctx, 174, RULE_iterationStatement);
        int _la;
        try {
            setState(1158);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 132, _ctx)) {
                case 1:
                    _localctx = new DoStatementContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1089);
                    match(Do);
                    setState(1090);
                    statement();
                    setState(1091);
                    match(While);
                    setState(1092);
                    match(OpenParen);
                    setState(1093);
                    expressionSequence();
                    setState(1094);
                    match(CloseParen);
                    setState(1095);
                    eos();
                }
                break;
                case 2:
                    _localctx = new WhileStatementContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1097);
                    match(While);
                    setState(1098);
                    match(OpenParen);
                    setState(1099);
                    expressionSequence();
                    setState(1100);
                    match(CloseParen);
                    setState(1101);
                    statement();
                }
                break;
                case 3:
                    _localctx = new ForStatementContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1103);
                    match(For);
                    setState(1104);
                    match(OpenParen);
                    setState(1106);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795928641192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8092563910088982527L) != 0) {
                        {
                            setState(1105);
                            expressionSequence();
                        }
                    }

                    setState(1108);
                    match(SemiColon);
                    setState(1110);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795928641192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8092563910088982527L) != 0) {
                        {
                            setState(1109);
                            expressionSequence();
                        }
                    }

                    setState(1112);
                    match(SemiColon);
                    setState(1114);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795928641192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8092563910088982527L) != 0) {
                        {
                            setState(1113);
                            expressionSequence();
                        }
                    }

                    setState(1116);
                    match(CloseParen);
                    setState(1117);
                    statement();
                }
                break;
                case 4:
                    _localctx = new ForVarStatementContext(_localctx);
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1118);
                    match(For);
                    setState(1119);
                    match(OpenParen);
                    setState(1120);
                    varModifier();
                    setState(1121);
                    variableDeclarationList();
                    setState(1122);
                    match(SemiColon);
                    setState(1124);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795928641192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8092563910088982527L) != 0) {
                        {
                            setState(1123);
                            expressionSequence();
                        }
                    }

                    setState(1126);
                    match(SemiColon);
                    setState(1128);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795928641192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8092563910088982527L) != 0) {
                        {
                            setState(1127);
                            expressionSequence();
                        }
                    }

                    setState(1130);
                    match(CloseParen);
                    setState(1131);
                    statement();
                }
                break;
                case 5:
                    _localctx = new ForInStatementContext(_localctx);
                    enterOuterAlt(_localctx, 5);
                {
                    setState(1133);
                    match(For);
                    setState(1134);
                    match(OpenParen);
                    setState(1135);
                    singleExpression(0);
                    setState(1139);
                    _errHandler.sync(this);
                    switch (_input.LA(1)) {
                        case In: {
                            setState(1136);
                            match(In);
                        }
                        break;
                        case Identifier: {
                            setState(1137);
                            match(Identifier);
                            setState(1138);
                            if (!(this.p("of"))) throw new FailedPredicateException(this, "this.p(\"of\")");
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(1141);
                    expressionSequence();
                    setState(1142);
                    match(CloseParen);
                    setState(1143);
                    statement();
                }
                break;
                case 6:
                    _localctx = new ForVarInStatementContext(_localctx);
                    enterOuterAlt(_localctx, 6);
                {
                    setState(1145);
                    match(For);
                    setState(1146);
                    match(OpenParen);
                    setState(1147);
                    varModifier();
                    setState(1148);
                    variableDeclaration();
                    setState(1152);
                    _errHandler.sync(this);
                    switch (_input.LA(1)) {
                        case In: {
                            setState(1149);
                            match(In);
                        }
                        break;
                        case Identifier: {
                            setState(1150);
                            match(Identifier);
                            setState(1151);
                            if (!(this.p("of"))) throw new FailedPredicateException(this, "this.p(\"of\")");
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(1154);
                    expressionSequence();
                    setState(1155);
                    match(CloseParen);
                    setState(1156);
                    statement();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final VarModifierContext varModifier() throws RecognitionException {
        VarModifierContext _localctx = new VarModifierContext(_ctx, getState());
        enterRule(_localctx, 176, RULE_varModifier);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1160);
                _la = _input.LA(1);
                if (!((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 2281701377L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ContinueStatementContext continueStatement() throws RecognitionException {
        ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
        enterRule(_localctx, 178, RULE_continueStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1162);
                match(Continue);
                setState(1165);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 133, _ctx)) {
                    case 1: {
                        setState(1163);
                        if (!(this.notLineTerminator()))
                            throw new FailedPredicateException(this, "this.notLineTerminator()");
                        setState(1164);
                        match(Identifier);
                    }
                    break;
                }
                setState(1167);
                eos();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BreakStatementContext breakStatement() throws RecognitionException {
        BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
        enterRule(_localctx, 180, RULE_breakStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1169);
                match(Break);
                setState(1172);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 134, _ctx)) {
                    case 1: {
                        setState(1170);
                        if (!(this.notLineTerminator()))
                            throw new FailedPredicateException(this, "this.notLineTerminator()");
                        setState(1171);
                        match(Identifier);
                    }
                    break;
                }
                setState(1174);
                eos();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ReturnStatementContext returnStatement() throws RecognitionException {
        ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
        enterRule(_localctx, 182, RULE_returnStatement);
        try {
            setState(1188);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 136, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1176);
                    match(Return);
                    setState(1179);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 135, _ctx)) {
                        case 1: {
                            setState(1177);
                            if (!(this.notLineTerminator()))
                                throw new FailedPredicateException(this, "this.notLineTerminator()");
                            setState(1178);
                            expressionSequence();
                        }
                        break;
                    }
                    setState(1181);
                    eos();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1182);
                    match(Return);
                    setState(1183);
                    match(OpenParen);
                    setState(1184);
                    htmlElements();
                    setState(1185);
                    match(CloseParen);
                    setState(1186);
                    eos();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final YieldStatementContext yieldStatement() throws RecognitionException {
        YieldStatementContext _localctx = new YieldStatementContext(_ctx, getState());
        enterRule(_localctx, 184, RULE_yieldStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1190);
                match(Yield);
                setState(1193);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 137, _ctx)) {
                    case 1: {
                        setState(1191);
                        if (!(this.notLineTerminator()))
                            throw new FailedPredicateException(this, "this.notLineTerminator()");
                        setState(1192);
                        expressionSequence();
                    }
                    break;
                }
                setState(1195);
                eos();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final WithStatementContext withStatement() throws RecognitionException {
        WithStatementContext _localctx = new WithStatementContext(_ctx, getState());
        enterRule(_localctx, 186, RULE_withStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1197);
                match(With);
                setState(1198);
                match(OpenParen);
                setState(1199);
                expressionSequence();
                setState(1200);
                match(CloseParen);
                setState(1201);
                statement();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SwitchStatementContext switchStatement() throws RecognitionException {
        SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
        enterRule(_localctx, 188, RULE_switchStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1203);
                match(Switch);
                setState(1204);
                match(OpenParen);
                setState(1205);
                expressionSequence();
                setState(1206);
                match(CloseParen);
                setState(1207);
                caseBlock();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CaseBlockContext caseBlock() throws RecognitionException {
        CaseBlockContext _localctx = new CaseBlockContext(_ctx, getState());
        enterRule(_localctx, 190, RULE_caseBlock);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1209);
                match(OpenBrace);
                setState(1211);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Case) {
                    {
                        setState(1210);
                        caseClauses();
                    }
                }

                setState(1217);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Default) {
                    {
                        setState(1213);
                        defaultClause();
                        setState(1215);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la == Case) {
                            {
                                setState(1214);
                                caseClauses();
                            }
                        }

                    }
                }

                setState(1219);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CaseClausesContext caseClauses() throws RecognitionException {
        CaseClausesContext _localctx = new CaseClausesContext(_ctx, getState());
        enterRule(_localctx, 192, RULE_caseClauses);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1222);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(1221);
                            caseClause();
                        }
                    }
                    setState(1224);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == Case);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CaseClauseContext caseClause() throws RecognitionException {
        CaseClauseContext _localctx = new CaseClauseContext(_ctx, getState());
        enterRule(_localctx, 194, RULE_caseClause);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1226);
                match(Case);
                setState(1227);
                expressionSequence();
                setState(1228);
                match(Colon);
                setState(1230);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 142, _ctx)) {
                    case 1: {
                        setState(1229);
                        statementList();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DefaultClauseContext defaultClause() throws RecognitionException {
        DefaultClauseContext _localctx = new DefaultClauseContext(_ctx, getState());
        enterRule(_localctx, 196, RULE_defaultClause);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1232);
                match(Default);
                setState(1233);
                match(Colon);
                setState(1235);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 143, _ctx)) {
                    case 1: {
                        setState(1234);
                        statementList();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final LabelledStatementContext labelledStatement() throws RecognitionException {
        LabelledStatementContext _localctx = new LabelledStatementContext(_ctx, getState());
        enterRule(_localctx, 198, RULE_labelledStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1237);
                match(Identifier);
                setState(1238);
                match(Colon);
                setState(1239);
                statement();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ThrowStatementContext throwStatement() throws RecognitionException {
        ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
        enterRule(_localctx, 200, RULE_throwStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1241);
                match(Throw);
                setState(1242);
                if (!(this.notLineTerminator())) throw new FailedPredicateException(this, "this.notLineTerminator()");
                setState(1243);
                expressionSequence();
                setState(1244);
                eos();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TryStatementContext tryStatement() throws RecognitionException {
        TryStatementContext _localctx = new TryStatementContext(_ctx, getState());
        enterRule(_localctx, 202, RULE_tryStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1246);
                match(Try);
                setState(1247);
                block();
                setState(1253);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case Catch: {
                        setState(1248);
                        catchProduction();
                        setState(1250);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 144, _ctx)) {
                            case 1: {
                                setState(1249);
                                finallyProduction();
                            }
                            break;
                        }
                    }
                    break;
                    case Finally: {
                        setState(1252);
                        finallyProduction();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CatchProductionContext catchProduction() throws RecognitionException {
        CatchProductionContext _localctx = new CatchProductionContext(_ctx, getState());
        enterRule(_localctx, 204, RULE_catchProduction);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1255);
                match(Catch);
                setState(1256);
                match(OpenParen);
                setState(1257);
                match(Identifier);
                setState(1258);
                match(CloseParen);
                setState(1259);
                block();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FinallyProductionContext finallyProduction() throws RecognitionException {
        FinallyProductionContext _localctx = new FinallyProductionContext(_ctx, getState());
        enterRule(_localctx, 206, RULE_finallyProduction);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1261);
                match(Finally);
                setState(1262);
                block();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final DebuggerStatementContext debuggerStatement() throws RecognitionException {
        DebuggerStatementContext _localctx = new DebuggerStatementContext(_ctx, getState());
        enterRule(_localctx, 208, RULE_debuggerStatement);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1264);
                match(Debugger);
                setState(1265);
                eos();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
        FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
        enterRule(_localctx, 210, RULE_functionDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1268);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Async) {
                    {
                        setState(1267);
                        match(Async);
                    }
                }

                setState(1270);
                match(Function_);
                setState(1271);
                match(Identifier);
                setState(1272);
                callSignature();
                setState(1278);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case OpenBrace: {
                        {
                            setState(1273);
                            match(OpenBrace);
                            setState(1274);
                            functionBody();
                            setState(1275);
                            match(CloseBrace);
                        }
                    }
                    break;
                    case SemiColon: {
                        setState(1277);
                        match(SemiColon);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ClassDeclarationContext classDeclaration() throws RecognitionException {
        ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
        enterRule(_localctx, 212, RULE_classDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1281);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Abstract) {
                    {
                        setState(1280);
                        match(Abstract);
                    }
                }

                setState(1283);
                match(Class);
                setState(1284);
                match(Identifier);
                setState(1286);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == LessThan) {
                    {
                        setState(1285);
                        typeParameters();
                    }
                }

                setState(1288);
                classHeritage();
                setState(1289);
                classTail();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ClassHeritageContext classHeritage() throws RecognitionException {
        ClassHeritageContext _localctx = new ClassHeritageContext(_ctx, getState());
        enterRule(_localctx, 214, RULE_classHeritage);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1292);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Extends) {
                    {
                        setState(1291);
                        classExtendsClause();
                    }
                }

                setState(1295);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Implements) {
                    {
                        setState(1294);
                        implementsClause();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ClassTailContext classTail() throws RecognitionException {
        ClassTailContext _localctx = new ClassTailContext(_ctx, getState());
        enterRule(_localctx, 216, RULE_classTail);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1297);
                match(OpenBrace);
                setState(1301);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 152, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(1298);
                                classElement();
                            }
                        }
                    }
                    setState(1303);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 152, _ctx);
                }
                setState(1304);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ClassExtendsClauseContext classExtendsClause() throws RecognitionException {
        ClassExtendsClauseContext _localctx = new ClassExtendsClauseContext(_ctx, getState());
        enterRule(_localctx, 218, RULE_classExtendsClause);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1306);
                match(Extends);
                setState(1307);
                typeReference();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ImplementsClauseContext implementsClause() throws RecognitionException {
        ImplementsClauseContext _localctx = new ImplementsClauseContext(_ctx, getState());
        enterRule(_localctx, 220, RULE_implementsClause);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1309);
                match(Implements);
                setState(1310);
                classOrInterfaceTypeList();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ClassElementContext classElement() throws RecognitionException {
        ClassElementContext _localctx = new ClassElementContext(_ctx, getState());
        enterRule(_localctx, 222, RULE_classElement);
        int _la;
        try {
            setState(1319);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 154, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1312);
                    constructorDeclaration();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1314);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == At) {
                        {
                            setState(1313);
                            decoratorList();
                        }
                    }

                    setState(1316);
                    propertyMemberDeclaration();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1317);
                    indexMemberDeclaration();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1318);
                    statement();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PropertyMemberDeclarationContext propertyMemberDeclaration() throws RecognitionException {
        PropertyMemberDeclarationContext _localctx = new PropertyMemberDeclarationContext(_ctx, getState());
        enterRule(_localctx, 224, RULE_propertyMemberDeclaration);
        int _la;
        try {
            setState(1350);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 160, _ctx)) {
                case 1:
                    _localctx = new PropertyDeclarationExpressionContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1321);
                    propertyMemberBase();
                    setState(1322);
                    propertyName();
                    setState(1324);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == QuestionMark) {
                        {
                            setState(1323);
                            match(QuestionMark);
                        }
                    }

                    setState(1327);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == Colon) {
                        {
                            setState(1326);
                            typeAnnotation();
                        }
                    }

                    setState(1330);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == Assign) {
                        {
                            setState(1329);
                            initializer();
                        }
                    }

                    setState(1332);
                    match(SemiColon);
                }
                break;
                case 2:
                    _localctx = new MethodDeclarationExpressionContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1334);
                    propertyMemberBase();
                    setState(1335);
                    propertyName();
                    setState(1336);
                    callSignature();
                    setState(1342);
                    _errHandler.sync(this);
                    switch (_input.LA(1)) {
                        case OpenBrace: {
                            {
                                setState(1337);
                                match(OpenBrace);
                                setState(1338);
                                functionBody();
                                setState(1339);
                                match(CloseBrace);
                            }
                        }
                        break;
                        case SemiColon: {
                            setState(1341);
                            match(SemiColon);
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                }
                break;
                case 3:
                    _localctx = new GetterSetterDeclarationExpressionContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1344);
                    propertyMemberBase();
                    setState(1347);
                    _errHandler.sync(this);
                    switch (_input.LA(1)) {
                        case Get: {
                            setState(1345);
                            getAccessor();
                        }
                        break;
                        case Set: {
                            setState(1346);
                            setAccessor();
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                }
                break;
                case 4:
                    _localctx = new AbstractMemberDeclarationContext(_localctx);
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1349);
                    abstractDeclaration();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PropertyMemberBaseContext propertyMemberBase() throws RecognitionException {
        PropertyMemberBaseContext _localctx = new PropertyMemberBaseContext(_ctx, getState());
        enterRule(_localctx, 226, RULE_propertyMemberBase);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1353);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 161, _ctx)) {
                    case 1: {
                        setState(1352);
                        match(Async);
                    }
                    break;
                }
                setState(1356);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 162, _ctx)) {
                    case 1: {
                        setState(1355);
                        accessibilityModifier();
                    }
                    break;
                }
                setState(1359);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 163, _ctx)) {
                    case 1: {
                        setState(1358);
                        match(Static);
                    }
                    break;
                }
                setState(1362);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 164, _ctx)) {
                    case 1: {
                        setState(1361);
                        match(ReadOnly);
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IndexMemberDeclarationContext indexMemberDeclaration() throws RecognitionException {
        IndexMemberDeclarationContext _localctx = new IndexMemberDeclarationContext(_ctx, getState());
        enterRule(_localctx, 228, RULE_indexMemberDeclaration);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1364);
                indexSignature();
                setState(1365);
                match(SemiColon);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GeneratorMethodContext generatorMethod() throws RecognitionException {
        GeneratorMethodContext _localctx = new GeneratorMethodContext(_ctx, getState());
        enterRule(_localctx, 230, RULE_generatorMethod);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1368);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Multiply) {
                    {
                        setState(1367);
                        match(Multiply);
                    }
                }

                setState(1370);
                match(Identifier);
                setState(1371);
                match(OpenParen);
                setState(1373);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & 65808L) != 0 || (((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & 104186667009L) != 0) {
                    {
                        setState(1372);
                        formalParameterList();
                    }
                }

                setState(1375);
                match(CloseParen);
                setState(1376);
                match(OpenBrace);
                setState(1377);
                functionBody();
                setState(1378);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GeneratorFunctionDeclarationContext generatorFunctionDeclaration() throws RecognitionException {
        GeneratorFunctionDeclarationContext _localctx = new GeneratorFunctionDeclarationContext(_ctx, getState());
        enterRule(_localctx, 232, RULE_generatorFunctionDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1380);
                match(Function_);
                setState(1381);
                match(Multiply);
                setState(1383);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Identifier) {
                    {
                        setState(1382);
                        match(Identifier);
                    }
                }

                setState(1385);
                match(OpenParen);
                setState(1387);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & 65808L) != 0 || (((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & 104186667009L) != 0) {
                    {
                        setState(1386);
                        formalParameterList();
                    }
                }

                setState(1389);
                match(CloseParen);
                setState(1390);
                match(OpenBrace);
                setState(1391);
                functionBody();
                setState(1392);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GeneratorBlockContext generatorBlock() throws RecognitionException {
        GeneratorBlockContext _localctx = new GeneratorBlockContext(_ctx, getState());
        enterRule(_localctx, 234, RULE_generatorBlock);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1394);
                match(OpenBrace);
                setState(1395);
                generatorDefinition();
                setState(1400);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 169, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(1396);
                                match(Comma);
                                setState(1397);
                                generatorDefinition();
                            }
                        }
                    }
                    setState(1402);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 169, _ctx);
                }
                setState(1404);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Comma) {
                    {
                        setState(1403);
                        match(Comma);
                    }
                }

                setState(1406);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GeneratorDefinitionContext generatorDefinition() throws RecognitionException {
        GeneratorDefinitionContext _localctx = new GeneratorDefinitionContext(_ctx, getState());
        enterRule(_localctx, 236, RULE_generatorDefinition);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1408);
                match(Multiply);
                setState(1409);
                iteratorDefinition();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IteratorBlockContext iteratorBlock() throws RecognitionException {
        IteratorBlockContext _localctx = new IteratorBlockContext(_ctx, getState());
        enterRule(_localctx, 238, RULE_iteratorBlock);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1411);
                match(OpenBrace);
                setState(1412);
                iteratorDefinition();
                setState(1417);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 171, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(1413);
                                match(Comma);
                                setState(1414);
                                iteratorDefinition();
                            }
                        }
                    }
                    setState(1419);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 171, _ctx);
                }
                setState(1421);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Comma) {
                    {
                        setState(1420);
                        match(Comma);
                    }
                }

                setState(1423);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IteratorDefinitionContext iteratorDefinition() throws RecognitionException {
        IteratorDefinitionContext _localctx = new IteratorDefinitionContext(_ctx, getState());
        enterRule(_localctx, 240, RULE_iteratorDefinition);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1425);
                match(OpenBracket);
                setState(1426);
                singleExpression(0);
                setState(1427);
                match(CloseBracket);
                setState(1428);
                match(OpenParen);
                setState(1430);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & 65808L) != 0 || (((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & 104186667009L) != 0) {
                    {
                        setState(1429);
                        formalParameterList();
                    }
                }

                setState(1432);
                match(CloseParen);
                setState(1433);
                match(OpenBrace);
                setState(1434);
                functionBody();
                setState(1435);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FormalParameterListContext formalParameterList() throws RecognitionException {
        FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
        enterRule(_localctx, 242, RULE_formalParameterList);
        int _la;
        try {
            int _alt;
            setState(1456);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case As:
                case Private:
                case Public:
                case Protected:
                case TypeAlias:
                case Require:
                case At:
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1437);
                    formalParameterArg();
                    setState(1442);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 174, _ctx);
                    while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(1438);
                                    match(Comma);
                                    setState(1439);
                                    formalParameterArg();
                                }
                            }
                        }
                        setState(1444);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 174, _ctx);
                    }
                    setState(1447);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == Comma) {
                        {
                            setState(1445);
                            match(Comma);
                            setState(1446);
                            lastFormalParameterArg();
                        }
                    }

                }
                break;
                case Ellipsis:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1449);
                    lastFormalParameterArg();
                }
                break;
                case OpenBracket:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1450);
                    arrayLiteral();
                }
                break;
                case OpenBrace:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1451);
                    objectLiteral();
                    setState(1454);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == Colon) {
                        {
                            setState(1452);
                            match(Colon);
                            setState(1453);
                            formalParameterList();
                        }
                    }

                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FormalParameterArgContext formalParameterArg() throws RecognitionException {
        FormalParameterArgContext _localctx = new FormalParameterArgContext(_ctx, getState());
        enterRule(_localctx, 244, RULE_formalParameterArg);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1459);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == At) {
                    {
                        setState(1458);
                        decorator();
                    }
                }

                setState(1462);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la - 101)) & ~0x3f) == 0 && ((1L << (_la - 101)) & 19L) != 0) {
                    {
                        setState(1461);
                        accessibilityModifier();
                    }
                }

                setState(1464);
                identifierOrKeyWord();
                setState(1466);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == QuestionMark) {
                    {
                        setState(1465);
                        match(QuestionMark);
                    }
                }

                setState(1469);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(1468);
                        typeAnnotation();
                    }
                }

                setState(1473);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Assign) {
                    {
                        setState(1471);
                        match(Assign);
                        setState(1472);
                        singleExpression(0);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final LastFormalParameterArgContext lastFormalParameterArg() throws RecognitionException {
        LastFormalParameterArgContext _localctx = new LastFormalParameterArgContext(_ctx, getState());
        enterRule(_localctx, 246, RULE_lastFormalParameterArg);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1475);
                match(Ellipsis);
                setState(1476);
                match(Identifier);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FunctionBodyContext functionBody() throws RecognitionException {
        FunctionBodyContext _localctx = new FunctionBodyContext(_ctx, getState());
        enterRule(_localctx, 248, RULE_functionBody);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1479);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 183, _ctx)) {
                    case 1: {
                        setState(1478);
                        sourceElements();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SourceElementsContext sourceElements() throws RecognitionException {
        SourceElementsContext _localctx = new SourceElementsContext(_ctx, getState());
        enterRule(_localctx, 250, RULE_sourceElements);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1482);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(1481);
                                sourceElement();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(1484);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 184, _ctx);
                } while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
        ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
        enterRule(_localctx, 252, RULE_arrayLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                {
                    setState(1486);
                    match(OpenBracket);
                    setState(1488);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795928641192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8092563910088982527L) != 0) {
                        {
                            setState(1487);
                            elementList();
                        }
                    }

                    setState(1490);
                    match(CloseBracket);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ElementListContext elementList() throws RecognitionException {
        ElementListContext _localctx = new ElementListContext(_ctx, getState());
        enterRule(_localctx, 254, RULE_elementList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1492);
                arrayElement();
                setState(1501);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == Comma) {
                    {
                        {
                            setState(1494);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            do {
                                {
                                    {
                                        setState(1493);
                                        match(Comma);
                                    }
                                }
                                setState(1496);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            } while (_la == Comma);
                            setState(1498);
                            arrayElement();
                        }
                    }
                    setState(1503);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArrayElementContext arrayElement() throws RecognitionException {
        ArrayElementContext _localctx = new ArrayElementContext(_ctx, getState());
        enterRule(_localctx, 256, RULE_arrayElement);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1505);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Ellipsis) {
                    {
                        setState(1504);
                        match(Ellipsis);
                    }
                }

                setState(1509);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 189, _ctx)) {
                    case 1: {
                        setState(1507);
                        singleExpression(0);
                    }
                    break;
                    case 2: {
                        setState(1508);
                        match(Identifier);
                    }
                    break;
                }
                setState(1512);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 190, _ctx)) {
                    case 1: {
                        setState(1511);
                        match(Comma);
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ObjectLiteralContext objectLiteral() throws RecognitionException {
        ObjectLiteralContext _localctx = new ObjectLiteralContext(_ctx, getState());
        enterRule(_localctx, 258, RULE_objectLiteral);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1514);
                match(OpenBrace);
                setState(1526);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028797002121200L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 3480877891678371839L) != 0) {
                    {
                        setState(1515);
                        propertyAssignment();
                        setState(1520);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 191, _ctx);
                        while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                            if (_alt == 1) {
                                {
                                    {
                                        setState(1516);
                                        match(Comma);
                                        setState(1517);
                                        propertyAssignment();
                                    }
                                }
                            }
                            setState(1522);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 191, _ctx);
                        }
                        setState(1524);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la == Comma) {
                            {
                                setState(1523);
                                match(Comma);
                            }
                        }

                    }
                }

                setState(1528);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PropertyAssignmentContext propertyAssignment() throws RecognitionException {
        PropertyAssignmentContext _localctx = new PropertyAssignmentContext(_ctx, getState());
        enterRule(_localctx, 260, RULE_propertyAssignment);
        int _la;
        try {
            setState(1545);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 194, _ctx)) {
                case 1:
                    _localctx = new PropertyExpressionAssignmentContext(_localctx);
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1530);
                    propertyName();
                    setState(1531);
                    _la = _input.LA(1);
                    if (!(_la == Assign || _la == Colon)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                    setState(1532);
                    singleExpression(0);
                }
                break;
                case 2:
                    _localctx = new ComputedPropertyExpressionAssignmentContext(_localctx);
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1534);
                    match(OpenBracket);
                    setState(1535);
                    singleExpression(0);
                    setState(1536);
                    match(CloseBracket);
                    setState(1537);
                    match(Colon);
                    setState(1538);
                    singleExpression(0);
                }
                break;
                case 3:
                    _localctx = new PropertyGetterContext(_localctx);
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1540);
                    getAccessor();
                }
                break;
                case 4:
                    _localctx = new PropertySetterContext(_localctx);
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1541);
                    setAccessor();
                }
                break;
                case 5:
                    _localctx = new MethodPropertyContext(_localctx);
                    enterOuterAlt(_localctx, 5);
                {
                    setState(1542);
                    generatorMethod();
                }
                break;
                case 6:
                    _localctx = new PropertyShorthandContext(_localctx);
                    enterOuterAlt(_localctx, 6);
                {
                    setState(1543);
                    identifierOrKeyWord();
                }
                break;
                case 7:
                    _localctx = new RestParameterInObjectContext(_localctx);
                    enterOuterAlt(_localctx, 7);
                {
                    setState(1544);
                    restParameter();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GetAccessorContext getAccessor() throws RecognitionException {
        GetAccessorContext _localctx = new GetAccessorContext(_ctx, getState());
        enterRule(_localctx, 262, RULE_getAccessor);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1547);
                getter();
                setState(1548);
                match(OpenParen);
                setState(1549);
                match(CloseParen);
                setState(1551);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(1550);
                        typeAnnotation();
                    }
                }

                setState(1553);
                match(OpenBrace);
                setState(1554);
                functionBody();
                setState(1555);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SetAccessorContext setAccessor() throws RecognitionException {
        SetAccessorContext _localctx = new SetAccessorContext(_ctx, getState());
        enterRule(_localctx, 264, RULE_setAccessor);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1557);
                setter();
                setState(1558);
                match(OpenParen);
                setState(1561);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case Identifier: {
                        setState(1559);
                        match(Identifier);
                    }
                    break;
                    case OpenBracket:
                    case OpenBrace: {
                        setState(1560);
                        bindingPattern();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                setState(1564);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(1563);
                        typeAnnotation();
                    }
                }

                setState(1566);
                match(CloseParen);
                setState(1567);
                match(OpenBrace);
                setState(1568);
                functionBody();
                setState(1569);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final PropertyNameContext propertyName() throws RecognitionException {
        PropertyNameContext _localctx = new PropertyNameContext(_ctx, getState());
        enterRule(_localctx, 266, RULE_propertyName);
        try {
            setState(1574);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case NullLiteral:
                case BooleanLiteral:
                case Break:
                case Do:
                case Instanceof:
                case Typeof:
                case Case:
                case Else:
                case New:
                case Var:
                case Catch:
                case Finally:
                case Return:
                case Void:
                case Continue:
                case For:
                case Switch:
                case While:
                case Debugger:
                case Function_:
                case This:
                case With:
                case Default:
                case If:
                case Throw:
                case Delete:
                case In:
                case Try:
                case From:
                case ReadOnly:
                case Async:
                case Class:
                case Enum:
                case Extends:
                case Super:
                case Const:
                case Export:
                case Import:
                case Implements:
                case Let:
                case Private:
                case Public:
                case Interface:
                case Package:
                case Protected:
                case Static:
                case Yield:
                case String:
                case TypeAlias:
                case Get:
                case Set:
                case Require:
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1571);
                    identifierName();
                }
                break;
                case StringLiteral:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1572);
                    match(StringLiteral);
                }
                break;
                case DecimalLiteral:
                case HexIntegerLiteral:
                case OctalIntegerLiteral:
                case OctalIntegerLiteral2:
                case BinaryIntegerLiteral:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1573);
                    numericLiteral();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArgumentsContext arguments() throws RecognitionException {
        ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
        enterRule(_localctx, 268, RULE_arguments);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1576);
                match(OpenParen);
                setState(1581);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & -36028795928641192L) != 0 || (((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8092563910088982527L) != 0) {
                    {
                        setState(1577);
                        argumentList();
                        setState(1579);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la == Comma) {
                            {
                                setState(1578);
                                match(Comma);
                            }
                        }

                    }
                }

                setState(1583);
                match(CloseParen);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArgumentListContext argumentList() throws RecognitionException {
        ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
        enterRule(_localctx, 270, RULE_argumentList);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1585);
                argument();
                setState(1590);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 201, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(1586);
                                match(Comma);
                                setState(1587);
                                argument();
                            }
                        }
                    }
                    setState(1592);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 201, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArgumentContext argument() throws RecognitionException {
        ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
        enterRule(_localctx, 272, RULE_argument);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1594);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Ellipsis) {
                    {
                        setState(1593);
                        match(Ellipsis);
                    }
                }

                setState(1598);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 203, _ctx)) {
                    case 1: {
                        setState(1596);
                        singleExpression(0);
                    }
                    break;
                    case 2: {
                        setState(1597);
                        match(Identifier);
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ExpressionSequenceContext expressionSequence() throws RecognitionException {
        ExpressionSequenceContext _localctx = new ExpressionSequenceContext(_ctx, getState());
        enterRule(_localctx, 274, RULE_expressionSequence);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1601);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Ellipsis) {
                    {
                        setState(1600);
                        match(Ellipsis);
                    }
                }

                setState(1603);
                singleExpression(0);
                setState(1608);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 205, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(1604);
                                match(Comma);
                                setState(1605);
                                singleExpression(0);
                            }
                        }
                    }
                    setState(1610);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 205, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final FunctionExpressionDeclarationContext functionExpressionDeclaration() throws RecognitionException {
        FunctionExpressionDeclarationContext _localctx = new FunctionExpressionDeclarationContext(_ctx, getState());
        enterRule(_localctx, 276, RULE_functionExpressionDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1611);
                match(Function_);
                setState(1613);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Identifier) {
                    {
                        setState(1612);
                        match(Identifier);
                    }
                }

                setState(1615);
                match(OpenParen);
                setState(1617);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (((_la) & ~0x3f) == 0 && ((1L << _la) & 65808L) != 0 || (((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & 104186667009L) != 0) {
                    {
                        setState(1616);
                        formalParameterList();
                    }
                }

                setState(1619);
                match(CloseParen);
                setState(1621);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(1620);
                        typeAnnotation();
                    }
                }

                setState(1623);
                match(OpenBrace);
                setState(1624);
                functionBody();
                setState(1625);
                match(CloseBrace);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SingleExpressionContext singleExpression() throws RecognitionException {
        return singleExpression(0);
    }

    private SingleExpressionContext singleExpression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        SingleExpressionContext _localctx = new SingleExpressionContext(_ctx, _parentState);
        SingleExpressionContext _prevctx = _localctx;
        int _startState = 278;
        enterRecursionRule(_localctx, 278, RULE_singleExpression, _p);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1687);
                _errHandler.sync(this);
                switch (getInterpreter().adaptivePredict(_input, 214, _ctx)) {
                    case 1: {
                        _localctx = new FunctionExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;

                        setState(1628);
                        functionExpressionDeclaration();
                    }
                    break;
                    case 2: {
                        _localctx = new ArrowFunctionExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1629);
                        arrowFunctionDeclaration();
                    }
                    break;
                    case 3: {
                        _localctx = new ClassExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1630);
                        match(Class);
                        setState(1632);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la == Identifier) {
                            {
                                setState(1631);
                                match(Identifier);
                            }
                        }

                        setState(1634);
                        classTail();
                    }
                    break;
                    case 4: {
                        _localctx = new NewExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1635);
                        match(New);
                        setState(1636);
                        singleExpression(0);
                        setState(1638);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        if (_la == LessThan) {
                            {
                                setState(1637);
                                typeArguments();
                            }
                        }

                        setState(1640);
                        arguments();
                    }
                    break;
                    case 5: {
                        _localctx = new NewExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1642);
                        match(New);
                        setState(1643);
                        singleExpression(0);
                        setState(1645);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 211, _ctx)) {
                            case 1: {
                                setState(1644);
                                typeArguments();
                            }
                            break;
                        }
                    }
                    break;
                    case 6: {
                        _localctx = new DeleteExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1647);
                        match(Delete);
                        setState(1648);
                        singleExpression(39);
                    }
                    break;
                    case 7: {
                        _localctx = new VoidExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1649);
                        match(Void);
                        setState(1650);
                        singleExpression(38);
                    }
                    break;
                    case 8: {
                        _localctx = new TypeofExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1651);
                        match(Typeof);
                        setState(1652);
                        singleExpression(37);
                    }
                    break;
                    case 9: {
                        _localctx = new PreIncrementExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1653);
                        match(PlusPlus);
                        setState(1654);
                        singleExpression(36);
                    }
                    break;
                    case 10: {
                        _localctx = new PreDecreaseExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1655);
                        match(MinusMinus);
                        setState(1656);
                        singleExpression(35);
                    }
                    break;
                    case 11: {
                        _localctx = new UnaryPlusExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1657);
                        match(Plus);
                        setState(1658);
                        singleExpression(34);
                    }
                    break;
                    case 12: {
                        _localctx = new UnaryMinusExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1659);
                        match(Minus);
                        setState(1660);
                        singleExpression(33);
                    }
                    break;
                    case 13: {
                        _localctx = new BitNotExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1661);
                        match(BitNot);
                        setState(1662);
                        singleExpression(32);
                    }
                    break;
                    case 14: {
                        _localctx = new NotExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1663);
                        match(Not);
                        setState(1664);
                        singleExpression(31);
                    }
                    break;
                    case 15: {
                        _localctx = new IteratorsExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1665);
                        iteratorBlock();
                    }
                    break;
                    case 16: {
                        _localctx = new GeneratorsExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1666);
                        generatorBlock();
                    }
                    break;
                    case 17: {
                        _localctx = new GeneratorsFunctionExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1667);
                        generatorFunctionDeclaration();
                    }
                    break;
                    case 18: {
                        _localctx = new YieldExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1668);
                        yieldStatement();
                    }
                    break;
                    case 19: {
                        _localctx = new ThisExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1669);
                        match(This);
                    }
                    break;
                    case 20: {
                        _localctx = new IdentifierExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1670);
                        identifierName();
                        setState(1672);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 212, _ctx)) {
                            case 1: {
                                setState(1671);
                                singleExpression(0);
                            }
                            break;
                        }
                    }
                    break;
                    case 21: {
                        _localctx = new SuperExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1674);
                        match(Super);
                    }
                    break;
                    case 22: {
                        _localctx = new LiteralExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1675);
                        literal();
                    }
                    break;
                    case 23: {
                        _localctx = new ArrayLiteralExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1676);
                        arrayLiteral();
                    }
                    break;
                    case 24: {
                        _localctx = new ObjectLiteralExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1677);
                        objectLiteral();
                    }
                    break;
                    case 25: {
                        _localctx = new ParenthesizedExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1678);
                        match(OpenParen);
                        setState(1679);
                        expressionSequence();
                        setState(1680);
                        match(CloseParen);
                    }
                    break;
                    case 26: {
                        _localctx = new GenericTypesContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1682);
                        typeArguments();
                        setState(1684);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 213, _ctx)) {
                            case 1: {
                                setState(1683);
                                expressionSequence();
                            }
                            break;
                        }
                    }
                    break;
                    case 27: {
                        _localctx = new HtmlElementExpressionContext(_localctx);
                        _ctx = _localctx;
                        _prevctx = _localctx;
                        setState(1686);
                        htmlElements();
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(1767);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 218, _ctx);
                while (_alt != 2 && _alt != ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(1765);
                            _errHandler.sync(this);
                            switch (getInterpreter().adaptivePredict(_input, 217, _ctx)) {
                                case 1: {
                                    _localctx = new MultiplicativeExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1689);
                                    if (!(precpred(_ctx, 30)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 30)");
                                    setState(1690);
                                    _la = _input.LA(1);
                                    if (!(((_la) & ~0x3f) == 0 && ((1L << _la) & 117440512L) != 0)) {
                                        _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(1691);
                                    singleExpression(31);
                                }
                                break;
                                case 2: {
                                    _localctx = new AdditiveExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1692);
                                    if (!(precpred(_ctx, 29)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 29)");
                                    setState(1693);
                                    _la = _input.LA(1);
                                    if (!(_la == Plus || _la == Minus)) {
                                        _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(1694);
                                    singleExpression(30);
                                }
                                break;
                                case 3: {
                                    _localctx = new BitShiftExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1695);
                                    if (!(precpred(_ctx, 28)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 28)");
                                    setState(1696);
                                    _la = _input.LA(1);
                                    if (!(((_la) & ~0x3f) == 0 && ((1L << _la) & 939524096L) != 0)) {
                                        _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(1697);
                                    singleExpression(29);
                                }
                                break;
                                case 4: {
                                    _localctx = new RelationalExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1698);
                                    if (!(precpred(_ctx, 27)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 27)");
                                    setState(1699);
                                    _la = _input.LA(1);
                                    if (!(((_la) & ~0x3f) == 0 && ((1L << _la) & 16106127360L) != 0)) {
                                        _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(1700);
                                    singleExpression(28);
                                }
                                break;
                                case 5: {
                                    _localctx = new InstanceofExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1701);
                                    if (!(precpred(_ctx, 26)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 26)");
                                    setState(1702);
                                    match(Instanceof);
                                    setState(1703);
                                    singleExpression(27);
                                }
                                break;
                                case 6: {
                                    _localctx = new InExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1704);
                                    if (!(precpred(_ctx, 25)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 25)");
                                    setState(1705);
                                    match(In);
                                    setState(1706);
                                    singleExpression(26);
                                }
                                break;
                                case 7: {
                                    _localctx = new EqualityExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1707);
                                    if (!(precpred(_ctx, 24)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 24)");
                                    setState(1708);
                                    _la = _input.LA(1);
                                    if (!(((_la) & ~0x3f) == 0 && ((1L << _la) & 257698037760L) != 0)) {
                                        _errHandler.recoverInline(this);
                                    } else {
                                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                        _errHandler.reportMatch(this);
                                        consume();
                                    }
                                    setState(1709);
                                    singleExpression(25);
                                }
                                break;
                                case 8: {
                                    _localctx = new BitAndExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1710);
                                    if (!(precpred(_ctx, 23)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 23)");
                                    setState(1711);
                                    match(BitAnd);
                                    setState(1712);
                                    singleExpression(24);
                                }
                                break;
                                case 9: {
                                    _localctx = new BitXOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1713);
                                    if (!(precpred(_ctx, 22)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 22)");
                                    setState(1714);
                                    match(BitXOr);
                                    setState(1715);
                                    singleExpression(23);
                                }
                                break;
                                case 10: {
                                    _localctx = new BitOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1716);
                                    if (!(precpred(_ctx, 21)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 21)");
                                    setState(1717);
                                    match(BitOr);
                                    setState(1718);
                                    singleExpression(22);
                                }
                                break;
                                case 11: {
                                    _localctx = new LogicalAndExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1719);
                                    if (!(precpred(_ctx, 20)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 20)");
                                    setState(1720);
                                    match(And);
                                    setState(1721);
                                    singleExpression(21);
                                }
                                break;
                                case 12: {
                                    _localctx = new LogicalOrExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1722);
                                    if (!(precpred(_ctx, 19)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 19)");
                                    setState(1723);
                                    match(Or);
                                    setState(1724);
                                    singleExpression(20);
                                }
                                break;
                                case 13: {
                                    _localctx = new TernaryExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1725);
                                    if (!(precpred(_ctx, 18)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 18)");
                                    setState(1726);
                                    match(QuestionMark);
                                    setState(1727);
                                    singleExpression(0);
                                    setState(1728);
                                    match(Colon);
                                    setState(1729);
                                    singleExpression(19);
                                }
                                break;
                                case 14: {
                                    _localctx = new AssignmentExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1731);
                                    if (!(precpred(_ctx, 17)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 17)");
                                    setState(1732);
                                    match(Assign);
                                    setState(1733);
                                    singleExpression(18);
                                }
                                break;
                                case 15: {
                                    _localctx = new AssignmentOperatorExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1734);
                                    if (!(precpred(_ctx, 16)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 16)");
                                    setState(1735);
                                    assignmentOperator();
                                    setState(1736);
                                    singleExpression(17);
                                }
                                break;
                                case 16: {
                                    _localctx = new MemberIndexExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1738);
                                    if (!(precpred(_ctx, 46)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 46)");
                                    setState(1739);
                                    match(OpenBracket);
                                    setState(1740);
                                    expressionSequence();
                                    setState(1741);
                                    match(CloseBracket);
                                }
                                break;
                                case 17: {
                                    _localctx = new MemberDotExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1743);
                                    if (!(precpred(_ctx, 45)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 45)");
                                    setState(1745);
                                    _errHandler.sync(this);
                                    _la = _input.LA(1);
                                    if (_la == QuestionMark) {
                                        {
                                            setState(1744);
                                            match(QuestionMark);
                                        }
                                    }

                                    setState(1747);
                                    match(Dot);
                                    setState(1748);
                                    identifierName();
                                    setState(1750);
                                    _errHandler.sync(this);
                                    switch (getInterpreter().adaptivePredict(_input, 216, _ctx)) {
                                        case 1: {
                                            setState(1749);
                                            nestedTypeGeneric();
                                        }
                                        break;
                                    }
                                }
                                break;
                                case 18: {
                                    _localctx = new ArgumentsExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1752);
                                    if (!(precpred(_ctx, 42)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 42)");
                                    setState(1753);
                                    arguments();
                                }
                                break;
                                case 19: {
                                    _localctx = new PostIncrementExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1754);
                                    if (!(precpred(_ctx, 41)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 41)");
                                    setState(1755);
                                    if (!(this.notLineTerminator()))
                                        throw new FailedPredicateException(this, "this.notLineTerminator()");
                                    setState(1756);
                                    match(PlusPlus);
                                }
                                break;
                                case 20: {
                                    _localctx = new PostDecreaseExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1757);
                                    if (!(precpred(_ctx, 40)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 40)");
                                    setState(1758);
                                    if (!(this.notLineTerminator()))
                                        throw new FailedPredicateException(this, "this.notLineTerminator()");
                                    setState(1759);
                                    match(MinusMinus);
                                }
                                break;
                                case 21: {
                                    _localctx = new TemplateStringExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1760);
                                    if (!(precpred(_ctx, 15)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 15)");
                                    setState(1761);
                                    templateStringLiteral();
                                }
                                break;
                                case 22: {
                                    _localctx = new CastAsExpressionContext(new SingleExpressionContext(_parentctx, _parentState));
                                    pushNewRecursionContext(_localctx, _startState, RULE_singleExpression);
                                    setState(1762);
                                    if (!(precpred(_ctx, 2)))
                                        throw new FailedPredicateException(this, "precpred(_ctx, 2)");
                                    setState(1763);
                                    match(As);
                                    setState(1764);
                                    asExpression();
                                }
                                break;
                            }
                        }
                    }
                    setState(1769);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 218, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public final AsExpressionContext asExpression() throws RecognitionException {
        AsExpressionContext _localctx = new AsExpressionContext(_ctx, getState());
        enterRule(_localctx, 280, RULE_asExpression);
        try {
            setState(1776);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 220, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1770);
                    predefinedType();
                    setState(1773);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 219, _ctx)) {
                        case 1: {
                            setState(1771);
                            match(OpenBracket);
                            setState(1772);
                            match(CloseBracket);
                        }
                        break;
                    }
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1775);
                    singleExpression(0);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArrowFunctionDeclarationContext arrowFunctionDeclaration() throws RecognitionException {
        ArrowFunctionDeclarationContext _localctx = new ArrowFunctionDeclarationContext(_ctx, getState());
        enterRule(_localctx, 282, RULE_arrowFunctionDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1779);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Async) {
                    {
                        setState(1778);
                        match(Async);
                    }
                }

                setState(1781);
                arrowFunctionParameters();
                setState(1783);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if (_la == Colon) {
                    {
                        setState(1782);
                        typeAnnotation();
                    }
                }

                setState(1785);
                match(ARROW);
                setState(1786);
                arrowFunctionBody();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArrowFunctionParametersContext arrowFunctionParameters() throws RecognitionException {
        ArrowFunctionParametersContext _localctx = new ArrowFunctionParametersContext(_ctx, getState());
        enterRule(_localctx, 284, RULE_arrowFunctionParameters);
        int _la;
        try {
            setState(1794);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1788);
                    match(Identifier);
                }
                break;
                case OpenParen:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1789);
                    match(OpenParen);
                    setState(1791);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (((_la) & ~0x3f) == 0 && ((1L << _la) & 65808L) != 0 || (((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & 104186667009L) != 0) {
                        {
                            setState(1790);
                            formalParameterList();
                        }
                    }

                    setState(1793);
                    match(CloseParen);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ArrowFunctionBodyContext arrowFunctionBody() throws RecognitionException {
        ArrowFunctionBodyContext _localctx = new ArrowFunctionBodyContext(_ctx, getState());
        enterRule(_localctx, 286, RULE_arrowFunctionBody);
        try {
            setState(1801);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 225, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1796);
                    singleExpression(0);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1797);
                    match(OpenBrace);
                    setState(1798);
                    functionBody();
                    setState(1799);
                    match(CloseBrace);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
        AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
        enterRule(_localctx, 288, RULE_assignmentOperator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1803);
                _la = _input.LA(1);
                if (!(((_la) & ~0x3f) == 0 && ((1L << _la) & 18005602416459776L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final LiteralContext literal() throws RecognitionException {
        LiteralContext _localctx = new LiteralContext(_ctx, getState());
        enterRule(_localctx, 290, RULE_literal);
        try {
            setState(1811);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case NullLiteral:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1805);
                    match(NullLiteral);
                }
                break;
                case BooleanLiteral:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1806);
                    match(BooleanLiteral);
                }
                break;
                case StringLiteral:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1807);
                    match(StringLiteral);
                }
                break;
                case BackTick:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1808);
                    templateStringLiteral();
                }
                break;
                case RegularExpressionLiteral:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(1809);
                    match(RegularExpressionLiteral);
                }
                break;
                case DecimalLiteral:
                case HexIntegerLiteral:
                case OctalIntegerLiteral:
                case OctalIntegerLiteral2:
                case BinaryIntegerLiteral:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(1810);
                    numericLiteral();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TemplateStringLiteralContext templateStringLiteral() throws RecognitionException {
        TemplateStringLiteralContext _localctx = new TemplateStringLiteralContext(_ctx, getState());
        enterRule(_localctx, 292, RULE_templateStringLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1813);
                match(BackTick);
                setState(1817);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == TemplateStringStartExpression || _la == TemplateStringAtom) {
                    {
                        {
                            setState(1814);
                            templateStringAtom();
                        }
                    }
                    setState(1819);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(1820);
                match(BackTick);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TemplateStringAtomContext templateStringAtom() throws RecognitionException {
        TemplateStringAtomContext _localctx = new TemplateStringAtomContext(_ctx, getState());
        enterRule(_localctx, 294, RULE_templateStringAtom);
        try {
            setState(1827);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case TemplateStringAtom:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1822);
                    match(TemplateStringAtom);
                }
                break;
                case TemplateStringStartExpression:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1823);
                    match(TemplateStringStartExpression);
                    setState(1824);
                    singleExpression(0);
                    setState(1825);
                    match(TemplateCloseBrace);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final NumericLiteralContext numericLiteral() throws RecognitionException {
        NumericLiteralContext _localctx = new NumericLiteralContext(_ctx, getState());
        enterRule(_localctx, 296, RULE_numericLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1829);
                _la = _input.LA(1);
                if (!(((_la) & ~0x3f) == 0 && ((1L << _la) & 4467570830351532032L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IdentifierNameContext identifierName() throws RecognitionException {
        IdentifierNameContext _localctx = new IdentifierNameContext(_ctx, getState());
        enterRule(_localctx, 298, RULE_identifierName);
        try {
            setState(1833);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1831);
                    match(Identifier);
                }
                break;
                case NullLiteral:
                case BooleanLiteral:
                case Break:
                case Do:
                case Instanceof:
                case Typeof:
                case Case:
                case Else:
                case New:
                case Var:
                case Catch:
                case Finally:
                case Return:
                case Void:
                case Continue:
                case For:
                case Switch:
                case While:
                case Debugger:
                case Function_:
                case This:
                case With:
                case Default:
                case If:
                case Throw:
                case Delete:
                case In:
                case Try:
                case From:
                case ReadOnly:
                case Async:
                case Class:
                case Enum:
                case Extends:
                case Super:
                case Const:
                case Export:
                case Import:
                case Implements:
                case Let:
                case Private:
                case Public:
                case Interface:
                case Package:
                case Protected:
                case Static:
                case Yield:
                case String:
                case TypeAlias:
                case Get:
                case Set:
                case Require:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1832);
                    reservedWord();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final IdentifierOrKeyWordContext identifierOrKeyWord() throws RecognitionException {
        IdentifierOrKeyWordContext _localctx = new IdentifierOrKeyWordContext(_ctx, getState());
        enterRule(_localctx, 300, RULE_identifierOrKeyWord);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1835);
                _la = _input.LA(1);
                if (!((((_la - 88)) & ~0x3f) == 0 && ((1L << (_la - 88)) & 69826772993L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ReservedWordContext reservedWord() throws RecognitionException {
        ReservedWordContext _localctx = new ReservedWordContext(_ctx, getState());
        enterRule(_localctx, 302, RULE_reservedWord);
        try {
            setState(1840);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case Break:
                case Do:
                case Instanceof:
                case Typeof:
                case Case:
                case Else:
                case New:
                case Var:
                case Catch:
                case Finally:
                case Return:
                case Void:
                case Continue:
                case For:
                case Switch:
                case While:
                case Debugger:
                case Function_:
                case This:
                case With:
                case Default:
                case If:
                case Throw:
                case Delete:
                case In:
                case Try:
                case From:
                case ReadOnly:
                case Async:
                case Class:
                case Enum:
                case Extends:
                case Super:
                case Const:
                case Export:
                case Import:
                case Implements:
                case Let:
                case Private:
                case Public:
                case Interface:
                case Package:
                case Protected:
                case Static:
                case Yield:
                case String:
                case TypeAlias:
                case Get:
                case Set:
                case Require:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1837);
                    keyword();
                }
                break;
                case NullLiteral:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1838);
                    match(NullLiteral);
                }
                break;
                case BooleanLiteral:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1839);
                    match(BooleanLiteral);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final KeywordContext keyword() throws RecognitionException {
        KeywordContext _localctx = new KeywordContext(_ctx, getState());
        enterRule(_localctx, 304, RULE_keyword);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1842);
                _la = _input.LA(1);
                if (!((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & 88453511364214783L) != 0)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GetterContext getter() throws RecognitionException {
        GetterContext _localctx = new GetterContext(_ctx, getState());
        enterRule(_localctx, 306, RULE_getter);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1844);
                match(Get);
                setState(1845);
                propertyName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final SetterContext setter() throws RecognitionException {
        SetterContext _localctx = new SetterContext(_ctx, getState());
        enterRule(_localctx, 308, RULE_setter);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1847);
                match(Set);
                setState(1848);
                propertyName();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final EosContext eos() throws RecognitionException {
        EosContext _localctx = new EosContext(_ctx, getState());
        enterRule(_localctx, 310, RULE_eos);
        try {
            setState(1854);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 231, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1850);
                    match(SemiColon);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1851);
                    match(EOF);
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1852);
                    if (!(this.lineTerminatorAhead()))
                        throw new FailedPredicateException(this, "this.lineTerminatorAhead()");
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1853);
                    if (!(this.closeBrace())) throw new FailedPredicateException(this, "this.closeBrace()");
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 4:
                return htmlTagClosingName_sempred((HtmlTagClosingNameContext) _localctx, predIndex);
            case 22:
                return unionOrIntersectionOrPrimaryType_sempred((UnionOrIntersectionOrPrimaryTypeContext) _localctx, predIndex);
            case 23:
                return primaryType_sempred((PrimaryTypeContext) _localctx, predIndex);
            case 34:
                return arrayType_sempred((ArrayTypeContext) _localctx, predIndex);
            case 69:
                return decoratorMemberExpression_sempred((DecoratorMemberExpressionContext) _localctx, predIndex);
            case 85:
                return expressionStatement_sempred((ExpressionStatementContext) _localctx, predIndex);
            case 87:
                return iterationStatement_sempred((IterationStatementContext) _localctx, predIndex);
            case 89:
                return continueStatement_sempred((ContinueStatementContext) _localctx, predIndex);
            case 90:
                return breakStatement_sempred((BreakStatementContext) _localctx, predIndex);
            case 91:
                return returnStatement_sempred((ReturnStatementContext) _localctx, predIndex);
            case 92:
                return yieldStatement_sempred((YieldStatementContext) _localctx, predIndex);
            case 100:
                return throwStatement_sempred((ThrowStatementContext) _localctx, predIndex);
            case 139:
                return singleExpression_sempred((SingleExpressionContext) _localctx, predIndex);
            case 155:
                return eos_sempred((EosContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean htmlTagClosingName_sempred(HtmlTagClosingNameContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return this.popHtmlTagName((((HtmlTagClosingNameContext) _localctx).htmlTagName != null ? _input.getText(((HtmlTagClosingNameContext) _localctx).htmlTagName.start, ((HtmlTagClosingNameContext) _localctx).htmlTagName.stop) : null));
        }
        return true;
    }

    private boolean unionOrIntersectionOrPrimaryType_sempred(UnionOrIntersectionOrPrimaryTypeContext _localctx, int predIndex) {
        switch (predIndex) {
            case 1:
                return precpred(_ctx, 3);
            case 2:
                return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean primaryType_sempred(PrimaryTypeContext _localctx, int predIndex) {
        switch (predIndex) {
            case 3:
                return precpred(_ctx, 5);
            case 4:
                return notLineTerminator();
        }
        return true;
    }

    private boolean arrayType_sempred(ArrayTypeContext _localctx, int predIndex) {
        switch (predIndex) {
            case 5:
                return notLineTerminator();
        }
        return true;
    }

    private boolean decoratorMemberExpression_sempred(DecoratorMemberExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 6:
                return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean expressionStatement_sempred(ExpressionStatementContext _localctx, int predIndex) {
        switch (predIndex) {
            case 7:
                return this.notOpenBraceAndNotFunction();
        }
        return true;
    }

    private boolean iterationStatement_sempred(IterationStatementContext _localctx, int predIndex) {
        switch (predIndex) {
            case 8:
                return this.p("of");
            case 9:
                return this.p("of");
        }
        return true;
    }

    private boolean continueStatement_sempred(ContinueStatementContext _localctx, int predIndex) {
        switch (predIndex) {
            case 10:
                return this.notLineTerminator();
        }
        return true;
    }

    private boolean breakStatement_sempred(BreakStatementContext _localctx, int predIndex) {
        switch (predIndex) {
            case 11:
                return this.notLineTerminator();
        }
        return true;
    }

    private boolean returnStatement_sempred(ReturnStatementContext _localctx, int predIndex) {
        switch (predIndex) {
            case 12:
                return this.notLineTerminator();
        }
        return true;
    }

    private boolean yieldStatement_sempred(YieldStatementContext _localctx, int predIndex) {
        switch (predIndex) {
            case 13:
                return this.notLineTerminator();
        }
        return true;
    }

    private boolean throwStatement_sempred(ThrowStatementContext _localctx, int predIndex) {
        switch (predIndex) {
            case 14:
                return this.notLineTerminator();
        }
        return true;
    }

    private boolean singleExpression_sempred(SingleExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 15:
                return precpred(_ctx, 30);
            case 16:
                return precpred(_ctx, 29);
            case 17:
                return precpred(_ctx, 28);
            case 18:
                return precpred(_ctx, 27);
            case 19:
                return precpred(_ctx, 26);
            case 20:
                return precpred(_ctx, 25);
            case 21:
                return precpred(_ctx, 24);
            case 22:
                return precpred(_ctx, 23);
            case 23:
                return precpred(_ctx, 22);
            case 24:
                return precpred(_ctx, 21);
            case 25:
                return precpred(_ctx, 20);
            case 26:
                return precpred(_ctx, 19);
            case 27:
                return precpred(_ctx, 18);
            case 28:
                return precpred(_ctx, 17);
            case 29:
                return precpred(_ctx, 16);
            case 30:
                return precpred(_ctx, 46);
            case 31:
                return precpred(_ctx, 45);
            case 32:
                return precpred(_ctx, 42);
            case 33:
                return precpred(_ctx, 41);
            case 34:
                return this.notLineTerminator();
            case 35:
                return precpred(_ctx, 40);
            case 36:
                return this.notLineTerminator();
            case 37:
                return precpred(_ctx, 15);
            case 38:
                return precpred(_ctx, 2);
        }
        return true;
    }

    private boolean eos_sempred(EosContext _localctx, int predIndex) {
        switch (predIndex) {
            case 39:
                return this.lineTerminatorAhead();
            case 40:
                return this.closeBrace();
        }
        return true;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlElementsContext extends ParserRuleContext {
        public HtmlElementsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<HtmlElementContext> htmlElement() {
            return getRuleContexts(HtmlElementContext.class);
        }

        public HtmlElementContext htmlElement(int i) {
            return getRuleContext(HtmlElementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlElements;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlElements(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlElementContext extends ParserRuleContext {
        public HtmlElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> LessThan() {
            return getTokens(TypeScriptParser.LessThan);
        }

        public TerminalNode LessThan(int i) {
            return getToken(TypeScriptParser.LessThan, i);
        }

        public HtmlTagStartNameContext htmlTagStartName() {
            return getRuleContext(HtmlTagStartNameContext.class, 0);
        }

        public List<TerminalNode> MoreThan() {
            return getTokens(TypeScriptParser.MoreThan);
        }

        public TerminalNode MoreThan(int i) {
            return getToken(TypeScriptParser.MoreThan, i);
        }

        public HtmlContentContext htmlContent() {
            return getRuleContext(HtmlContentContext.class, 0);
        }

        public TerminalNode Divide() {
            return getToken(TypeScriptParser.Divide, 0);
        }

        public HtmlTagClosingNameContext htmlTagClosingName() {
            return getRuleContext(HtmlTagClosingNameContext.class, 0);
        }

        public List<HtmlAttributeContext> htmlAttribute() {
            return getRuleContexts(HtmlAttributeContext.class);
        }

        public HtmlAttributeContext htmlAttribute(int i) {
            return getRuleContext(HtmlAttributeContext.class, i);
        }

        public HtmlTagNameContext htmlTagName() {
            return getRuleContext(HtmlTagNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlElement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlElement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlContentContext extends ParserRuleContext {
        public HtmlContentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<HtmlChardataContext> htmlChardata() {
            return getRuleContexts(HtmlChardataContext.class);
        }

        public HtmlChardataContext htmlChardata(int i) {
            return getRuleContext(HtmlChardataContext.class, i);
        }

        public List<HtmlElementContext> htmlElement() {
            return getRuleContexts(HtmlElementContext.class);
        }

        public HtmlElementContext htmlElement(int i) {
            return getRuleContext(HtmlElementContext.class, i);
        }

        public List<ObjectExpressionSequenceContext> objectExpressionSequence() {
            return getRuleContexts(ObjectExpressionSequenceContext.class);
        }

        public ObjectExpressionSequenceContext objectExpressionSequence(int i) {
            return getRuleContext(ObjectExpressionSequenceContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlContent;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlContent(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlTagStartNameContext extends ParserRuleContext {
        public HtmlTagNameContext htmlTagName;

        public HtmlTagStartNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public HtmlTagNameContext htmlTagName() {
            return getRuleContext(HtmlTagNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlTagStartName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlTagStartName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlTagClosingNameContext extends ParserRuleContext {
        public HtmlTagNameContext htmlTagName;

        public HtmlTagClosingNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public HtmlTagNameContext htmlTagName() {
            return getRuleContext(HtmlTagNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlTagClosingName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlTagClosingName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlTagNameContext extends ParserRuleContext {
        public HtmlTagNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> TagName() {
            return getTokens(TypeScriptParser.TagName);
        }

        public TerminalNode TagName(int i) {
            return getToken(TypeScriptParser.TagName, i);
        }

        public List<KeywordContext> keyword() {
            return getRuleContexts(KeywordContext.class);
        }

        public KeywordContext keyword(int i) {
            return getRuleContext(KeywordContext.class, i);
        }

        public List<TerminalNode> Identifier() {
            return getTokens(TypeScriptParser.Identifier);
        }

        public TerminalNode Identifier(int i) {
            return getToken(TypeScriptParser.Identifier, i);
        }

        public List<TerminalNode> Dot() {
            return getTokens(TypeScriptParser.Dot);
        }

        public TerminalNode Dot(int i) {
            return getToken(TypeScriptParser.Dot, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlTagName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlTagName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlAttributeContext extends ParserRuleContext {
        public HtmlAttributeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public HtmlAttributeNameContext htmlAttributeName() {
            return getRuleContext(HtmlAttributeNameContext.class, 0);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        public HtmlAttributeValueContext htmlAttributeValue() {
            return getRuleContext(HtmlAttributeValueContext.class, 0);
        }

        public ObjectExpressionSequenceContext objectExpressionSequence() {
            return getRuleContext(ObjectExpressionSequenceContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlAttribute;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlAttribute(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlAttributeNameContext extends ParserRuleContext {
        public HtmlAttributeNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode TagName() {
            return getToken(TypeScriptParser.TagName, 0);
        }

        public List<IdentifierOrKeyWordForHtmlAttributeContext> identifierOrKeyWordForHtmlAttribute() {
            return getRuleContexts(IdentifierOrKeyWordForHtmlAttributeContext.class);
        }

        public IdentifierOrKeyWordForHtmlAttributeContext identifierOrKeyWordForHtmlAttribute(int i) {
            return getRuleContext(IdentifierOrKeyWordForHtmlAttributeContext.class, i);
        }

        public List<TerminalNode> Minus() {
            return getTokens(TypeScriptParser.Minus);
        }

        public TerminalNode Minus(int i) {
            return getToken(TypeScriptParser.Minus, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlAttributeName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlAttributeName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IdentifierOrKeyWordForHtmlAttributeContext extends ParserRuleContext {
        public IdentifierOrKeyWordForHtmlAttributeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode As() {
            return getToken(TypeScriptParser.As, 0);
        }

        public KeywordContext keyword() {
            return getRuleContext(KeywordContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_identifierOrKeyWordForHtmlAttribute;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIdentifierOrKeyWordForHtmlAttribute(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlChardataContext extends ParserRuleContext {
        public HtmlChardataContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> LessThan() {
            return getTokens(TypeScriptParser.LessThan);
        }

        public TerminalNode LessThan(int i) {
            return getToken(TypeScriptParser.LessThan, i);
        }

        public List<TerminalNode> OpenBrace() {
            return getTokens(TypeScriptParser.OpenBrace);
        }

        public TerminalNode OpenBrace(int i) {
            return getToken(TypeScriptParser.OpenBrace, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlChardata;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlChardata(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlAttributeValueContext extends ParserRuleContext {
        public HtmlAttributeValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode AttributeValue() {
            return getToken(TypeScriptParser.AttributeValue, 0);
        }

        public TerminalNode StringLiteral() {
            return getToken(TypeScriptParser.StringLiteral, 0);
        }

        public ObjectExpressionSequenceContext objectExpressionSequence() {
            return getRuleContext(ObjectExpressionSequenceContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_htmlAttributeValue;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlAttributeValue(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ObjectExpressionSequenceContext extends ParserRuleContext {
        public ObjectExpressionSequenceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_objectExpressionSequence;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitObjectExpressionSequence(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class InitializerContext extends ParserRuleContext {
        public InitializerContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_initializer;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitInitializer(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BindingPatternContext extends ParserRuleContext {
        public BindingPatternContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ArrayLiteralContext arrayLiteral() {
            return getRuleContext(ArrayLiteralContext.class, 0);
        }

        public ObjectLiteralContext objectLiteral() {
            return getRuleContext(ObjectLiteralContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_bindingPattern;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBindingPattern(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeParametersContext extends ParserRuleContext {
        public TypeParametersContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode LessThan() {
            return getToken(TypeScriptParser.LessThan, 0);
        }

        public TerminalNode MoreThan() {
            return getToken(TypeScriptParser.MoreThan, 0);
        }

        public TypeParameterListContext typeParameterList() {
            return getRuleContext(TypeParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeParameters;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeParameters(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeParameterListContext extends ParserRuleContext {
        public TypeParameterListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TypeParameterContext> typeParameter() {
            return getRuleContexts(TypeParameterContext.class);
        }

        public TypeParameterContext typeParameter(int i) {
            return getRuleContext(TypeParameterContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeParameterList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeParameterList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeParameterContext extends ParserRuleContext {
        public TypeParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public ConstraintContext constraint() {
            return getRuleContext(ConstraintContext.class, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeParameter;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeParameter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ConstraintContext extends ParserRuleContext {
        public ConstraintContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Extends() {
            return getToken(TypeScriptParser.Extends, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constraint;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitConstraint(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeArgumentsContext extends ParserRuleContext {
        public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode LessThan() {
            return getToken(TypeScriptParser.LessThan, 0);
        }

        public TerminalNode MoreThan() {
            return getToken(TypeScriptParser.MoreThan, 0);
        }

        public TypeArgumentListContext typeArgumentList() {
            return getRuleContext(TypeArgumentListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeArguments;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeArguments(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeArgumentListContext extends ParserRuleContext {
        public TypeArgumentListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TypeArgumentContext> typeArgument() {
            return getRuleContexts(TypeArgumentContext.class);
        }

        public TypeArgumentContext typeArgument(int i) {
            return getRuleContext(TypeArgumentContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeArgumentList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeArgumentList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeArgumentContext extends ParserRuleContext {
        public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeArgument;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeArgument(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class Type_Context extends ParserRuleContext {
        public Type_Context(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType() {
            return getRuleContext(UnionOrIntersectionOrPrimaryTypeContext.class, 0);
        }

        public FunctionTypeContext functionType() {
            return getRuleContext(FunctionTypeContext.class, 0);
        }

        public ConstructorTypeContext constructorType() {
            return getRuleContext(ConstructorTypeContext.class, 0);
        }

        public TypeGenericContext typeGeneric() {
            return getRuleContext(TypeGenericContext.class, 0);
        }

        public TerminalNode StringLiteral() {
            return getToken(TypeScriptParser.StringLiteral, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_type_;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitType_(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class UnionOrIntersectionOrPrimaryTypeContext extends ParserRuleContext {
        public UnionOrIntersectionOrPrimaryTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public UnionOrIntersectionOrPrimaryTypeContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_unionOrIntersectionOrPrimaryType;
        }

        public void copyFrom(UnionOrIntersectionOrPrimaryTypeContext ctx) {
            super.copyFrom(ctx);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IntersectionContext extends UnionOrIntersectionOrPrimaryTypeContext {
        public IntersectionContext(UnionOrIntersectionOrPrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public List<UnionOrIntersectionOrPrimaryTypeContext> unionOrIntersectionOrPrimaryType() {
            return getRuleContexts(UnionOrIntersectionOrPrimaryTypeContext.class);
        }

        public UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType(int i) {
            return getRuleContext(UnionOrIntersectionOrPrimaryTypeContext.class, i);
        }

        public TerminalNode BitAnd() {
            return getToken(TypeScriptParser.BitAnd, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIntersection(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PrimaryContext extends UnionOrIntersectionOrPrimaryTypeContext {
        public PrimaryContext(UnionOrIntersectionOrPrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public PrimaryTypeContext primaryType() {
            return getRuleContext(PrimaryTypeContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPrimary(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class UnionContext extends UnionOrIntersectionOrPrimaryTypeContext {
        public UnionContext(UnionOrIntersectionOrPrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public List<UnionOrIntersectionOrPrimaryTypeContext> unionOrIntersectionOrPrimaryType() {
            return getRuleContexts(UnionOrIntersectionOrPrimaryTypeContext.class);
        }

        public UnionOrIntersectionOrPrimaryTypeContext unionOrIntersectionOrPrimaryType(int i) {
            return getRuleContext(UnionOrIntersectionOrPrimaryTypeContext.class, i);
        }

        public TerminalNode BitOr() {
            return getToken(TypeScriptParser.BitOr, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitUnion(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PrimaryTypeContext extends ParserRuleContext {
        public PrimaryTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PrimaryTypeContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_primaryType;
        }

        public void copyFrom(PrimaryTypeContext ctx) {
            super.copyFrom(ctx);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RedefinitionOfTypeContext extends PrimaryTypeContext {
        public RedefinitionOfTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public TypeReferenceContext typeReference() {
            return getRuleContext(TypeReferenceContext.class, 0);
        }

        public TerminalNode Is() {
            return getToken(TypeScriptParser.Is, 0);
        }

        public PrimaryTypeContext primaryType() {
            return getRuleContext(PrimaryTypeContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitRedefinitionOfType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PredefinedPrimTypeContext extends PrimaryTypeContext {
        public PredefinedPrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public PredefinedTypeContext predefinedType() {
            return getRuleContext(PredefinedTypeContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPredefinedPrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrayPrimTypeContext extends PrimaryTypeContext {
        public ArrayPrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public PrimaryTypeContext primaryType() {
            return getRuleContext(PrimaryTypeContext.class, 0);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrayPrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ParenthesizedPrimTypeContext extends PrimaryTypeContext {
        public ParenthesizedPrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitParenthesizedPrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ThisPrimTypeContext extends PrimaryTypeContext {
        public ThisPrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode This() {
            return getToken(TypeScriptParser.This, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitThisPrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TuplePrimTypeContext extends PrimaryTypeContext {
        public TuplePrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public TupleElementTypesContext tupleElementTypes() {
            return getRuleContext(TupleElementTypesContext.class, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTuplePrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ObjectPrimTypeContext extends PrimaryTypeContext {
        public ObjectPrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public ObjectTypeContext objectType() {
            return getRuleContext(ObjectTypeContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitObjectPrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ReferencePrimTypeContext extends PrimaryTypeContext {
        public ReferencePrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public TypeReferenceContext typeReference() {
            return getRuleContext(TypeReferenceContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitReferencePrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class QueryPrimTypeContext extends PrimaryTypeContext {
        public QueryPrimTypeContext(PrimaryTypeContext ctx) {
            copyFrom(ctx);
        }

        public TypeQueryContext typeQuery() {
            return getRuleContext(TypeQueryContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitQueryPrimType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PredefinedTypeContext extends ParserRuleContext {
        public PredefinedTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Any() {
            return getToken(TypeScriptParser.Any, 0);
        }

        public TerminalNode Number() {
            return getToken(TypeScriptParser.Number, 0);
        }

        public TerminalNode Boolean() {
            return getToken(TypeScriptParser.Boolean, 0);
        }

        public TerminalNode String() {
            return getToken(TypeScriptParser.String, 0);
        }

        public TerminalNode Symbol() {
            return getToken(TypeScriptParser.Symbol, 0);
        }

        public TerminalNode Void() {
            return getToken(TypeScriptParser.Void, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_predefinedType;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPredefinedType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeReferenceContext extends ParserRuleContext {
        public TypeReferenceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TypeNameContext typeName() {
            return getRuleContext(TypeNameContext.class, 0);
        }

        public NestedTypeGenericContext nestedTypeGeneric() {
            return getRuleContext(NestedTypeGenericContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeReference;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeReference(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NestedTypeGenericContext extends ParserRuleContext {
        public NestedTypeGenericContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TypeIncludeGenericContext typeIncludeGeneric() {
            return getRuleContext(TypeIncludeGenericContext.class, 0);
        }

        public TypeGenericContext typeGeneric() {
            return getRuleContext(TypeGenericContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_nestedTypeGeneric;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitNestedTypeGeneric(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeGenericContext extends ParserRuleContext {
        public TypeGenericContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode LessThan() {
            return getToken(TypeScriptParser.LessThan, 0);
        }

        public TypeArgumentListContext typeArgumentList() {
            return getRuleContext(TypeArgumentListContext.class, 0);
        }

        public TerminalNode MoreThan() {
            return getToken(TypeScriptParser.MoreThan, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeGeneric;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeGeneric(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeIncludeGenericContext extends ParserRuleContext {
        public TypeIncludeGenericContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> LessThan() {
            return getTokens(TypeScriptParser.LessThan);
        }

        public TerminalNode LessThan(int i) {
            return getToken(TypeScriptParser.LessThan, i);
        }

        public List<TypeArgumentListContext> typeArgumentList() {
            return getRuleContexts(TypeArgumentListContext.class);
        }

        public TypeArgumentListContext typeArgumentList(int i) {
            return getRuleContext(TypeArgumentListContext.class, i);
        }

        public List<TerminalNode> MoreThan() {
            return getTokens(TypeScriptParser.MoreThan);
        }

        public TerminalNode MoreThan(int i) {
            return getToken(TypeScriptParser.MoreThan, i);
        }

        public BindingPatternContext bindingPattern() {
            return getRuleContext(BindingPatternContext.class, 0);
        }

        public TerminalNode RightShiftArithmetic() {
            return getToken(TypeScriptParser.RightShiftArithmetic, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeIncludeGeneric;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeIncludeGeneric(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeNameContext extends ParserRuleContext {
        public TypeNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public NamespaceNameContext namespaceName() {
            return getRuleContext(NamespaceNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ObjectTypeContext extends ParserRuleContext {
        public ObjectTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public TypeBodyContext typeBody() {
            return getRuleContext(TypeBodyContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_objectType;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitObjectType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeBodyContext extends ParserRuleContext {
        public TypeBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TypeMemberListContext typeMemberList() {
            return getRuleContext(TypeMemberListContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public TerminalNode Comma() {
            return getToken(TypeScriptParser.Comma, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeBody;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeBody(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeMemberListContext extends ParserRuleContext {
        public TypeMemberListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TypeMemberContext> typeMember() {
            return getRuleContexts(TypeMemberContext.class);
        }

        public TypeMemberContext typeMember(int i) {
            return getRuleContext(TypeMemberContext.class, i);
        }

        public List<TerminalNode> SemiColon() {
            return getTokens(TypeScriptParser.SemiColon);
        }

        public TerminalNode SemiColon(int i) {
            return getToken(TypeScriptParser.SemiColon, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeMemberList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeMemberList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeMemberContext extends ParserRuleContext {
        public TypeMemberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PropertySignaturContext propertySignatur() {
            return getRuleContext(PropertySignaturContext.class, 0);
        }

        public CallSignatureContext callSignature() {
            return getRuleContext(CallSignatureContext.class, 0);
        }

        public ConstructSignatureContext constructSignature() {
            return getRuleContext(ConstructSignatureContext.class, 0);
        }

        public IndexSignatureContext indexSignature() {
            return getRuleContext(IndexSignatureContext.class, 0);
        }

        public MethodSignatureContext methodSignature() {
            return getRuleContext(MethodSignatureContext.class, 0);
        }

        public TerminalNode ARROW() {
            return getToken(TypeScriptParser.ARROW, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeMember;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeMember(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrayTypeContext extends ParserRuleContext {
        public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PrimaryTypeContext primaryType() {
            return getRuleContext(PrimaryTypeContext.class, 0);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrayType;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrayType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TupleTypeContext extends ParserRuleContext {
        public TupleTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public TupleElementTypesContext tupleElementTypes() {
            return getRuleContext(TupleElementTypesContext.class, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tupleType;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTupleType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TupleElementTypesContext extends ParserRuleContext {
        public TupleElementTypesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<Type_Context> type_() {
            return getRuleContexts(Type_Context.class);
        }

        public Type_Context type_(int i) {
            return getRuleContext(Type_Context.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tupleElementTypes;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTupleElementTypes(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FunctionTypeContext extends ParserRuleContext {
        public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode ARROW() {
            return getToken(TypeScriptParser.ARROW, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public ParameterListContext parameterList() {
            return getRuleContext(ParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionType;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFunctionType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ConstructorTypeContext extends ParserRuleContext {
        public ConstructorTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode New() {
            return getToken(TypeScriptParser.New, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode ARROW() {
            return getToken(TypeScriptParser.ARROW, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public ParameterListContext parameterList() {
            return getRuleContext(ParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constructorType;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitConstructorType(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeQueryContext extends ParserRuleContext {
        public TypeQueryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Typeof() {
            return getToken(TypeScriptParser.Typeof, 0);
        }

        public TypeQueryExpressionContext typeQueryExpression() {
            return getRuleContext(TypeQueryExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeQuery;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeQuery(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeQueryExpressionContext extends ParserRuleContext {
        public TypeQueryExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public List<IdentifierNameContext> identifierName() {
            return getRuleContexts(IdentifierNameContext.class);
        }

        public IdentifierNameContext identifierName(int i) {
            return getRuleContext(IdentifierNameContext.class, i);
        }

        public List<TerminalNode> Dot() {
            return getTokens(TypeScriptParser.Dot);
        }

        public TerminalNode Dot(int i) {
            return getToken(TypeScriptParser.Dot, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeQueryExpression;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeQueryExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertySignaturContext extends ParserRuleContext {
        public PropertySignaturContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        public TerminalNode ReadOnly() {
            return getToken(TypeScriptParser.ReadOnly, 0);
        }

        public TerminalNode QuestionMark() {
            return getToken(TypeScriptParser.QuestionMark, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        public TerminalNode ARROW() {
            return getToken(TypeScriptParser.ARROW, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_propertySignatur;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertySignatur(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeAnnotationContext extends ParserRuleContext {
        public TypeAnnotationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeAnnotation;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeAnnotation(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CallSignatureContext extends ParserRuleContext {
        public CallSignatureContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public ParameterListContext parameterList() {
            return getRuleContext(ParameterListContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_callSignature;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitCallSignature(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ParameterListContext extends ParserRuleContext {
        public ParameterListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public RestParameterContext restParameter() {
            return getRuleContext(RestParameterContext.class, 0);
        }

        public List<ParameterContext> parameter() {
            return getRuleContexts(ParameterContext.class);
        }

        public ParameterContext parameter(int i) {
            return getRuleContext(ParameterContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_parameterList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitParameterList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RequiredParameterListContext extends ParserRuleContext {
        public RequiredParameterListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<RequiredParameterContext> requiredParameter() {
            return getRuleContexts(RequiredParameterContext.class);
        }

        public RequiredParameterContext requiredParameter(int i) {
            return getRuleContext(RequiredParameterContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_requiredParameterList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitRequiredParameterList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ParameterContext extends ParserRuleContext {
        public ParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public RequiredParameterContext requiredParameter() {
            return getRuleContext(RequiredParameterContext.class, 0);
        }

        public OptionalParameterContext optionalParameter() {
            return getRuleContext(OptionalParameterContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_parameter;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitParameter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class OptionalParameterContext extends ParserRuleContext {
        public OptionalParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IdentifierOrPatternContext identifierOrPattern() {
            return getRuleContext(IdentifierOrPatternContext.class, 0);
        }

        public DecoratorListContext decoratorList() {
            return getRuleContext(DecoratorListContext.class, 0);
        }

        public TerminalNode QuestionMark() {
            return getToken(TypeScriptParser.QuestionMark, 0);
        }

        public InitializerContext initializer() {
            return getRuleContext(InitializerContext.class, 0);
        }

        public AccessibilityModifierContext accessibilityModifier() {
            return getRuleContext(AccessibilityModifierContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_optionalParameter;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitOptionalParameter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RestParameterContext extends ParserRuleContext {
        public RestParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Ellipsis() {
            return getToken(TypeScriptParser.Ellipsis, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_restParameter;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitRestParameter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RequiredParameterContext extends ParserRuleContext {
        public RequiredParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IdentifierOrPatternContext identifierOrPattern() {
            return getRuleContext(IdentifierOrPatternContext.class, 0);
        }

        public DecoratorListContext decoratorList() {
            return getRuleContext(DecoratorListContext.class, 0);
        }

        public AccessibilityModifierContext accessibilityModifier() {
            return getRuleContext(AccessibilityModifierContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_requiredParameter;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitRequiredParameter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AccessibilityModifierContext extends ParserRuleContext {
        public AccessibilityModifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Public() {
            return getToken(TypeScriptParser.Public, 0);
        }

        public TerminalNode Private() {
            return getToken(TypeScriptParser.Private, 0);
        }

        public TerminalNode Protected() {
            return getToken(TypeScriptParser.Protected, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_accessibilityModifier;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAccessibilityModifier(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IdentifierOrPatternContext extends ParserRuleContext {
        public IdentifierOrPatternContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IdentifierNameContext identifierName() {
            return getRuleContext(IdentifierNameContext.class, 0);
        }

        public BindingPatternContext bindingPattern() {
            return getRuleContext(BindingPatternContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_identifierOrPattern;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIdentifierOrPattern(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ConstructSignatureContext extends ParserRuleContext {
        public ConstructSignatureContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode New() {
            return getToken(TypeScriptParser.New, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public ParameterListContext parameterList() {
            return getRuleContext(ParameterListContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constructSignature;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitConstructSignature(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IndexSignatureContext extends ParserRuleContext {
        public IndexSignatureContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        public TerminalNode Number() {
            return getToken(TypeScriptParser.Number, 0);
        }

        public TerminalNode String() {
            return getToken(TypeScriptParser.String, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_indexSignature;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIndexSignature(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MethodSignatureContext extends ParserRuleContext {
        public MethodSignatureContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        public CallSignatureContext callSignature() {
            return getRuleContext(CallSignatureContext.class, 0);
        }

        public TerminalNode QuestionMark() {
            return getToken(TypeScriptParser.QuestionMark, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_methodSignature;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitMethodSignature(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeAliasDeclarationContext extends ParserRuleContext {
        public TypeAliasDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode TypeAlias() {
            return getToken(TypeScriptParser.TypeAlias, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        public Type_Context type_() {
            return getRuleContext(Type_Context.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeAliasDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeAliasDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ConstructorDeclarationContext extends ParserRuleContext {
        public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Constructor() {
            return getToken(TypeScriptParser.Constructor, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public AccessibilityModifierContext accessibilityModifier() {
            return getRuleContext(AccessibilityModifierContext.class, 0);
        }

        public FormalParameterListContext formalParameterList() {
            return getRuleContext(FormalParameterListContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constructorDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitConstructorDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class InterfaceDeclarationContext extends ParserRuleContext {
        public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Interface() {
            return getToken(TypeScriptParser.Interface, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public ObjectTypeContext objectType() {
            return getRuleContext(ObjectTypeContext.class, 0);
        }

        public TerminalNode Export() {
            return getToken(TypeScriptParser.Export, 0);
        }

        public TerminalNode Declare() {
            return getToken(TypeScriptParser.Declare, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public InterfaceExtendsClauseContext interfaceExtendsClause() {
            return getRuleContext(InterfaceExtendsClauseContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitInterfaceDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class InterfaceExtendsClauseContext extends ParserRuleContext {
        public InterfaceExtendsClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Extends() {
            return getToken(TypeScriptParser.Extends, 0);
        }

        public ClassOrInterfaceTypeListContext classOrInterfaceTypeList() {
            return getRuleContext(ClassOrInterfaceTypeListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceExtendsClause;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitInterfaceExtendsClause(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ClassOrInterfaceTypeListContext extends ParserRuleContext {
        public ClassOrInterfaceTypeListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TypeReferenceContext> typeReference() {
            return getRuleContexts(TypeReferenceContext.class);
        }

        public TypeReferenceContext typeReference(int i) {
            return getRuleContext(TypeReferenceContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classOrInterfaceTypeList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitClassOrInterfaceTypeList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class EnumDeclarationContext extends ParserRuleContext {
        public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Enum() {
            return getToken(TypeScriptParser.Enum, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public TerminalNode Const() {
            return getToken(TypeScriptParser.Const, 0);
        }

        public EnumBodyContext enumBody() {
            return getRuleContext(EnumBodyContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitEnumDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class EnumBodyContext extends ParserRuleContext {
        public EnumBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public EnumMemberListContext enumMemberList() {
            return getRuleContext(EnumMemberListContext.class, 0);
        }

        public TerminalNode Comma() {
            return getToken(TypeScriptParser.Comma, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumBody;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitEnumBody(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class EnumMemberListContext extends ParserRuleContext {
        public EnumMemberListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<EnumMemberContext> enumMember() {
            return getRuleContexts(EnumMemberContext.class);
        }

        public EnumMemberContext enumMember(int i) {
            return getRuleContext(EnumMemberContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumMemberList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitEnumMemberList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class EnumMemberContext extends ParserRuleContext {
        public EnumMemberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumMember;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitEnumMember(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NamespaceDeclarationContext extends ParserRuleContext {
        public NamespaceDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Namespace() {
            return getToken(TypeScriptParser.Namespace, 0);
        }

        public NamespaceNameContext namespaceName() {
            return getRuleContext(NamespaceNameContext.class, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public StatementListContext statementList() {
            return getRuleContext(StatementListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_namespaceDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitNamespaceDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NamespaceNameContext extends ParserRuleContext {
        public NamespaceNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> Identifier() {
            return getTokens(TypeScriptParser.Identifier);
        }

        public TerminalNode Identifier(int i) {
            return getToken(TypeScriptParser.Identifier, i);
        }

        public List<TerminalNode> Dot() {
            return getTokens(TypeScriptParser.Dot);
        }

        public TerminalNode Dot(int i) {
            return getToken(TypeScriptParser.Dot, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_namespaceName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitNamespaceName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ImportAliasDeclarationContext extends ParserRuleContext {
        public ImportAliasDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        public NamespaceNameContext namespaceName() {
            return getRuleContext(NamespaceNameContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_importAliasDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitImportAliasDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DecoratorListContext extends ParserRuleContext {
        public DecoratorListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<DecoratorContext> decorator() {
            return getRuleContexts(DecoratorContext.class);
        }

        public DecoratorContext decorator(int i) {
            return getRuleContext(DecoratorContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_decoratorList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDecoratorList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DecoratorContext extends ParserRuleContext {
        public DecoratorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode At() {
            return getToken(TypeScriptParser.At, 0);
        }

        public DecoratorMemberExpressionContext decoratorMemberExpression() {
            return getRuleContext(DecoratorMemberExpressionContext.class, 0);
        }

        public DecoratorCallExpressionContext decoratorCallExpression() {
            return getRuleContext(DecoratorCallExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_decorator;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDecorator(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DecoratorMemberExpressionContext extends ParserRuleContext {
        public DecoratorMemberExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public DecoratorMemberExpressionContext decoratorMemberExpression() {
            return getRuleContext(DecoratorMemberExpressionContext.class, 0);
        }

        public TerminalNode Dot() {
            return getToken(TypeScriptParser.Dot, 0);
        }

        public IdentifierNameContext identifierName() {
            return getRuleContext(IdentifierNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_decoratorMemberExpression;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDecoratorMemberExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DecoratorCallExpressionContext extends ParserRuleContext {
        public DecoratorCallExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public DecoratorMemberExpressionContext decoratorMemberExpression() {
            return getRuleContext(DecoratorMemberExpressionContext.class, 0);
        }

        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_decoratorCallExpression;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDecoratorCallExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ProgramContext extends ParserRuleContext {
        public ProgramContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode EOF() {
            return getToken(TypeScriptParser.EOF, 0);
        }

        public SourceElementsContext sourceElements() {
            return getRuleContext(SourceElementsContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_program;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitProgram(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SourceElementContext extends ParserRuleContext {
        public SourceElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public TerminalNode Export() {
            return getToken(TypeScriptParser.Export, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_sourceElement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitSourceElement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StatementContext extends ParserRuleContext {
        public StatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public ImportStatementContext importStatement() {
            return getRuleContext(ImportStatementContext.class, 0);
        }

        public ExportStatementContext exportStatement() {
            return getRuleContext(ExportStatementContext.class, 0);
        }

        public EmptyStatement_Context emptyStatement_() {
            return getRuleContext(EmptyStatement_Context.class, 0);
        }

        public AbstractDeclarationContext abstractDeclaration() {
            return getRuleContext(AbstractDeclarationContext.class, 0);
        }

        public DecoratorListContext decoratorList() {
            return getRuleContext(DecoratorListContext.class, 0);
        }

        public ClassDeclarationContext classDeclaration() {
            return getRuleContext(ClassDeclarationContext.class, 0);
        }

        public InterfaceDeclarationContext interfaceDeclaration() {
            return getRuleContext(InterfaceDeclarationContext.class, 0);
        }

        public NamespaceDeclarationContext namespaceDeclaration() {
            return getRuleContext(NamespaceDeclarationContext.class, 0);
        }

        public IfStatementContext ifStatement() {
            return getRuleContext(IfStatementContext.class, 0);
        }

        public IterationStatementContext iterationStatement() {
            return getRuleContext(IterationStatementContext.class, 0);
        }

        public ContinueStatementContext continueStatement() {
            return getRuleContext(ContinueStatementContext.class, 0);
        }

        public BreakStatementContext breakStatement() {
            return getRuleContext(BreakStatementContext.class, 0);
        }

        public ReturnStatementContext returnStatement() {
            return getRuleContext(ReturnStatementContext.class, 0);
        }

        public YieldStatementContext yieldStatement() {
            return getRuleContext(YieldStatementContext.class, 0);
        }

        public WithStatementContext withStatement() {
            return getRuleContext(WithStatementContext.class, 0);
        }

        public LabelledStatementContext labelledStatement() {
            return getRuleContext(LabelledStatementContext.class, 0);
        }

        public SwitchStatementContext switchStatement() {
            return getRuleContext(SwitchStatementContext.class, 0);
        }

        public ThrowStatementContext throwStatement() {
            return getRuleContext(ThrowStatementContext.class, 0);
        }

        public TryStatementContext tryStatement() {
            return getRuleContext(TryStatementContext.class, 0);
        }

        public DebuggerStatementContext debuggerStatement() {
            return getRuleContext(DebuggerStatementContext.class, 0);
        }

        public FunctionDeclarationContext functionDeclaration() {
            return getRuleContext(FunctionDeclarationContext.class, 0);
        }

        public ArrowFunctionDeclarationContext arrowFunctionDeclaration() {
            return getRuleContext(ArrowFunctionDeclarationContext.class, 0);
        }

        public GeneratorFunctionDeclarationContext generatorFunctionDeclaration() {
            return getRuleContext(GeneratorFunctionDeclarationContext.class, 0);
        }

        public VariableStatementContext variableStatement() {
            return getRuleContext(VariableStatementContext.class, 0);
        }

        public TypeAliasDeclarationContext typeAliasDeclaration() {
            return getRuleContext(TypeAliasDeclarationContext.class, 0);
        }

        public EnumDeclarationContext enumDeclaration() {
            return getRuleContext(EnumDeclarationContext.class, 0);
        }

        public ExpressionStatementContext expressionStatement() {
            return getRuleContext(ExpressionStatementContext.class, 0);
        }

        public TerminalNode Export() {
            return getToken(TypeScriptParser.Export, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_statement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BlockContext extends ParserRuleContext {
        public BlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public StatementListContext statementList() {
            return getRuleContext(StatementListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_block;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StatementListContext extends ParserRuleContext {
        public StatementListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_statementList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitStatementList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AbstractDeclarationContext extends ParserRuleContext {
        public AbstractDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Abstract() {
            return getToken(TypeScriptParser.Abstract, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public CallSignatureContext callSignature() {
            return getRuleContext(CallSignatureContext.class, 0);
        }

        public VariableStatementContext variableStatement() {
            return getRuleContext(VariableStatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_abstractDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAbstractDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ImportStatementContext extends ParserRuleContext {
        public ImportStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Import() {
            return getToken(TypeScriptParser.Import, 0);
        }

        public FromBlockContext fromBlock() {
            return getRuleContext(FromBlockContext.class, 0);
        }

        public ImportAliasDeclarationContext importAliasDeclaration() {
            return getRuleContext(ImportAliasDeclarationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_importStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitImportStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FromBlockContext extends ParserRuleContext {
        public FromBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode From() {
            return getToken(TypeScriptParser.From, 0);
        }

        public TerminalNode StringLiteral() {
            return getToken(TypeScriptParser.StringLiteral, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        public TerminalNode Multiply() {
            return getToken(TypeScriptParser.Multiply, 0);
        }

        public MultipleImportStatementContext multipleImportStatement() {
            return getRuleContext(MultipleImportStatementContext.class, 0);
        }

        public List<IdentifierNameContext> identifierName() {
            return getRuleContexts(IdentifierNameContext.class);
        }

        public IdentifierNameContext identifierName(int i) {
            return getRuleContext(IdentifierNameContext.class, i);
        }

        public TerminalNode As() {
            return getToken(TypeScriptParser.As, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_fromBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFromBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MultipleImportStatementContext extends ParserRuleContext {
        public MultipleImportStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public List<IdentifierNameContext> identifierName() {
            return getRuleContexts(IdentifierNameContext.class);
        }

        public IdentifierNameContext identifierName(int i) {
            return getRuleContext(IdentifierNameContext.class, i);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_multipleImportStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitMultipleImportStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ExportStatementContext extends ParserRuleContext {
        public ExportStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Export() {
            return getToken(TypeScriptParser.Export, 0);
        }

        public FromBlockContext fromBlock() {
            return getRuleContext(FromBlockContext.class, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public TerminalNode Default() {
            return getToken(TypeScriptParser.Default, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_exportStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitExportStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class VariableStatementContext extends ParserRuleContext {
        public VariableStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public BindingPatternContext bindingPattern() {
            return getRuleContext(BindingPatternContext.class, 0);
        }

        public InitializerContext initializer() {
            return getRuleContext(InitializerContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public VariableDeclarationListContext variableDeclarationList() {
            return getRuleContext(VariableDeclarationListContext.class, 0);
        }

        public AccessibilityModifierContext accessibilityModifier() {
            return getRuleContext(AccessibilityModifierContext.class, 0);
        }

        public VarModifierContext varModifier() {
            return getRuleContext(VarModifierContext.class, 0);
        }

        public TerminalNode ReadOnly() {
            return getToken(TypeScriptParser.ReadOnly, 0);
        }

        public TerminalNode Declare() {
            return getToken(TypeScriptParser.Declare, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitVariableStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class VariableDeclarationListContext extends ParserRuleContext {
        public VariableDeclarationListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<VariableDeclarationContext> variableDeclaration() {
            return getRuleContexts(VariableDeclarationContext.class);
        }

        public VariableDeclarationContext variableDeclaration(int i) {
            return getRuleContext(VariableDeclarationContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableDeclarationList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitVariableDeclarationList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class VariableDeclarationContext extends ParserRuleContext {
        public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IdentifierOrKeyWordContext identifierOrKeyWord() {
            return getRuleContext(IdentifierOrKeyWordContext.class, 0);
        }

        public ArrayLiteralContext arrayLiteral() {
            return getRuleContext(ArrayLiteralContext.class, 0);
        }

        public ObjectLiteralContext objectLiteral() {
            return getRuleContext(ObjectLiteralContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitVariableDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class EmptyStatement_Context extends ParserRuleContext {
        public EmptyStatement_Context(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_emptyStatement_;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitEmptyStatement_(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ExpressionStatementContext extends ParserRuleContext {
        public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expressionStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitExpressionStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IfStatementContext extends ParserRuleContext {
        public IfStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode If() {
            return getToken(TypeScriptParser.If, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        public TerminalNode Else() {
            return getToken(TypeScriptParser.Else, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_ifStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIfStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IterationStatementContext extends ParserRuleContext {
        public IterationStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IterationStatementContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_iterationStatement;
        }

        public void copyFrom(IterationStatementContext ctx) {
            super.copyFrom(ctx);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DoStatementContext extends IterationStatementContext {
        public DoStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Do() {
            return getToken(TypeScriptParser.Do, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public TerminalNode While() {
            return getToken(TypeScriptParser.While, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDoStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ForVarStatementContext extends IterationStatementContext {
        public ForVarStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode For() {
            return getToken(TypeScriptParser.For, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public VarModifierContext varModifier() {
            return getRuleContext(VarModifierContext.class, 0);
        }

        public VariableDeclarationListContext variableDeclarationList() {
            return getRuleContext(VariableDeclarationListContext.class, 0);
        }

        public List<TerminalNode> SemiColon() {
            return getTokens(TypeScriptParser.SemiColon);
        }

        public TerminalNode SemiColon(int i) {
            return getToken(TypeScriptParser.SemiColon, i);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public List<ExpressionSequenceContext> expressionSequence() {
            return getRuleContexts(ExpressionSequenceContext.class);
        }

        public ExpressionSequenceContext expressionSequence(int i) {
            return getRuleContext(ExpressionSequenceContext.class, i);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitForVarStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ForVarInStatementContext extends IterationStatementContext {
        public ForVarInStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode For() {
            return getToken(TypeScriptParser.For, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public VarModifierContext varModifier() {
            return getRuleContext(VarModifierContext.class, 0);
        }

        public VariableDeclarationContext variableDeclaration() {
            return getRuleContext(VariableDeclarationContext.class, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public TerminalNode In() {
            return getToken(TypeScriptParser.In, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitForVarInStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class WhileStatementContext extends IterationStatementContext {
        public WhileStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode While() {
            return getToken(TypeScriptParser.While, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitWhileStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ForStatementContext extends IterationStatementContext {
        public ForStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode For() {
            return getToken(TypeScriptParser.For, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public List<TerminalNode> SemiColon() {
            return getTokens(TypeScriptParser.SemiColon);
        }

        public TerminalNode SemiColon(int i) {
            return getToken(TypeScriptParser.SemiColon, i);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public List<ExpressionSequenceContext> expressionSequence() {
            return getRuleContexts(ExpressionSequenceContext.class);
        }

        public ExpressionSequenceContext expressionSequence(int i) {
            return getRuleContext(ExpressionSequenceContext.class, i);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitForStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ForInStatementContext extends IterationStatementContext {
        public ForInStatementContext(IterationStatementContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode For() {
            return getToken(TypeScriptParser.For, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public TerminalNode In() {
            return getToken(TypeScriptParser.In, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitForInStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class VarModifierContext extends ParserRuleContext {
        public VarModifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Var() {
            return getToken(TypeScriptParser.Var, 0);
        }

        public TerminalNode Let() {
            return getToken(TypeScriptParser.Let, 0);
        }

        public TerminalNode Const() {
            return getToken(TypeScriptParser.Const, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_varModifier;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitVarModifier(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ContinueStatementContext extends ParserRuleContext {
        public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Continue() {
            return getToken(TypeScriptParser.Continue, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_continueStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitContinueStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BreakStatementContext extends ParserRuleContext {
        public BreakStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Break() {
            return getToken(TypeScriptParser.Break, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_breakStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBreakStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ReturnStatementContext extends ParserRuleContext {
        public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Return() {
            return getToken(TypeScriptParser.Return, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public HtmlElementsContext htmlElements() {
            return getRuleContext(HtmlElementsContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_returnStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitReturnStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class YieldStatementContext extends ParserRuleContext {
        public YieldStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Yield() {
            return getToken(TypeScriptParser.Yield, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_yieldStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitYieldStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class WithStatementContext extends ParserRuleContext {
        public WithStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode With() {
            return getToken(TypeScriptParser.With, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_withStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitWithStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SwitchStatementContext extends ParserRuleContext {
        public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Switch() {
            return getToken(TypeScriptParser.Switch, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public CaseBlockContext caseBlock() {
            return getRuleContext(CaseBlockContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_switchStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitSwitchStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CaseBlockContext extends ParserRuleContext {
        public CaseBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public List<CaseClausesContext> caseClauses() {
            return getRuleContexts(CaseClausesContext.class);
        }

        public CaseClausesContext caseClauses(int i) {
            return getRuleContext(CaseClausesContext.class, i);
        }

        public DefaultClauseContext defaultClause() {
            return getRuleContext(DefaultClauseContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_caseBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitCaseBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CaseClausesContext extends ParserRuleContext {
        public CaseClausesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<CaseClauseContext> caseClause() {
            return getRuleContexts(CaseClauseContext.class);
        }

        public CaseClauseContext caseClause(int i) {
            return getRuleContext(CaseClauseContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_caseClauses;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitCaseClauses(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CaseClauseContext extends ParserRuleContext {
        public CaseClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Case() {
            return getToken(TypeScriptParser.Case, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        public StatementListContext statementList() {
            return getRuleContext(StatementListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_caseClause;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitCaseClause(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DefaultClauseContext extends ParserRuleContext {
        public DefaultClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Default() {
            return getToken(TypeScriptParser.Default, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        public StatementListContext statementList() {
            return getRuleContext(StatementListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_defaultClause;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDefaultClause(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LabelledStatementContext extends ParserRuleContext {
        public LabelledStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_labelledStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitLabelledStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ThrowStatementContext extends ParserRuleContext {
        public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Throw() {
            return getToken(TypeScriptParser.Throw, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_throwStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitThrowStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TryStatementContext extends ParserRuleContext {
        public TryStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Try() {
            return getToken(TypeScriptParser.Try, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public CatchProductionContext catchProduction() {
            return getRuleContext(CatchProductionContext.class, 0);
        }

        public FinallyProductionContext finallyProduction() {
            return getRuleContext(FinallyProductionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tryStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTryStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CatchProductionContext extends ParserRuleContext {
        public CatchProductionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Catch() {
            return getToken(TypeScriptParser.Catch, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_catchProduction;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitCatchProduction(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FinallyProductionContext extends ParserRuleContext {
        public FinallyProductionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Finally() {
            return getToken(TypeScriptParser.Finally, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_finallyProduction;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFinallyProduction(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DebuggerStatementContext extends ParserRuleContext {
        public DebuggerStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Debugger() {
            return getToken(TypeScriptParser.Debugger, 0);
        }

        public EosContext eos() {
            return getRuleContext(EosContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_debuggerStatement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDebuggerStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FunctionDeclarationContext extends ParserRuleContext {
        public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Function_() {
            return getToken(TypeScriptParser.Function_, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public CallSignatureContext callSignature() {
            return getRuleContext(CallSignatureContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public TerminalNode Async() {
            return getToken(TypeScriptParser.Async, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFunctionDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ClassDeclarationContext extends ParserRuleContext {
        public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Class() {
            return getToken(TypeScriptParser.Class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public ClassHeritageContext classHeritage() {
            return getRuleContext(ClassHeritageContext.class, 0);
        }

        public ClassTailContext classTail() {
            return getRuleContext(ClassTailContext.class, 0);
        }

        public TerminalNode Abstract() {
            return getToken(TypeScriptParser.Abstract, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitClassDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ClassHeritageContext extends ParserRuleContext {
        public ClassHeritageContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ClassExtendsClauseContext classExtendsClause() {
            return getRuleContext(ClassExtendsClauseContext.class, 0);
        }

        public ImplementsClauseContext implementsClause() {
            return getRuleContext(ImplementsClauseContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classHeritage;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitClassHeritage(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ClassTailContext extends ParserRuleContext {
        public ClassTailContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public List<ClassElementContext> classElement() {
            return getRuleContexts(ClassElementContext.class);
        }

        public ClassElementContext classElement(int i) {
            return getRuleContext(ClassElementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classTail;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitClassTail(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ClassExtendsClauseContext extends ParserRuleContext {
        public ClassExtendsClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Extends() {
            return getToken(TypeScriptParser.Extends, 0);
        }

        public TypeReferenceContext typeReference() {
            return getRuleContext(TypeReferenceContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classExtendsClause;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitClassExtendsClause(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ImplementsClauseContext extends ParserRuleContext {
        public ImplementsClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Implements() {
            return getToken(TypeScriptParser.Implements, 0);
        }

        public ClassOrInterfaceTypeListContext classOrInterfaceTypeList() {
            return getRuleContext(ClassOrInterfaceTypeListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_implementsClause;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitImplementsClause(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ClassElementContext extends ParserRuleContext {
        public ClassElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ConstructorDeclarationContext constructorDeclaration() {
            return getRuleContext(ConstructorDeclarationContext.class, 0);
        }

        public PropertyMemberDeclarationContext propertyMemberDeclaration() {
            return getRuleContext(PropertyMemberDeclarationContext.class, 0);
        }

        public DecoratorListContext decoratorList() {
            return getRuleContext(DecoratorListContext.class, 0);
        }

        public IndexMemberDeclarationContext indexMemberDeclaration() {
            return getRuleContext(IndexMemberDeclarationContext.class, 0);
        }

        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classElement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitClassElement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyMemberDeclarationContext extends ParserRuleContext {
        public PropertyMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PropertyMemberDeclarationContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_propertyMemberDeclaration;
        }

        public void copyFrom(PropertyMemberDeclarationContext ctx) {
            super.copyFrom(ctx);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyDeclarationExpressionContext extends PropertyMemberDeclarationContext {
        public PropertyDeclarationExpressionContext(PropertyMemberDeclarationContext ctx) {
            copyFrom(ctx);
        }

        public PropertyMemberBaseContext propertyMemberBase() {
            return getRuleContext(PropertyMemberBaseContext.class, 0);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public TerminalNode QuestionMark() {
            return getToken(TypeScriptParser.QuestionMark, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        public InitializerContext initializer() {
            return getRuleContext(InitializerContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertyDeclarationExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MethodDeclarationExpressionContext extends PropertyMemberDeclarationContext {
        public MethodDeclarationExpressionContext(PropertyMemberDeclarationContext ctx) {
            copyFrom(ctx);
        }

        public PropertyMemberBaseContext propertyMemberBase() {
            return getRuleContext(PropertyMemberBaseContext.class, 0);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        public CallSignatureContext callSignature() {
            return getRuleContext(CallSignatureContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitMethodDeclarationExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GetterSetterDeclarationExpressionContext extends PropertyMemberDeclarationContext {
        public GetterSetterDeclarationExpressionContext(PropertyMemberDeclarationContext ctx) {
            copyFrom(ctx);
        }

        public PropertyMemberBaseContext propertyMemberBase() {
            return getRuleContext(PropertyMemberBaseContext.class, 0);
        }

        public GetAccessorContext getAccessor() {
            return getRuleContext(GetAccessorContext.class, 0);
        }

        public SetAccessorContext setAccessor() {
            return getRuleContext(SetAccessorContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGetterSetterDeclarationExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AbstractMemberDeclarationContext extends PropertyMemberDeclarationContext {
        public AbstractMemberDeclarationContext(PropertyMemberDeclarationContext ctx) {
            copyFrom(ctx);
        }

        public AbstractDeclarationContext abstractDeclaration() {
            return getRuleContext(AbstractDeclarationContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAbstractMemberDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyMemberBaseContext extends ParserRuleContext {
        public PropertyMemberBaseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Async() {
            return getToken(TypeScriptParser.Async, 0);
        }

        public AccessibilityModifierContext accessibilityModifier() {
            return getRuleContext(AccessibilityModifierContext.class, 0);
        }

        public TerminalNode Static() {
            return getToken(TypeScriptParser.Static, 0);
        }

        public TerminalNode ReadOnly() {
            return getToken(TypeScriptParser.ReadOnly, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_propertyMemberBase;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertyMemberBase(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IndexMemberDeclarationContext extends ParserRuleContext {
        public IndexMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IndexSignatureContext indexSignature() {
            return getRuleContext(IndexSignatureContext.class, 0);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_indexMemberDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIndexMemberDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GeneratorMethodContext extends ParserRuleContext {
        public GeneratorMethodContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public TerminalNode Multiply() {
            return getToken(TypeScriptParser.Multiply, 0);
        }

        public FormalParameterListContext formalParameterList() {
            return getRuleContext(FormalParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_generatorMethod;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGeneratorMethod(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GeneratorFunctionDeclarationContext extends ParserRuleContext {
        public GeneratorFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Function_() {
            return getToken(TypeScriptParser.Function_, 0);
        }

        public TerminalNode Multiply() {
            return getToken(TypeScriptParser.Multiply, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public FormalParameterListContext formalParameterList() {
            return getRuleContext(FormalParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_generatorFunctionDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGeneratorFunctionDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GeneratorBlockContext extends ParserRuleContext {
        public GeneratorBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public List<GeneratorDefinitionContext> generatorDefinition() {
            return getRuleContexts(GeneratorDefinitionContext.class);
        }

        public GeneratorDefinitionContext generatorDefinition(int i) {
            return getRuleContext(GeneratorDefinitionContext.class, i);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_generatorBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGeneratorBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GeneratorDefinitionContext extends ParserRuleContext {
        public GeneratorDefinitionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Multiply() {
            return getToken(TypeScriptParser.Multiply, 0);
        }

        public IteratorDefinitionContext iteratorDefinition() {
            return getRuleContext(IteratorDefinitionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_generatorDefinition;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGeneratorDefinition(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IteratorBlockContext extends ParserRuleContext {
        public IteratorBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public List<IteratorDefinitionContext> iteratorDefinition() {
            return getRuleContexts(IteratorDefinitionContext.class);
        }

        public IteratorDefinitionContext iteratorDefinition(int i) {
            return getRuleContext(IteratorDefinitionContext.class, i);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_iteratorBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIteratorBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IteratorDefinitionContext extends ParserRuleContext {
        public IteratorDefinitionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public FormalParameterListContext formalParameterList() {
            return getRuleContext(FormalParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_iteratorDefinition;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIteratorDefinition(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FormalParameterListContext extends ParserRuleContext {
        public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<FormalParameterArgContext> formalParameterArg() {
            return getRuleContexts(FormalParameterArgContext.class);
        }

        public FormalParameterArgContext formalParameterArg(int i) {
            return getRuleContext(FormalParameterArgContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        public LastFormalParameterArgContext lastFormalParameterArg() {
            return getRuleContext(LastFormalParameterArgContext.class, 0);
        }

        public ArrayLiteralContext arrayLiteral() {
            return getRuleContext(ArrayLiteralContext.class, 0);
        }

        public ObjectLiteralContext objectLiteral() {
            return getRuleContext(ObjectLiteralContext.class, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        public FormalParameterListContext formalParameterList() {
            return getRuleContext(FormalParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formalParameterList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFormalParameterList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FormalParameterArgContext extends ParserRuleContext {
        public FormalParameterArgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IdentifierOrKeyWordContext identifierOrKeyWord() {
            return getRuleContext(IdentifierOrKeyWordContext.class, 0);
        }

        public DecoratorContext decorator() {
            return getRuleContext(DecoratorContext.class, 0);
        }

        public AccessibilityModifierContext accessibilityModifier() {
            return getRuleContext(AccessibilityModifierContext.class, 0);
        }

        public TerminalNode QuestionMark() {
            return getToken(TypeScriptParser.QuestionMark, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formalParameterArg;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFormalParameterArg(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LastFormalParameterArgContext extends ParserRuleContext {
        public LastFormalParameterArgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Ellipsis() {
            return getToken(TypeScriptParser.Ellipsis, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_lastFormalParameterArg;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitLastFormalParameterArg(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FunctionBodyContext extends ParserRuleContext {
        public FunctionBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SourceElementsContext sourceElements() {
            return getRuleContext(SourceElementsContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionBody;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFunctionBody(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SourceElementsContext extends ParserRuleContext {
        public SourceElementsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<SourceElementContext> sourceElement() {
            return getRuleContexts(SourceElementContext.class);
        }

        public SourceElementContext sourceElement(int i) {
            return getRuleContext(SourceElementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_sourceElements;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitSourceElements(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrayLiteralContext extends ParserRuleContext {
        public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        public ElementListContext elementList() {
            return getRuleContext(ElementListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrayLiteral;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrayLiteral(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ElementListContext extends ParserRuleContext {
        public ElementListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<ArrayElementContext> arrayElement() {
            return getRuleContexts(ArrayElementContext.class);
        }

        public ArrayElementContext arrayElement(int i) {
            return getRuleContext(ArrayElementContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_elementList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitElementList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrayElementContext extends ParserRuleContext {
        public ArrayElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode Ellipsis() {
            return getToken(TypeScriptParser.Ellipsis, 0);
        }

        public TerminalNode Comma() {
            return getToken(TypeScriptParser.Comma, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrayElement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrayElement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ObjectLiteralContext extends ParserRuleContext {
        public ObjectLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public List<PropertyAssignmentContext> propertyAssignment() {
            return getRuleContexts(PropertyAssignmentContext.class);
        }

        public PropertyAssignmentContext propertyAssignment(int i) {
            return getRuleContext(PropertyAssignmentContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_objectLiteral;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitObjectLiteral(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyAssignmentContext extends ParserRuleContext {
        public PropertyAssignmentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PropertyAssignmentContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_propertyAssignment;
        }

        public void copyFrom(PropertyAssignmentContext ctx) {
            super.copyFrom(ctx);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyExpressionAssignmentContext extends PropertyAssignmentContext {
        public PropertyExpressionAssignmentContext(PropertyAssignmentContext ctx) {
            copyFrom(ctx);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertyExpressionAssignment(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ComputedPropertyExpressionAssignmentContext extends PropertyAssignmentContext {
        public ComputedPropertyExpressionAssignmentContext(PropertyAssignmentContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitComputedPropertyExpressionAssignment(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyShorthandContext extends PropertyAssignmentContext {
        public PropertyShorthandContext(PropertyAssignmentContext ctx) {
            copyFrom(ctx);
        }

        public IdentifierOrKeyWordContext identifierOrKeyWord() {
            return getRuleContext(IdentifierOrKeyWordContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertyShorthand(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertySetterContext extends PropertyAssignmentContext {
        public PropertySetterContext(PropertyAssignmentContext ctx) {
            copyFrom(ctx);
        }

        public SetAccessorContext setAccessor() {
            return getRuleContext(SetAccessorContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertySetter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyGetterContext extends PropertyAssignmentContext {
        public PropertyGetterContext(PropertyAssignmentContext ctx) {
            copyFrom(ctx);
        }

        public GetAccessorContext getAccessor() {
            return getRuleContext(GetAccessorContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertyGetter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RestParameterInObjectContext extends PropertyAssignmentContext {
        public RestParameterInObjectContext(PropertyAssignmentContext ctx) {
            copyFrom(ctx);
        }

        public RestParameterContext restParameter() {
            return getRuleContext(RestParameterContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitRestParameterInObject(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MethodPropertyContext extends PropertyAssignmentContext {
        public MethodPropertyContext(PropertyAssignmentContext ctx) {
            copyFrom(ctx);
        }

        public GeneratorMethodContext generatorMethod() {
            return getRuleContext(GeneratorMethodContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitMethodProperty(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GetAccessorContext extends ParserRuleContext {
        public GetAccessorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public GetterContext getter() {
            return getRuleContext(GetterContext.class, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_getAccessor;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGetAccessor(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetAccessorContext extends ParserRuleContext {
        public SetAccessorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SetterContext setter() {
            return getRuleContext(SetterContext.class, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public BindingPatternContext bindingPattern() {
            return getRuleContext(BindingPatternContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setAccessor;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitSetAccessor(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PropertyNameContext extends ParserRuleContext {
        public PropertyNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public IdentifierNameContext identifierName() {
            return getRuleContext(IdentifierNameContext.class, 0);
        }

        public TerminalNode StringLiteral() {
            return getToken(TypeScriptParser.StringLiteral, 0);
        }

        public NumericLiteralContext numericLiteral() {
            return getRuleContext(NumericLiteralContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_propertyName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPropertyName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArgumentsContext extends ParserRuleContext {
        public ArgumentsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public ArgumentListContext argumentList() {
            return getRuleContext(ArgumentListContext.class, 0);
        }

        public TerminalNode Comma() {
            return getToken(TypeScriptParser.Comma, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arguments;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArguments(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArgumentListContext extends ParserRuleContext {
        public ArgumentListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<ArgumentContext> argument() {
            return getRuleContexts(ArgumentContext.class);
        }

        public ArgumentContext argument(int i) {
            return getRuleContext(ArgumentContext.class, i);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_argumentList;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArgumentList(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArgumentContext extends ParserRuleContext {
        public ArgumentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode Ellipsis() {
            return getToken(TypeScriptParser.Ellipsis, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_argument;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArgument(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ExpressionSequenceContext extends ParserRuleContext {
        public ExpressionSequenceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Ellipsis() {
            return getToken(TypeScriptParser.Ellipsis, 0);
        }

        public List<TerminalNode> Comma() {
            return getTokens(TypeScriptParser.Comma);
        }

        public TerminalNode Comma(int i) {
            return getToken(TypeScriptParser.Comma, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expressionSequence;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitExpressionSequence(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FunctionExpressionDeclarationContext extends ParserRuleContext {
        public FunctionExpressionDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Function_() {
            return getToken(TypeScriptParser.Function_, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public FormalParameterListContext formalParameterList() {
            return getRuleContext(FormalParameterListContext.class, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_functionExpressionDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFunctionExpressionDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SingleExpressionContext extends ParserRuleContext {
        public SingleExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SingleExpressionContext() {
        }

        @Override
        public int getRuleIndex() {
            return RULE_singleExpression;
        }

        public void copyFrom(SingleExpressionContext ctx) {
            super.copyFrom(ctx);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TemplateStringExpressionContext extends SingleExpressionContext {
        public TemplateStringExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TemplateStringLiteralContext templateStringLiteral() {
            return getRuleContext(TemplateStringLiteralContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTemplateStringExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TernaryExpressionContext extends SingleExpressionContext {
        public TernaryExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode QuestionMark() {
            return getToken(TypeScriptParser.QuestionMark, 0);
        }

        public TerminalNode Colon() {
            return getToken(TypeScriptParser.Colon, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTernaryExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LogicalAndExpressionContext extends SingleExpressionContext {
        public LogicalAndExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode And() {
            return getToken(TypeScriptParser.And, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitLogicalAndExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GeneratorsExpressionContext extends SingleExpressionContext {
        public GeneratorsExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public GeneratorBlockContext generatorBlock() {
            return getRuleContext(GeneratorBlockContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGeneratorsExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PreIncrementExpressionContext extends SingleExpressionContext {
        public PreIncrementExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode PlusPlus() {
            return getToken(TypeScriptParser.PlusPlus, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPreIncrementExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ObjectLiteralExpressionContext extends SingleExpressionContext {
        public ObjectLiteralExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ObjectLiteralContext objectLiteral() {
            return getRuleContext(ObjectLiteralContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitObjectLiteralExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class InExpressionContext extends SingleExpressionContext {
        public InExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode In() {
            return getToken(TypeScriptParser.In, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitInExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LogicalOrExpressionContext extends SingleExpressionContext {
        public LogicalOrExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Or() {
            return getToken(TypeScriptParser.Or, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitLogicalOrExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GenericTypesContext extends SingleExpressionContext {
        public GenericTypesContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TypeArgumentsContext typeArguments() {
            return getRuleContext(TypeArgumentsContext.class, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGenericTypes(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NotExpressionContext extends SingleExpressionContext {
        public NotExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Not() {
            return getToken(TypeScriptParser.Not, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitNotExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PreDecreaseExpressionContext extends SingleExpressionContext {
        public PreDecreaseExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode MinusMinus() {
            return getToken(TypeScriptParser.MinusMinus, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPreDecreaseExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArgumentsExpressionContext extends SingleExpressionContext {
        public ArgumentsExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArgumentsExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ThisExpressionContext extends SingleExpressionContext {
        public ThisExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode This() {
            return getToken(TypeScriptParser.This, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitThisExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FunctionExpressionContext extends SingleExpressionContext {
        public FunctionExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public FunctionExpressionDeclarationContext functionExpressionDeclaration() {
            return getRuleContext(FunctionExpressionDeclarationContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitFunctionExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class UnaryMinusExpressionContext extends SingleExpressionContext {
        public UnaryMinusExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Minus() {
            return getToken(TypeScriptParser.Minus, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitUnaryMinusExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AssignmentExpressionContext extends SingleExpressionContext {
        public AssignmentExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Assign() {
            return getToken(TypeScriptParser.Assign, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAssignmentExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PostDecreaseExpressionContext extends SingleExpressionContext {
        public PostDecreaseExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode MinusMinus() {
            return getToken(TypeScriptParser.MinusMinus, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPostDecreaseExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TypeofExpressionContext extends SingleExpressionContext {
        public TypeofExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Typeof() {
            return getToken(TypeScriptParser.Typeof, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTypeofExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class InstanceofExpressionContext extends SingleExpressionContext {
        public InstanceofExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Instanceof() {
            return getToken(TypeScriptParser.Instanceof, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitInstanceofExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class UnaryPlusExpressionContext extends SingleExpressionContext {
        public UnaryPlusExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Plus() {
            return getToken(TypeScriptParser.Plus, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitUnaryPlusExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DeleteExpressionContext extends SingleExpressionContext {
        public DeleteExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Delete() {
            return getToken(TypeScriptParser.Delete, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitDeleteExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GeneratorsFunctionExpressionContext extends SingleExpressionContext {
        public GeneratorsFunctionExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public GeneratorFunctionDeclarationContext generatorFunctionDeclaration() {
            return getRuleContext(GeneratorFunctionDeclarationContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGeneratorsFunctionExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrowFunctionExpressionContext extends SingleExpressionContext {
        public ArrowFunctionExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ArrowFunctionDeclarationContext arrowFunctionDeclaration() {
            return getRuleContext(ArrowFunctionDeclarationContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrowFunctionExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IteratorsExpressionContext extends SingleExpressionContext {
        public IteratorsExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public IteratorBlockContext iteratorBlock() {
            return getRuleContext(IteratorBlockContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIteratorsExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class EqualityExpressionContext extends SingleExpressionContext {
        public EqualityExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Equals_() {
            return getToken(TypeScriptParser.Equals_, 0);
        }

        public TerminalNode NotEquals() {
            return getToken(TypeScriptParser.NotEquals, 0);
        }

        public TerminalNode IdentityEquals() {
            return getToken(TypeScriptParser.IdentityEquals, 0);
        }

        public TerminalNode IdentityNotEquals() {
            return getToken(TypeScriptParser.IdentityNotEquals, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitEqualityExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BitXOrExpressionContext extends SingleExpressionContext {
        public BitXOrExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode BitXOr() {
            return getToken(TypeScriptParser.BitXOr, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBitXOrExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CastAsExpressionContext extends SingleExpressionContext {
        public CastAsExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode As() {
            return getToken(TypeScriptParser.As, 0);
        }

        public AsExpressionContext asExpression() {
            return getRuleContext(AsExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitCastAsExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SuperExpressionContext extends SingleExpressionContext {
        public SuperExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Super() {
            return getToken(TypeScriptParser.Super, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitSuperExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MultiplicativeExpressionContext extends SingleExpressionContext {
        public MultiplicativeExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Multiply() {
            return getToken(TypeScriptParser.Multiply, 0);
        }

        public TerminalNode Divide() {
            return getToken(TypeScriptParser.Divide, 0);
        }

        public TerminalNode Modulus() {
            return getToken(TypeScriptParser.Modulus, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitMultiplicativeExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtmlElementExpressionContext extends SingleExpressionContext {
        public HtmlElementExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public HtmlElementsContext htmlElements() {
            return getRuleContext(HtmlElementsContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitHtmlElementExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BitShiftExpressionContext extends SingleExpressionContext {
        public BitShiftExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode LeftShiftArithmetic() {
            return getToken(TypeScriptParser.LeftShiftArithmetic, 0);
        }

        public TerminalNode RightShiftArithmetic() {
            return getToken(TypeScriptParser.RightShiftArithmetic, 0);
        }

        public TerminalNode RightShiftLogical() {
            return getToken(TypeScriptParser.RightShiftLogical, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBitShiftExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ParenthesizedExpressionContext extends SingleExpressionContext {
        public ParenthesizedExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitParenthesizedExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AdditiveExpressionContext extends SingleExpressionContext {
        public AdditiveExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode Plus() {
            return getToken(TypeScriptParser.Plus, 0);
        }

        public TerminalNode Minus() {
            return getToken(TypeScriptParser.Minus, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAdditiveExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RelationalExpressionContext extends SingleExpressionContext {
        public RelationalExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode LessThan() {
            return getToken(TypeScriptParser.LessThan, 0);
        }

        public TerminalNode MoreThan() {
            return getToken(TypeScriptParser.MoreThan, 0);
        }

        public TerminalNode LessThanEquals() {
            return getToken(TypeScriptParser.LessThanEquals, 0);
        }

        public TerminalNode GreaterThanEquals() {
            return getToken(TypeScriptParser.GreaterThanEquals, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitRelationalExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PostIncrementExpressionContext extends SingleExpressionContext {
        public PostIncrementExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode PlusPlus() {
            return getToken(TypeScriptParser.PlusPlus, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitPostIncrementExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class YieldExpressionContext extends SingleExpressionContext {
        public YieldExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public YieldStatementContext yieldStatement() {
            return getRuleContext(YieldStatementContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitYieldExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BitNotExpressionContext extends SingleExpressionContext {
        public BitNotExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode BitNot() {
            return getToken(TypeScriptParser.BitNot, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBitNotExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NewExpressionContext extends SingleExpressionContext {
        public NewExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode New() {
            return getToken(TypeScriptParser.New, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        public TypeArgumentsContext typeArguments() {
            return getRuleContext(TypeArgumentsContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitNewExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LiteralExpressionContext extends SingleExpressionContext {
        public LiteralExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public LiteralContext literal() {
            return getRuleContext(LiteralContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitLiteralExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrayLiteralExpressionContext extends SingleExpressionContext {
        public ArrayLiteralExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public ArrayLiteralContext arrayLiteral() {
            return getRuleContext(ArrayLiteralContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrayLiteralExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MemberDotExpressionContext extends SingleExpressionContext {
        public MemberDotExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode Dot() {
            return getToken(TypeScriptParser.Dot, 0);
        }

        public IdentifierNameContext identifierName() {
            return getRuleContext(IdentifierNameContext.class, 0);
        }

        public TerminalNode QuestionMark() {
            return getToken(TypeScriptParser.QuestionMark, 0);
        }

        public NestedTypeGenericContext nestedTypeGeneric() {
            return getRuleContext(NestedTypeGenericContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitMemberDotExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ClassExpressionContext extends SingleExpressionContext {
        public ClassExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Class() {
            return getToken(TypeScriptParser.Class, 0);
        }

        public ClassTailContext classTail() {
            return getRuleContext(ClassTailContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitClassExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MemberIndexExpressionContext extends SingleExpressionContext {
        public MemberIndexExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public ExpressionSequenceContext expressionSequence() {
            return getRuleContext(ExpressionSequenceContext.class, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitMemberIndexExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IdentifierExpressionContext extends SingleExpressionContext {
        public IdentifierExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public IdentifierNameContext identifierName() {
            return getRuleContext(IdentifierNameContext.class, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIdentifierExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BitAndExpressionContext extends SingleExpressionContext {
        public BitAndExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode BitAnd() {
            return getToken(TypeScriptParser.BitAnd, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBitAndExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BitOrExpressionContext extends SingleExpressionContext {
        public BitOrExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public TerminalNode BitOr() {
            return getToken(TypeScriptParser.BitOr, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitBitOrExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AssignmentOperatorExpressionContext extends SingleExpressionContext {
        public AssignmentOperatorExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public List<SingleExpressionContext> singleExpression() {
            return getRuleContexts(SingleExpressionContext.class);
        }

        public SingleExpressionContext singleExpression(int i) {
            return getRuleContext(SingleExpressionContext.class, i);
        }

        public AssignmentOperatorContext assignmentOperator() {
            return getRuleContext(AssignmentOperatorContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAssignmentOperatorExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class VoidExpressionContext extends SingleExpressionContext {
        public VoidExpressionContext(SingleExpressionContext ctx) {
            copyFrom(ctx);
        }

        public TerminalNode Void() {
            return getToken(TypeScriptParser.Void, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitVoidExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AsExpressionContext extends ParserRuleContext {
        public AsExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public PredefinedTypeContext predefinedType() {
            return getRuleContext(PredefinedTypeContext.class, 0);
        }

        public TerminalNode OpenBracket() {
            return getToken(TypeScriptParser.OpenBracket, 0);
        }

        public TerminalNode CloseBracket() {
            return getToken(TypeScriptParser.CloseBracket, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_asExpression;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAsExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrowFunctionDeclarationContext extends ParserRuleContext {
        public ArrowFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ArrowFunctionParametersContext arrowFunctionParameters() {
            return getRuleContext(ArrowFunctionParametersContext.class, 0);
        }

        public TerminalNode ARROW() {
            return getToken(TypeScriptParser.ARROW, 0);
        }

        public ArrowFunctionBodyContext arrowFunctionBody() {
            return getRuleContext(ArrowFunctionBodyContext.class, 0);
        }

        public TerminalNode Async() {
            return getToken(TypeScriptParser.Async, 0);
        }

        public TypeAnnotationContext typeAnnotation() {
            return getRuleContext(TypeAnnotationContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrowFunctionDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrowFunctionDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrowFunctionParametersContext extends ParserRuleContext {
        public ArrowFunctionParametersContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode OpenParen() {
            return getToken(TypeScriptParser.OpenParen, 0);
        }

        public TerminalNode CloseParen() {
            return getToken(TypeScriptParser.CloseParen, 0);
        }

        public FormalParameterListContext formalParameterList() {
            return getRuleContext(FormalParameterListContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrowFunctionParameters;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrowFunctionParameters(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArrowFunctionBodyContext extends ParserRuleContext {
        public ArrowFunctionBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode OpenBrace() {
            return getToken(TypeScriptParser.OpenBrace, 0);
        }

        public FunctionBodyContext functionBody() {
            return getRuleContext(FunctionBodyContext.class, 0);
        }

        public TerminalNode CloseBrace() {
            return getToken(TypeScriptParser.CloseBrace, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrowFunctionBody;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitArrowFunctionBody(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AssignmentOperatorContext extends ParserRuleContext {
        public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode MultiplyAssign() {
            return getToken(TypeScriptParser.MultiplyAssign, 0);
        }

        public TerminalNode DivideAssign() {
            return getToken(TypeScriptParser.DivideAssign, 0);
        }

        public TerminalNode ModulusAssign() {
            return getToken(TypeScriptParser.ModulusAssign, 0);
        }

        public TerminalNode PlusAssign() {
            return getToken(TypeScriptParser.PlusAssign, 0);
        }

        public TerminalNode MinusAssign() {
            return getToken(TypeScriptParser.MinusAssign, 0);
        }

        public TerminalNode LeftShiftArithmeticAssign() {
            return getToken(TypeScriptParser.LeftShiftArithmeticAssign, 0);
        }

        public TerminalNode RightShiftArithmeticAssign() {
            return getToken(TypeScriptParser.RightShiftArithmeticAssign, 0);
        }

        public TerminalNode RightShiftLogicalAssign() {
            return getToken(TypeScriptParser.RightShiftLogicalAssign, 0);
        }

        public TerminalNode BitAndAssign() {
            return getToken(TypeScriptParser.BitAndAssign, 0);
        }

        public TerminalNode BitXorAssign() {
            return getToken(TypeScriptParser.BitXorAssign, 0);
        }

        public TerminalNode BitOrAssign() {
            return getToken(TypeScriptParser.BitOrAssign, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assignmentOperator;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitAssignmentOperator(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LiteralContext extends ParserRuleContext {
        public LiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode NullLiteral() {
            return getToken(TypeScriptParser.NullLiteral, 0);
        }

        public TerminalNode BooleanLiteral() {
            return getToken(TypeScriptParser.BooleanLiteral, 0);
        }

        public TerminalNode StringLiteral() {
            return getToken(TypeScriptParser.StringLiteral, 0);
        }

        public TemplateStringLiteralContext templateStringLiteral() {
            return getRuleContext(TemplateStringLiteralContext.class, 0);
        }

        public TerminalNode RegularExpressionLiteral() {
            return getToken(TypeScriptParser.RegularExpressionLiteral, 0);
        }

        public NumericLiteralContext numericLiteral() {
            return getRuleContext(NumericLiteralContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_literal;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitLiteral(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TemplateStringLiteralContext extends ParserRuleContext {
        public TemplateStringLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> BackTick() {
            return getTokens(TypeScriptParser.BackTick);
        }

        public TerminalNode BackTick(int i) {
            return getToken(TypeScriptParser.BackTick, i);
        }

        public List<TemplateStringAtomContext> templateStringAtom() {
            return getRuleContexts(TemplateStringAtomContext.class);
        }

        public TemplateStringAtomContext templateStringAtom(int i) {
            return getRuleContext(TemplateStringAtomContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_templateStringLiteral;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTemplateStringLiteral(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TemplateStringAtomContext extends ParserRuleContext {
        public TemplateStringAtomContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode TemplateStringAtom() {
            return getToken(TypeScriptParser.TemplateStringAtom, 0);
        }

        public TerminalNode TemplateStringStartExpression() {
            return getToken(TypeScriptParser.TemplateStringStartExpression, 0);
        }

        public SingleExpressionContext singleExpression() {
            return getRuleContext(SingleExpressionContext.class, 0);
        }

        public TerminalNode TemplateCloseBrace() {
            return getToken(TypeScriptParser.TemplateCloseBrace, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_templateStringAtom;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitTemplateStringAtom(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NumericLiteralContext extends ParserRuleContext {
        public NumericLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode DecimalLiteral() {
            return getToken(TypeScriptParser.DecimalLiteral, 0);
        }

        public TerminalNode HexIntegerLiteral() {
            return getToken(TypeScriptParser.HexIntegerLiteral, 0);
        }

        public TerminalNode OctalIntegerLiteral() {
            return getToken(TypeScriptParser.OctalIntegerLiteral, 0);
        }

        public TerminalNode OctalIntegerLiteral2() {
            return getToken(TypeScriptParser.OctalIntegerLiteral2, 0);
        }

        public TerminalNode BinaryIntegerLiteral() {
            return getToken(TypeScriptParser.BinaryIntegerLiteral, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_numericLiteral;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitNumericLiteral(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IdentifierNameContext extends ParserRuleContext {
        public IdentifierNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public ReservedWordContext reservedWord() {
            return getRuleContext(ReservedWordContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_identifierName;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIdentifierName(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IdentifierOrKeyWordContext extends ParserRuleContext {
        public IdentifierOrKeyWordContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Identifier() {
            return getToken(TypeScriptParser.Identifier, 0);
        }

        public TerminalNode TypeAlias() {
            return getToken(TypeScriptParser.TypeAlias, 0);
        }

        public TerminalNode Require() {
            return getToken(TypeScriptParser.Require, 0);
        }

        public TerminalNode As() {
            return getToken(TypeScriptParser.As, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_identifierOrKeyWord;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitIdentifierOrKeyWord(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ReservedWordContext extends ParserRuleContext {
        public ReservedWordContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public KeywordContext keyword() {
            return getRuleContext(KeywordContext.class, 0);
        }

        public TerminalNode NullLiteral() {
            return getToken(TypeScriptParser.NullLiteral, 0);
        }

        public TerminalNode BooleanLiteral() {
            return getToken(TypeScriptParser.BooleanLiteral, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_reservedWord;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitReservedWord(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class KeywordContext extends ParserRuleContext {
        public KeywordContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Break() {
            return getToken(TypeScriptParser.Break, 0);
        }

        public TerminalNode Do() {
            return getToken(TypeScriptParser.Do, 0);
        }

        public TerminalNode Instanceof() {
            return getToken(TypeScriptParser.Instanceof, 0);
        }

        public TerminalNode Typeof() {
            return getToken(TypeScriptParser.Typeof, 0);
        }

        public TerminalNode Case() {
            return getToken(TypeScriptParser.Case, 0);
        }

        public TerminalNode Else() {
            return getToken(TypeScriptParser.Else, 0);
        }

        public TerminalNode New() {
            return getToken(TypeScriptParser.New, 0);
        }

        public TerminalNode Var() {
            return getToken(TypeScriptParser.Var, 0);
        }

        public TerminalNode Catch() {
            return getToken(TypeScriptParser.Catch, 0);
        }

        public TerminalNode Finally() {
            return getToken(TypeScriptParser.Finally, 0);
        }

        public TerminalNode Return() {
            return getToken(TypeScriptParser.Return, 0);
        }

        public TerminalNode Void() {
            return getToken(TypeScriptParser.Void, 0);
        }

        public TerminalNode Continue() {
            return getToken(TypeScriptParser.Continue, 0);
        }

        public TerminalNode For() {
            return getToken(TypeScriptParser.For, 0);
        }

        public TerminalNode Switch() {
            return getToken(TypeScriptParser.Switch, 0);
        }

        public TerminalNode While() {
            return getToken(TypeScriptParser.While, 0);
        }

        public TerminalNode Debugger() {
            return getToken(TypeScriptParser.Debugger, 0);
        }

        public TerminalNode Function_() {
            return getToken(TypeScriptParser.Function_, 0);
        }

        public TerminalNode This() {
            return getToken(TypeScriptParser.This, 0);
        }

        public TerminalNode With() {
            return getToken(TypeScriptParser.With, 0);
        }

        public TerminalNode Default() {
            return getToken(TypeScriptParser.Default, 0);
        }

        public TerminalNode If() {
            return getToken(TypeScriptParser.If, 0);
        }

        public TerminalNode Throw() {
            return getToken(TypeScriptParser.Throw, 0);
        }

        public TerminalNode Delete() {
            return getToken(TypeScriptParser.Delete, 0);
        }

        public TerminalNode In() {
            return getToken(TypeScriptParser.In, 0);
        }

        public TerminalNode Try() {
            return getToken(TypeScriptParser.Try, 0);
        }

        public TerminalNode ReadOnly() {
            return getToken(TypeScriptParser.ReadOnly, 0);
        }

        public TerminalNode Async() {
            return getToken(TypeScriptParser.Async, 0);
        }

        public TerminalNode From() {
            return getToken(TypeScriptParser.From, 0);
        }

        public TerminalNode Class() {
            return getToken(TypeScriptParser.Class, 0);
        }

        public TerminalNode Enum() {
            return getToken(TypeScriptParser.Enum, 0);
        }

        public TerminalNode Extends() {
            return getToken(TypeScriptParser.Extends, 0);
        }

        public TerminalNode Super() {
            return getToken(TypeScriptParser.Super, 0);
        }

        public TerminalNode Const() {
            return getToken(TypeScriptParser.Const, 0);
        }

        public TerminalNode Export() {
            return getToken(TypeScriptParser.Export, 0);
        }

        public TerminalNode Import() {
            return getToken(TypeScriptParser.Import, 0);
        }

        public TerminalNode Implements() {
            return getToken(TypeScriptParser.Implements, 0);
        }

        public TerminalNode Let() {
            return getToken(TypeScriptParser.Let, 0);
        }

        public TerminalNode Private() {
            return getToken(TypeScriptParser.Private, 0);
        }

        public TerminalNode Public() {
            return getToken(TypeScriptParser.Public, 0);
        }

        public TerminalNode Interface() {
            return getToken(TypeScriptParser.Interface, 0);
        }

        public TerminalNode Package() {
            return getToken(TypeScriptParser.Package, 0);
        }

        public TerminalNode Protected() {
            return getToken(TypeScriptParser.Protected, 0);
        }

        public TerminalNode Static() {
            return getToken(TypeScriptParser.Static, 0);
        }

        public TerminalNode Yield() {
            return getToken(TypeScriptParser.Yield, 0);
        }

        public TerminalNode Get() {
            return getToken(TypeScriptParser.Get, 0);
        }

        public TerminalNode Set() {
            return getToken(TypeScriptParser.Set, 0);
        }

        public TerminalNode Require() {
            return getToken(TypeScriptParser.Require, 0);
        }

        public TerminalNode TypeAlias() {
            return getToken(TypeScriptParser.TypeAlias, 0);
        }

        public TerminalNode String() {
            return getToken(TypeScriptParser.String, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_keyword;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitKeyword(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GetterContext extends ParserRuleContext {
        public GetterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Get() {
            return getToken(TypeScriptParser.Get, 0);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_getter;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitGetter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetterContext extends ParserRuleContext {
        public SetterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode Set() {
            return getToken(TypeScriptParser.Set, 0);
        }

        public PropertyNameContext propertyName() {
            return getRuleContext(PropertyNameContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setter;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitSetter(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class EosContext extends ParserRuleContext {
        public EosContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode SemiColon() {
            return getToken(TypeScriptParser.SemiColon, 0);
        }

        public TerminalNode EOF() {
            return getToken(TypeScriptParser.EOF, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_eos;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof TypeScriptParserVisitor)
                return ((TypeScriptParserVisitor<? extends T>) visitor).visitEos(this);
            else return visitor.visitChildren(this);
        }
    }
}