package io.codiga.cli.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.difflib.DiffUtils;
import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.patch.Patch;
import io.codiga.cli.model.OpenAiSuggestionMode;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.utils.openai.api.OpenAiChatRequest;
import io.codiga.cli.utils.openai.api.OpenAiEditRequest;
import io.codiga.cli.utils.openai.api.OpenAiMessage;
import io.codiga.cli.utils.openai.api.OpenAiResponse;
import io.codiga.cli.utils.openai.fixes.OpenAiFix;
import io.codiga.model.error.Edit;
import io.codiga.model.error.EditType;
import io.codiga.model.error.Fix;
import io.codiga.utils.EnvironmentUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class OpenAiUtils {
  public static final String OPENAI_HOST = "https://api.openai.com";
  public static final String API_PATH_CHAT = "v1/chat/completions";
    public static final String API_PATH_EDIT = "v1/edits";

  public static String getCode(String directory, String path) throws IOException {
    String res = null;
    try (FileInputStream fileInputStream =
        new FileInputStream(new File(Paths.get(directory, path).toUri()))) {
      res = new String(fileInputStream.readAllBytes());
    }
    return res;
  }

  public static OpenAiResponse requestChat(OpenAiChatRequest openAiChatRequest)
      throws JsonProcessingException, IOException, InterruptedException {
      long startTime = System.currentTimeMillis();
    Optional<String> openAiApiKeyOptional =
        EnvironmentUtils.getEnvironmentValue(EnvironmentUtils.OPENAI_API_KEY);
    if (openAiApiKeyOptional.isEmpty()) {
      return null;
    }

    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    String requestAsString = mapper.writeValueAsString(openAiChatRequest);

    var client = HttpClient.newHttpClient();

    String url = String.format("%s/%s", OPENAI_HOST, API_PATH_CHAT);
    var request =
        HttpRequest.newBuilder(URI.create(url))
            .header("Content-Type", "application/json")
            .header("Authorization", String.format("Bearer %s", openAiApiKeyOptional.get()))
            .POST(HttpRequest.BodyPublishers.ofString(requestAsString))
            .build();
    var response = client.send(request, HttpResponse.BodyHandlers.ofString());
    //      System.out.println(url);
    //      System.out.println(response.body());
      long endTime = System.currentTimeMillis();
      System.out.println(String.format("Request to openai took %s ms", endTime - startTime));
    return mapper.readValue(response.body(), OpenAiResponse.class);
  }

    public static OpenAiResponse requestEdit(OpenAiEditRequest openAiEditRequest)
        throws JsonProcessingException, IOException, InterruptedException {
        long startTime = System.currentTimeMillis();
        Optional<String> openAiApiKeyOptional =
            EnvironmentUtils.getEnvironmentValue(EnvironmentUtils.OPENAI_API_KEY);
        if (openAiApiKeyOptional.isEmpty()) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        String requestAsString = mapper.writeValueAsString(openAiEditRequest);

        var client = HttpClient.newHttpClient();

        String url = String.format("%s/%s", OPENAI_HOST, API_PATH_EDIT);
        var request =
            HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", openAiApiKeyOptional.get()))
                .POST(HttpRequest.BodyPublishers.ofString(requestAsString))
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //      System.out.println(url);
              System.out.println(response.body());
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Request to openai took %s ms", endTime - startTime));
        return mapper.readValue(response.body(), OpenAiResponse.class);
    }

    public static OpenAiFix getOpenAiFix(String code, ViolationWithFilename violation)
        throws IOException, InterruptedException {
        System.out.println("requesting a single fix");
        String question =
            String.format(
                """
    The following Python code has a problem on line %s.
    The problem is: %s.
    Please fix the code on line %s to fix the issue (issue: %s).
    
    ```
    %s
    ```
    
    Your answer should be a JSON object with the following key:
    1. description: what needs to be done to fix the error (100 words max) and escape quotes
    """, violation.start.line, violation.message, violation.start.line, violation.message, code);

        var message = OpenAiMessage.builder().role("user").content(question).build();
        var openAiRequest =
            OpenAiChatRequest.builder()
                .model("gpt-4-32k")
                .temperature(0.7f)
                .messages(List.of(message))
                .build();

        var openAiResponse = requestChat(openAiRequest);

        if (openAiResponse == null) {
            return null;
        }
        if (!openAiResponse.choices.isEmpty()) {
            var choice = openAiResponse.choices.get(0);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(choice.message.content);
            return OpenAiFix.builder().description(jsonNode.get("description").asText()).build();
        }

        return null;
    }


    public static OpenAiFix getOpenAiFixWithFullFile(String code, ViolationWithFilename violation)
        throws IOException, InterruptedException {
        System.out.println("requesting a single fix with full file");
        String instructions =
            String.format("Fix the code on line %s for the violation: %s", violation.start.line, violation.message);

        var systemMessage = OpenAiMessage.builder().role("system").content("You are an AI that fixes code").build();
        var openAiRequest = OpenAiEditRequest.builder().instruction(instructions).input(code).model("code-davinci-edit-001").build();


        var openAiResponse = requestEdit(openAiRequest);

        if (openAiResponse == null) {
            return null;
        }
        if (!openAiResponse.choices.isEmpty()) {
            var choice = openAiResponse.choices.get(0);

            return OpenAiFix.builder()
                .description("see full content")
                .file(Base64.getEncoder().encodeToString(choice.text.getBytes()))
                .build();
        }

        return null;
    }

    public static OpenAiFix getOpenAiFixWithDiff(String code, ViolationWithFilename violation)
        throws IOException, InterruptedException {
        System.out.println("requesting a single fix with diff");
        String instructions =
            String.format("The following violation was found on line %s: %s. Fix the code to avoid the violation %s on line %s",
                violation.start.line, violation.message,violation.message, violation.start.line);

        var openAiRequest = OpenAiEditRequest.builder().instruction(instructions).input(code).model("code-davinci-edit-001").build();


        var openAiResponse = requestEdit(openAiRequest);

        if (openAiResponse == null) {
            return null;
        }
        if (!openAiResponse.choices.isEmpty()) {
            var choice = openAiResponse.choices.get(0);
            var fixedCode = choice.text;

            var originalCodeList = Arrays.stream(code.split("\n")).toList();
            var fixedCodeList = Arrays.stream(fixedCode.split("\n")).toList();
            Patch<String> diff = DiffUtils.diff(originalCodeList, fixedCodeList);
        List<String> unifiedDiff =
          UnifiedDiffUtils.generateUnifiedDiff(
              String.format("%s.orig", violation.filename), violation.filename, originalCodeList, diff, 0);

            String patch = String.join("\n", unifiedDiff);
            String patchBase64 = Base64.getEncoder().encodeToString(patch.getBytes());
            return OpenAiFix.builder()
                .description("see patch")
                .diff(patchBase64)
                .build();
        }

        return null;
    }

    public static OpenAiFix getOpenAiFixWithDiffChat(String code, ViolationWithFilename violation)
        throws IOException, InterruptedException {
        System.out.println("requesting a single fix with full file");
        String question =
            String.format(
                """
    The following Python code has a problem on line %s.
    The problem on line %s is: %s.
        
    ```
    %s
    ```
    
    We want to get an explanation to fix the bug and a diff that fixes the code.
    
    Your answer should be a JSON object with the following key:
    1. description: a description of what needs to be done to fix the error (100 words max)
    2. diff: a unified diff we can apply to fix the code
    
    Make sure the JSON content is valid with escaped quotes.
    """, violation.start.line, violation.start.line, violation.message, violation.start.line, violation.message, code);

        var message = OpenAiMessage.builder().role("user").content(question).build();
        var openAiRequest =
            OpenAiChatRequest.builder()
                .model("gpt-4-32k")
                .temperature(0.7f)
                .messages(List.of(message))
                .build();

        var openAiResponse = requestChat(openAiRequest);

        if (openAiResponse == null) {
            return null;
        }
        if (!openAiResponse.choices.isEmpty()) {
            var choice = openAiResponse.choices.get(0);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(choice.message.content);
            return OpenAiFix.builder()
                .description(jsonNode.get("description").asText())
                .diff(jsonNode.get("diff").asText())
                .build();
        }

        return null;
    }

  /**
   * curl https://api.openai.com/v1/chat/completions \ -H "Content-Type: application/json" \ -H
   * "Authorization: Bearer $OPENAI_API_KEY" \ -d '{ "model": "gpt-3.5-turbo", "messages": [{"role":
   * "user", "content": "Say this is a test!"}], "temperature": 0.7 }'
   */
  public static ViolationWithFilename addFixes(
      ViolationWithFilename violation, String codeDirectory, OpenAiSuggestionMode genFixesMode)
      throws IOException, InterruptedException {

    String code = getCode(codeDirectory, violation.filename);
    OpenAiFix openAiFix = null;
    switch (genFixesMode){
        case PLAIN_ENGLISH -> {
            openAiFix = getOpenAiFix(code, violation);
            break;
        }
        case DIFF -> {
            openAiFix = getOpenAiFixWithDiff(code, violation);
            break;
        }
        case FIXED_FILE -> {
            openAiFix = getOpenAiFixWithFullFile(code, violation);
            break;
        }
    }
    if (openAiFix == null) {
        return violation;
    }

    if(openAiFix.description != null && openAiFix.diff == null && openAiFix.file == null) {
        Fix addedFix = new Fix(openAiFix.description, List.of());
        violation.fixes.add(addedFix);
        return violation;
    }

      if(openAiFix.description != null && openAiFix.diff == null && openAiFix.file != null) {
      Fix addedFix = new Fix(openAiFix.description, List.of(new Edit(null, null, EditType.FILE_REPLACEMENT, openAiFix.file)));
          violation.fixes.add(addedFix);
          return violation;
      }

      if(openAiFix.description != null && openAiFix.diff != null && openAiFix.file == null) {
          Fix addedFix = new Fix(openAiFix.description, List.of(new Edit(null, null, EditType.DIFF, openAiFix.diff)));
          violation.fixes.add(addedFix);
          return violation;
      }

    return violation;
  }

  /**
   * generate list of missing fixes for OpenAI. If a violation does not have a fix, we will add it.
   *
   * @param originalList - the original list of violation with fix.
   * @param directory - the directory that contains the code
   * @return a list of violations with more fixes
   */
  public static List<ViolationWithFilename> generateMissingFixes(
      List<ViolationWithFilename> originalList, String directory, OpenAiSuggestionMode genFixesMode) {

    return originalList.stream()
        .map(
            violation -> {
              if (violation.fixes.isEmpty()) {
                try {
                  return addFixes(violation, directory, genFixesMode);
                } catch (IOException | InterruptedException ioException) {
                  ioException.printStackTrace();
                  return violation;
                }
              } else {
                return violation;
              }
            })
        .toList();
  }
}
