package br.com.frontier.employer.application.create;

import br.com.frontier.employer.domain.commands.CreateEmployerCommand;
import br.com.frontier.employer.domain.entities.Employer;
import br.com.frontier.employer.domain.entities.EmployerID;
import br.com.frontier.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.employer.infrastructure.persistence.repositories.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@Service
@RequiredArgsConstructor
public class CreateEmployerUseCase {

    private final EmployerRepository repository;

    public EmployerID execute(CreateEmployerInput input){

        CreateEmployerCommand command = CreateEmployerCommand.builder()
                .razaoSocial(input.getRazaoSocial())
                .fantasyName(input.getFantasyName())
                .cnpj(input.getCnpj())
                .email(input.getEmail())
                .build();

        Employer employer = Employer.create(command);

        repository.save(EmployerJpaEntity.from(employer));
        return employer.getId();
    }
}
