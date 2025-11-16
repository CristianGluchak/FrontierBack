package br.com.frontier.employee.domain.commands;

import br.com.frontier.employer.domain.entities.EmployerID;
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
public class CreateEmployeeCommand {
    private final EmployerID employerID;

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
        private final String street;

        private final String number;

        private final String district;

        private final String city;

        private final String state;

        private final String cep;
    }
}
