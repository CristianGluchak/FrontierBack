package br.com.frontier.rh_simplificado.employee.application.update;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class UpdateEmployeeInput {
    private final EmployeeID id;

    private final String name;

    private final String cpf;

    private final String position;

    private final String hours;

    private final BigDecimal salary;
}
