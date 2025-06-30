package br.com.frontier.rh_simplificado.employee.infrastructure.dtos;

import br.com.frontier.rh_simplificado.employee.application.create.CreateEmployeeInput;
import br.com.frontier.rh_simplificado.employee.application.update.UpdateEmployeeInput;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
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
public class UpdateEmployeeRequest {

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



    public static UpdateEmployeeInput from(UpdateEmployeeRequest request, UUID id){
        return UpdateEmployeeInput.builder()
                .id(EmployeeID.from(id))
                .name(request.getName())
                .cpf(request.getCpf())
                .position(request.getPosition())
                .hours(request.getHours())
                .salary(request.getSalary())
                .build();
    }

}
