package io.codiga.cli.config;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * This class represent the data from the configuration file static-analysis.datadog.yml in each
 * project. It is being deserialized when we analyze the project and attempt to read the file.
 */
@Builder
public class Configuration {

    public static final String RULESET_KEY = "rulesets";
    public static final String IGNORE_PATHS_KEY = "ignore-paths";
    public static final String DATADOG_CONFIGURATION_FILE = "static-analysis.datadog.yml";

    @Getter
    List<String> rulesets;

    @Getter
    List<String> ignorePaths;
}
