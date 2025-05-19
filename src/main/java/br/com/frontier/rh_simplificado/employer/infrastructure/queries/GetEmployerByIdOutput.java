package br.com.frontier.rh_simplificado.employer.infrastructure.queries;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Getter
@Builder
public class GetEmployerByIdOutput {

    private final EmployerID id;

    private final String razaoSocial;

    private final String fantasyName;

    private final String cnpj;

    private final String email;
}
