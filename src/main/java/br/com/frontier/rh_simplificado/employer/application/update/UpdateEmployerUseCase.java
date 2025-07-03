package br.com.frontier.rh_simplificado.employer.application.update;

import br.com.frontier.rh_simplificado.employee.application.update.UpdateEmployeeInput;
import br.com.frontier.rh_simplificado.employee.domain.commands.UpdateEmployeeCommand;
import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.rh_simplificado.employer.domain.commands.UpdateEmployerCommand;
import br.com.frontier.rh_simplificado.employer.domain.entities.Employer;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.repositories.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Service
@RequiredArgsConstructor
public class UpdateEmployerUseCase {

    private final EmployerRepository repository;

    public EmployerID execute(UpdateEmployerInput input) {

        Employer employer = repository.findById(input.getId().getValue())
            .map(EmployerJpaEntity::toDomain)
            .orElseThrow(() -> new RuntimeException(format("Não foi possivel localizar uma empresa com o id %s", input.getId().getValue())));

        UpdateEmployerCommand command = UpdateEmployerCommand.builder()
            .razaoSocial(input.getRazaoSocial())
            .fantasyName(input.getFantasyName())
            .cnpj(input.getCnpj())
            .email(input.getEmail())
            .build();

        employer.update(command);

        repository.save(EmployerJpaEntity.from(employer));
        return employer.getId();
    }
}
