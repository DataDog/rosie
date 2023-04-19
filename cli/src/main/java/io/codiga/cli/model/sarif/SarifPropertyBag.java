package io.codiga.cli.model.sarif;

import lombok.Builder;

import java.util.List;

/**
 * Properties being added to an object
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1654">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifPropertyBag {
    @Builder.Default
    public List<String> tags = List.of();
}
