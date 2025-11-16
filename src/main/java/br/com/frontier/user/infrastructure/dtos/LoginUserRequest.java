package br.com.frontier.user.infrastructure.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 31/08/25
 */


@Builder
public record LoginUserRequest(
    @Schema(description = "Email da Usuario", example = "Jhonstore@mail.com")
    String email,

    @Schema(description = "Senha do usuario", example = "123456")
    String password
) {
}
