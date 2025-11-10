package br.com.frontier.rh_simplificado.employee.infrastructure.dtos;

import br.com.frontier.rh_simplificado.employee.application.create.CreateEmployeeInput;
import br.com.frontier.rh_simplificado.employee.application.update.UpdateEmployeeInput;
import br.com.frontier.rh_simplificado.employee.domain.commands.UpdateEmployeeCommand;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeAddressID;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import br.com.frontier.rh_simplificado.shared.enums.CivilState;
import br.com.frontier.rh_simplificado.shared.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
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

    @JsonProperty("gender")
    @Schema(description = "Gênero do empregado", example = "MASCULINO")
    private final Gender gender;

    @JsonProperty("civilState")
    @Schema(description = "Estado civil do empregado", example = "SOLTEIRO")
    private final CivilState civilState;

    @JsonProperty("birthDate")
    @Schema(description = "Data de nascimento do empregado", example = "1990-01-01")
    private final LocalDate birthDate;

    @JsonProperty("phone")
    @Schema(description = "Número de telefone do empregado", example = "(11) 91234-5678")
    private final String phoneNumber;

    @JsonProperty("email")
    @Schema(description = "Email do empregado", example = "JhonDoe@mail.com")
    private final String email;

    @JsonProperty("nationality")
    @Schema(description = "Nacionalidade do empregado", example = "Brasileiro")
    private final String nationality;

    @Schema(description = "Endereço do empregado")
    private final Address address;

    @Getter
    @Builder
    public static class Address {

        @JsonProperty("id")
        @Schema(description = "ID do endereço", example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
        private final String id;

        @JsonProperty("street")
        @Schema(description = "Rua do endereço", example = "Av. Paulista")
        private final String street;

        @JsonProperty("number")
        @Schema(description = "Número do endereço", example = "1000")
        private final String number;

        @JsonProperty("district")
        @Schema(description = "Bairro do endereço", example = "Bela Vista")
        private final String district;

        @JsonProperty("city")
        @Schema(description = "Cidade do endereço", example = "São Paulo")
        private final String city;

        @JsonProperty("state")
        @Schema(description = "Estado do endereço", example = "SP")
        private final String state;

        @JsonProperty("cep")
        private final String cep;
    }


    public static UpdateEmployeeInput from(UpdateEmployeeRequest request, UUID id) {
        return UpdateEmployeeInput.builder()
            .id(EmployeeID.from(id))
            .name(request.getName())
            .cpf(request.getCpf())
            .position(request.getPosition())
            .hours(request.getHours())
            .salary(request.getSalary())
            .gender(request.getGender())
            .civilState(request.getCivilState())
            .birthDate(request.getBirthDate())
            .phoneNumber(request.getPhoneNumber())
            .email(request.getEmail())
            .nationality(request.getNationality())
            .address(Objects.isNull(request.getAddress()) ? null
                : UpdateEmployeeInput.Address.builder()
                .id(Optional.ofNullable(request.getAddress().getId())
                    .map(EmployeeAddressID::from)
                    .orElse(null))
                .street(request.getAddress().getStreet())
                .number(request.getAddress().getNumber())
                .district(request.getAddress().getDistrict())
                .city(request.getAddress().getCity())
                .state(request.getAddress().getState())
                .cep(request.getAddress().getCep())
                .build())
            .build();
    }

}
