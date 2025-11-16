package br.com.frontier.payroll.application.calculateall;

import br.com.frontier.employee.domain.entities.EmployeeID;
import br.com.frontier.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.payroll.domain.commands.CalculatePayrollCommand;
import br.com.frontier.payroll.domain.commands.UpdatePayrollCommand;
import br.com.frontier.payroll.domain.entities.Payroll;
import br.com.frontier.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import br.com.frontier.payroll.infrastructure.persistence.repositories.PayrollRepository;
import br.com.frontier.shared.enums.AtivoInativo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */
@Service
@RequiredArgsConstructor
public class CalculateAllPayrollUseCase {

    private final EmployeeRepository employeeRepository;

    private final PayrollRepository payrollRepository;

    public void execute(final CalculateAllPayrollInput input) {

        List<EmployeeJpaEntity> activeEmployeeToCalculate = employeeRepository.findByEmployerIdAndStatus(
            input.getEmployerID().getValue(), AtivoInativo.ATIVO);

        for (EmployeeJpaEntity employee : activeEmployeeToCalculate) {
            payrollRepository.findByEmployerAndEmployeeAndReferenceMonth(input.getEmployerID()
                .getValue(), employee.getId(), YearMonth.now()).ifPresentOrElse(
                payrollJpaEntity -> {

                    Payroll payrollDomain = payrollJpaEntity.toDomain();

                    UpdatePayrollCommand command = UpdatePayrollCommand.builder()
                        .employeeID(EmployeeID.from(employee.getId()))
                        .employerID(input.getEmployerID())
                        .baseSalary(employee.getSalary())
                        .build();
                    payrollDomain.update(command);

                    payrollRepository.save(PayrollJpaEntity.from(payrollDomain));
                },
                () -> {

                    CalculatePayrollCommand command = CalculatePayrollCommand.builder()
                        .employeeID(EmployeeID.from(employee.getId()))
                        .employerID(input.getEmployerID())
                        .baseSalary(employee.getSalary())
                        .build();

                    Payroll payroll = Payroll.create(command);
                    payrollRepository.save(PayrollJpaEntity.from(payroll));
                });
        }
    }
}
