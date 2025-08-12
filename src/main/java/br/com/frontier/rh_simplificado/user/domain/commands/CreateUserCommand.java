package br.com.frontier.rh_simplificado.user.domain.commands;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.shared.enums.Role;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class CreateUserCommand {
    private String name;

    private String password;

    private String email;

    private EmployerID employerID;

}
