rm -rf gen && antlr4 -o gen -no-listener -visitor -package io.codiga.parser.typescript.gen TypeScriptLexer.g4 TypeScriptParser.g4
