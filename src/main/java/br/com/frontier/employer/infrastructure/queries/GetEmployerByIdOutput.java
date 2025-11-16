package br.com.frontier.employer.infrastructure.queries;

import br.com.frontier.employer.domain.entities.EmployerID;
import lombok.Builder;
import lombok.Getter;

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
