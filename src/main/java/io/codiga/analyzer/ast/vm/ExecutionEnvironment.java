package io.codiga.analyzer.ast.vm;

import io.codiga.analyzer.ast.common.ErrorReporting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class ExecutionEnvironment {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionEnvironment.class);
    private final static int MAX_OUTPUT_SIZE = 1024 * 1024;
    public Object rootObject;
    public ErrorReporting errorReporting;
    public String code;
    public String filename = null;
    public boolean logOutput;
    private OutputStream outputStream;
    private InputStream inputStream;

    public ExecutionEnvironment(Object rootObject, String code, boolean logOutput, String filename) {
        this.rootObject = rootObject;
        this.errorReporting = new ErrorReporting();
        this.logOutput = logOutput;
        this.filename = filename;
        this.code = code;

        if (this.logOutput) {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            this.outputStream = pipedOutputStream;
            try {
                this.inputStream = new PipedInputStream(pipedOutputStream, MAX_OUTPUT_SIZE);
            } catch (IOException ioException) {
                logger.error("Cannot create streams", ioException);
                this.outputStream = null;
                this.inputStream = null;
            }
        } else {
            this.outputStream = null;
            this.inputStream = null;
        }

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
            this.outputStream.close();
            this.inputStream.close();
            return new String(buffer);

        } catch (IOException ioException) {
            logger.error("impossible to read the data");
            return null;
        }
    }
}
