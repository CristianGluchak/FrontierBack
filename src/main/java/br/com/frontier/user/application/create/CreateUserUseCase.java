package br.com.frontier.user.application.create;

import br.com.frontier.user.domain.commands.CreateUserCommand;
import br.com.frontier.user.domain.entities.User;
import br.com.frontier.user.domain.entities.UserID;
import br.com.frontier.user.infrastructure.persistence.entities.UserJpaEntity;
import br.com.frontier.user.infrastructure.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository repository;

    public UserID execute(CreateUserInput input) {

        final CreateUserCommand command = CreateUserCommand.builder()
            .name(input.getName())
            .password(input.getPassword())
            .email(input.getEmail())
            .employerID(input.getEmployerID())
            .build();

        User user = User.create(command);

        repository.save(UserJpaEntity.from(user));
        return user.getId();
    }
}
