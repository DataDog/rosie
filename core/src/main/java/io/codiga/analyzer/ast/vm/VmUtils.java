package io.codiga.analyzer.ast.vm;

import org.graalvm.polyglot.HostAccess;

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
            javaScriptCode + " visit(root, filename, code);";
        return finalCode;
    }
}
