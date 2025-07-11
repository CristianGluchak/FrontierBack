package br.com.frontier.rh_simplificado.payroll.infrastructure.queries;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.payroll.domain.entities.PayrollID;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.YearMonth;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Getter
@Builder
public class GetPayrollByIdOutput {

    private final PayrollID id;

    private final EmployeeID employeeID;

    private final EmployerID employerID;

    private final YearMonth referenceMonth;

    private final BigDecimal baseSalary;

    private final BigDecimal grossTotal;

    private final BigDecimal netTotal;

    private final BigDecimal totalDeductions;

    private final BigDecimal inss;

    private final BigDecimal irrf;
}
