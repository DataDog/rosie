package io.codiga.analyzer.ast.vm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class VmContext {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionEnvironment.class);
    private static final HostAccess SANDBOX = HostAccess.newBuilder().
        allowPublicAccess(true).
        allowArrayAccess(true).
        allowListAccess(true).
        allowAllImplementations(true).
        denyAccess(Class.class).
        denyAccess(Method.class).
        denyAccess(Field.class).
        denyAccess(Proxy.class).
        denyAccess(Object.class, false).
        build();

    private Context context;

    public VmContext(OutputStream outputStream) {
        Context.Builder contextBuilder = Context
            .newBuilder("js")
            .allowHostAccess(SANDBOX)
            .allowExperimentalOptions(false)
            .allowValueSharing(true)
            .allowIO(false)
            .logHandler(OutputStream.nullOutputStream());

        if(outputStream != null) {
            contextBuilder.out(outputStream);
        }

        context = contextBuilder.build();
    }

    public Context getContext() {
        return this.context;
    }

    public void prepareForExecutionEnvironment(ExecutionEnvironment executionEnvironment) {
        context.getBindings("js").putMember("root", executionEnvironment.rootObject);
        context.getBindings("js").putMember("addError", executionEnvironment.errorReporting);
        context.getBindings("js").putMember("code", executionEnvironment.code);
        context.getBindings("js").putMember("filename", executionEnvironment.filename);
    }
}
