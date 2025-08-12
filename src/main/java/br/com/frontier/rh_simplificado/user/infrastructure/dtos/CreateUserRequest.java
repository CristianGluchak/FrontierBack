package br.com.frontier.rh_simplificado.user.infrastructure.dtos;

import br.com.frontier.rh_simplificado.user.application.create.CreateUserInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class CreateUserRequest {

    @JsonProperty(value = "name")
    @Schema(description = "Nome do usuario", example = "Jhon")
    private final String name;

    @JsonProperty(value = "email")
    @Schema(description = "Email da empresa", example = "Jhonstore@mail.com")
    private final String email;

    @JsonProperty(value = "password")
    @Schema(description = "Senha do usuario", example = "123456")
    private final String password;
}
