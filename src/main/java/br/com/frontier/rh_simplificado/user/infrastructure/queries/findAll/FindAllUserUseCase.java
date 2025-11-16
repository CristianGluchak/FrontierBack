package br.com.frontier.rh_simplificado.user.infrastructure.queries.findAll;


import br.com.frontier.rh_simplificado.user.infrastructure.persistence.repositories.UserRepository;
import br.com.frontier.rh_simplificado.user.infrastructure.persistence.repositories.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllUserUseCase {

    private final UserRepository repository;

    public Page<FindAllUserOutput> execute(String name, UUID employerId, Pageable pageable) {
        return repository
            .findAll(UserSpecification.filter(name, employerId), pageable)
            .map(FindAllUserOutput::fromEntity);
    }
}
