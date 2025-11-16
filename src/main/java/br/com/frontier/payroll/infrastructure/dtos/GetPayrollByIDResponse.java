package br.com.frontier.payroll.infrastructure.dtos;

import br.com.frontier.payroll.infrastructure.queries.get.GetPayrollByIdOutput;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.YearMonth;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@Builder
public record GetPayrollByIDResponse(
    String id,
    String employeeID,
    String employerID,
    YearMonth referenceMonth,
    BigDecimal baseSalary,
    BigDecimal grossTotal,
    BigDecimal netTotal,
    BigDecimal totalDeductions,
    BigDecimal inss,
    BigDecimal irrf

) {
    public static GetPayrollByIDResponse from(final GetPayrollByIdOutput output) {
        return GetPayrollByIDResponse.builder()
            .id(output.getId().toString())
            .employeeID(output.getEmployeeID().toString())
            .employerID(output.getEmployerID().toString())
            .referenceMonth(output.getReferenceMonth())
            .baseSalary(output.getBaseSalary())
            .grossTotal(output.getGrossTotal())
            .netTotal(output.getNetTotal())
            .totalDeductions(output.getTotalDeductions())
            .inss(output.getInss())
            .irrf(output.getIrrf())
            .build();
    }


}
