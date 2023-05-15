package io.codiga.utils.openai.api;

import java.util.List;
import lombok.Builder;

@Builder
public class OpenAiChatRequest {
  public String model;
  public Integer max_tokens;
  public List<OpenAiMessage> messages;
  public float temperature;
}
