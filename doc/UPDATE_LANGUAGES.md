# Update Languages

Updating the languages consists in starting docker containers for a specific architecture and building a library that is later started by our Java wrapper.
In the following sections, we explain each step for building the shared library on a developer laptop.

## Step 1: spin up a docker container for the architecture you want

Start a linux distribution in a docker container with the architecture you want (`linux/amd64` or `linux/arm64`).
You can mount a volume if you want persistent storage

```
docker run -it --entrypoint /bin/bash  --platform linux/amd64 --name rosie-dev-amd64 --mount source=rosie-dev-amd64,target=/home ubuntu:latest
```


## Step 2: install dependencies

These are the dependencies needed to build tree sitter languages.

```shell
apt-get update
apt-get install clang git make python3-distutils
```


## Step 3: clone the languages repositories

Clone all the languages repositories you want/need.

```shell
cd $HOME
mkdir -p tree-sitter/languages
cd tree-sitter/languages
git clone https://github.com/tree-sitter/tree-sitter-go.git
git clone https://github.com/tree-sitter/tree-sitter-c-sharp.git
git clone https://github.com/tree-sitter/tree-sitter-javascript.git
git clone https://github.com/tree-sitter/tree-sitter-typescript.git
git clone https://github.com/tree-sitter/tree-sitter-java.git
git clone https://github.com/tree-sitter/tree-sitter-json.git
git clone https://github.com/ikatyang/tree-sitter-yaml.git
git clone https://github.com/tree-sitter/tree-sitter-python.git
```


## Step 4: get java-tree-sitter

This is the wrapper we need to interface tree-sitter with Java.

```shell
cd $HOME/tree-sitter
git clone git@github.com:juli1/java-tree-sitter.git
```

And then, set the `JVM_HOME` variable

```shell
export JVM_HOME=...
```


## Step 4: Build the library

```shell
cd $HOME/tree-sitter/java-tree-sitter
./build.py -o libjava-tree-sitter \
    $HOME/tree-sitter/languages/tree-sitter-python \
    $HOME/tree-sitter/languages/tree-sitter-go \
    $HOME/tree-sitter/languages/tree-sitter-java \
    $HOME/tree-sitter/languages/tree-sitter-javascript \
    $HOME/tree-sitter/languages/tree-sitter-typescript/tsx \
    $HOME/tree-sitter/languages/tree-sitter-yaml \
    $HOME/tree-sitter/languages/tree-sitter-c-sharp \
    $HOME/tree-sitter/languages/tree-sitter-json
```
