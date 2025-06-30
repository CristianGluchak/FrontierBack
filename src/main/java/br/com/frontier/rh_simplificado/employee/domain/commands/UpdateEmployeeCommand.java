package br.com.frontier.rh_simplificado.employee.domain.commands;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
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
}
