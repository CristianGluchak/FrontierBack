package br.com.frontier.rh_simplificado.employee.application.create;

import br.com.frontier.rh_simplificado.employee.domain.commands.CreateEmployeeCommand;
import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeAddress;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeAddressID;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCase {

    private final EmployeeRepository repository;

    public EmployeeID execute(CreateEmployeeInput input) {

        CreateEmployeeCommand command = CreateEmployeeCommand.builder()
            .employerID(input.getEmployerID())
            .name(input.getName())
            .cpf(input.getCpf())
            .position(input.getPosition())
            .hours(input.getHours())
            .salary(input.getSalary())
            .status(input.getStatus())
            .gender(input.getGender())
            .civilState(input.getCivilState())
            .birthDate(input.getBirthDate())
            .phoneNumber(input.getPhoneNumber())
            .email(input.getEmail())
            .nationality(input.getNationality())
            .address(Objects.isNull(input.getAddress()) ? null
                : CreateEmployeeCommand.Address.builder()
                .street(input.getAddress().getStreet())
                .number(input.getAddress().getNumber())
                .district(input.getAddress().getDistrict())
                .city(input.getAddress().getCity())
                .state(input.getAddress().getState())
                .cep(input.getAddress().getCep())
                .build())
            .build();

        Employee employee = Employee.create(command);

        repository.save(EmployeeJpaEntity.from(employee));
        return employee.getId();
    }
}
