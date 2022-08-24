package io.codiga.ast.vm;

import io.codiga.ast.common.ErrorReporting;
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

    public static Context createContext(Object root, ErrorReporting errorReporting) {
        Context context = Context
            .newBuilder("js")
            .allowHostAccess(SANDBOX)
            .allowExperimentalOptions(false)
            .allowValueSharing(true)
            .allowIO(false)

            .logHandler(OutputStream.nullOutputStream())
            .build();
        context.getBindings("js").putMember("root", root);
        context.getBindings("js").putMember("addError", errorReporting);
        return context;
    }
}
