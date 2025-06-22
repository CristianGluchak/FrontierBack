package br.com.frontier.rh_simplificado.employee.infrastructure.dtos;

import br.com.frontier.rh_simplificado.employee.application.create.CreateEmployeeInput;
import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerInput;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class CreateEmployeeRequest {

    @JsonProperty("employer_id")
    @Schema(description = "ID do empregador", example = "a3f1e234-56b7-4c88-9e89-123456789abc")
    private final UUID employerID;

    @JsonProperty("name")
    @Schema(description = "Nome do empregado", example = "John Doe")
    private final String name;

    @JsonProperty("cpf")
    @Schema(description = "CPF do empregado", example = "123.456.789-00")
    private final String cpf;

    @JsonProperty("position")
    @Schema(description = "Cargo do empregado", example = "Analista de Sistemas")
    private final String position;

    @JsonProperty("hours")
    @Schema(description = "Carga horária semanal", example = "40")
    private final String hours;

    @JsonProperty("salary")
    @Schema(description = "Salário do empregado", example = "2000.00")
    private final BigDecimal salary;

    @JsonProperty("status")
    @Schema(description = "Situação do empregado", example = "ATIVO")
    private final AtivoInativo status;

    @JsonProperty("inactivation_date")
    @Schema(description = "Data de inativação do empregado, se houver", example = "2024-12-31")
    private final LocalDate inactivationDate;


    public static CreateEmployeeInput from(CreateEmployeeRequest request){
        return CreateEmployeeInput.builder()
                .employerID(EmployerID.from(request.getEmployerID()))
                .name(request.getName())
                .cpf(request.getCpf())
                .position(request.getPosition())
                .hours(request.getHours())
                .salary(request.getSalary())
                .status(request.getStatus())
                .inactivationDate(request.getInactivationDate())
                .build();
    }

}
