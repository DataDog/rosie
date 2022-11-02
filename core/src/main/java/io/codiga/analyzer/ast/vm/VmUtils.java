package io.codiga.analyzer.ast.vm;

public class VmUtils {


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
