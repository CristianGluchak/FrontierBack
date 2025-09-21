package br.com.frontier.rh_simplificado.user.infrastructure.dtos;

import br.com.frontier.rh_simplificado.user.application.update.UpdateUserInput;
import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
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
public class UpdateUserRequest {

    @JsonProperty(value = "name")
    @Schema(description = "Nome do usuario", example = "Jhon")
    private final String name;

    @JsonProperty(value = "email")
    @Schema(description = "Email da Usuario", example = "Jhonstore@mail.com")
    private final String email;

    @JsonProperty(value = "password")
    @Schema(description = "Senha do usuario", example = "123456")
    private final String password;

    public static UpdateUserInput from(UpdateUserRequest request, UUID id) {
        return UpdateUserInput.builder()
            .id(UserID.from(id))
            .name(request.getName())
            .email(request.getEmail())
            .password(request.getPassword())
            .build();
    }

}
