package br.com.frontier.rh_simplificado.employer.infrastructure.dtos;

import br.com.frontier.rh_simplificado.employer.infrastructure.queries.GetEmployerByIdOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class GetEmployerByIDResponse {
    @JsonProperty(value ="id")
    private final UUID id;

    @JsonProperty(value = "razaoSocial")
    private final String razaoSocial;

    @JsonProperty(value = "nomeFantasia")
    private final String fantasyName;

    @JsonProperty(value = "cnpj")
    private final String cnpj;

    @JsonProperty(value = "email")
    private final String email;

    public static GetEmployerByIDResponse from(final GetEmployerByIdOutput output){
        return GetEmployerByIDResponse.builder()
                .id(output.getId().getValue())
                .razaoSocial(output.getRazaoSocial())
                .fantasyName(output.getFantasyName())
                .email(output.getEmail())
                .cnpj(output.getCnpj())
                .build();
    }
}
