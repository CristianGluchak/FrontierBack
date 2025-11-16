package br.com.frontier.payroll.domain.commands;

import br.com.frontier.employee.domain.entities.EmployeeID;
import br.com.frontier.employer.domain.entities.EmployerID;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */
@Getter
@Builder
public class CalculatePayrollCommand {

    private final EmployeeID employeeID;
    private final EmployerID employerID;
    private final BigDecimal baseSalary;
}
