package br.com.frontier.rh_simplificado.user.infrastructure.queries.getbyid;

import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Getter
@Builder
public class GetUserByIdOutput {

    private final UserID id;

    private final String name;

    private final String email;
}
