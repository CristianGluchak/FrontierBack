package br.com.frontier.rh_simplificado.salarySheet.application.calculate;

import br.com.frontier.rh_simplificado.employee.domain.entities.Employee;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.repositories.EmployeeRepository;
import br.com.frontier.rh_simplificado.salarySheet.domain.commands.CreatePayrollCommand;
import br.com.frontier.rh_simplificado.salarySheet.domain.entities.Payroll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 06/07/2025
 */
@Service
@RequiredArgsConstructor
public class CreatePayrollUseCase {

    private final EmployeeRepository employeeRepository;

    public void execute(final CreatePayrollInput input) {
        Employee employee = employeeRepository.findById(input.getEmployeeID().getValue())
            .map(EmployeeJpaEntity::ToDomain)
            .orElseThrow(() -> new RuntimeException(format(
                "Não foi possivel localizar um funcionario com o id %s",
                input.getEmployeeID().getValue())));

        /*TODO: implementar consulta por mes para validar calculo
        * folha ja existente no mes então substitua pela calculada
        *
         */
        CreatePayrollCommand command = CreatePayrollCommand.builder()
            .employeeID(input.getEmployeeID())
            .employerID(input.getEmployerID())
            .baseSalary(employee.getSalary())
            .build();

        Payroll payroll = Payroll.create(command);



    }


}
