package io.codiga.cli.model.sarif;

import lombok.Builder;

/**
 * Encapsulates a multiformat message intended to be read by the end user.
 * <br>
 * <a href="https://github.com/DataDog/datadog-ci/blob/master/src/commands/sarif/json-schema/sarif-schema-2.1.0.json#L1483-L1506">See in the SARIF JSON Schema</a>
 */
@Builder
public class SarifMultiformatMessage {
    public String text;

    public static SarifMultiformatMessage from(String str) {
        if(str == null || str.length() == 0){
            return null;
        }

        return SarifMultiformatMessage.builder().text(str).build();
    }
}
