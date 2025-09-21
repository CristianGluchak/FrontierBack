package br.com.frontier.rh_simplificado.core.jwt;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Map;
import java.util.UUID;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 10/08/25
 */
@Getter
@Component
@RequestScope
public class AuthenticatedUser {

    private final UUID userID;
    private final EmployerID employerID;

    public AuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getDetails() instanceof Map<?, ?> details) {
            this.userID = UUID.fromString(details.get("userID").toString());
            this.employerID = EmployerID.from(details.get("employerID").toString());
        } else {
            this.userID = null;
            this.employerID = null;
        }
    }
}
