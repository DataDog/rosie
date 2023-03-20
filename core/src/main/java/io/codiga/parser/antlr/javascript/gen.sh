rm -rf gen && antlr4 -o gen -no-listener -visitor -package io.codiga.parser.javascript.gen JavaScriptParser.g4 JavaScriptLexer.g4
