package br.com.frontier.employee.domain.commands;

import br.com.frontier.employee.domain.entities.EmployeeAddressID;
import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.shared.enums.CivilState;
import br.com.frontier.shared.enums.Gender;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 22/06/2025
 */
@Getter
@Builder
public class UpdateEmployeeCommand {
    private final String name;

    private final String cpf;

    private final String position;

    private final String hours;

    private final BigDecimal salary;

    private final AtivoInativo status;

    private final Gender gender;

    private final CivilState civilState;

    private final LocalDate birthDate;

    private final String phoneNumber;

    private final String email;

    private final String nationality;

    private final Address address;

    @Getter
    @Builder
    public static class Address {
        private final EmployeeAddressID id;

        private final String street;

        private final String number;

        private final String district;

        private final String city;

        private final String state;

        private final String cep;

        public CreateEmployeeCommand.Address toCreate() {
            return CreateEmployeeCommand.Address.builder()
                .street(street)
                .number(number)
                .district(district)
                .city(city)
                .state(state)
                .cep(cep)
                .build();
        }
    }
}
