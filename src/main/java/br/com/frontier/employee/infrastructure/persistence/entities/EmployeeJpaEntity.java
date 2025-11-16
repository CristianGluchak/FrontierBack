package br.com.frontier.employee.infrastructure.persistence.entities;

import br.com.frontier.employee.domain.entities.Employee;
import br.com.frontier.employee.domain.entities.EmployeeID;
import br.com.frontier.employer.domain.entities.EmployerID;
import br.com.frontier.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.shared.enums.CivilState;
import br.com.frontier.shared.enums.Gender;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 11/05/2025
 */

@Entity
@Getter
@Setter
@Table(name = "employee")
public class EmployeeJpaEntity {

    @Id
    @Column(name = "id_employee")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employer")
    private EmployerJpaEntity employer;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "position")
    private String position;

    @Column(name = "hours")
    private String hours;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private AtivoInativo status;

    @Column(name = "inactivation_date")
    private LocalDate inactivationDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Gender gender;

    @Column(name = "civil_state")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private CivilState civilState;

    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "nationality")
    private String nationality;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeAddressJpaEntity address;

    public static EmployeeJpaEntity from(final EmployeeID id) {
        EmployeeJpaEntity orm = new EmployeeJpaEntity();
        orm.setId(id.getValue());
        return orm;
    }

    public static EmployeeJpaEntity from(final Employee dto) {
        EmployeeJpaEntity orm = new EmployeeJpaEntity();
        orm.setId(dto.getId().getValue());
        orm.setEmployer(EmployerJpaEntity.from(dto.getEmployerID()));
        orm.setName(dto.getName());
        orm.setCpf(dto.getCpf());
        orm.setPosition(dto.getPosition());
        orm.setHours(dto.getHours());
        orm.setSalary(dto.getSalary());
        orm.setStatus(dto.getStatus());
        orm.setInactivationDate(dto.getInactivationDate());
        orm.setGender(dto.getGender());
        orm.setCivilState(dto.getCivilState());
        orm.setBirthDate(dto.getBirthDate());
        orm.setPhoneNumber(dto.getPhoneNumber());
        orm.setEmail(dto.getEmail());
        orm.setNationality(dto.getNationality());

        orm.setAddress(Optional.ofNullable(dto.getAddress())
            .map(EmployeeAddressJpaEntity::from)
            .map(address -> {
                address.setEmployee(orm);
                return address;
            })
            .orElse(null));

        return orm;
    }


    public Employee ToDomain() {
        return Employee.builder()
            .employeeID(EmployeeID.from(id))
            .employerID(EmployerID.from(employer.getId()))
            .name(name)
            .cpf(cpf)
            .position(position)
            .hours(hours)
            .salary(salary)
            .status(status)
            .inactivationDate(inactivationDate)
            .gender(gender)
            .civilState(civilState)
            .birthDate(birthDate)
            .phoneNumber(phoneNumber)
            .email(email)
            .nationality(nationality)
            .address(Optional.ofNullable(address)
                .map(EmployeeAddressJpaEntity::toDomain)
                .orElse(null))
            .build();
    }
}
