package br.com.frontier.rh_simplificado.user.infrastructure.queries.getbyemailandpassword;

import br.com.frontier.rh_simplificado.user.infrastructure.persistence.entities.UserJpaEntity;
import br.com.frontier.rh_simplificado.user.infrastructure.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 31/08/25
 */

@Service
@ApplicationScope
@RequiredArgsConstructor
public class GetUserByEmailAndPasswordUseCase {

    private final UserRepository repository;


    public GetUserByEmailAndPasswordOutput execute(String name, String password) {
        final UserJpaEntity user = repository.findByEmailAndPassword(name, password);

        if (user == null) {
            throw new RuntimeException("Usuario não encontrado");
        }
        return GetUserByEmailAndPasswordOutput.builder()
            .id(user.getId().toString())
            .name(user.getName())
            .password(user.getPassword())
            .employerId(user.getEmployer().getId().toString())
            .build();
    }
}
