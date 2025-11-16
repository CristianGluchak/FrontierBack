package br.com.frontier.employee.infrastructure.dtos;

import br.com.frontier.employee.infrastructure.queries.findAll.FindAllEmployeeOutput;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Employee response model for API")
public record FindAllEmployeeResponse(

    @Schema(description = "Employee unique identifier", example = "c29c32d2-7717-45a5-8e9e-0dca3dff8a7d")
    UUID id,

    @Schema(description = "Employee name", example = "João da Silva")
    String name,

    @Schema(description = "CPF of the employee", example = "12345678900")
    String cpf,

    @Schema(description = "Employee position", example = "Analista de Sistemas")
    String position,

    @Schema(description = "Working hours", example = "44")
    String hours,

    @Schema(description = "Gross salary", example = "4500.00")
    BigDecimal salary,

    @Schema(description = "Status (ATIVO / INATIVO)", example = "ATIVO")
    String status,

    @Schema(description = "Date of inactivation (if any)", example = "2024-10-10")
    LocalDate inactivationDate,

    @Schema(description = "Employer ID", example = "5a81b17f-4de3-451a-bc6c-9982b92d1c36")
    UUID employerId,

    @Schema(description = "Employer fantasy name", example = "Tech Solutions LTDA")
    String employerFantasyName
) {
    public static FindAllEmployeeResponse fromOutput(FindAllEmployeeOutput output) {
        return new FindAllEmployeeResponse(
            output.id().getValue(),
            output.name(),
            output.cpf(),
            output.position(),
            output.hours(),
            output.salary(),
            output.status(),
            output.inactivationDate(),
            output.employerId(),
            output.employerFantasyName()
        );
    }
}
