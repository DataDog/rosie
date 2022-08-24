package io.codiga.model.ast;

import java.util.List;
import java.util.Optional;


public record FunctionCall(Optional<String> moduleOrObject,
                           String functionName,
                           List<FunctionCallArgument> arguments,
                           int line) {
}
