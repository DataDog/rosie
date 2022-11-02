package io.codiga.analyzer.ast.common;

import io.codiga.analyzer.ast.vm.VmContext;
import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.Language;
import org.antlr.v4.runtime.Parser;

import java.io.*;
import java.util.List;

/**
 * This class holds all the context that is built BEFORE all threads are executed.
 * We get all the data that needs to be computed by all tasks before launching them here.
 * For example, for an AST rule, we build the AST prior to starting the analysis.
 */
public class AnalyzerContext {
    private final static int MAX_OUTPUT_SIZE = 1024 * 1024;

    Parser parser = null;
    Language language = null;
    String filename = null;
    String code = null;
    List<AnalyzerRule> rules = null;
    protected boolean logOutput = false;

    private VmContext vmContext = null;
    private OutputStream outputStream;
    private InputStream inputStream;


    public AnalyzerContext(Language language, String filename, String code, List<AnalyzerRule> rules, boolean logOutput) {
        this.language = language;
        this.filename = filename;
        this.code = code;
        this.rules = rules;
        this.logOutput = logOutput;

        if (logOutput) {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            this.outputStream = pipedOutputStream;
            try {
                this.inputStream = new PipedInputStream(pipedOutputStream, MAX_OUTPUT_SIZE);
            } catch (IOException ioException) {
                this.outputStream = null;
                this.inputStream = null;
            }
        } else {
            this.outputStream = null;
            this.inputStream = null;
        }

        vmContext = new VmContext(this.outputStream);
    }

    public Parser getParser() {
        return this.parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public String getCode() {
        return this.code;
    }

    public Language getLanguage() {
        return this.language;
    }

    public String getFilename() {
        return filename;
    }

    public boolean isLogOutput() {
        return logOutput;
    }


    public VmContext getVmContext() {
        return this.vmContext;
    }

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public String getOutput() {
        if (!this.logOutput) {
            return null;
        }

        if (this.outputStream == null) {
            return null;
        }
        try {
            int nBytesAvailable = this.inputStream.available();
            byte[] buffer = new byte[nBytesAvailable];
            this.inputStream.read(buffer);

            return new String(buffer);

        } catch (IOException ioException) {
            return null;
        }
    }

    public void releaseResources() throws IOException {
        if (this.outputStream != null) {
            this.outputStream.close();
            this.outputStream = null;
        }
        if (this.inputStream != null) {
            this.inputStream.close();
            this.inputStream = null;
        }

    }
}
