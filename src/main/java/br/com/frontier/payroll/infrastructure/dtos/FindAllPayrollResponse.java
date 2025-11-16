package br.com.frontier.payroll.infrastructure.dtos;


import br.com.frontier.payroll.infrastructure.queries.findall.FindAllPayrollOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record FindAllPayrollResponse(
        @Schema(example = "d1e7d8f3-7b9a-4a8c-9b0e-3f4a7e2fbb93") UUID id,
        @Schema(example = "c2d4a9b8-1a7e-4d0a-bf76-1d93b1e2a7a4") UUID employeeId,
        @Schema(example = "João da Silva") String employeeName,
        @Schema(example = "b3f7a4e2-9b1d-4c7a-8d6e-5a1b3c4d2e9f") UUID employerId,
        @Schema(example = "2025-10") String referenceMonth,
        @Schema(example = "3000.00") BigDecimal baseSalary,
        @Schema(example = "3500.00") BigDecimal grossTotal,
        @Schema(example = "2800.00") BigDecimal netTotal,
        @Schema(example = "700.00") BigDecimal totalDeductions
) {
    public static FindAllPayrollResponse fromOutput(FindAllPayrollOutput output) {
        return FindAllPayrollResponse.builder()
                .id(output.id())
                .employeeId(output.employeeId())
                .employeeName(output.employeeName())
                .employerId(output.employerId())
                .referenceMonth(output.referenceMonth())
                .baseSalary(output.baseSalary())
                .grossTotal(output.grossTotal())
                .netTotal(output.netTotal())
                .totalDeductions(output.totalDeductions())
                .build();
    }
}
