package br.com.frontier.rh_simplificado.salarySheet.application.create;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */

@Getter
@Builder
public class CreatePayrollInput {

    private final EmployeeID employeeID;
    private final EmployerID employerID;
}
