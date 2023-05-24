package io.codiga.server.e2e;

import static io.codiga.utils.Base64Utils.encodeBase64;

import io.codiga.model.EntityChecked;
import io.codiga.model.Language;
import io.codiga.model.RuleType;
import io.codiga.server.ServerMainController;
import io.codiga.server.configuration.ServerTestConfiguration;
import io.codiga.server.request.*;
import io.codiga.server.response.GetTreeSitterAstResponse;
import io.codiga.server.response.Response;
import io.codiga.server.response.ViolationFix;
import io.codiga.server.response.ViolationFixEdit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 * Base class for all tests.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ServerTestConfiguration.class})
@TestPropertySource(locations = "classpath:test.properties")
@ContextConfiguration
public class E2EBase {
    public final static List<Language> JAVASCRIPT_TYPESCRIPT = List.of(Language.JAVASCRIPT, Language.TYPESCRIPT);
    public final static List<Language> TYPESCRIPT_ONLY = List.of(Language.TYPESCRIPT);
    public final static List<Language> JAVASCRIPT_ONLY = List.of(Language.JAVASCRIPT);


    protected final Logger logger = LoggerFactory.getLogger(E2EBase.class);
    @Autowired
    private ServerMainController controller;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;


    public Response executeTest(String filename,
                                String code,
                                Language language,
                                String ruleCode,
                                String ruleName,
                                RuleType ruleType,
                                EntityChecked entityChecked,
                                String regex,
                                Map<String, String> variables,
                                boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, false);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(language)
                .setFileEncoding("utf-8")
                .setCode(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(
                        List.of(
                                new RuleBuilder()
                                        .setId(ruleName)
                                        .setCode(encodeBase64(ruleCode))
                                        .setLanguage(language)
                                        .setType(ruleType)
                                        .setEntityChecked(entityChecked)
                                        .setRegex(regex)
                                        .setVariables(variables)
                                        .createRule()
                        )
                ).createRequest();
        Response response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/analyze", request,
                Response.class);
        return response;
    }

    public Response executeTestWithTreeSitter(String filename,
                                              String code,
                                              Language language,
                                              String ruleCode,
                                              String ruleName,
                                              RuleType ruleType,
                                              EntityChecked entityChecked,
                                              String regex,
                                              String treeSitterQuery,
                                              Map<String, String> variables,
                                              boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, true);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(language)
                .setFileEncoding("utf-8")
                .setCode(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(
                        List.of(
                                new RuleBuilder()
                                        .setId(ruleName)
                                        .setCode(encodeBase64(ruleCode))
                                        .setLanguage(language)
                                        .setType(ruleType)
                                        .setEntityChecked(entityChecked)
                                        .setRegex(regex)
                                        .setTreeSitterQuery(treeSitterQuery)
                                        .setVariables(variables)
                                        .createRule()
                        )
                ).createRequest();
////            String r = """
////                        { "filename": "bla.js", "code": "Y29uc3QgdmFsaWQgPSBmYWxzZTsKICAgICAgICBjb25zdCBpbnZhbGlkID0gdHJ1ZTsKICAgICAgICAgICAgICAgIAogICAgICAgIGNvbnN0IHZhbHVlID0gZmFsc2U7CiAgICAgICAgY29uc3Qgbm9WYWx1ZSA9IHRydWU7CiAgICAgICAgICAgICAgICAKICAgICAgICBjb25zdCBlZGl0ID0gZmFsc2U7CiAgICAgICAgY29uc3Qgbm90RWRpdCA9IHRydWU7", "language": "JAVASCRIPT", "file_encoding": "utf-8", "rules": [ { "id": "boolean-naming", "code": "LyoqCiAgICAgICAgICogd2hhdCdzIHRoZSBtaW5pbXVtIGxlbmd0aCBvZiB2YXJpYWJsZSBuYW1lcyB0aGF0IHRoaXMgc2hvdWxkIHJ1biBvbgogICAgICAgICAqLwogICAgICAgIGNvbnN0IE1JTklNVU1fTEVOR1RIID0gNDsKICAgICAgICAgICAgICAgIAogICAgICAgIC8qKgogICAgICAgICAqIHdoYXQgYSBib29sZWFuIGFzc2lnbm1lbnQgc2hvdWxkIHN0YXJ0IHdpdGgKICAgICAgICAgKi8KICAgICAgICBjb25zdCBCT09MRUFOX0FTU0lHTk1FTlRfU1RBUlRFUlMgPSBbImlzIiwgImhhcyIsICJjYW4iXTsKICAgICAgICAgICAgICAgIAogICAgICAgIC8qKgogICAgICAgICAqIGNoZWNrIHdoZXRoZXIgdGhlIGxlZnQtc2lkZSBvZiB0aGUgYXNzaWdubWVudAogICAgICAgICAqIGhhcyBhIGJvb2xlYW4gdHlwZSBuYW1pbmcKICAgICAgICAgKi8KICAgICAgICBmdW5jdGlvbiBjaGVja0xlZnRTaWRlQm9vbGVhbk5hbWluZyh2YWx1ZSkgewogICAgICAgICAgbGV0IGlzQm9vbGVhbk5hbWluZyA9IGZhbHNlOwogICAgICAgICAgZm9yIChjb25zdCBib29sZWFuQXNzaWdubWVudFN0YXJ0ZXIgb2YgQk9PTEVBTl9BU1NJR05NRU5UX1NUQVJURVJTKSB7CiAgICAgICAgICAgIGlmICh2YWx1ZS5zdGFydHNXaXRoKGJvb2xlYW5Bc3NpZ25tZW50U3RhcnRlcikpIHsKICAgICAgICAgICAgICBpc0Jvb2xlYW5OYW1pbmcgPSB0cnVlOwogICAgICAgICAgICAgIGJyZWFrOwogICAgICAgICAgICB9CiAgICAgICAgICB9CiAgICAgICAgICByZXR1cm4gaXNCb29sZWFuTmFtaW5nOwogICAgICAgIH0KICAgICAgICAgICAgICAgIAogICAgICAgIC8qKgogICAgICAgICAqIGNoZWNrIHdoZXRoZXIgdGhlIHJpZ2h0LXNpZGUgb2YgdGhlIGFzc2lnbm1lbnQgaXMgYSBib29sZWFuCiAgICAgICAgICovCiAgICAgICAgZnVuY3Rpb24gY2hlY2tSaWdodFNpZGVGb3JCb29sZWFuKHZhbHVlKSB7CiAgICAgICAgICBpZiAodmFsdWUgPT09ICJ0cnVlIiB8fCB2YWx1ZSA9PT0gImZhbHNlIikgewogICAgICAgICAgICByZXR1cm4gdHJ1ZTsKICAgICAgICAgIH0gZWxzZSB7CiAgICAgICAgICAgIHJldHVybiBmYWxzZTsKICAgICAgICAgIH0KICAgICAgICB9CiAgICAgICAgICAgICAgICAKICAgICAgICAvKioKICAgICAgICAgKiBjaGVjayB3aGV0aGVyIHRoZSByaWdodC1zaWRlIG9mIHRoZSBhc3NpZ25tZW50IGlzIGEgZnVuY3Rpb25jYWxsCiAgICAgICAgICovCiAgICAgICAgZnVuY3Rpb24gY2hlY2tSaWdodFNpZGVGb3JGdW5jdGlvbihhc3RUeXBlKSB7CiAgICAgICAgICBpZiAoYXN0VHlwZSA9PT0gImZ1bmN0aW9uY2FsbCIpIHsKICAgICAgICAgICAgcmV0dXJuIHRydWU7CiAgICAgICAgICB9IGVsc2UgewogICAgICAgICAgICByZXR1cm4gZmFsc2U7CiAgICAgICAgICB9CiAgICAgICAgfQogICAgICAgICAgICAgICAgCiAgICAgICAgLyoqCiAgICAgICAgICogY29uY2F0ZW5hdGVzIHRoZSBzdGFydGVyIGFuZCB2YWx1ZSAocGFzY2FsQ2FzZSkKICAgICAgICAgKi8KICAgICAgICBmdW5jdGlvbiBlZGl0VmFsdWUodmFsdWUsIHN0YXJ0ZXIpIHsKICAgICAgICAgIHJldHVybiBgJHtzdGFydGVyfSR7dmFsdWUuY2hhckF0KDApLnRvVXBwZXJDYXNlKCkgKyB2YWx1ZS5zdWJzdHJpbmcoMSl9YDsKICAgICAgICB9CiAgICAgICAgICAgICAgICAKICAgICAgICAvKioKICAgICAgICAgKiBoYW5kbGVzIGFsbCB0aGUgbG9naWMgd2hlbiBDb2RpZ2EgaGl0cyBhbiBhc3NpZ25tZW50IGluIGZpbGUncyBBU1QKICAgICAgICAgKi8KICAgICAgICBmdW5jdGlvbiB2aXNpdChub2RlLCBmaWxlbmFtZSwgY29kZSkgewogICAgICAgICAgaWYgKCFub2RlIHx8ICFub2RlLmxlZnQgfHwgIW5vZGUucmlnaHQpIHJldHVybjsKICAgICAgICAgICAgICAgIAogICAgICAgICAgY29uc3QgaXNMZWZ0U2lkZUJvb2xlYW5OYW1pbmcgPSBjaGVja0xlZnRTaWRlQm9vbGVhbk5hbWluZyhub2RlLmxlZnQudmFsdWUpOwogICAgICAgICAgY29uc3QgaXNSaWdodFNpZGVBQm9vbGVhbiA9IGNoZWNrUmlnaHRTaWRlRm9yQm9vbGVhbihub2RlLnJpZ2h0LnZhbHVlKTsKICAgICAgICAgIGNvbnN0IGlzUmlnaHRTaWRlQUZ1bmN0aW9uID0gY2hlY2tSaWdodFNpZGVGb3JGdW5jdGlvbihub2RlLnJpZ2h0LmFzdFR5cGUpOwogICAgICAgICAgICAgICAgCiAgICAgICAgICBpZiAobm9kZS5sZWZ0LnZhbHVlLmxlbmd0aCA8IE1JTklNVU1fTEVOR1RIKSByZXR1cm47CiAgICAgICAgICAgICAgICAKICAgICAgICAgIC8qKgogICAgICAgICAgICogaWYgdGhlIGFzc2lnbm1lbnQgaGFzIGJvb2xlYW4gdHlwZSBuYW1pbmcgYW5kIHRoZSB2YWx1ZQogICAgICAgICAgICogaXNuJ3QgYSBib29sZWFuIHNob3cgYSB3YXJuaW5nIHRoYXQgaXQncyBub3Qgc3RhbmRhcmQKICAgICAgICAgICAqLwogICAgICAgICAgaWYgKAogICAgICAgICAgICBpc0xlZnRTaWRlQm9vbGVhbk5hbWluZyAmJgogICAgICAgICAgICAhaXNSaWdodFNpZGVBQm9vbGVhbiAmJgogICAgICAgICAgICAhaXNSaWdodFNpZGVBRnVuY3Rpb24KICAgICAgICAgICkgewogICAgICAgICAgICBjb25zdCBlcnJvciA9IGJ1aWxkRXJyb3IoCiAgICAgICAgICAgICAgbm9kZS5sZWZ0LnN0YXJ0LmxpbmUsCiAgICAgICAgICAgICAgbm9kZS5sZWZ0LnN0YXJ0LmNvbCwKICAgICAgICAgICAgICBub2RlLmxlZnQuZW5kLmxpbmUsCiAgICAgICAgICAgICAgbm9kZS5sZWZ0LmVuZC5jb2wsCiAgICAgICAgICAgICAgIllvdXIgbmFtaW5nIGluZGljYXRlcyB0aGlzIHZhcmlhYmxlIGlzIGEgYm9vbGVhbi4iLAogICAgICAgICAgICAgICJXQVJOSU5HIiwKICAgICAgICAgICAgICAiQkVTVF9QUkFDVElDRVMiCiAgICAgICAgICAgICk7CiAgICAgICAgICAgIGFkZEVycm9yKGVycm9yKTsKICAgICAgICAgIH0KICAgICAgICAgICAgICAgIAogICAgICAgICAgLyoqCiAgICAgICAgICAgKiBpZiB0aGUgYXNzaWdubWVudCBkb2Vzbid0IGhhdmUgYm9vbGVhbiB0eXBlIG5hbWluZywgYnV0IHRoZQogICAgICAgICAgICogdmFsdWUgaXMgYSBib29sZWFuLCBzdWdnZXN0IGZpeGVzIGZvciB0aGUgYXNzaWdubWVudC4KICAgICAgICAgICAqLwogICAgICAgICAgaWYgKCFpc0xlZnRTaWRlQm9vbGVhbk5hbWluZyAmJiBpc1JpZ2h0U2lkZUFCb29sZWFuKSB7CiAgICAgICAgICAgIGNvbnN0IGVycm9yID0gYnVpbGRFcnJvcigKICAgICAgICAgICAgICBub2RlLmxlZnQuc3RhcnQubGluZSwKICAgICAgICAgICAgICBub2RlLmxlZnQuc3RhcnQuY29sLAogICAgICAgICAgICAgIG5vZGUubGVmdC5lbmQubGluZSwKICAgICAgICAgICAgICBub2RlLmxlZnQuZW5kLmNvbCwKICAgICAgICAgICAgICAiWW91ciB2YXJpYWJsZSBuYW1pbmcgc2hvdWxkIGluZGljYXRlIGl0J3MgYSBib29sZWFuLiIsCiAgICAgICAgICAgICAgIldBUk5JTkciLAogICAgICAgICAgICAgICJCRVNUX1BSQUNUSUNFUyIKICAgICAgICAgICAgKTsKICAgICAgICAgICAgICAgIAogICAgICAgICAgICBjb25zdCBvbGRWYWx1ZSA9IG5vZGUubGVmdC52YWx1ZTsKICAgICAgICAgICAgICAgIAogICAgICAgICAgICBjb25zdCBlZGl0cyA9IEJPT0xFQU5fQVNTSUdOTUVOVF9TVEFSVEVSUy5tYXAoKHN0YXJ0ZXIpID0+IHsKICAgICAgICAgICAgICBjb25zdCBuZXdWYWx1ZSA9IGVkaXRWYWx1ZShvbGRWYWx1ZSwgc3RhcnRlcik7CiAgICAgICAgICAgICAgcmV0dXJuIFsKICAgICAgICAgICAgICAgIGJ1aWxkRWRpdFVwZGF0ZSgKICAgICAgICAgICAgICAgICAgbm9kZS5sZWZ0LnN0YXJ0LmxpbmUsCiAgICAgICAgICAgICAgICAgIG5vZGUubGVmdC5zdGFydC5jb2wsCiAgICAgICAgICAgICAgICAgIG5vZGUubGVmdC5lbmQubGluZSwKICAgICAgICAgICAgICAgICAgbm9kZS5sZWZ0LmVuZC5jb2wsCiAgICAgICAgICAgICAgICAgIGVkaXRWYWx1ZShvbGRWYWx1ZSwgc3RhcnRlcikKICAgICAgICAgICAgICAgICksCiAgICAgICAgICAgICAgXTsKICAgICAgICAgICAgfSk7CiAgICAgICAgICAgICAgICAKICAgICAgICAgICAgY29uc3QgaXNGaXggPSBidWlsZEZpeChgbmFtaW5nIHdpdGg6IGlzYCwgZWRpdHNbMF0pOwogICAgICAgICAgICBjb25zdCBoYXNGaXggPSBidWlsZEZpeChgbmFtaW5nIHdpdGg6IGhhc2AsIGVkaXRzWzFdKTsKICAgICAgICAgICAgY29uc3QgY2FuRml4ID0gYnVpbGRGaXgoYG5hbWluZyB3aXRoOiBjYW5gLCBlZGl0c1syXSk7CiAgICAgICAgICAgICAgICAKICAgICAgICAgICAgYWRkRXJyb3IoZXJyb3IuYWRkRml4KGlzRml4KS5hZGRGaXgoaGFzRml4KS5hZGRGaXgoY2FuRml4KSk7CiAgICAgICAgICB9CiAgICAgICAgfQ==", "language": "JAVASCRIPT", "regex": null, "type": "AST_CHECK", "entity_checked": "ASSIGNMENT" } ], "options": { "log_output": true, "use_tree_sitter": true } }
////                       """;
////            ObjectMapper mapper =  new ObjectMapper();
//        ObjectMapper mapper = new ObjectMapper()
//            // converts Java variables to snake_case JSON; and vice-versa
//            .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy())
//            // converts unknown values to a default enum value (i.e. UNKNOWN)
//            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
//            var r = "";
//        try {
//                r = mapper.writeValueAsString(request);
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
            Response response = restTemplate.postForObject("http://localhost:" + port +
                "/analyze", request,
            Response.class);
            return response;
    }

    public Response executeTestWithTreeSitter(String filename,
                                              String code,
                                              Language language,
                                              String ruleCode,
                                              String ruleName,
                                              RuleType ruleType,
                                              EntityChecked entityChecked,
                                              String regex,
                                              Map<String, String> variables,
                                              boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, ruleType, entityChecked, regex, null, variables, logOutput);
    }

    public Response executeTestWithTreeSitter(String filename,
                                              String code,
                                              Language language,
                                              String ruleCode,
                                              String ruleName,
                                              RuleType ruleType,
                                              EntityChecked entityChecked,
                                              String regex,
                                              boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, ruleType, entityChecked, regex, null, null, logOutput);
    }

    public Response executeTest(String filename,
                                String code,
                                Language language,
                                String ruleCode,
                                String ruleName,
                                RuleType ruleType,
                                EntityChecked entityChecked,
                                String regex,
                                boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, ruleType, entityChecked, regex, null, null, logOutput);
    }

    public Response executeTestTsQuery(String filename,
                                       String code,
                                       Language language,
                                       String ruleCode,
                                       String ruleName,
                                       String treeSitterQuery,
                                       boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, RuleType.TREE_SITTER_QUERY,
                null, null, encodeBase64(treeSitterQuery), null, logOutput);
    }

    public Response executeTestTsQuery(String filename,
                                       String code,
                                       Language language,
                                       String ruleCode,
                                       String ruleName,
                                       String treeSitterQuery,
                                       Map<String, String> variables,
                                       boolean logOutput) {
        return executeTestWithTreeSitter(filename, code, language, ruleCode, ruleName, RuleType.TREE_SITTER_QUERY,
                null, null, encodeBase64(treeSitterQuery), variables, logOutput);
    }

    public Response executeTest(String filename,
                                String code,
                                Language codeLanguage,
                                Language ruleLanguage,
                                String ruleCode,
                                String ruleName,
                                RuleType ruleType,
                                EntityChecked entityChecked,
                                String regex,
                                Map<String, String> variables,
                                boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, false);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(codeLanguage)
                .setFileEncoding("utf-8")
                .setCode(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(
                        List.of(
                                new RuleBuilder()
                                        .setId(ruleName)
                                        .setCode(encodeBase64(ruleCode))
                                        .setLanguage(ruleLanguage)
                                        .setType(ruleType)
                                        .setEntityChecked(entityChecked)
                                        .setRegex(regex)
                                        .setVariables(variables)
                                        .createRule()
                        )
                ).createRequest();
        Response response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/analyze", request,
                Response.class);
        return response;
    }

    public Response executeTest(String filename,
                                String code,
                                Language codeLanguage,
                                Language ruleLanguage,
                                String ruleCode,
                                String ruleName,
                                RuleType ruleType,
                                EntityChecked entityChecked,
                                String regex,
                                boolean logOutput) {
        return executeTest(filename, code, codeLanguage, ruleLanguage, ruleCode, ruleName, ruleType, entityChecked, regex, null, logOutput);
    }


    public Response executeTestWithRules(String filename,
                                         String code,
                                         Language language,
                                         List<Rule> rules,
                                         boolean logOutput) {
        RequestOptions requestOptions = new RequestOptions(logOutput, false);
        Request request = new RequestBuilder()
                .setFilename(filename)
                .setLanguage(language)
                .setFileEncoding("utf-8")
                .setCode(encodeBase64(code))
                .setOptions(requestOptions)
                .setRules(rules)
                .createRequest();
        Response response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/analyze", request,
                Response.class);
        return response;
    }

    public GetTreeSitterAstResponse executeTreesitterAstTest(String decodedCode,
                                             Language language,
                                             String fileEncoding) {
        GetTreeSitterAstRequest request = GetTreeSitterAstRequest
                .builder()
                .language(language)
                .fileEncoding(fileEncoding)
                .code(encodeBase64(decodedCode))
                .build();

        GetTreeSitterAstResponse response = this.restTemplate.postForObject(
                "http://localhost:" + port + "/get-treesitter-ast", request,
                GetTreeSitterAstResponse.class);
        return response;
    }

    protected String applyFix(String originalCode, ViolationFix violationFix) {
        logger.info("====== START OF ORIGINAL CODE =======");
        logger.info(originalCode);
        logger.info("======= END OF ORIGINAL CODE ========");
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(originalCode);
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }

        for (ViolationFixEdit violationFixEdit : violationFix.edits) {
            if (violationFixEdit.editType.equalsIgnoreCase("update")) {

                if (violationFixEdit.start.line == violationFixEdit.end.line) {
                    int lineIndex = violationFixEdit.start.line - 1;
                    String line = lines.get(lineIndex);
                    logger.info(String.format("updating line %s:%s-%s:%s -> %s", violationFixEdit.start.line, violationFixEdit.start.col, violationFixEdit.end.line, violationFixEdit.end.col, line));
                    line = line.substring(0, violationFixEdit.start.col - 1) + violationFixEdit.content + line.substring(violationFixEdit.end.col - 1);
                    lines.set(lineIndex, line);
                }
            }
            if (violationFixEdit.editType.equalsIgnoreCase("add")) {
                int lineIndex = violationFixEdit.start.line - 1;
                String line = lines.get(lineIndex);
                line = line.substring(0, violationFixEdit.start.col - 1) + violationFixEdit.content + line.substring(violationFixEdit.start.col - 1);
                lines.set(lineIndex, line);
            }
            if (violationFixEdit.editType.equalsIgnoreCase("remove")) {
                int lineIndex = violationFixEdit.start.line - 1;
                String line = lines.get(lineIndex);
                line = line.substring(0, violationFixEdit.start.col - 1) + line.substring(violationFixEdit.end.col - 1);
                lines.set(lineIndex, line);
            }
        }
        String newCode = String.join("\n", lines);
        logger.info("====== START OF UPDATED CODE =======");
        logger.info(newCode);
        logger.info("======= END OF UPDATED CODE ========");
        return newCode;
    }
}
