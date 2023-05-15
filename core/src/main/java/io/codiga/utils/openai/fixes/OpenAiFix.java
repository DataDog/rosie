package io.codiga.utils.openai.fixes;

import lombok.Builder;

@Builder
public class OpenAiFix {
    public String description;
    public String file;
    public String diff;
}
