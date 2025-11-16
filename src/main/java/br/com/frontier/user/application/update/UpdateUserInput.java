package br.com.frontier.user.application.update;

import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.user.domain.entities.UserID;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class UpdateUserInput {
    private UserID id;

    private String name;

    private String email;

    private AtivoInativo status;
}
