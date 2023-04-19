package io.codiga.cli.model.sarif;

import lombok.Builder;

/**
 * Encapsulates a message intended to be read by the end user.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1436">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifResultMessage {
    public String text;


}
