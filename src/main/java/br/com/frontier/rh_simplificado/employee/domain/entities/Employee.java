package br.com.frontier.rh_simplificado.employee.domain.entities;

import br.com.frontier.rh_simplificado.employee.domain.commands.CreateEmployeeCommand;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.shared.AggregateRoot;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
@Getter

public class Employee extends AggregateRoot<EmployeeID> {

    private EmployerID employerID;

    private String name;

    private String cpf;

    private String position;

    private String hours;

    private BigDecimal salary;

    private AtivoInativo status;

    private LocalDate inactivationDate;

    @Builder
    public Employee(EmployeeID employeeID, EmployerID employerID, String name, String cpf, String position, String hours, BigDecimal salary, AtivoInativo status, LocalDate inactivationDate) {
        super(employeeID);
        this.employerID = employerID;
        this.name = name;
        this.cpf = cpf;
        this.position = position;
        this.hours = hours;
        this.salary = salary;
        this.status = status;
        this.inactivationDate = inactivationDate;
    }

    public static Employee create(CreateEmployeeCommand command) {
        return Employee.builder()
                .employeeID(EmployeeID.unique())
                .employerID(command.getEmployerID())
                .name(command.getName())
                .cpf(command.getCpf())
                .position(command.getPosition())
                .hours(command.getHours())
                .salary(command.getSalary())
                .status(command.getStatus())
                .inactivationDate(command.getInactivationDate())
                .build();
    }


}
