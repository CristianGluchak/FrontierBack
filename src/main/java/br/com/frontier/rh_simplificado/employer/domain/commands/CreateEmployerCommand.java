package br.com.frontier.rh_simplificado.employer.domain.commands;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class CreateEmployerCommand {
    private final String razaoSocial;

    private final String fantasyName;

    private final String cnpj;

    private final String email;
}
