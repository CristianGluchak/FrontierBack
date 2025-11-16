package br.com.frontier.user.domain.commands;

import br.com.frontier.employer.domain.entities.EmployerID;
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
