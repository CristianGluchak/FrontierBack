package br.com.frontier.user.infrastructure.persistence.entities;

import br.com.frontier.employer.domain.entities.EmployerID;
import br.com.frontier.employer.infrastructure.persistence.entities.EmployerJpaEntity;
import br.com.frontier.shared.enums.AtivoInativo;
import br.com.frontier.shared.enums.Role;
import br.com.frontier.user.domain.entities.User;
import br.com.frontier.user.domain.entities.UserID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 11/05/2025
 */

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserJpaEntity {

    @Id
    @Column(name = "id_user")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employer")
    private EmployerJpaEntity employer;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private AtivoInativo status;

    public static UserJpaEntity from(final UUID id) {
        UserJpaEntity orm = new UserJpaEntity();
        orm.setId(id);
        return orm;
    }

    public static UserJpaEntity from(final UserID id) {
        UserJpaEntity orm = new UserJpaEntity();
        orm.setId(id.getValue());
        return orm;
    }

    public static UserJpaEntity from(final User dto) {
        UserJpaEntity orm = new UserJpaEntity();
        orm.setId(dto.getId().getValue());
        orm.setName(dto.getName());
        orm.setPassword(dto.getPassword());
        orm.setEmail(dto.getEmail());
        orm.setRole(dto.getRole());
        orm.setStatus(dto.getStatus());
        orm.setEmployer(EmployerJpaEntity.from(dto.getEmployerID()));

        return orm;
    }

    public User toDomain() {
        return User.builder()
            .id(UserID.from(id))
            .name(name)
            .password(password)
            .role(role)
            .email(email)
            .status(status)
            .employerID(EmployerID.from(employer.getId()))
            .build();
    }
}
