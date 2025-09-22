package br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.entities;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.rh_simplificado.payroll.domain.entities.Payroll;
import br.com.frontier.rh_simplificado.payroll.domain.entities.PayrollID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 11/05/2025
 */

@Entity
@Getter
@Setter
@Table(name = "payroll")
public class PayrollJpaEntity {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    @Id
    @Column(name = "id_payroll")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private EmployeeJpaEntity employeeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employer")
    private EmployerJpaEntity employerID;

    @Column(name = "reference_month")
    private String referenceMonth;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;

    @Column(name = "gross_total")
    private BigDecimal grossTotal;

    @Column(name = "net_total")
    private BigDecimal netTotal;

    @Column(name = "total_deductions")
    private BigDecimal totalDeductions;

    @Column(name = "inss")
    private BigDecimal inss;

    @Column(name = "irrf")
    private BigDecimal irrf;

    public static PayrollJpaEntity from(final UUID id) {
        PayrollJpaEntity orm = new PayrollJpaEntity();
        orm.setId(id);
        return orm;
    }

    public static PayrollJpaEntity from(final PayrollID id) {
        PayrollJpaEntity orm = new PayrollJpaEntity();
        orm.setId(id.getValue());
        return orm;
    }


    public static PayrollJpaEntity from(final Payroll dto) {
        PayrollJpaEntity orm = new PayrollJpaEntity();
        orm.setId(dto.getId().getValue());
        orm.setBaseSalary(dto.getBaseSalary());
        orm.setGrossTotal(dto.getGrossTotal());
        orm.setNetTotal(dto.getNetTotal());
        orm.setTotalDeductions(dto.getTotalDeductions());
        orm.setInss(dto.getInss());
        orm.setIrrf(dto.getIrrf());
        orm.setReferenceMonth(dto.getReferenceMonth().toString());
        orm.setEmployeeID(EmployeeJpaEntity.from(dto.getEmployeeID()));
        orm.setEmployerID(EmployerJpaEntity.from(dto.getEmployerID()));

        return orm;
    }

    public Payroll toDomain() {
        return Payroll.builder()
            .id(PayrollID.from(id))
            .employeeID(EmployeeID.from(employeeID.getId()))
            .employerID(EmployerID.from(employerID.getId()))
            .referenceMonth(YearMonth.parse(referenceMonth, FORMATTER))
            .baseSalary(baseSalary)
            .grossTotal(grossTotal)
            .netTotal(netTotal)
            .totalDeductions(totalDeductions)
            .inss(inss)
            .irrf(irrf)
            .build();
    }
}
