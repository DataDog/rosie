package io.codiga.cli.config;

import static io.codiga.cli.config.Configuration.IGNORE_PATHS_KEY;
import static io.codiga.cli.config.Configuration.RULESET_KEY;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.yaml.snakeyaml.Yaml;

public class ConfigurationUtils {
    public static Optional<Configuration> getConfigurationFromFile(File file) {
        Yaml yaml = new Yaml();
        Set<String> rulesetsToUse = new HashSet<String>();
        Set<String> directoriesToIgnore = new HashSet<String>();
        try (InputStream inputStream = new FileInputStream(file)) {
            Object yamlObject = yaml.load(inputStream);

            if(! (yamlObject instanceof HashMap)) {
                return Optional.empty();
            }

            @SuppressWarnings("unchecked")
            HashMap<String, ArrayList<Object>> mainHashmap = (HashMap<String, ArrayList<Object>>) yamlObject;
            if (!mainHashmap.containsKey(RULESET_KEY)) {
                return Optional.empty();
            }


            // handle rulesets first 
            List<Object> rulesets = mainHashmap.get(RULESET_KEY);
            for(Object ruleset: rulesets){
                if (ruleset instanceof String) {
                    rulesetsToUse.add((String)ruleset);
                }
                if (ruleset instanceof HashMap) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> rulesetHashMap = (HashMap<String, Object>) ruleset;
                    rulesetsToUse.addAll(rulesetHashMap.keySet());
                }
            }

            // then do the directories
            if(mainHashmap.containsKey(IGNORE_PATHS_KEY)){
                List<Object> ignoreKeysObject = mainHashmap.get(IGNORE_PATHS_KEY);
                for (Object o: ignoreKeysObject) {
                    if(o instanceof String) {
                        directoriesToIgnore.add((String)o);
                    }
                }
            }

            return Optional.of(Configuration.builder()
                    .ignorePaths(directoriesToIgnore.stream().toList())
                    .rulesets(rulesetsToUse.stream().toList())
                .build());

        } catch (IOException e) {
            System.err.printf("cannot read configuration file %s%n", file.getPath());
        }

        return Optional.empty();
    }
}
