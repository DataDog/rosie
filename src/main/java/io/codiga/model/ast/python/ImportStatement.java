package io.codiga.model.ast.python;

public class ImportStatement {
    public String packageName;

    public ImportStatement(String packageName) {
        this.packageName = packageName;
    }
}
