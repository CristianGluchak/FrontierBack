package br.com.frontier.rh_simplificado.payroll.infrastructure.queries.findall;


import br.com.frontier.rh_simplificado.payroll.infrastructure.persistence.entities.PayrollJpaEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record FindAllPayrollOutput(
        UUID id,
        UUID employeeId,
        String employeeName,
        UUID employerId,
        String referenceMonth,
        BigDecimal baseSalary,
        BigDecimal grossTotal,
        BigDecimal netTotal,
        BigDecimal totalDeductions
) {

    public static FindAllPayrollOutput fromEntity(PayrollJpaEntity entity) {
        return FindAllPayrollOutput.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployee() != null ? entity.getEmployee().getId() : null)
                .employeeName(entity.getEmployee() != null ? entity.getEmployee().getName() : null)
                .employerId(entity.getEmployer() != null ? entity.getEmployer().getId() : null)
                .referenceMonth(entity.getReferenceMonth())
                .baseSalary(entity.getBaseSalary())
                .grossTotal(entity.getGrossTotal())
                .netTotal(entity.getNetTotal())
                .totalDeductions(entity.getTotalDeductions())
                .build();
    }
}
