package io.codiga.warmup;

import io.codiga.analyzer.rule.AnalyzerRule;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;

import java.util.List;

public class AnalyzerWarmupCode {

    public final static List<AnalyzerWarmupCodeData> WARMUP_CODE = List.of(
        new AnalyzerWarmupCodeDataBuilder()
            .setFilename("pythoncode.py")
            .setCodeBase64("YSA9IDEKYiA9IDIKcmFpc2UgTm90SW1wbGVtZW50ZWQKYyA9IDM=")
            .setLanguage(Language.PYTHON)
            .setAnalyzerRuleList(
                List.of(new AnalyzerRule(
                    "raising-not-implemented",
                    Language.PYTHON,
                    RuleType.PATTERN,
                    null,
                    "ZnVuY3Rpb24gdmlzaXQocGF0dGVybiwgZmlsZW5hbWUsIGNvZGUpIHsKICBjb25zdCBleGNlcHRpb25OYW1lID0gcGF0dGVybi52YXJpYWJsZXMuZ2V0KCJleGNlcHRpb25OYW1lIik7CiAgaWYgKGV4Y2VwdGlvbk5hbWUgJiYgZXhjZXB0aW9uTmFtZS52YWx1ZSA9PT0gIk5vdEltcGxlbWVudGVkIikgewogICAgY29uc3QgZXJyb3IgPSBidWlsZEVycm9yKHBhdHRlcm4uc3RhcnQubGluZSwgcGF0dGVybi5zdGFydC5jb2wsIHBhdHRlcm4uZW5kLmxpbmUsIHBhdHRlcm4uZW5kLmNvbCwgInJhaXNlIE5vdEltcGxlbWVudGVkIGlzIG5vdCBhIHZhbGlkIGVycm9yIiwgIklORk8iLCAiQkVTVF9QUkFDVElDRVMiKTsKICAgIGNvbnN0IGVkaXQgPSBidWlsZEVkaXQocGF0dGVybi5zdGFydC5saW5lLCBwYXR0ZXJuLnN0YXJ0LmNvbCwgcGF0dGVybi5lbmQubGluZSwgcGF0dGVybi5lbmQuY29sLCAidXBkYXRlIiwgInJhaXNlIE5vdEltcGxlbWVudGVkRXJyb3IiKTsKICAgIGNvbnN0IGZpeCA9IGJ1aWxkRml4KCJyYWlzZSBOb3RJbXBsZW1lbnRlZEVycm9yIiwgW2VkaXRdKTsKICAgIGFkZEVycm9yKGVycm9yLmFkZEZpeChmaXgpKTsKICB9Cn0=",
                    "raise ${exceptionName}",
                    null
                ))
            ).createAnalyzerWarmupCodeData(),
        new AnalyzerWarmupCodeDataBuilder()
            .setFilename("pythoncode.py")
            .setCodeBase64("cHJpbnQoImJsYSIpCmV2YWwoJ1sxLCAyLCAzXScp")
            .setLanguage(Language.PYTHON)
            .setAnalyzerRuleList(
                List.of(new AnalyzerRule(
                    "no-eval",
                    Language.PYTHON,
                    RuleType.AST_CHECK,
                    EntityChecked.FUNCTION_CALL,
                    "ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGlmKG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSAiZXZhbCIpewogICAgY29uc3QgaGFzT25lQXJndW1lbnQgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXMgJiYgbm9kZS5hcmd1bWVudHMudmFsdWVzLmxlbmd0aCA9PT0gMTsKCiAgICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3Iobm9kZS5zdGFydC5saW5lLCBub2RlLnN0YXJ0LmNvbCwgbm9kZS5lbmQubGluZSwgbm9kZS5lbmQuY29sLCAiZG8gbm90IHVzZSBldmFsIGFzIHRoaXMgaXMgdW5zYWZlIiwgIkNSSVRJQ0FMIiwgIlNBRkVUWSIpOwoKICAgIGNvbnN0IGFyZ3VtZW50VmFsdWUgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXNbMF0udmFsdWUuc3RyOwogICAgY29uc3QgbmV3RnVuY3Rpb25DYWxsID0gYGxpdGVyYWxfZXZhbCgke2FyZ3VtZW50VmFsdWV9KWA7CiAgICBjb25zdCBlZGl0UmVwbGFjZUZ1bmN0aW9uQ2FsbCA9IGJ1aWxkRWRpdFVwZGF0ZShub2RlLnN0YXJ0LmxpbmUsIG5vZGUuc3RhcnQuY29sLCBub2RlLmVuZC5saW5lLCBub2RlLmVuZC5jb2wsIG5ld0Z1bmN0aW9uQ2FsbCkKCiAgICBjb25zdCBlZGl0QWRkSW1wb3J0ID0gYnVpbGRFZGl0QWRkKDEsIDEsICJmcm9tIGFzdCBpbXBvcnQgbGl0ZXJhbF9ldmFsXG4iKTsKCgogICAgY29uc3QgZml4ID0gYnVpbGRGaXgoInJlcGxhY2Ugd2l0aCBsaXRlcmFsX2V2YWwiLCBbZWRpdFJlcGxhY2VGdW5jdGlvbkNhbGwsIGVkaXRBZGRJbXBvcnRdKTsKICAgIGFkZEVycm9yKGVycm9yLmFkZEZpeChmaXgpKTsKICB9Cn0=",
                    null,
                    null
                ))
            ).createAnalyzerWarmupCodeData(),
        new AnalyzerWarmupCodeDataBuilder()
            .setFilename("pythoncode.py")
            .setCodeBase64("aW1wb3J0IHJlcXVlc3RzCnIgPSByZXF1ZXN0cy5nZXQodywgdmVyaWZ5PUZhbHNlKQpyID0gcmVxdWVzdHMuZ2V0KHcsIHZlcmlmeT1GYWxzZSwgdGltZW91dD0xMCk=")
            .setLanguage(Language.PYTHON)
            .setAnalyzerRuleList(
                List.of(new AnalyzerRule(
                    "no-timeout",
                    Language.PYTHON,
                    RuleType.AST_CHECK,
                    EntityChecked.FUNCTION_CALL,
                    "ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGNvbnN0IGhhc1RpbWVvdXQgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXMuZmlsdGVyKGEgPT4gYS5uYW1lICYmIGEubmFtZS52YWx1ZSA9PSAidGltZW91dCIpLmxlbmd0aCA+IDA7CiAgY29uc3QgYXJndW1lbnRzID0gbm9kZS5hcmd1bWVudHMudmFsdWVzOwogIGNvbnN0IG5iQXJndW1lbnRzID0gbm9kZS5hcmd1bWVudHMudmFsdWVzLmxlbmd0aDsKICBjb25zdCBhbGxQYWNrYWdlcyA9IG5vZGUuY29udGV4dC5pbXBvcnRzLmZpbHRlcihpID0+IGkucGFja2FnZXMpLmZsYXRNYXAoaSA9PiBpLnBhY2thZ2VzLm1hcChwID0+IHAubmFtZS5zdHIpKTsKICBjb25zdCB1c2VSZXF1ZXN0c1BhY2thZ2UgPSBhbGxQYWNrYWdlcy5maWx0ZXIoaSA9PiBpID09PSAicmVxdWVzdHMiKS5sZW5ndGggPiAwOwogIGlmKCFoYXNUaW1lb3V0ICYmIHVzZVJlcXVlc3RzUGFja2FnZSAmJiBub2RlLmZ1bmN0aW9uTmFtZS52YWx1ZSA9PT0gImdldCIgJiYgbm9kZS5tb2R1bGVPck9iamVjdC52YWx1ZSA9PT0gInJlcXVlc3RzIil7CiAgICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3Iobm9kZS5zdGFydC5saW5lLCBub2RlLnN0YXJ0LmNvbCwgbm9kZS5lbmQubGluZSwgbm9kZS5lbmQuY29sLCAidGltZW91dCBub3QgZGVmaW5lZCIsICJDUklUSUNBTCIsICJTQUZFVFkiKTsKICAgIGNvbnN0IGxpbmVUb0luc2VydCA9IGFyZ3VtZW50c1thcmd1bWVudHMubGVuZ3RoIC0gMV0uZW5kLmxpbmU7CiAgICBjb25zdCBjb2xUb0luc2VydCA9IGFyZ3VtZW50c1thcmd1bWVudHMubGVuZ3RoIC0gMV0uZW5kLmNvbDsKICAgIGNvbnN0IGVkaXQgPSBidWlsZEVkaXRBZGQobGluZVRvSW5zZXJ0LCBjb2xUb0luc2VydCwgIiwgdGltZW91dD01IikKICAgIGNvbnN0IGZpeCA9IGJ1aWxkRml4KCJhZGQgdGltZW91dCBhcmd1bWVudCIsIFtlZGl0XSk7CiAgICBhZGRFcnJvcihlcnJvci5hZGRGaXgoZml4KSk7CiAgfQp9",
                    null,
                    null
                ))
            ).createAnalyzerWarmupCodeData(),
        new AnalyzerWarmupCodeDataBuilder()
            .setFilename("pythoncode.py")
            .setCodeBase64("aW1wb3J0IHN1YnByb2Nlc3MKc3VicHJvY2Vzcy5Qb3BlbignL2Jpbi9scyAlcycgJSAoJ3NvbWV0aGluZycsKSwgc2hlbGw9VHJ1ZSk=")
            .setLanguage(Language.PYTHON)
            .setAnalyzerRuleList(
                List.of(new AnalyzerRule(
                    "subprocess-shell-true",
                    Language.PYTHON,
                    RuleType.AST_CHECK,
                    EntityChecked.FUNCTION_CALL,
                    "ZnVuY3Rpb24gdmlzaXQobm9kZSwgZmlsZW5hbWUsIGNvZGUpIHsKICBpZiAoIW5vZGUuYXJndW1lbnRzIHx8ICFub2RlLmNvbnRleHQgfHwgIW5vZGUuYXJndW1lbnRzLnZhbHVlcyB8fCAhbm9kZS5mdW5jdGlvbk5hbWUpewogICAgcmV0dXJuOwogIH0KICAKICBjb25zdCBmdW5jdGlvbnMgPSBbIlBvcGVuIiwgInJ1biIsICJjYWxsIl07CiAgCiAgaWYgKCFmdW5jdGlvbnMuaW5jbHVkZXMobm9kZS5mdW5jdGlvbk5hbWUudmFsdWUpKSB7CiAgICByZXR1cm47CiAgfQogIAogIGZ1bmN0aW9ucy5mb3JFYWNoKGZ1bmN0aW9uTmFtZSA9PiB7CiAgICBjb25zdCBoYXNTaGVsbFRydWUgPSBub2RlLmFyZ3VtZW50cy52YWx1ZXMuZmlsdGVyKGEgPT4gYS5uYW1lICYmIGEubmFtZS52YWx1ZSA9PSAic2hlbGwiICYmIGEudmFsdWUgJiYgYS52YWx1ZS52YWx1ZSA9PSAiVHJ1ZSIpLmxlbmd0aCA+IDA7CiAgICBjb25zdCBhbGxQYWNrYWdlcyA9IG5vZGUuY29udGV4dC5pbXBvcnRzLmZpbHRlcihpID0+IGkucGFja2FnZXMpLmZsYXRNYXAoaSA9PiBpLnBhY2thZ2VzLm1hcChwID0+IHAubmFtZS5zdHIpKTsKICAgIGNvbnN0IHVzZVN1YnByb2Nlc3NQYWNrYWdlID0gYWxsUGFja2FnZXMuZmlsdGVyKGkgPT4gaSA9PT0gInN1YnByb2Nlc3MiKS5sZW5ndGggPiAwOwogICAgY29uc3QgaW1wb3J0RnJvbSA9IG5vZGUuY29udGV4dC5pbXBvcnRzCiAgICAgICAgLmZpbHRlcihyID0+IHIucGtnICYmIHIucGtnLnZhbHVlID09PSAic3VicHJvY2VzcyIgJiYgci5lbGVtZW50cyAmJiByLmVsZW1lbnRzLmZpbHRlcihlID0+IGUubmFtZSkubWFwKGUgPT4gZS5uYW1lLnZhbHVlKS5pbmNsdWRlcyhmdW5jdGlvbk5hbWUpKTsKICAgIGNvbnN0IHVzZUltcG9ydEZyb20gPSBpbXBvcnRGcm9tLmxlbmd0aCA+IDA7CiAgICBpZigoaGFzU2hlbGxUcnVlICYmIHVzZVN1YnByb2Nlc3NQYWNrYWdlICYmIG5vZGUuZnVuY3Rpb25OYW1lLnZhbHVlID09PSBmdW5jdGlvbk5hbWUgJiYgbm9kZS5tb2R1bGVPck9iamVjdC52YWx1ZSA9PT0gInN1YnByb2Nlc3MiKXx8CiAgICAgICAodXNlSW1wb3J0RnJvbSAmJiBub2RlLmZ1bmN0aW9uTmFtZS52YWx1ZSA9PT0gZnVuY3Rpb25OYW1lKSl7CiAgICAgIAogICAgICAKICAgICAgZm9yICh2YXIgaSA9IDAgOyBpIDwgbm9kZS5hcmd1bWVudHMudmFsdWVzLmxlbmd0aCA7IGkrKykgewogICAgICAgIGNvbnN0IGFyZyA9IG5vZGUuYXJndW1lbnRzLnZhbHVlc1tpXTsKICAgICAgICBjb25zdCBuYkFyZ3VtZW50cyA9IG5vZGUuYXJndW1lbnRzLnZhbHVlcy5sZW5ndGg7CiAgICAgICAgaWYoYXJnLm5hbWUgJiYgYXJnLm5hbWUudmFsdWUgPT0gInNoZWxsIiAmJiBhcmcudmFsdWUgJiYgYXJnLnZhbHVlLnZhbHVlID09ICJUcnVlIikgewogICAgICAgICAgdmFyIGVycm9yID0gYnVpbGRFcnJvcihub2RlLnN0YXJ0LmxpbmUsIG5vZGUuc3RhcnQuY29sLCBub2RlLmVuZC5saW5lLCBub2RlLmVuZC5jb2wsICJzaGVsbCBkZWZpbmVkIHdpdGggdHJ1ZSIsICJDUklUSUNBTCIsICJTRUNVUklUWSIpOwoKCgkJCQkJaWYoaSA+IDApIHsKICAgICAgICAgICBjb25zdCBwcmV2aW91c0FyZ3VtZW50ID0gbm9kZS5hcmd1bWVudHMudmFsdWVzW2ktMV07CiAgICAgICAgICAgY29uc3QgZWRpdCA9IGJ1aWxkRWRpdFJlbW92ZShwcmV2aW91c0FyZ3VtZW50LmVuZC5saW5lLCBwcmV2aW91c0FyZ3VtZW50LmVuZC5jb2wsCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICBhcmcuZW5kLmxpbmUsIGFyZy5lbmQuY29sKQogICAgCQkJIGNvbnN0IGZpeCA9IGJ1aWxkRml4KCJyZW1vdmUgc2hlbGwgYXJndW1lbnQiLCBbZWRpdF0pOwogICAgICAgICAgIGVycm9yID0gZXJyb3IuYWRkRml4KGZpeCk7CiAgICAgICAgICB9CgogICAgICAgICAgCgkJCQkJIGFkZEVycm9yKGVycm9yKTsKICAgICAgICB9CiAgICAgIH0gICAgICAKICAgIH0KICB9KTsKfQ==",
                    null,
                    null
                ))
            ).createAnalyzerWarmupCodeData()
    );
}
