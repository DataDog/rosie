package io.codiga.model.error;

import io.codiga.model.common.Position;

import java.util.Optional;


public record Edit(Position start, EditType kind, Position end, Optional<String> content) {
}
