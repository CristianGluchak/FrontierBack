package br.com.frontier.rh_simplificado.employee.application.create;

import br.com.frontier.rh_simplificado.employee.domain.commands.CreateEmployeeCommand;
import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.rh_simplificado.employer.domain.commands.CreateEmployerCommand;
import br.com.frontier.rh_simplificado.employer.domain.entities.Employer;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.repositories.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCase {

    private final EmployeeRepository repository;

    public EmployeeID execute(CreateEmployeeInput input){

        CreateEmployeeCommand command = CreateEmployeeCommand.builder()
                .employerID(input.getEmployerID())
                .name(input.getName())
                .cpf(input.getCpf())
                .position(input.getPosition())
                .hours(input.getHours())
                .salary(input.getSalary())
                .status(input.getStatus())
                .inactivationDate(input.getInactivationDate())
                .build();

        Employee employee = Employee.create(command);

        repository.save(EmployeeJpaEntity.from(employee));
        return employee.getId();
    }
}
