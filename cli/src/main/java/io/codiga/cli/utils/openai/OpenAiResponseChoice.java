package io.codiga.cli.utils.openai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenAiResponseChoice {
    public OpenAiMessage message;
    public String finishReason;
    public int index;

}
