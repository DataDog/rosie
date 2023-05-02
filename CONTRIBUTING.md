# Contribute

## Run all tests

```bash
./gradlew test
```

## Run a single test

```bash
./gradlew test --tests io.codiga.server.e2e.ping.PingTest
```

## ANTLR

### Generate ASTs

#### JavaScript

```bash
antlr4 -o gen -no-listener -visitor -package io.codiga.parser.javascript.gen JavaScriptLexer.g4 JavaScriptParser.g4
```

Make sure you copy the `Java/*` file with the `.g4` file

#### TypeScript

```bash
antlr4 -o gen -no-listener -visitor -package io.codiga.parser.typescript.gen TypeScriptLexer.g4 TypeScriptParser.g4
```

```bash
javac *.java
```

### Visualize a file's AST

```bash
grun Python root <python-file> -gui
```

### Run the CLI version

```bash
./gradlew cli:run --args='--debug true --directory /Users/julien/git/ci-backend-executor/backend_lib/ --output /tmp/bla.json --rules /Users/julien/git/rosie/cli/src/test/resources/20rules.json'
```

### Custom span tracer in Datadog

https://docs.datadoghq.com/tracing/trace_collection/custom_instrumentation/java/
