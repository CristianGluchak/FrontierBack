package br.com.frontier.employee.domain.entities;

import br.com.frontier.employee.domain.commands.CreateEmployeeCommand;
import br.com.frontier.employee.domain.commands.UpdateEmployeeCommand;
import br.com.frontier.employer.domain.entities.EmployerID;
import br.com.frontier.shared.AggregateRoot;
import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.shared.enums.CivilState;
import br.com.frontier.shared.enums.Gender;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

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

    private Gender gender;

    private CivilState civilState;

    private LocalDate birthDate;

    private String phoneNumber;

    private String email;

    private String nationality;

    private EmployeeAddress address;



    @Builder
    public Employee(EmployeeID employeeID,
        EmployerID employerID,
        String name,
        String cpf,
        String position,
        String hours,
        BigDecimal salary,
        AtivoInativo status,
        LocalDate inactivationDate,
        Gender gender,
        CivilState civilState,
        LocalDate birthDate,
        String phoneNumber,
        String email,
        String nationality,
        EmployeeAddress address
    ) {
        super(employeeID);
        this.employerID = employerID;
        this.name = name;
        this.cpf = cpf;
        this.position = position;
        this.hours = hours;
        this.salary = salary;
        this.status = status;
        this.inactivationDate = inactivationDate;
        this.gender = gender;
        this.civilState = civilState;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nationality = nationality;
        this.address = address;
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
            .gender(command.getGender())
            .civilState(command.getCivilState())
            .birthDate(command.getBirthDate())
            .phoneNumber(command.getPhoneNumber())
            .email(command.getEmail())
            .nationality(command.getNationality())
            .address(Optional.ofNullable(command.getAddress())
                .map(EmployeeAddress::create)
                .orElse(null))
            .build();
    }

    public void update(UpdateEmployeeCommand command) {
        this.name = command.getName();
        this.cpf = command.getCpf();
        this.position = command.getPosition();
        this.hours = command.getHours();
        this.salary = command.getSalary();
        this.gender = command.getGender();
        this.civilState = command.getCivilState();
        this.birthDate = command.getBirthDate();
        this.phoneNumber = command.getPhoneNumber();
        this.email = command.getEmail();
        this.nationality = command.getNationality();

        this.address = Optional.ofNullable(command.getAddress())
            .map(addressCmd -> {
                if (Objects.nonNull(addressCmd.getId())) {
                    this.address.update(addressCmd);
                    return this.address;
                }
                return EmployeeAddress.create(addressCmd.toCreate());
            })
            .orElse(null);
    }


}
