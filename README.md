# rosie

Rosie code analyzer from Codiga

## Install tree-sitter

In the [java-tree-sitter](https://github.com/serenadeai/java-tree-sitter) project (once cloned), issue the following
command:

```bash
./build.py -o libjava-tree-sitter <path-to-tree-sitter-python>
```

Make sure all submodules are up-to-date. Tree-sitter for java-tree-sitter (the `tree-sitter` directory)
and the version for the Python grammars must be the same (or the python grammar be less than the `tree-sitter`
directory).

To update the submodules: `git submodule update --recursive`

