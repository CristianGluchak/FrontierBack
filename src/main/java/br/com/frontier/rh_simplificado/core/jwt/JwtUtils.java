package br.com.frontier.rh_simplificado.core.jwt;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 10/08/25
 */
@Component
public class JwtUtils {

    private final Key secretKey;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarTokenExpirationTime1H(String username, String userId, String employerId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userID", userId);
        claims.put("employerID", employerId);

        return Jwts.builder()
            .setSubject(username)
            .addClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public Claims validarToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}

