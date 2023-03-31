package io.codiga.model.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Context {
    private String code;
    public Map<String, String> variables;

    public void setVariables(Map<String, String> variables) {
        if(variables == null) {
            this.variables = new HashMap<>();
            return;
        }
        this.variables = variables;
    }
}
