package br.com.frontier.user.domain.entities;

import br.com.frontier.employer.domain.entities.EmployerID;
import br.com.frontier.shared.AggregateRoot;
import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.shared.enums.Role;
import br.com.frontier.user.domain.commands.CreateUserCommand;
import br.com.frontier.user.domain.commands.UpdateUserCommand;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
@Getter
public class User extends AggregateRoot<UserID> {

    private String name;

    private String password;

    private String email;

    private Role role;

    private EmployerID employerID;

    private AtivoInativo status;


    @Builder
    public User(final UserID id,
        final String name,
        final String password,
        final String email,
        final Role role,
        final EmployerID employerID, final AtivoInativo status) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.employerID = employerID;
        this.status = status;
    }

    public static User create(CreateUserCommand command) {
        return User.builder()
            .id(UserID.unique())
            .name(command.getName())
            .password(command.getPassword())
            .email(command.getEmail())
            .role(Role.ADMIN)
            .employerID(command.getEmployerID())
            .status(AtivoInativo.ATIVO)
            .build();
    }

    public void update(UpdateUserCommand command) {
        this.name = command.getName();
        this.email = command.getEmail();
        this.status = command.getStatus();
    }

}
