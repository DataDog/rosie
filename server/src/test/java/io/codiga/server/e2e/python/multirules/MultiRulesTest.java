package io.codiga.server.e2e.python.multirules;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.request.Rule;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.codiga.server.e2e.python.multirules.Rules.*;
import static org.junit.jupiter.api.Assertions.*;


public class MultiRulesTest extends E2EBase {

    List<Rule> RULES = List.of(
        RULE_INSECURE_HASH_FUNCTIONS,
        RULE_NO_EVAL,
        RULE_MKTEMP,
        RULE_REQUEST_TIMEOUT,
        RULE_SUBPROCESS_SHELL_TRUE,
        RULE_AVOID_RANDOM,
        RULE_NO_EMPTY_ARRAY_AS_PARAMETER,
        RULE_FILE_WRITE_OTHERS,
        RULE_JINJA2_AUTOESCAPE,
        RULE_DESERIALIZA_UNTRUSTED_DATA,
        RULE_INSECURE_SSL_PROTOCOLS
    );

    String code = """
        import stat
        import hashlib
                
        import tempfile
        import requests
                
        import subprocess
        import random
                
        n = random.random()
                
                
        subprocess.Popen('/bin/ls %s' % ('something',), shell=True)
                
                
        r = requests.get(w, verify=False)
        r = requests.get(w, verify=False, timeout=10)
                
                
        tempfile.mktemp(dir=self._tmp_dir)
                
        hashlib.new('md5')
                        
        eval('[1, 2, 3]')
                
        def newFunction(arg1, arg2: int, arg3 = []):
          print("bla")
                
        path = "/path/to/file"
        os.chmod(path, stat.S_IROTH | stat.S_IWOTH | stat.S_IXOTH)
        """;

    @Test
    public void testPythonSubprocessWithShell() throws Exception {
        Response response = executeTestWithRules("bla.py", code, Language.PYTHON, RULES, false);

        assertEquals(11, response.ruleResponses.size());
        response.ruleResponses.forEach(ruleResponse -> {
            logger.info(ruleResponse.identifier);
//            logger.info(ruleResponse.executionError);
//            logger.info(String.join(",", ruleResponse.errors));
            assertNull(ruleResponse.executionError);

            assertTrue(ruleResponse.errors.isEmpty());
        });

    }

}
