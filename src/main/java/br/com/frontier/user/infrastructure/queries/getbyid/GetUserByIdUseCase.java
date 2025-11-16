package br.com.frontier.user.infrastructure.queries.getbyid;

import br.com.frontier.user.domain.entities.UserID;
import br.com.frontier.user.infrastructure.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserRepository repository;

    public GetUserByIdOutput execute(final UserID id) {
        return repository.findById(id.getValue())
            .map(entity -> GetUserByIdOutput.builder()
                .id(UserID.from(entity.getId()))
                .email(entity.getEmail())
                .name(entity.getName())
                .status(entity.getStatus())
                .build())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
}
