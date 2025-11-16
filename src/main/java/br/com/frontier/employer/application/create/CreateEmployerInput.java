package br.com.frontier.employer.application.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class CreateEmployerInput {

    private final String razaoSocial;

    private final String fantasyName;

    private final String cnpj;

    private final String email;
}
