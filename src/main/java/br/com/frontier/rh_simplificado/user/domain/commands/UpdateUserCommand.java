package br.com.frontier.rh_simplificado.user.domain.commands;

import br.com.frontier.rh_simplificado.shared.enums.AtivoInativo;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */
@Getter
@Builder
public class UpdateUserCommand {
    private String name;

    private String email;

    private AtivoInativo status;
}
