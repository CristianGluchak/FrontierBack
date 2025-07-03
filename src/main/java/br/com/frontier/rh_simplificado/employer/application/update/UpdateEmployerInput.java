package br.com.frontier.rh_simplificado.employer.application.update;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class UpdateEmployerInput {
    private final EmployerID id;

    private final String razaoSocial;

    private final String fantasyName;

    private final String cnpj;

    private final String email;
}
