package io.codiga;

import io.codiga.cli.model.OutputFormat;
import java.util.StringJoiner;

/**
 * A builder class that's used to create the arguments needed to test the CLI
 */
public class CmdOptionBuilder {
    private String directory;
    private String rules;
    private String debug;
    private String treeSitter;
    private String output;
    private String format;
    private String ignorePaths;
    private String testMode;

    public CmdOptionBuilder directoryPath(String directoryPath) {
    if (directoryPath == null || directoryPath.equals("")) {
            this.directory = null;
        } else {
            this.directory = "--directory " + directoryPath;
        }
        return this;
    }

    public CmdOptionBuilder rulesPath(String rulesPath) {
        if (rulesPath == null || rulesPath.equals("")) {
            this.rules = null;
        } else {
            this.rules = "--rules " + rulesPath;
        }
        return this;
    }

    public CmdOptionBuilder debug(Boolean debugFlag) {
        if(debugFlag == null) {
            this.debug = null;
        } else {
            this.debug = "--debug " + debugFlag;
        }
        return this;
    }

    public CmdOptionBuilder treeSitter(Boolean useTreeSitter) {
        if(useTreeSitter == null){
            this.treeSitter = null;
        } else {
            this.treeSitter = "--tree-sitter " + useTreeSitter;
        }
        return this;
    }

    public CmdOptionBuilder outputPath(String outputPath) {
        if(outputPath == null || outputPath.equals("")) {
            this.output = null;
        } else {
            this.output = "--output " + outputPath;
        }
        return this;
    }

    public CmdOptionBuilder outputFormat(OutputFormat outputFormat) {
        if(outputFormat == null) {
            this.format = null;
        } else {
            this.format = "--format " + outputFormat;
        }
        return this;
    }

    public CmdOptionBuilder ignorePaths(String ignorePathsPath) {
    if (ignorePathsPath == null || ignorePathsPath.equals("")) {
            this.ignorePaths = null;
        } else {
            this.ignorePaths = "--ignore-paths " + ignorePathsPath;
        }
        return this;
    }

    public CmdOptionBuilder testMode(Boolean isTestMode) {
        if(isTestMode == null) {
            this.testMode = null;
        } else {
            this.testMode = "--test-mode " + isTestMode;
        }
        return this;
    }

    public String[] build() {
        var sj = new StringJoiner(" ");
        if(this.debug != null) sj.add(this.debug);
        if(this.directory != null) sj.add(this.directory);
        if(this.format != null) sj.add(this.format);
        if(this.ignorePaths != null) sj.add(this.ignorePaths);
        if(this.output != null) sj.add(this.output);
        if(this.rules != null) sj.add(this.rules);
        if(this.testMode != null) sj.add(this.testMode);
        if(this.treeSitter != null) sj.add(this.treeSitter);
        return sj.toString().split(" ");
    }
}
