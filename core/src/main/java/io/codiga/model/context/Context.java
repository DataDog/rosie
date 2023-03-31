package io.codiga.model.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Context {
    private String code;
    public @Setter Map<String, String> variables;
}
