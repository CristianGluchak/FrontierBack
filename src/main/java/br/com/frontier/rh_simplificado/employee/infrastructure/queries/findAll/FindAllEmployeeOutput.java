package br.com.frontier.rh_simplificado.employee.infrastructure.queries.findAll;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record FindAllEmployeeOutput(
    EmployeeID id,
    String name,
    String cpf,
    String position,
    String hours,
    BigDecimal salary,
    String status,
    LocalDate inactivationDate,
    UUID employerId,
    String employerFantasyName
) {

    public static FindAllEmployeeOutput fromEntity(EmployeeJpaEntity e) {
        return FindAllEmployeeOutput.builder()
            .id(EmployeeID.from(e.getId()))
            .name(e.getName())
            .cpf(e.getCpf())
            .position(e.getPosition())
            .hours(e.getHours())
            .salary(e.getSalary())
            .status(e.getStatus() != null ? e.getStatus().name() : null)
            .inactivationDate(e.getInactivationDate())
            .employerId(e.getEmployer() != null ? e.getEmployer().getId() : null)
            .employerFantasyName(e.getEmployer() != null ? e.getEmployer().getFantasyName() : null)
            .build();
    }
}
