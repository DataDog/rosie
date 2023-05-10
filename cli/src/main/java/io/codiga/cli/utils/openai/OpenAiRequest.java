package io.codiga.cli.utils.openai;

import java.util.List;
import lombok.Builder;

@Builder
public class OpenAiRequest {
    public String model;
    public List<OpenAiMessage> messages;
    public float temperature;

}
