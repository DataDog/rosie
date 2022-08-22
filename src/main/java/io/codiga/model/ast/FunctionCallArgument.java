package io.codiga.model.ast;

import java.util.Optional;

public record FunctionCallArgument(Optional<String> name,
                                   String value) {

}