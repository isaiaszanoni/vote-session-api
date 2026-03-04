package com.siccase.vote_session_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopicRequestDTO {
    @Schema(name = "title",
            description = "Título da pergunta",
            example = "Você gostou da nova versão do nosso App?")
    private String title;
}
