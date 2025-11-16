package br.com.frontier.rh_simplificado.payroll.application.calculate;

import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.rh_simplificado.payroll.domain.commands.CalculatePayrollCommand;
import br.com.frontier.rh_simplificado.payroll.domain.commands.UpdatePayrollCommand;
import br.com.frontier.rh_simplificado.payroll.domain.entities.Payroll;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.repositories.PayrollRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

import static java.lang.String.format;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CalculatePayrollUseCase {

    private final EmployeeRepository employeeRepository;

    private final PayrollRepository payrollRepository;

    public void execute(final CalculatePayrollInput input) {
        Employee employee = employeeRepository.findById(input.getEmployeeID().getValue())
            .map(EmployeeJpaEntity::ToDomain)
            .orElseThrow(() -> new RuntimeException(format(
                "Não foi possivel localizar um funcionario com o id %s",
                input.getEmployeeID().getValue())));

        payrollRepository.findByEmployerAndEmployeeAndReferenceMonth(input.getEmployerID()
                .getValue(),
            input.getEmployeeID().getValue(),
            YearMonth.now()).ifPresentOrElse(
            payrollJpaEntity -> {

                Payroll payrollDomain = payrollJpaEntity.toDomain();

                UpdatePayrollCommand command = UpdatePayrollCommand.builder()
                    .employeeID(input.getEmployeeID())
                    .employerID(input.getEmployerID())
                    .baseSalary(employee.getSalary())
                    .build();
                payrollDomain.update(command);

                payrollRepository.save(PayrollJpaEntity.from(payrollDomain));
            },
            () -> {

                CalculatePayrollCommand command = CalculatePayrollCommand.builder()
                    .employeeID(input.getEmployeeID())
                    .employerID(input.getEmployerID())
                    .baseSalary(employee.getSalary())
                    .build();

                Payroll payroll = Payroll.create(command);
                payrollRepository.save(PayrollJpaEntity.from(payroll));
            });

    }
}
