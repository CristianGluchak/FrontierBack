package br.com.frontier.rh_simplificado.payroll.infrastructure.queries;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.payroll.domain.entities.PayrollID;
import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.repositories.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Service
@RequiredArgsConstructor
public class GetPayrollByIdUseCase {

    private final PayrollRepository repository;

    public GetPayrollByIdOutput execute(final PayrollID id, final EmployerID employerID) {
        return repository.findByIdAndEmployer_id(id.getValue(), employerID.getValue())
            .map(entity -> GetPayrollByIdOutput.builder()
                .id(PayrollID.from(entity.getId()))
                .employeeID(EmployeeID.from(entity.getEmployee().getId()))
                .employerID(EmployerID.from(entity.getEmployer().getId()))
                .referenceMonth(YearMonth.parse(entity.getReferenceMonth()))
                .baseSalary(entity.getBaseSalary())
                .grossTotal(entity.getGrossTotal())
                .netTotal(entity.getNetTotal())
                .totalDeductions(entity.getTotalDeductions())
                .inss(entity.getInss())
                .irrf(entity.getIrrf())
                .build())
            .orElseThrow(() -> new RuntimeException("Folha de pagamento nao encontrada"));
    }
}
