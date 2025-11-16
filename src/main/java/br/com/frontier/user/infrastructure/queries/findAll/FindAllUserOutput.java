package br.com.frontier.user.infrastructure.queries.findAll;

import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.user.domain.entities.UserID;
import br.com.frontier.user.infrastructure.persistence.entities.UserJpaEntity;
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
