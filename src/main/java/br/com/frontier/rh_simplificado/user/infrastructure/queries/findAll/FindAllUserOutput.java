package br.com.frontier.rh_simplificado.user.infrastructure.queries.findAll;

import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.employee.infrastructure.persistence.entities.EmployeeJpaEntity;
import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
import br.com.frontier.rh_simplificado.user.infrastructure.persistence.entities.UserJpaEntity;
import lombok.Builder;

@Builder
public record FindAllUserOutput(
    UserID id,

    String name,

    AtivoInativo status
) {

    public static FindAllUserOutput fromEntity(UserJpaEntity e) {
        return FindAllUserOutput.builder()
            .id(UserID.from(e.getId()))
            .name(e.getName())
            .status(e.getStatus())
            .build();
    }
}
