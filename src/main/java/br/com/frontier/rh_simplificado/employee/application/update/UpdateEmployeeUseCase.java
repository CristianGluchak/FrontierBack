package br.com.frontier.rh_simplificado.employee.application.update;

import br.com.frontier.rh_simplificado.employee.domain.commands.UpdateEmployeeCommand;
import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCase {

    private final EmployeeRepository repository;

    public EmployeeID execute(UpdateEmployeeInput input) {

        Employee employee = repository.findById(input.getId().getValue())
                .map(EmployeeJpaEntity::ToDomain)
                .orElseThrow(() -> new RuntimeException(format("Não foi possivel localizar um funcionario com o id %s", input.getId().getValue())));

        UpdateEmployeeCommand command = UpdateEmployeeCommand.builder()
                .name(input.getName())
                .cpf(input.getCpf())
                .position(input.getPosition())
                .hours(input.getHours())
                .salary(input.getSalary())
                .build();

        employee.update(command);

        repository.save(EmployeeJpaEntity.from(employee));
        return employee.getId();
    }
}
