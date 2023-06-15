package io.codiga.model.tree_sitter;

import io.codiga.model.ast.common.TreeSitterAstElement;
import io.codiga.model.context.Context;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.proxy.ProxyHashMap;

public class TsPatternMatch {

    @HostAccess.Export
    public Context context;

    @HostAccess.Export
    public ProxyHashMap captures;

    @HostAccess.Export
    public ProxyHashMap capturesList;

    public TsPatternMatch(Map<String, TreeSitterAstElement> matches, Map<String, List<TreeSitterAstElement>> matchesList, Context context) {
        this.captures = ProxyHashMap.from(Collections.unmodifiableMap(matches));
        this.capturesList = ProxyHashMap.from(Collections.unmodifiableMap(matchesList));
        this.context = context;
    }

}
