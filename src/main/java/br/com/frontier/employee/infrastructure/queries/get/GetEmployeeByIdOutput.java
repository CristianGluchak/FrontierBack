package br.com.frontier.employee.infrastructure.queries.get;

import br.com.frontier.employee.domain.entities.EmployeeAddressID;
import br.com.frontier.employee.domain.entities.EmployeeID;
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
 * @since 18/05/2025
 */
@Getter
@Builder
public class GetEmployeeByIdOutput {
    private final EmployeeID id;

    private final EmployerID employerID;

    private final String name;

    private final String cpf;

    private final String position;

    private final String hours;

    private final BigDecimal salary;

    private final AtivoInativo status;

    private final LocalDate inactivationDate;

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
    }
}
