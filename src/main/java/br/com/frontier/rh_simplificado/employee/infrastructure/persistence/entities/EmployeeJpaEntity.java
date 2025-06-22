package br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities;

import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        return orm;
    }
}
