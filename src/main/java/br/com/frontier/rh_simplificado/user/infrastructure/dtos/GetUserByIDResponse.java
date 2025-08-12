package br.com.frontier.rh_simplificado.user.infrastructure.dtos;

import br.com.frontier.rh_simplificado.user.infrastructure.queries.GetUserByIdOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class GetUserByIDResponse {
    @JsonProperty(value = "id")
    private final UUID id;

    @JsonProperty(value = "name")
    @Schema(description = "Nome do usuario", example = "Jhon")
    private final String name;

    @JsonProperty(value = "email")
    @Schema(description = "Email do Usuario", example = "Jhonstore@mail.com")
    private final String email;

    public static GetUserByIDResponse from(final GetUserByIdOutput output) {
        return GetUserByIDResponse.builder()
            .id(output.getId().getValue())
            .email(output.getEmail())
            .name(output.getName())
            .build();
    }
}
