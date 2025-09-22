package br.com.frontier.rh_simplificado.payroll.application.calculateall;

import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.rh_simplificado.payroll.domain.commands.CalculatePayrollCommand;
import br.com.frontier.rh_simplificado.payroll.domain.commands.UpdatePayrollCommand;
import br.com.frontier.rh_simplificado.payroll.domain.entities.Payroll;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.repositories.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

import static java.lang.String.format;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */
@Service
@RequiredArgsConstructor
public class CalculateAllPayrollUseCase {

    private final EmployeeRepository employeeRepository;

    public void execute(final CalculateAllPayrollInput input) {



    }
}
