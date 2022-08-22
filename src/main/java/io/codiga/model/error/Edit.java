package io.codiga.model.error;

import java.util.Optional;




public record Edit(Position start, EditType kind, Position end, Optional<String> content) {
}
