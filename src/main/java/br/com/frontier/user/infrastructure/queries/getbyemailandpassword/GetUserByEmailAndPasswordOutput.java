package br.com.frontier.user.infrastructure.queries.getbyemailandpassword;

import lombok.Builder;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 31/08/25
 */

@Builder
public record GetUserByEmailAndPasswordOutput(
    String name,
    String password,
    String id,
    String employerId

) {
}
