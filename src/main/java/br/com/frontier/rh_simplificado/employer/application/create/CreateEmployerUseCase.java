package br.com.frontier.rh_simplificado.employer.application.create;

import br.com.frontier.rh_simplificado.employer.domain.commands.CreateEmployerCommand;
import br.com.frontier.rh_simplificado.employer.domain.entities.Employer;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.repositories.EmployerRepository;
import lombok.RequiredArgsConstructor;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RequiredArgsConstructor
public class CreateEmployerUseCase {

    private final EmployerRepository repository;


    public void execute(CreateEmployerInput input){

        CreateEmployerCommand command = CreateEmployerCommand.builder()
                .razaoSocial(input.getRazaoSocial())
                .fantasyName(input.getFantasyName())
                .cnpj(input.getCnpj())
                .email(input.getEmail())
                .build();

        Employer employer = Employer.create(command);

        repository.save(EmployerJpaEntity.from(employer));

    }
}
