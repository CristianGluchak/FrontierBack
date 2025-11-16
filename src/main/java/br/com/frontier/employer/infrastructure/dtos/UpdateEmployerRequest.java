package br.com.frontier.employer.infrastructure.dtos;

import br.com.frontier.employer.application.update.UpdateEmployerInput;
import br.com.frontier.employer.domain.entities.EmployerID;
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
public class UpdateEmployerRequest {

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

    public static UpdateEmployerInput from(UpdateEmployerRequest request, UUID id) {
        return UpdateEmployerInput.builder()
            .id(EmployerID.from(id))
            .fantasyName(request.getFantasyName())
            .razaoSocial(request.getRazaoSocial())
            .email(request.getEmail())
            .cnpj(request.getCnpj())
            .build();
    }

}
