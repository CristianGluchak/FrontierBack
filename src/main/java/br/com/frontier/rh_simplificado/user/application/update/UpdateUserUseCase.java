package br.com.frontier.rh_simplificado.user.application.update;

import br.com.frontier.rh_simplificado.user.domain.commands.UpdateUserCommand;
import br.com.frontier.rh_simplificado.user.domain.entities.User;
import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
import br.com.frontier.rh_simplificado.user.infrastructure.persistence.entities.UserJpaEntity;
import br.com.frontier.rh_simplificado.user.infrastructure.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository repository;

    public UserID execute(UpdateUserInput input) {

        User user = repository.findById(input.getId().getValue())
            .map(UserJpaEntity::toDomain)
            .orElseThrow(() -> new RuntimeException(format(
                "Não foi possivel localizar um usuario com o id %s",
                input.getId().getValue())));

        UpdateUserCommand command = UpdateUserCommand.builder()
            .name(input.getName())
            .email(input.getEmail())
            .status(input.getStatus())
            .build();

        user.update(command);

        repository.save(UserJpaEntity.from(user));
        return user.getId();
    }
}
