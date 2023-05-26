package io.codiga.server.e2e.python.multirules;

import static io.codiga.constants.Languages.*;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.request.Rule;
import io.codiga.server.request.RuleBuilder;

public class Rules {
    public static Rule RULE_AVOID_RANDOM = new RuleBuilder()
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setType(RuleType.AST_CHECK)
        .setCode("ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGNvbnN0IGFsbFBhY2thZ2VzID0gbm9kZS5jb250ZXh0LmltcG9ydHMuZmlsdGVyKHIgPT4gci5wYWNrYWdlcykuZmxhdE1hcChpID0+IGkucGFja2FnZXMubWFwKHAgPT4gcC5uYW1lLnN0cikpOwogIGNvbnN0IHVzZVJhbmRvbVBhY2thZ2UgPSBhbGxQYWNrYWdlcy5maWx0ZXIoaSA9PiBpID09PSAicmFuZG9tIikubGVuZ3RoID4gMDsKICBjb25zdCB1c2VTZWNyZXRzUGFja2FnZSA9IGFsbFBhY2thZ2VzLmZpbHRlcihpID0+IGkgPT09ICJzZWNyZXRzIikubGVuZ3RoID4gMDsKICBjb25zdCBpbXBvcnRSYW5kb21GdW5jdGlvbkZyb20gPSBub2RlLmNvbnRleHQuaW1wb3J0cwogIAkuZmlsdGVyKHIgPT4gci5wa2cgJiYgci5wa2cudmFsdWUgPT09ICJyYW5kb20iICYmIHIuZWxlbWVudHMgJiYgci5lbGVtZW50cy5maWx0ZXIoZSA9PiBlLm5hbWUpLm1hcChlID0+IGUubmFtZS52YWx1ZSkuaW5jbHVkZXMoInJhbmRvbSIpKS5sZW5ndGggPiAwOwoJY29uc3QgaW1wb3J0UmFuZFJhbmdlRnVuY3Rpb25Gcm9tID0gbm9kZS5jb250ZXh0LmltcG9ydHMKICAJLmZpbHRlcihyID0+IHIucGtnICYmIHIucGtnLnZhbHVlID09PSAicmFuZG9tIiAmJiByLmVsZW1lbnRzICYmIHIuZWxlbWVudHMuZmlsdGVyKGUgPT4gZS5uYW1lKS5tYXAoZSA9PiBlLm5hbWUudmFsdWUpLmluY2x1ZGVzKCJyYW5kcmFuZ2UiKSkubGVuZ3RoID4gMDsKICAKCWNvbnN0IGVkaXRzID0gW107CgoKICBpZighbm9kZS5mdW5jdGlvbk5hbWUpewogICAgcmV0dXJuOwogIH0KCiAgY29uc3QgZWRpdEFkZEltcG9ydCA9IGJ1aWxkRWRpdEFkZCgxLCAxLCAiaW1wb3J0IHNlY3JldHNcbiIpOwogIAogIGlmKHVzZVJhbmRvbVBhY2thZ2UgJiYgbm9kZS5tb2R1bGVPck9iamVjdCAmJiAKICAgICBub2RlLmZ1bmN0aW9uTmFtZSAmJiBub2RlLmZ1bmN0aW9uTmFtZS52YWx1ZSA9PT0gInJhbmRvbSIgJiYgCiAgICAgbm9kZS5tb2R1bGVPck9iamVjdC52YWx1ZSA9PT0gInJhbmRvbSIpewogICAgY29uc3QgaGFzQXJndW1lbnRzID0gKG5vZGUuYXJndW1lbnRzICYmIG5vZGUuYXJndW1lbnRzLnZhbHVlcyAmJiBub2RlLmFyZ3VtZW50cy52YWx1ZXMgJiYgbm9kZS5hcmd1bWVudHMudmFsdWVzLmxlbmd0aCA+IDApIHx8IGZhbHNlOwogIAkKICAgIHZhciBlcnJvciA9IGJ1aWxkRXJyb3Iobm9kZS5zdGFydC5saW5lLCBub2RlLnN0YXJ0LmNvbCwgCiAgICAgICAgICAgICAgICAgICAgICAgICAgIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwgCiAgICAgICAgICAgICAgICAgICAgICAgICAgImRvIG5vdCB1c2UgcmFuZG9tIiwgIldBUk5JTkciLCAiU0VDVVJJVFkiKTsKCiAgICBpZighaGFzQXJndW1lbnRzKSB7CiAgICAgICAgICAKICAgICAJY29uc3QgZWRpdCA9IGJ1aWxkRWRpdFVwZGF0ZShub2RlLnN0YXJ0LmxpbmUsIG5vZGUuc3RhcnQuY29sLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAic2VjcmV0cy5yYW5kYmVsb3coMTAwKSAvIDEwMCIpOwogICAgICBlZGl0cy5wdXNoKGVkaXQpOwogICAgICBpZighdXNlU2VjcmV0c1BhY2thZ2UpewogICAgICAgIGVkaXRzLnB1c2goYnVpbGRFZGl0QWRkKDEsIDEsICJpbXBvcnQgc2VjcmV0c1xuIikpOwogICAgICB9CiAgICAgIGNvbnN0IGZpeCA9IGJ1aWxkRml4KCJ1c2Ugc2VjcmV0cy5yYW5kYmVsb3cgaW5zdGVhZCIsIGVkaXRzKTsKICAgICAgZXJyb3IgPSBlcnJvci5hZGRGaXgoZml4KTsKICAgIH0KICAgIAogICAJYWRkRXJyb3IoZXJyb3IpOwogIH0KICBpZihpbXBvcnRSYW5kb21GdW5jdGlvbkZyb20gJiYgCiAgICAgbm9kZS5mdW5jdGlvbk5hbWUgJiYgbm9kZS5mdW5jdGlvbk5hbWUudmFsdWUgPT09ICJyYW5kb20iKXsKICAgIGNvbnN0IGhhc0FyZ3VtZW50cyA9IChub2RlLmFyZ3VtZW50cyAmJiBub2RlLmFyZ3VtZW50cy52YWx1ZXMgJiYgCiAgICAgICAgICAgICAgICAgICAgICAgICAgbm9kZS5hcmd1bWVudHMudmFsdWVzICYmIG5vZGUuYXJndW1lbnRzLnZhbHVlcy5sZW5ndGggPiAwKSB8fCBmYWxzZTsKICAJCiAgICB2YXIgZXJyb3IgPSBidWlsZEVycm9yKG5vZGUuc3RhcnQubGluZSwgbm9kZS5zdGFydC5jb2wsIAogICAgICAgICAgICAgICAgICAgICAgICAgICBub2RlLmVuZC5saW5lLCBub2RlLmVuZC5jb2wsIAogICAgICAgICAgICAgICAgICAgICAgICAgICJkbyBub3QgdXNlIHJhbmRvbSIsICJXQVJOSU5HIiwgIlNFQ1VSSVRZIik7CgogICAgaWYoIWhhc0FyZ3VtZW50cykgewogICAgICAgICAgCiAgICAgCWNvbnN0IGVkaXQgPSBidWlsZEVkaXRVcGRhdGUobm9kZS5zdGFydC5saW5lLCBub2RlLnN0YXJ0LmNvbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBub2RlLmVuZC5saW5lLCBub2RlLmVuZC5jb2wsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgInNlY3JldHMucmFuZGJlbG93KDEwMCkgLyAxMDAiKTsKICAgICAgZWRpdHMucHVzaChlZGl0KTsKICAgICAgaWYoIXVzZVNlY3JldHNQYWNrYWdlKXsKICAgICAgICBlZGl0cy5wdXNoKGJ1aWxkRWRpdEFkZCgxLCAxLCAiaW1wb3J0IHNlY3JldHNcbiIpKTsKICAgICAgfQogICAgICBjb25zdCBmaXggPSBidWlsZEZpeCgidXNlIHNlY3JldHMucmFuZGJlbG93IGluc3RlYWQiLCBlZGl0cyk7CiAgICAgIGVycm9yID0gZXJyb3IuYWRkRml4KGZpeCk7CiAgICB9CiAgICAKICAgIGNvbnN0IGVkaXRBZGRJbXBvcnQgPSBidWlsZEVkaXRBZGQoMSwgMSwgImltcG9ydCBzeXNcbiIpOwogICAJYWRkRXJyb3IoZXJyb3IpOwogIH0KICBpZih1c2VSYW5kb21QYWNrYWdlICYmIG5vZGUubW9kdWxlT3JPYmplY3QgJiYgCiAgICAgbm9kZS5mdW5jdGlvbk5hbWUgJiYgbm9kZS5mdW5jdGlvbk5hbWUudmFsdWUgPT09ICJyYW5kcmFuZ2UiICYmIAogICAgIG5vZGUubW9kdWxlT3JPYmplY3QudmFsdWUgPT09ICJyYW5kb20iKXsKICAgIGNvbnN0IGhhc0FyZ3VtZW50cyA9IChub2RlLmFyZ3VtZW50cyAmJiBub2RlLmFyZ3VtZW50cy52YWx1ZXMgJiYgbm9kZS5hcmd1bWVudHMudmFsdWVzICYmIG5vZGUuYXJndW1lbnRzLnZhbHVlcy5sZW5ndGggPiAwKSB8fCBmYWxzZTsKICAJY29uc29sZS5sb2coImhlcmUiKTsKICAgIGlmIChoYXNBcmd1bWVudHMpIHsKICAgICAgCXZhciBlcnJvciA9IGJ1aWxkRXJyb3Iobm9kZS5zdGFydC5saW5lLCBub2RlLnN0YXJ0LmNvbCwgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJkbyBub3QgdXNlIHJhbmdyYW5nZSIsICJXQVJOSU5HIiwgIlNFQ1VSSVRZIik7CgogICAgICBjb25zdCBmaXJzdEFyZ3VtZW50VmFsdWUgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXNbMF0udmFsdWUudmFsdWU7CiAgICAgIGNvbnN0IGVkaXQgPSBidWlsZEVkaXRVcGRhdGUobm9kZS5zdGFydC5saW5lLCAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBub2RlLnN0YXJ0LmNvbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBub2RlLmVuZC5saW5lLCAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBub2RlLmVuZC5jb2wsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgYHNlY3JldHMucmFuZGJlbG93KCR7Zmlyc3RBcmd1bWVudFZhbHVlfSlgKTsKICAgICAgZWRpdHMucHVzaChlZGl0KTsKICAgICAgaWYoIXVzZVNlY3JldHNQYWNrYWdlKXsKICAgICAgICBlZGl0cy5wdXNoKGJ1aWxkRWRpdEFkZCgxLCAxLCAiaW1wb3J0IHNlY3JldHNcbiIpKTsKICAgICAgfQogICAgICBjb25zdCBmaXggPSBidWlsZEZpeCgidXNlIHNlY3JldHMucmFuZGJlbG93IGluc3RlYWQiLCBlZGl0cyk7CiAgICAgIGVycm9yID0gZXJyb3IuYWRkRml4KGZpeCk7CgogICAgCWFkZEVycm9yKGVycm9yKTsKICAgIH0KICB9CiAgCiAgaWYoaW1wb3J0UmFuZFJhbmdlRnVuY3Rpb25Gcm9tICYmIAogICAgIG5vZGUuZnVuY3Rpb25OYW1lICYmIG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSAicmFuZHJhbmdlIil7CiAgICBjb25zdCBoYXNBcmd1bWVudHMgPSAobm9kZS5hcmd1bWVudHMgJiYgbm9kZS5hcmd1bWVudHMudmFsdWVzICYmIG5vZGUuYXJndW1lbnRzLnZhbHVlcyAmJiBub2RlLmFyZ3VtZW50cy52YWx1ZXMubGVuZ3RoID4gMCkgfHwgZmFsc2U7CiAgCQogICAgdmFyIGVycm9yID0gYnVpbGRFcnJvcihub2RlLnN0YXJ0LmxpbmUsIG5vZGUuc3RhcnQuY29sLCAKICAgICAgICAgICAgICAgICAgICAgICAgICAgbm9kZS5lbmQubGluZSwgbm9kZS5lbmQuY29sLCAKICAgICAgICAgICAgICAgICAgICAgICAgICAiZG8gbm90IHVzZSByYW5kcmFuZ2UiLCAiV0FSTklORyIsICJTRUNVUklUWSIpOwoKICAgIGlmKCFoYXNBcmd1bWVudHMpIHsKICAgICAgICAgIAogICAgIAljb25zdCBlZGl0ID0gYnVpbGRFZGl0VXBkYXRlKG5vZGUuc3RhcnQubGluZSwgbm9kZS5zdGFydC5jb2wsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgbm9kZS5lbmQubGluZSwgbm9kZS5lbmQuY29sLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICJzZWNyZXRzLnJhbmRiZWxvdygxMDApIC8gMTAwIik7CiAgICAgIGVkaXRzLnB1c2goZWRpdCk7CiAgICAgIGlmKCF1c2VTZWNyZXRzUGFja2FnZSl7CiAgICAgICAgZWRpdHMucHVzaChidWlsZEVkaXRBZGQoMSwgMSwgImltcG9ydCBzZWNyZXRzXG4iKSk7CiAgICAgIH0KICAgICAgY29uc3QgZml4ID0gYnVpbGRGaXgoInVzZSBzZWNyZXRzLnJhbmRiZWxvdyBpbnN0ZWFkIiwgZWRpdHMpOwogICAgICBlcnJvciA9IGVycm9yLmFkZEZpeChmaXgpOwogICAgfQogICAgCiAgICBjb25zdCBlZGl0QWRkSW1wb3J0ID0gYnVpbGRFZGl0QWRkKDEsIDEsICJpbXBvcnQgc3lzXG4iKTsKICAgCWFkZEVycm9yKGVycm9yKTsKICB9CiAgCn0=")
        .setLanguage(Language.PYTHON)
        .setId("avoid-random")
        .setRegex(null)
        .createRule();

    public static Rule RULE_SUBPROCESS_SHELL_TRUE = new RuleBuilder()
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setType(RuleType.AST_CHECK)
        .setCode("ZnVuY3Rpb24gdmlzaXQobm9kZSwgZmlsZW5hbWUsIGNvZGUpIHsKICBpZiAoIW5vZGUuYXJndW1lbnRzIHx8ICFub2RlLmFyZ3VtZW50cy52YWx1ZXMpewogICAgcmV0dXJuOwogIH0KICAKICBjb25zdCBmdW5jdGlvbnMgPSBbIlBvcGVuIiwgInJ1biIsICJjYWxsIl07CiAgCiAgZnVuY3Rpb25zLmZvckVhY2goZnVuY3Rpb25OYW1lID0+IHsKICAgIGNvbnN0IGhhc1NoZWxsVHJ1ZSA9IG5vZGUuYXJndW1lbnRzLnZhbHVlcy5maWx0ZXIoYSA9PiBhLm5hbWUgJiYgYS5uYW1lLnZhbHVlID09ICJzaGVsbCIgJiYgYS52YWx1ZSAmJiBhLnZhbHVlLnZhbHVlID09ICJUcnVlIikubGVuZ3RoID4gMDsKICAgIGNvbnN0IGFsbFBhY2thZ2VzID0gbm9kZS5jb250ZXh0LmltcG9ydHMuZmlsdGVyKGkgPT4gaS5wYWNrYWdlcykuZmxhdE1hcChpID0+IGkucGFja2FnZXMubWFwKHAgPT4gcC5uYW1lLnN0cikpOwogICAgY29uc3QgdXNlU3VicHJvY2Vzc1BhY2thZ2UgPSBhbGxQYWNrYWdlcy5maWx0ZXIoaSA9PiBpID09PSAic3VicHJvY2VzcyIpLmxlbmd0aCA+IDA7CiAgICBjb25zdCBpbXBvcnRGcm9tID0gbm9kZS5jb250ZXh0LmltcG9ydHMKICAgICAgICAuZmlsdGVyKHIgPT4gci5wa2cgJiYgci5wa2cudmFsdWUgPT09ICJzdWJwcm9jZXNzIiAmJiByLmVsZW1lbnRzICYmIHIuZWxlbWVudHMuZmlsdGVyKGUgPT4gZS5uYW1lKS5tYXAoZSA9PiBlLm5hbWUudmFsdWUpLmluY2x1ZGVzKGZ1bmN0aW9uTmFtZSkpOwogICAgY29uc3QgdXNlSW1wb3J0RnJvbSA9IGltcG9ydEZyb20ubGVuZ3RoID4gMDsKICAgIGlmKChoYXNTaGVsbFRydWUgJiYgdXNlU3VicHJvY2Vzc1BhY2thZ2UgJiYgbm9kZS5mdW5jdGlvbk5hbWUudmFsdWUgPT09IGZ1bmN0aW9uTmFtZSAmJiBub2RlLm1vZHVsZU9yT2JqZWN0LnZhbHVlID09PSAic3VicHJvY2VzcyIpfHwKICAgICAgICh1c2VJbXBvcnRGcm9tICYmIG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSBmdW5jdGlvbk5hbWUpKXsKICAgICAgCiAgICAgIAogICAgICBmb3IgKHZhciBpID0gMCA7IGkgPCBub2RlLmFyZ3VtZW50cy52YWx1ZXMubGVuZ3RoIDsgaSsrKSB7CiAgICAgICAgY29uc3QgYXJnID0gbm9kZS5hcmd1bWVudHMudmFsdWVzW2ldOwogICAgICAgIGNvbnN0IG5iQXJndW1lbnRzID0gbm9kZS5hcmd1bWVudHMudmFsdWVzLmxlbmd0aDsKICAgICAgICBpZihhcmcubmFtZSAmJiBhcmcubmFtZS52YWx1ZSA9PSAic2hlbGwiICYmIGFyZy52YWx1ZSAmJiBhcmcudmFsdWUudmFsdWUgPT0gIlRydWUiKSB7CiAgICAgICAgICB2YXIgZXJyb3IgPSBidWlsZEVycm9yKG5vZGUuc3RhcnQubGluZSwgbm9kZS5zdGFydC5jb2wsIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwgInNoZWxsIGRlZmluZWQgd2l0aCB0cnVlIiwgIkNSSVRJQ0FMIiwgIlNFQ1VSSVRZIik7CgoKCQkJCQlpZihpID4gMCkgewogICAgICAgICAgIGNvbnN0IHByZXZpb3VzQXJndW1lbnQgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXNbaS0xXTsKICAgICAgICAgICBjb25zdCBlZGl0ID0gYnVpbGRFZGl0UmVtb3ZlKHByZXZpb3VzQXJndW1lbnQuZW5kLmxpbmUsIHByZXZpb3VzQXJndW1lbnQuZW5kLmNvbCwKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIGFyZy5lbmQubGluZSwgYXJnLmVuZC5jb2wpCiAgICAJCQkgY29uc3QgZml4ID0gYnVpbGRGaXgoInJlbW92ZSBzaGVsbCBhcmd1bWVudCIsIFtlZGl0XSk7CiAgICAgICAgICAgZXJyb3IgPSBlcnJvci5hZGRGaXgoZml4KTsKICAgICAgICAgIH0KCiAgICAgICAgICAKCQkJCQkgYWRkRXJyb3IoZXJyb3IpOwogICAgICAgIH0KICAgICAgfSAgICAgIAogICAgfQogIH0pOwp9")
        .setLanguage(Language.PYTHON)
        .setId("subprocess-shell-true")
        .setRegex(null)
        .createRule();


    public static Rule RULE_REQUEST_TIMEOUT = new RuleBuilder()
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setType(RuleType.AST_CHECK)
        .setCode("ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGlmKCFub2RlLmFyZ3VtZW50cyB8fCAhbm9kZS5hcmd1bWVudHMudmFsdWVzKSB7CiAgICByZXR1cm47CiAgfQogIAogIGNvbnN0IGFyZ3VtZW50cyA9IG5vZGUuYXJndW1lbnRzLnZhbHVlczsKICBjb25zdCBuYkFyZ3VtZW50cyA9IG5vZGUuYXJndW1lbnRzLnZhbHVlcy5sZW5ndGg7CiAgY29uc3QgYWxsUGFja2FnZXMgPSBub2RlLmNvbnRleHQuaW1wb3J0cy5maWx0ZXIociA9PiByLnBhY2thZ2VzKS5mbGF0TWFwKGkgPT4gaS5wYWNrYWdlcy5tYXAocCA9PiBwLm5hbWUuc3RyKSk7CiAgY29uc3QgdXNlUmVxdWVzdHNQYWNrYWdlID0gYWxsUGFja2FnZXMuZmlsdGVyKGkgPT4gaSA9PT0gInJlcXVlc3RzIikubGVuZ3RoID4gMDsKCiAgICAKICAKICBjb25zdCBmdW5jdGlvbnMgPSBbInBvc3QiLCAiZ2V0IiwgInB1dCIsICJwYXRjaCJdOwogIGNvbnN0IGhhc1RpbWVvdXQgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXMuZmlsdGVyKGEgPT4gYS5uYW1lICYmIGEubmFtZS52YWx1ZSA9PSAidGltZW91dCIpLmxlbmd0aCA+IDA7CiAgCiAgZnVuY3Rpb25zLmZvckVhY2goZnVuY3Rpb25OYW1lID0+IHsKICAgIGNvbnN0IGltcG9ydEZyb20gPSBub2RlLmNvbnRleHQuaW1wb3J0cwogICAgCS5maWx0ZXIociA9PiByLnBrZyAmJiByLnBrZy52YWx1ZSA9PT0gInJlcXVlc3RzIiAmJiByLmVsZW1lbnRzICYmIHIuZWxlbWVudHMuZmlsdGVyKGUgPT4gZS5uYW1lKS5tYXAoZSA9PiBlLm5hbWUudmFsdWUpLmluY2x1ZGVzKGZ1bmN0aW9uTmFtZSkpOwoJCWNvbnN0IHVzZUltcG9ydEZyb20gPSBpbXBvcnRGcm9tLmxlbmd0aCA+IDA7CgogICAgaWYoKHVzZVJlcXVlc3RzUGFja2FnZSAmJiAhaGFzVGltZW91dCAmJiBub2RlLm1vZHVsZU9yT2JqZWN0ICYmIG5vZGUuZnVuY3Rpb25OYW1lICYmIG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSBmdW5jdGlvbk5hbWUgJiYgbm9kZS5tb2R1bGVPck9iamVjdC52YWx1ZSA9PT0gInJlcXVlc3RzIikKICAgICAgfHwgKHVzZUltcG9ydEZyb20gJiYgbm9kZS5mdW5jdGlvbk5hbWUudmFsdWUgPT09IGZ1bmN0aW9uTmFtZSAmJiAhaGFzVGltZW91dCkpewogICAgY29uc3QgZXJyb3IgPSBidWlsZEVycm9yKG5vZGUuc3RhcnQubGluZSwgbm9kZS5zdGFydC5jb2wsIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwgInRpbWVvdXQgbm90IGRlZmluZWQiLCAiQ1JJVElDQUwiLCAiU0FGRVRZIik7CiAgICBjb25zdCBsaW5lVG9JbnNlcnQgPSBhcmd1bWVudHNbYXJndW1lbnRzLmxlbmd0aCAtIDFdLmVuZC5saW5lOwogICAgY29uc3QgY29sVG9JbnNlcnQgPSBhcmd1bWVudHNbYXJndW1lbnRzLmxlbmd0aCAtIDFdLmVuZC5jb2w7CiAgICBjb25zdCBlZGl0ID0gYnVpbGRFZGl0QWRkKGxpbmVUb0luc2VydCwgY29sVG9JbnNlcnQsICIsIHRpbWVvdXQ9NSIpCiAgICBjb25zdCBmaXggPSBidWlsZEZpeCgiYWRkIHRpbWVvdXQgYXJndW1lbnQiLCBbZWRpdF0pOwogICAgYWRkRXJyb3IoZXJyb3IuYWRkRml4KGZpeCkpOwogIH0KICB9KTsKICAKfQ==")
        .setLanguage(Language.PYTHON)
        .setId("requests-timeout")
        .setRegex(null)
        .createRule();

    public static Rule RULE_MKTEMP = new RuleBuilder()
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setType(RuleType.AST_CHECK)
        .setCode("ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGlmKCFub2RlLmFyZ3VtZW50cyB8fCAhbm9kZS5hcmd1bWVudHMudmFsdWVzKSB7CiAgICByZXR1cm47CiAgfQogIAogIGNvbnN0IGFyZ3VtZW50cyA9IG5vZGUuYXJndW1lbnRzLnZhbHVlczsKICBjb25zdCBuYkFyZ3VtZW50cyA9IG5vZGUuYXJndW1lbnRzLnZhbHVlcy5sZW5ndGg7CiAgY29uc3QgYWxsUGFja2FnZXMgPSBub2RlLmNvbnRleHQuaW1wb3J0cy5maWx0ZXIociA9PiByLnBhY2thZ2VzKS5mbGF0TWFwKGkgPT4gaS5wYWNrYWdlcy5tYXAocCA9PiBwLm5hbWUuc3RyKSk7CiAgY29uc3QgdXNlVGVtcGZpbGVQYWNrYWdlID0gYWxsUGFja2FnZXMuZmlsdGVyKGkgPT4gaSA9PT0gInRlbXBmaWxlIikubGVuZ3RoID4gMDsKICBjb25zdCBpbXBvcnRGcm9tID0gbm9kZS5jb250ZXh0LmltcG9ydHMKICAJLmZpbHRlcihyID0+IHIucGtnICYmIHIucGtnLnZhbHVlID09PSAidGVtcGZpbGUiICYmIHIuZWxlbWVudHMgJiYgci5lbGVtZW50cy5maWx0ZXIoZSA9PiBlLm5hbWUpLm1hcChlID0+IGUubmFtZS52YWx1ZSkuaW5jbHVkZXMoIm1rdGVtcCIpKTsKCWNvbnN0IHVzZUltcG9ydEZyb20gPSBpbXBvcnRGcm9tLmxlbmd0aCA+IDA7CiAgaWYgKCF1c2VUZW1wZmlsZVBhY2thZ2UgJiYgIXVzZUltcG9ydEZyb20pewogICAgcmV0dXJuOwogIH0KICAKCiAgaWYoKHVzZVRlbXBmaWxlUGFja2FnZSAmJiBub2RlLm1vZHVsZU9yT2JqZWN0ICYmIG5vZGUuZnVuY3Rpb25OYW1lICYmIG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSAibWt0ZW1wIiAmJiBub2RlLm1vZHVsZU9yT2JqZWN0LnZhbHVlID09PSAidGVtcGZpbGUiKSB8fAogICAgKHVzZUltcG9ydEZyb20gJiYgbm9kZS5mdW5jdGlvbk5hbWUudmFsdWUgPT09ICJta3RlbXAiKSApewogICAgY29uc3QgZXJyb3IgPSBidWlsZEVycm9yKG5vZGUuc3RhcnQubGluZSwgbm9kZS5zdGFydC5jb2wsIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIm1rdGVtcCBpcyB1bnNhZmUiLCAiQ1JJVElDQUwiLCAiU0VDVVJJVFkiKTsKCiAgICBjb25zdCBlZGl0cyA9IFtdOwogICAgY29uc3QgZWRpdCA9IGJ1aWxkRWRpdFVwZGF0ZShub2RlLmZ1bmN0aW9uTmFtZS5zdGFydC5saW5lLCBub2RlLmZ1bmN0aW9uTmFtZS5zdGFydC5jb2wsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIG5vZGUuZnVuY3Rpb25OYW1lLmVuZC5saW5lLCBub2RlLmZ1bmN0aW9uTmFtZS5lbmQuY29sLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAibWtzdGVtcCIpOwogICAgCiAgICBpZih1c2VJbXBvcnRGcm9tKSB7CiAgICAgIAogICAgICAgY29uc3QgYWRkTWlzc2luZ0ltcG9ydCA9IGJ1aWxkRWRpdFVwZGF0ZShpbXBvcnRGcm9tWzBdLmVsZW1lbnRzWzBdLnN0YXJ0LmxpbmUsIGltcG9ydEZyb21bMF0uZWxlbWVudHNbMF0uc3RhcnQuY29sLAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBpbXBvcnRGcm9tWzBdLmVsZW1lbnRzWzBdLmVuZC5saW5lLCBpbXBvcnRGcm9tWzBdLmVsZW1lbnRzWzBdLmVuZC5jb2wsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAkJCQkJCQkJIm1rc3RlbXAiKTsKICAgICAgZWRpdHMucHVzaChhZGRNaXNzaW5nSW1wb3J0KTsKICAgIH0KICAgIAogICAgZWRpdHMucHVzaChlZGl0KTsKICAgIGNvbnN0IGZpeCA9IGJ1aWxkRml4KCJ1c2UgbWtzdGVtcCBpbnN0ZWFkIiwgZWRpdHMpOwogICAgYWRkRXJyb3IoZXJyb3IuYWRkRml4KGZpeCkpOwogIH0KICAKfQ==")
        .setLanguage(Language.PYTHON)
        .setId("mktemp")
        .setRegex(null)
        .createRule();

    public static Rule RULE_NO_EVAL = new RuleBuilder()
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setType(RuleType.AST_CHECK)
        .setCode("ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGlmKG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSAiZXZhbCIpewogICAgY29uc3QgaGFzT25lQXJndW1lbnQgPSBub2RlLmFyZ3VtZW50cyAmJiBub2RlLmFyZ3VtZW50cy52YWx1ZXMgJiYgbm9kZS5hcmd1bWVudHMudmFsdWVzLmxlbmd0aCA9PT0gMTsKCiAgICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3Iobm9kZS5zdGFydC5saW5lLCBub2RlLnN0YXJ0LmNvbCwgbm9kZS5lbmQubGluZSwgbm9kZS5lbmQuY29sLCAiZG8gbm90IHVzZSBldmFsIGFzIHRoaXMgaXMgdW5zYWZlIiwgIkNSSVRJQ0FMIiwgIlNBRkVUWSIpOwoKICAgIGNvbnN0IGFyZ3VtZW50VmFsdWUgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXNbMF0udmFsdWUuc3RyOwogICAgY29uc3QgbmV3RnVuY3Rpb25DYWxsID0gYGxpdGVyYWxfZXZhbCgke2FyZ3VtZW50VmFsdWV9KWA7CiAgICBjb25zdCBlZGl0UmVwbGFjZUZ1bmN0aW9uQ2FsbCA9IGJ1aWxkRWRpdFVwZGF0ZShub2RlLnN0YXJ0LmxpbmUsIG5vZGUuc3RhcnQuY29sLCBub2RlLmVuZC5saW5lLCBub2RlLmVuZC5jb2wsIG5ld0Z1bmN0aW9uQ2FsbCkKCiAgICBjb25zdCBlZGl0QWRkSW1wb3J0ID0gYnVpbGRFZGl0QWRkKDEsIDEsICJmcm9tIGFzdCBpbXBvcnQgbGl0ZXJhbF9ldmFsXG4iKTsKCgogICAgY29uc3QgZml4ID0gYnVpbGRGaXgoInJlcGxhY2Ugd2l0aCBsaXRlcmFsX2V2YWwiLCBbZWRpdFJlcGxhY2VGdW5jdGlvbkNhbGwsIGVkaXRBZGRJbXBvcnRdKTsKICAgIGFkZEVycm9yKGVycm9yLmFkZEZpeChmaXgpKTsKICB9Cn0=")
        .setLanguage(Language.PYTHON)
        .setId("no-eval")
        .setRegex(null)
        .createRule();


    public static Rule RULE_INSECURE_HASH_FUNCTIONS = new RuleBuilder()
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setCode("ZnVuY3Rpb24gY2hlY2tBbGdvcml0aG0obm9kZSwgaGFzaE1ldGhvZCkgewogIGlmICghKG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSAibmV3IiAmJiBub2RlLm1vZHVsZU9yT2JqZWN0LnZhbHVlID09PSAiaGFzaGxpYiIpKSB7CiAgICByZXR1cm47CiAgfQogIGlmICghbm9kZS5hcmd1bWVudHMgfHwgIW5vZGUuYXJndW1lbnRzLnZhbHVlcyl7CiAgICByZXR1cm47CiAgfQoKICBjb25zdCB1c2VPdXRkYXRlZEhhc2hNZXRob2QgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXMKICAJLmZpbHRlcihhID0+IGEudmFsdWUgJiYgYS52YWx1ZS5zdHIudG9Mb3dlckNhc2UoKSA9PSBgJyR7aGFzaE1ldGhvZH0nYCkubGVuZ3RoID4gMDsKCiAgY29uc3QgYWxsUGFja2FnZXMgPSBub2RlLmNvbnRleHQuaW1wb3J0cwogIAkuZmlsdGVyKGkgPT4gaS5wYWNrYWdlcykKICAJLmZsYXRNYXAoaSA9PiBpLnBhY2thZ2VzLm1hcChwID0+IHAubmFtZS5zdHIpKTsKICBjb25zdCB1c2VIYXNobGliID0gYWxsUGFja2FnZXMuZmlsdGVyKGkgPT4gaSA9PT0gImhhc2hsaWIiKS5sZW5ndGggPiAwOwoKICBpZih1c2VPdXRkYXRlZEhhc2hNZXRob2QgJiYgdXNlSGFzaGxpYil7CiAgICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3Iobm9kZS5zdGFydC5saW5lLCBub2RlLnN0YXJ0LmNvbCwgbm9kZS5lbmQubGluZSwgbm9kZS5lbmQuY29sLCBgVXNlIG9mIGluc2VjdXJlIGhhc2hpbmcgbWV0aG9kICR7aGFzaE1ldGhvZH1gLCAiQ1JJVElDQUwiLCAiU0VDVVJJVFkiKTsKICAgIGFkZEVycm9yKGVycm9yKTsKICB9IAp9CgpmdW5jdGlvbiB2aXNpdChub2RlKSB7CiAgY29uc3QgbWV0aG9kcyA9IFsibWQ0IiwgIm1kNSIsICJzaGExIl07CiAgbWV0aG9kcy5mb3JFYWNoKG1ldGhvZCA9PiB7CiAgICBjaGVja0FsZ29yaXRobShub2RlLCBtZXRob2QpOwogIH0pOwp9")
        .setType(RuleType.AST_CHECK)
        .setLanguage(Language.PYTHON)
        .setId("insecure-hash-functions")
        .setRegex(null)
        .createRule();


    public static Rule RULE_NO_EMPTY_ARRAY_AS_PARAMETER = new RuleBuilder()
        .setEntityChecked(EntityChecked.FUNCTION_DEFINITION)
        .setCode("ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGlmIChub2RlLnBhcmFtZXRlcnMgJiYgbm9kZS5wYXJhbWV0ZXJzLnZhbHVlcykgewogICAgY29uc3QgcGFyYW1ldGVyc1dpdGhFbXB0eUFycmF5ID0gbm9kZS5wYXJhbWV0ZXJzLnZhbHVlcy5maWx0ZXIocCA9PiBwICYmIHAuZGVmYXVsdFZhbHVlICYmIHAuZGVmYXVsdFZhbHVlLnZhbHVlID09PSAiW10iKTsKCiAgICBmb3IodmFyIGkgPSAwIDsgaSA8IHBhcmFtZXRlcnNXaXRoRW1wdHlBcnJheS5sZW5ndGggOyBpKyspIHsKICAgICAgY29uc3QgcGFyYW1ldGVyID0gcGFyYW1ldGVyc1dpdGhFbXB0eUFycmF5W2ldOwogICAgICBjb25zb2xlLmxvZyhwYXJhbWV0ZXIubmFtZS52YWx1ZSk7CiAgICAgIGNvbnNvbGUubG9nKHBhcmFtZXRlci5zdGFydC5jb2wpOwogICAgICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3IocGFyYW1ldGVyLmRlZmF1bHRWYWx1ZS5zdGFydC5saW5lLCBwYXJhbWV0ZXIuZGVmYXVsdFZhbHVlLnN0YXJ0LmNvbCwgcGFyYW1ldGVyLmRlZmF1bHRWYWx1ZS5lbmQubGluZSwgcGFyYW1ldGVyLmRlZmF1bHRWYWx1ZS5lbmQuY29sLCAiY2Fubm90IHVzZSBkZWZhdWx0IGluaXRpYWxpemVyIFtdIGluIGZ1bmN0aW9uIiwgIkNSSVRJQ0FMIiwgIlNBRkVUWSIpOwogICAgICBhZGRFcnJvcihlcnJvcik7CiAgICB9CiAgfQp9")
        .setType(RuleType.AST_CHECK)
        .setLanguage(Language.PYTHON)
        .setId("array-no-empty-parameters")
        .setRegex(null)
        .createRule();

    public static Rule RULE_FILE_WRITE_OTHERS = new RuleBuilder()
        .setCode("ZnVuY3Rpb24gdmlzaXQocGF0dGVybiwgZmlsZW5hbWUsIGNvZGUpIHsKICBjb25zdCBtb2RlID0gcGF0dGVybi52YXJpYWJsZXMuZ2V0KCJtb2RlIik7CiAgCiAgaWYoZmlsZW5hbWUuaW5jbHVkZXMoIl90ZXN0LnB5IikgfHwgZmlsZW5hbWUuc3RhcnRzV2l0aCgidGVzdF8iKSkgewogICAgcmV0dXJuOwogIH0KCiAgY29uc29sZS5sb2cobW9kZS52YWx1ZSk7CiAgaWYgKG1vZGUudmFsdWUuaW5jbHVkZXMoInN0YXQuU19JV09USCIpKSB7CiAgICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3IobW9kZS5zdGFydC5saW5lLCBtb2RlLnN0YXJ0LmNvbCwgbW9kZS5lbmQubGluZSwgbW9kZS5lbmQuY29sLCAiZmlsZSBjYW4gYmUgd3JpdHRlbiBieSBvdGhlcnMiLCAiQ1JJVElDQUwiLCAic2VjdXJpdHkiKTsKICAgIGNvbnN0IGZpbGVuYW1lID0gcGF0dGVybi52YXJpYWJsZXMuZ2V0KCJmaWxlIikudmFsdWU7CiAgICBjb25zdCBtb2RlcyA9IG1vZGUudmFsdWUucmVwbGFjZUFsbCgiICIsICIiKS5zcGxpdCgifCIpLmZpbHRlcihlID0+IGUgIT09ICJzdGF0LlNfSVdPVEgiKTsKICAgIGNvbnN0IG5ld01vZGVzID0gbW9kZXMuam9pbigiIHwgIik7CiAgICBjb25zdCBlZGl0ID0gYnVpbGRFZGl0KG1vZGUuc3RhcnQubGluZSwgbW9kZS5zdGFydC5jb2wsIG1vZGUuZW5kLmxpbmUsIG1vZGUuZW5kLmNvbCwgInVwZGF0ZSIsIG5ld01vZGVzKTsKICAgIGNvbnN0IGZpeCA9IGJ1aWxkRml4KCJyZW1vdmUgdGhlIHdyaXRlIGZsYWciLCBbZWRpdF0pOwogICAgYWRkRXJyb3IoZXJyb3IuYWRkRml4KGZpeCkpOwogIH0KCn0=")
        .setType(RuleType.REGEX)
        .setLanguage(Language.PYTHON)
        .setId("array-no-empty-parameters")
        .setRegex("b3MuY2htb2QoJHtmaWxlfSwgJHttb2RlfSk=")
        .createRule();

    public static Rule RULE_JINJA2_AUTOESCAPE = new RuleBuilder()
        .setCode("ZnVuY3Rpb24gdmlzaXQobm9kZSwgZmlsZW5hbWUsIGNvZGUpIHsKICAKICBpZighbm9kZS5mdW5jdGlvbk5hbWUpIHsKICAgIHJldHVybjsKICB9CiAgCiAgaWYgKG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSAiRW52aXJvbm1lbnQiKSB7CiAgICAKICAgIGNvbnN0IHVzZUppbmphMkVudmlyb25tZW50ID0gbm9kZS5jb250ZXh0LmltcG9ydHMuZmlsdGVyKGkgPT4gaS5lbGVtZW50cyAmJiBpLnBrZykuZmlsdGVyKGkgPT4gaS5lbGVtZW50cy5tYXAoZSA9PiBlLm5hbWUudmFsdWUpLmluY2x1ZGVzKCJFbnZpcm9ubWVudCIpICYmIGkucGtnLnZhbHVlID09PSAiamluamEyIikubGVuZ3RoID4gMDsKCQogICAgaWYodXNlSmluamEyRW52aXJvbm1lbnQpIHsKICAgICAgaWYoIW5vZGUuYXJndW1lbnRzIHx8ICFub2RlLmFyZ3VtZW50cy52YWx1ZXMpewogICAgICAgIHJldHVybjsKICAgICAgfQogICAgICAKICAgICAgY29uc3QgaGFzQXV0b0VzY2FwZSA9IG5vZGUuYXJndW1lbnRzLnZhbHVlcy5maWx0ZXIoYSA9PiBhLm5hbWUgJiYgYS5uYW1lLnZhbHVlID09ICJhdXRvZXNjYXBlIikubGVuZ3RoID4gMDsKCQkJCiAgICAgIGlmKGhhc0F1dG9Fc2NhcGUpewogICAgICAJY29uc3QgYXJnID0gbm9kZS5hcmd1bWVudHMudmFsdWVzLmZpbHRlcihhID0+IGEubmFtZSAmJiBhLm5hbWUudmFsdWUgPT0gImF1dG9lc2NhcGUiKVswXTsKICAgICAgICBpZihhcmcudmFsdWUudmFsdWUgPT09ICJGYWxzZSIpewogICAgICAgICAgICAgY29uc3QgZXJyb3IgPSBidWlsZEVycm9yKGFyZy5zdGFydC5saW5lLCBhcmcuc3RhcnQuY29sLCBhcmcuZW5kLmxpbmUsIGFyZy5lbmQuY29sLCAiYXV0b2VzY2FwZT1GYWxzZSBsZWFkcyB0byBYU1MgaXNzdWVzIiwgIkNSSVRJQ0FMIiwgIlNFQ1VSSVRZIik7CgogICAgICAgICAgICAgY29uc3QgZWRpdCA9IGJ1aWxkRWRpdFVwZGF0ZSgKICAgICAgICAgICAgICAgYXJnLnZhbHVlLnN0YXJ0LmxpbmUsIGFyZy52YWx1ZS5zdGFydC5jb2wsCiAgICAgICAgICAgICAgIGFyZy52YWx1ZS5lbmQubGluZSwgYXJnLnZhbHVlLmVuZC5jb2wsICJUcnVlIikKICAgIAkJCQljb25zdCBmaXggPSBidWlsZEZpeCgidXNlIGF1dG9lc2NhcGUgVHJ1ZSIsIFtlZGl0XSk7CiAgICAJCQkJYWRkRXJyb3IoZXJyb3IuYWRkRml4KGZpeCkpOwogICAgICAgIH0KICAgICAgfQogICAgfQogIH0KICAKfQ==")
        .setType(RuleType.AST_CHECK)
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setLanguage(Language.PYTHON)
        .setId("jinja2-no-escape")
        .setRegex(null)
        .createRule();

    public static Rule RULE_DESERIALIZE_UNTRUSTED_DATA = new RuleBuilder()
        .setType(RuleType.AST_CHECK)
        .setCode("Y29uc3QgTU9EVUxFU19GVU5DVElPTlNfVE9fQVZPSUQgPSB7CiAgInBpY2tsZSI6IFsibG9hZHMiLCAibG9hZCIsICJVbnBpY2tsZXIiXSwKICAic2hlbHZlIjogWyJvcGVuIl0sCiAgIm1hcnNoYWwiOiBbImxvYWQiLCAibG9hZHMiXSwKICAianNvbnBpY2tsZWQiOiBbImRlY29kZSJdLAogICJwYW5kYXMiOiBbInJlYWRfcGlja2xlIl0KfQoKCmZ1bmN0aW9uIHZpc2l0KG5vZGUsIGZpbGVuYW1lLCBjb2RlKXsKICBpZighbm9kZSB8fCAhbm9kZS5mdW5jdGlvbk5hbWUgfHwgIW5vZGUubW9kdWxlT3JPYmplY3QpIHsKICAgIHJldHVybjsKICB9CiAgCiAgaWYoZmlsZW5hbWUuaW5jbHVkZXMoIl90ZXN0LnB5IikgfHwgZmlsZW5hbWUuc3RhcnRzV2l0aCgidGVzdF8iKSkgewogICAgcmV0dXJuOwogIH0KICBjb25zdCBhbGxQYWNrYWdlcyA9IG5vZGUuY29udGV4dC5pbXBvcnRzLmZpbHRlcihyID0+IHIucGFja2FnZXMpLmZsYXRNYXAoaSA9PiBpLnBhY2thZ2VzLm1hcChwID0+IHAubmFtZS5zdHIpKTsKCiAgCiAgY29uc3QgbW9kdWxlID0gbm9kZS5tb2R1bGVPck9iamVjdC52YWx1ZTsKICBjb25zdCBmdW5jID0gbm9kZS5mdW5jdGlvbk5hbWUudmFsdWU7CiAgCiAgaWYoTU9EVUxFU19GVU5DVElPTlNfVE9fQVZPSURbbW9kdWxlXSkgewogICAgY29uc3QgdXNlTW9kdWxlID0gYWxsUGFja2FnZXMuZmlsdGVyKGkgPT4gaSA9PT0gbW9kdWxlKS5sZW5ndGggPiAwOwogIAlpZiAoIXVzZU1vZHVsZSl7CiAgICAJcmV0dXJuOwogIAl9CiAgICAKICAgIGNvbnN0IGZ1bmN0aW9ucyA9IE1PRFVMRVNfRlVOQ1RJT05TX1RPX0FWT0lEW21vZHVsZV07CiAgICAKICAgIGlmIChmdW5jdGlvbnMuaW5jbHVkZXMoZnVuYykpIHsKICAgICAgY29uc3QgZXJyb3IgPSBidWlsZEVycm9yKG5vZGUuc3RhcnQubGluZSwgbm9kZS5zdGFydC5jb2wsIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwgYCR7bW9kdWxlfS4ke2Z1bmN9IGlzIG5vdCBzYWZlIGZvciBkZXNlcmlhbGl6aW5nIHVuc3RydXN0ZWQgZGF0YWAsICJDUklUSUNBTCIsICJTRUNVUklUWSIpOwogICAgCWFkZEVycm9yKGVycm9yKTsKICAgIH0KICB9Cn0=")
        .setLanguage(Language.PYTHON)
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setId("deserialize-untrusted-data")
        .setRegex(null)
        .createRule();

    public static Rule RULE_INSECURE_SSL_PROTOCOLS = new RuleBuilder()
        .setType(RuleType.AST_CHECK)
        .setCode("ZnVuY3Rpb24gY2hlY2tQcm90b2NvbChub2RlLCBwcm90b2NvbCkgewogIGlmICghbm9kZS5hcmd1bWVudHMpewogICAgcmV0dXJuOwogIH0KICBpZiAoIShub2RlLmZ1bmN0aW9uTmFtZS52YWx1ZSA9PT0gIndyYXBfc29ja2V0IiAmJiBub2RlLm1vZHVsZU9yT2JqZWN0LnZhbHVlID09PSAic3NsIikpIHsKICAgIHJldHVybjsKICB9CgogIGNvbnN0IHVzZU91dGRhdGVkUHJvdG9jb2wgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXMuZmlsdGVyKGEgPT4gYS52YWx1ZSAmJiBhLnZhbHVlLnN0ciA9PSBgc3NsLiR7cHJvdG9jb2x9YCkubGVuZ3RoID4gMDsKCiAgY29uc3QgYWxsUGFja2FnZXMgPSBub2RlLmNvbnRleHQuaW1wb3J0cy5maWx0ZXIoaSA9PiBpLnBhY2thZ2VzKS5mbGF0TWFwKGkgPT4gaS5wYWNrYWdlcy5tYXAocCA9PiBwLm5hbWUuc3RyKSk7CiAgY29uc3QgdXNlU3NsUGFja2FnZSA9IGFsbFBhY2thZ2VzLmZpbHRlcihpID0+IGkgPT09ICJzc2wiKS5sZW5ndGggPiAwOwoKICBpZih1c2VPdXRkYXRlZFByb3RvY29sICYmIHVzZVNzbFBhY2thZ2UpewogICAgY29uc3QgZXJyb3IgPSBidWlsZEVycm9yKG5vZGUuc3RhcnQubGluZSwgbm9kZS5zdGFydC5jb2wsIG5vZGUuZW5kLmxpbmUsIG5vZGUuZW5kLmNvbCwgYFVzZSBvZiBpbnNlY3VyZSBwcm90b2NvbCAke3Byb3RvY29sfWAsICJDUklUSUNBTCIsICJTRUNVUklUWSIpOwogICAgYWRkRXJyb3IoZXJyb3IpOwogIH0gCn0KCmZ1bmN0aW9uIHZpc2l0KG5vZGUpIHsKICBjb25zdCBwcm90b2NvbHMgPSBbIlBST1RPQ09MX1NTTHYzIiwgIlBST1RPQ09MX1NTTHYyIiwgIlNTTHYyX01FVEhPRCIsICJTU0x2MjNfTUVUSE9EIiwgIlBST1RPQ09MX1RMU3YxIiwgIlNTTHYzX01FVEhPRCIsICJUTFN2MV9NRVRIT0QiXTsKICBwcm90b2NvbHMuZm9yRWFjaChwcm90b2NvbCA9PiB7CiAgICBjaGVja1Byb3RvY29sKG5vZGUsIHByb3RvY29sKTsKICB9KTsKCn0=")
        .setLanguage(Language.PYTHON)
        .setEntityChecked(EntityChecked.FUNCTION_CALL)
        .setId("insecure-ssl-protocols")
        .setRegex(null)
        .createRule();
}
