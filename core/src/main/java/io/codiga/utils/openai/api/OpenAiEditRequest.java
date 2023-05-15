package io.codiga.utils.openai.api;

import lombok.Builder;

@Builder
public class OpenAiEditRequest {
  public String model;
  public String input;
  public String instruction;
}
