package io.codiga.cli.utils.openai;

import java.util.List;
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
public class OpenAiResponse {
    public String id;
    public List<OpenAiResponseChoice> choices;
}
