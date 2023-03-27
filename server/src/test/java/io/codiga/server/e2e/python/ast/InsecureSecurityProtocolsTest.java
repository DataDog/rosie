package io.codiga.server.e2e.python.ast;

import io.codiga.model.Language;
import io.codiga.server.e2e.E2EBase;
import io.codiga.server.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.codiga.constants.Languages.ENTITY_CHECKED_FUNCTION_CALL;
import static io.codiga.constants.Languages.RULE_TYPE_AST;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class InsecureSecurityProtocolsTest extends E2EBase {

    String pythonCodeWithError = """
        import ssl
                
        def newconnect(self):
                try:
                    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                    remote = ssl.wrap_socket(s,
                                             ca_certs= CA,
                                             cert_reqs=ssl.CERT_REQUIRED,
                                             ssl_version = ssl.PROTOCOL_SSLv3)
                    remote.connect(self.server.seradd)
                    if not self.server.seradd[0] == remote.getpeercert()['subjectAltName'][0][1]:
                        logging.error('Server crt error !! Server Name don\\'t mach !!')
                        logging.error(remote.getpeercert()['subjectAltName'][0][1])
                        return
                    if not self.send_PW(remote):
                        logging.warn('PW error !')
                        return
                except socket.error, e:
                    logging.warn(e)
                    return""";


    String ruleCode = """
        function checkProtocol(node, protocol) {
            if (!(node.functionName.value === "wrap_socket" && node.moduleOrObject.value === "ssl")) {
                return;
            }

            const useOutdatedProtocol = node.arguments.values.filter(a => a.value && a.value.str == `ssl.${protocol}`).length > 0;
            
            const allPackages = node.context.imports.filter(i => i.packages).flatMap(i => i.packages.map(p => p.name.str));
            const useSslPackage = allPackages.filter(i => i === "ssl").length > 0;

            if(useOutdatedProtocol && useSslPackage){
                const error = buildError(node.start.line, node.start.col, node.end.line, node.end.col, `Use of insecure protocol ${protocol}`, "CRITICAL", "SECURITY");
                addError(error);
            } 
        }
                
        function visit(node) {
            const protocols = ["PROTOCOL_SSLv3", "PROTOCOL_SSLv2", "SSLv2_METHOD", "SSLv23_METHOD", "PROTOCOL_TLSv1", "SSLv3_METHOD", "TLSv1_METHOD"];
            protocols.forEach(protocol => {
                checkProtocol(node, protocol);
            });
            
        }
        """;

    @Test
    @DisplayName("Detect insecure protocol for wrap_socket")
    public void testInsecureProtocol() throws Exception {
        Response response = executeTestWithTreeSitter("bla.py", pythonCodeWithError, Language.PYTHON, ruleCode, "insecure-protocol-wrap-socket", RULE_TYPE_AST, ENTITY_CHECKED_FUNCTION_CALL, null, true);
        logger.info("response:" + response);
        assertEquals(1, response.ruleResponses.size());
        assertEquals(1, response.ruleResponses.get(0).violations.size());
        assertEquals(6, response.ruleResponses.get(0).violations.get(0).start.line);
        assertEquals(22, response.ruleResponses.get(0).violations.get(0).start.col);

        assertEquals(9, response.ruleResponses.get(0).violations.get(0).end.line);
        assertEquals(71, response.ruleResponses.get(0).violations.get(0).end.col);
    }
}
