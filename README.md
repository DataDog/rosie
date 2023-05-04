# rosie

Rosie code analyzer from Datadog

## Install tree-sitter

In the [java-tree-sitter](https://github.com/serenadeai/java-tree-sitter) project (once cloned), issue the following
command:

```bash
./build.py -o libjava-tree-sitter <path-to-tree-sitter-python>
```

Make sure all submodules are up-to-date. Tree-sitter for java-tree-sitter (the `tree-sitter` directory)
and the version for the Python grammars must be the same (or the python grammar be less than the `tree-sitter`
directory).

To update the submodules: `git submodule update --init --recursive` (first time)
and `git submodule update --recursive --remote`

## Run


### CLI

```bash
./gradlew cli:run --args="--directory /Users/julien/git/ci-backend-executor/backend_lib/ -t true -r rules.json -o plop.json"
```


### Server

```bash
./gradlew server:bootRun
```
