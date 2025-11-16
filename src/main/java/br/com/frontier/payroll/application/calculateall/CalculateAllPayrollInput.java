package br.com.frontier.payroll.application.calculateall;

import br.com.frontier.employer.domain.entities.EmployerID;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */

@Getter
@Builder
public class CalculateAllPayrollInput {


    private final EmployerID employerID;
}
