package io.codiga.analyzer.ast.vm;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class VmUtils {
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


    public static Context createEmptyContext() {
        return (
            Context
                .newBuilder("js")
                .allowHostAccess(SANDBOX)
                .allowExperimentalOptions(false)
                .allowValueSharing(true)
                .allowIO(false)

                .logHandler(OutputStream.nullOutputStream())
                .build());
    }

    public static Context createContextForJavaScriptExecution(ExecutionEnvironment executionEnvironment) {
        Context context = createEmptyContext();
        context.getBindings("js").putMember("root", executionEnvironment.rootObject);
        context.getBindings("js").putMember("addError", executionEnvironment.errorReporting);
        context.getBindings("js").putMember("code", executionEnvironment.code);
        return context;
    }

    public static String formatVmErrorMessage(String errorMessage) {
        String executionMessage = errorMessage
            .replace("org.graalvm.polyglot.PolyglotException:", "")
            .replaceAll(".* failed due to: ", "");
        return executionMessage;
    }

    public static String buildExecutableCode(String javaScriptCode) {
        String finalCode = " reportError = addError.addError; " +
            "buildFix = addError.buildFix; " +
            "buildError = addError.buildViolation; " +
            "buildEdit = addError.buildEdit; " +
            "buildEditAdd = addError.buildEditAdd; " +
            "buildEditUpdate = addError.buildEditUpdate; " +
            "buildEditRemove = addError.buildEditRemove; " +
            "addError = addError.addViolation; " +
            javaScriptCode + " visit(root, code);";
        return finalCode;
    }
}
