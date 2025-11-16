package br.com.frontier.user.infrastructure.dtos;

import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.user.infrastructure.queries.findAll.FindAllUserOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record FindAllUserResponse(
    @JsonProperty(value = "id")
    @Schema(description = "ID do usuario", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    UUID id,

    @JsonProperty(value = "name")
    @Schema(description = "Nome do usuario", example = "Jhon")
    String name,

    @JsonProperty(value = "status")
    @Schema(description = "Status do usuario", example = "ATIVO")
    AtivoInativo status
) {

    public static FindAllUserResponse from(FindAllUserOutput e) {
        return FindAllUserResponse.builder()
            .id(e.id().getValue())
            .name(e.name())
            .status(e.status())
            .build();
    }
}