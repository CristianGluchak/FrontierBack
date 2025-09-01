package br.com.frontier.rh_simplificado.core.jwt;

import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cristian Gluchak <cristian.gluchak@nexuscloud.com.br>
 * @since 10/08/25
 */

@RequiredArgsConstructor
public class JwtUtils {

    @Value("{jwt.secret}")
    private static String secret;

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());


    public static String gerarTokenExpirationTime1H(String username,
        String userId,
        String employerId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userID", userId);
        claims.put("employerID", employerId);

        return Jwts.builder()
            .setSubject(username)
            .addClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact();
    }

    public static Claims validarToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
