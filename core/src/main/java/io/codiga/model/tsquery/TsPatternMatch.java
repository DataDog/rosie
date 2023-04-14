package io.codiga.model.tsquery;

import io.codiga.model.ast.common.AstElement;
import io.codiga.model.context.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.proxy.ProxyHashMap;

import java.util.Collections;
import java.util.Map;

public class TsPatternMatch {

    @HostAccess.Export
    public Context context;
    @HostAccess.Export
    public ProxyHashMap captures;


    public TsPatternMatch(Map<String, AstElement> matches, Context context) {
        this.captures = ProxyHashMap.from(Collections.unmodifiableMap(matches));
        this.context = context;
    }

}
