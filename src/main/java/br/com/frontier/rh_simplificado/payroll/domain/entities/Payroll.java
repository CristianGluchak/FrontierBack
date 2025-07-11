package br.com.frontier.rh_simplificado.payroll.domain.entities;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.payroll.domain.commands.CreatePayrollCommand;
import br.com.frontier.rh_simplificado.shared.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.YearMonth;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */
@Getter
public class Payroll extends AggregateRoot<PayrollID> {
    private EmployeeID employeeID;
    private EmployerID employerID;
    private YearMonth referenceMonth;

    private BigDecimal baseSalary;
    private BigDecimal grossTotal;
    private BigDecimal netTotal;
    private BigDecimal totalDeductions;

    private BigDecimal inss;
    private BigDecimal irrf;

    @Builder
    public Payroll(PayrollID id,
                   EmployeeID employeeID,
                   EmployerID employerID,
                   YearMonth referenceMonth,
                   BigDecimal baseSalary,
                   BigDecimal grossTotal,
                   BigDecimal netTotal,
                   BigDecimal totalDeductions,
                   BigDecimal inss,
                   BigDecimal irrf) {

        super(id);
        this.employeeID = employeeID;
        this.employerID = employerID;
        this.referenceMonth = referenceMonth;
        this.baseSalary = baseSalary;
        this.grossTotal = grossTotal;
        this.netTotal = netTotal;
        this.totalDeductions = totalDeductions;
        this.inss = inss;
        this.irrf = irrf;
    }


    public static Payroll create(CreatePayrollCommand command) {

        BigDecimal grossTotal = command.getBaseSalary();

        BigDecimal inss = TaxCalculatorUtils.calculateINSS(command.getBaseSalary());

        BigDecimal irrf = TaxCalculatorUtils.calculateIRRF(command.getBaseSalary(), inss);

        BigDecimal totalDeductions = BigDecimal.ZERO.add(inss).add(irrf);

        BigDecimal netTotal = grossTotal.subtract(totalDeductions);

        return Payroll.builder()
                .id(PayrollID.unique())
                .employeeID(command.getEmployeeID())
                .employerID(command.getEmployerID())
                .referenceMonth(YearMonth.now())
                .baseSalary(command.getBaseSalary())
                .grossTotal(grossTotal)
                .netTotal(netTotal)
                .totalDeductions(totalDeductions)
                .inss(inss)
                .irrf(irrf)
                .build();
    }
}
