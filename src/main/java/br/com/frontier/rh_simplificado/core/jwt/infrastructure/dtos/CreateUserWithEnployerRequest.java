package br.com.frontier.rh_simplificado.core.jwt.infrastructure.dtos;

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
public class CreateUserWithEnployerRequest {

    private final User user;

    private final Employer employer;

    @Getter
    @Builder
    public static class User {
        @JsonProperty(value = "name")
        @Schema(description = "Nome do usuario", example = "Jhon")
        private final String name;

        @JsonProperty(value = "email")
        @Schema(description = "Email da Usuario", example = "Jhonstore@mail.com")
        private final String email;

        @JsonProperty(value = "password")
        @Schema(description = "Senha do usuario", example = "123456")
        private final String password;
    }

    @Getter
    @Builder
    public static class Employer {
        @JsonProperty(value = "razaoSocial")
        @Schema(description = "Razão social da empresa", example = "Jhon's store LDT")
        private final String razaoSocial;

        @JsonProperty(value = "nomeFantasia")
        @Schema(description = "Nome fantasia da empresa", example = "Loja do Jhon")
        private final String fantasyName;

        @JsonProperty(value = "cnpj")
        @Schema(description = "CNPJ da empresa", example = "22829957000188")
        private final String cnpj;

        @JsonProperty(value = "email")
        @Schema(description = "Email da empresa", example = "Jhonstore@mail.com")
        private final String email;
    }
}
