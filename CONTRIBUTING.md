# Contribute

## Run all tests

```bash
./gradlew tests
```

## Run a single test

```bash
./gradlew test --tests io.codiga.server.e2e.ping.PingTest
```

## ANTLR

### Generate Python AST

```bash
antlr4 PythonParser.g4 PythonLexer.g4
```

Make sure you copy the `Java/*` file with the `.g4` file

```bash
javac *.java
```

### Visualize a file

```bash
grun Python root <python-file> -gui
```

### Run the CLI version

```bash
./gradlew cli:run --args='--debug true --directory /Users/julien/git/ci-backend-executor/backend_lib/ --output /tmp/bla.json --rules /Users/julien/git/rosie/cli/src/test/resources/20rules.json'
```