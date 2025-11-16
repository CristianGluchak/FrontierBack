package br.com.frontier.user.domain.entities;

import br.com.frontier.shared.Identifier;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
public class UserID extends Identifier<UUID> {

    protected UserID(UUID id) {
        super(id);
    }

    public static UserID unique() {
        return new UserID(UUID.randomUUID());
    }

    public static UserID from(final String id) {
        return new UserID(UUID.fromString(id));
    }

    public static UserID from(final UUID id) {
        return new UserID(id);
    }

}
