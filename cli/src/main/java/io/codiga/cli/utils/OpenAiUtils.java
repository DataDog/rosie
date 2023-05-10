package io.codiga.cli.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codiga.cli.model.ViolationWithFilename;
import io.codiga.cli.utils.openai.OpenAiMessage;
import io.codiga.cli.utils.openai.OpenAiRequest;
import io.codiga.cli.utils.openai.OpenAiResponse;
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
import java.util.List;
import java.util.Optional;

public class OpenAiUtils {
  public static final String OPENAI_HOST = "https://api.openai.com";
  public static final String API_PATH = "v1/chat/completions";



  public static String getCode(String directory, String path) throws IOException {
      String res = null;
      try( FileInputStream fileInputStream = new FileInputStream(new File(Paths.get(directory, path).toUri()))) {
         res = new String(fileInputStream.readAllBytes());
      }
      return res;
  }

  public static String getOpenAiFix(String code, ViolationWithFilename violation) throws IOException, InterruptedException {
      Optional<String> openAiApiKeyOptional =
          EnvironmentUtils.getEnvironmentValue(EnvironmentUtils.OPENAI_API_KEY);
      if (openAiApiKeyOptional.isEmpty()) {
          return null;
      }

      String question = String.format("""
The following Python code has a problem on line %s.
The problem is: %s.
Please fix the code on line %s to fix the issue (issue: %s).

```
%s
```

Your answer should be a JSON object with the following key:
1. description: what needs to be done to fix the error (100 words max)
""", violation.start.line, violation.message, violation.start.line, violation.message, code);

      var message = OpenAiMessage
          .builder()
          .role("user")
          .content(question)
          .build();
      var openAiRequest = OpenAiRequest
          .builder()
          .model("gpt-4-32k")
          .temperature(0.7f)
          .messages(List.of(message));

      ObjectMapper mapper = new ObjectMapper();
      mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
      String requestAsString = null;
      try {
          requestAsString = mapper.writeValueAsString(openAiRequest);
      } catch (JsonProcessingException e) {
          e.printStackTrace();
          System.err.println("JSON serialization error");
          return null;
      }

      if (requestAsString == null) {
          return null;
      }

      System.out.println(requestAsString);

      var client = HttpClient.newHttpClient();

String url = String.format("%s/%s", OPENAI_HOST, API_PATH);
      var request = HttpRequest.newBuilder(
              URI.create(url))
          .header("Content-Type", "application/json")
          .header("Authorization", String.format("Bearer %s", openAiApiKeyOptional.get()))
          .POST(HttpRequest.BodyPublishers.ofString(requestAsString))
          .build();
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println(url);
    System.out.println(response.body());


    OpenAiResponse openAiResponse = mapper.readValue(response.body(), OpenAiResponse.class);
    if (! openAiResponse.choices.isEmpty()) {
        var choice = openAiResponse.choices.get(0);
        System.out.println(choice.message);

        JsonNode jsonNode = mapper.readTree(choice.message.content);
        return jsonNode.get("description").asText();
    }

      System.out.println(response.body());
    return null;
  }

  /**
   * curl https://api.openai.com/v1/chat/completions \ -H "Content-Type: application/json" \ -H
   * "Authorization: Bearer $OPENAI_API_KEY" \ -d '{ "model": "gpt-3.5-turbo", "messages": [{"role":
   * "user", "content": "Say this is a test!"}], "temperature": 0.7 }'
   */
  public static ViolationWithFilename addFixes(
      ViolationWithFilename violation, String codeDirectory) throws IOException, InterruptedException{

      String code = getCode(codeDirectory, violation.filename);
      String fix = getOpenAiFix(code, violation);
      if (fix == null) {
          return violation;
      } else {
          Fix addedFix = new Fix(fix, List.of());
          violation.fixes.add(addedFix);
          return violation;
      }
  }

  /**
   * generate list of missing fixes for OpenAI. If a violation does not have a fix, we will add it.
   *
   * @param originalList - the original list of violation with fix.
   * @param directory - the directory that contains the code
   * @return a list of violations with more fixes
   */
  public static List<ViolationWithFilename> generateMissingFixes(
      List<ViolationWithFilename> originalList, String directory) {

    return originalList.stream()
        .map(
            violation -> {
              if (violation.fixes.isEmpty()) {
                  try{
                      return addFixes(violation, directory);
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
