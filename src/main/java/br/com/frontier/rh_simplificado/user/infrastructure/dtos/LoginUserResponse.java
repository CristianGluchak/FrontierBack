package br.com.frontier.rh_simplificado.user.infrastructure.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 31/08/25
 */

@Builder
public record LoginUserResponse(

    @Schema(description = "Token JWT do usuario", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
    String accessToken,
    String refreshToken,
    @Schema(description = "Nome do usuario", example = "Jhon")
    String name,

    @Schema(description = "ID do usuario", example = "1")
    String id,

    @Schema(description = "ID do empregador", example = "1")
    String employerId
) {
}
