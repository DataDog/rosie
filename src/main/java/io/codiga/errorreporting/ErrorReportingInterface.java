package io.codiga.errorreporting;

public interface ErrorReportingInterface {

    public void reportError(Throwable t);

    public void reportError(Throwable t, String description);

    public void reportMessage(String message);

}
