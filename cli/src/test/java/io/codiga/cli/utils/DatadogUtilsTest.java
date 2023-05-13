package io.codiga.cli.utils;

import static io.codiga.cli.utils.DatadogUtils.getRuleFromJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatadogUtilsTest {

    private final Logger log = Logger.getLogger("Test");

    @BeforeAll
    public static void init() {
        // Nothing to initialize
    }

    @AfterAll
    public static void done() {
        // Nothing to clean up.
    }


    @Test
    @DisplayName("map a pattern")
    public void testMappingRulePattern() throws JsonProcessingException {
        String definition = """
            {"id":"3","name":"no-datetime-today","description":"QXZvaWQgdXNpbmcgYGRhdGV0aW1lLnRvZGF5KClgIGFuZCB1c2UgaW5zdGVhZCBgZGF0ZXRpbWUubm93KClgLiBUaGUgdHdvIGNhbGxzIGFyZSBlcXVpdmFsZW50IChhcyBtZW50aW9uZWQgaW4gdGhlIFtvZmZpY2lhbCBkb2N1bWVudGF0aW9uXShodHRwczovL2RvY3MucHl0aG9uLm9yZy8zL2xpYnJhcnkvZGF0ZXRpbWUuaHRtbCNkYXRldGltZS5kYXRlLnRvZGF5KSkgYW5kIHRoZSB1c2Ugb2YgYG5vdygpYCBpcyBtb3JlIGV4cGxpY2l0IHRoYW4gYHRvZGF5KClgLg==","content":"ZnVuY3Rpb24gdmlzaXQocGF0dGVybiwgZmlsZW5hbWUsIGNvZGUpIHsKICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3IocGF0dGVybi5zdGFydC5saW5lLCBwYXR0ZXJuLnN0YXJ0LmNvbCwgcGF0dGVybi5lbmQubGluZSwgcGF0dGVybi5lbmQuY29sLCAidXNlIGRhdGV0aW1lLm5vdygpIGluc3RlYWQgb2YgZGF0ZXRpbWUudG9kYXkoKSIsICJJTkZPIiwgIkJFU1RfUFJBQ1RJQ0VTIik7CiAgY29uc3QgZWRpdCA9IGJ1aWxkRWRpdChwYXR0ZXJuLnN0YXJ0LmxpbmUsIHBhdHRlcm4uc3RhcnQuY29sLCBwYXR0ZXJuLmVuZC5saW5lLCBwYXR0ZXJuLmVuZC5jb2wsICJ1cGRhdGUiLCAiZGF0ZXRpbWUubm93KCkiKTsKICBjb25zdCBmaXggPSBidWlsZEZpeCgidXNlIGRhdGV0aW1lLm5vdygpIiwgW2VkaXRdKTsKICBhZGRFcnJvcihlcnJvci5hZGRGaXgoZml4KSk7Cn0=","language":"python","type":"PATTERN","pattern":"ZGF0ZXRpbWUudG9kYXkoKQ=="}
            """;
        ObjectMapper mapper = new ObjectMapper();
        var def = mapper.readTree(definition);
        var res = getRuleFromJson(def, "bla");
        assertTrue(res.isPresent());
        assertEquals("bla/no-datetime-today", res.get().name());
        assertEquals(Language.PYTHON, res.get().language());
        assertEquals("datetime.today()", res.get().pattern());
    }

    @Test
    @DisplayName("map an ast rule")
    public void testMappingAstRule() throws JsonProcessingException {
        String definition = """
            {"id":"11","name":"no-exit","description":"VXNlIGBzeXMuZXhpdCgpYCBpbnN0ZWFkIG9mIGBleGl0KClgLiBFeGl0IGlzIGEgW2J1aWx0aW5dKGh0dHBzOi8vZG9jcy5weXRob24ub3JnLzMvbGlicmFyeS9jb25zdGFudHMuaHRtbCNleGl0KSBhbmQgZG9uZSBtb3N0bHkgZm9yIHRoZSBjb25zb2xlLiBgc3lzLmV4aXQoKWAgaXMgZG9uZSBmb3IgcHJvZ3JhbSB3aXRoIGEgcHJvcGVyIHJldHVybiBhcmd1bWVudCAoW3NlZSBkb2N1bWVudGF0aW9uXShodHRwczovL2RvY3MucHl0aG9uLm9yZy8zL2xpYnJhcnkvc3lzLmh0bWwjc3lzLmV4aXQpKS4=","content":"ZnVuY3Rpb24gdmlzaXQobm9kZSkgewogIGlmKCghbm9kZS5tb2R1bGVPck9iamVjdCB8fCBub2RlLm1vZHVsZU9yT2JqZWN0LnZhbHVlICE9PSAic3lzIikgJiYgbm9kZS5mdW5jdGlvbk5hbWUudmFsdWUgPT09ICJleGl0Iil7CiAgICBjb25zdCBoYXNPbmVBcmd1bWVudCA9IG5vZGUuYXJndW1lbnRzLnZhbHVlcyAmJiBub2RlLmFyZ3VtZW50cy52YWx1ZXMubGVuZ3RoID09PSAxOwoKICAgIGNvbnN0IGVycm9yID0gYnVpbGRFcnJvcihub2RlLnN0YXJ0LmxpbmUsIG5vZGUuc3RhcnQuY29sLCBub2RlLmVuZC5saW5lLCBub2RlLmVuZC5jb2wsICJkbyBub3QgdXNlIGV4aXQoKSIsICJDUklUSUNBTCIsICJTQUZFVFkiKTsKCiAgICBjb25zdCBhcmd1bWVudFZhbHVlID0gbm9kZS5hcmd1bWVudHMudmFsdWVzWzBdLnZhbHVlLnN0cjsKICAgIGNvbnN0IG5ld0Z1bmN0aW9uQ2FsbCA9IGBzeXMuZXhpdCgke2FyZ3VtZW50VmFsdWV9KWA7CiAgICBjb25zdCBlZGl0UmVwbGFjZUZ1bmN0aW9uQ2FsbCA9IGJ1aWxkRWRpdFVwZGF0ZShub2RlLnN0YXJ0LmxpbmUsIG5vZGUuc3RhcnQuY29sLCBub2RlLmVuZC5saW5lLCBub2RlLmVuZC5jb2wsIG5ld0Z1bmN0aW9uQ2FsbCkKCiAgICBjb25zdCBlZGl0QWRkSW1wb3J0ID0gYnVpbGRFZGl0QWRkKDEsIDEsICJpbXBvcnQgc3lzXG4iKTsKCiAgICBjb25zdCBmaXggPSBidWlsZEZpeCgicmVwbGFjZSB3aXRoIHN5cy5leGl0KCkiLCBbZWRpdFJlcGxhY2VGdW5jdGlvbkNhbGwsIGVkaXRBZGRJbXBvcnRdKTsKICAgIGFkZEVycm9yKGVycm9yLmFkZEZpeChmaXgpKTsKICB9Cn0=","language":"python","type":"ast","entity_checked":"function_call"}
            """;
        ObjectMapper mapper = new ObjectMapper();
        var def = mapper.readTree(definition);
        var res = getRuleFromJson(def, "bla");
        assertTrue(res.isPresent());
        assertEquals("bla/no-exit", res.get().name());
        assertEquals(Language.PYTHON, res.get().language());
        assertEquals(EntityChecked.FUNCTION_CALL, res.get().entityChecked());
        assertNull(res.get().pattern());
    }
}
