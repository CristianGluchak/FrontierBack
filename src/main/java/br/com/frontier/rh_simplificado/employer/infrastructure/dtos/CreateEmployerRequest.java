package br.com.frontier.rh_simplificado.employer.infrastructure.dtos;

import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class CreateEmployerRequest {

    @JsonProperty(value = "razaoSocial")
    private final String razaoSocial;

    @JsonProperty(value = "nomeFantasia")
    private final String fantasyName;

    @JsonProperty(value = "cnpj")
    private final String cnpj;

    @JsonProperty(value = "email")
    private final String email;

    public static CreateEmployerInput from(CreateEmployerRequest request){
        return CreateEmployerInput.builder()
                .fantasyName(request.getFantasyName())
                .razaoSocial(request.getRazaoSocial())
                .email(request.getEmail())
                .cnpj(request.getCnpj())
                .build();
    }

}
