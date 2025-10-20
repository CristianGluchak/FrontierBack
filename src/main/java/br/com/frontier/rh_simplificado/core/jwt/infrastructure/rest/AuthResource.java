package br.com.frontier.rh_simplificado.core.jwt.infrastructure.rest;

import br.com.frontier.rh_simplificado.core.jwt.JwtUtils;
import br.com.frontier.rh_simplificado.core.jwt.infrastructure.dtos.CreateUserWithEnployerRequest;
import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerInput;
import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerUseCase;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.user.application.create.CreateUserInput;
import br.com.frontier.rh_simplificado.user.application.create.CreateUserUseCase;
import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
import br.com.frontier.rh_simplificado.user.infrastructure.dtos.LoginUserRequest;
import br.com.frontier.rh_simplificado.user.infrastructure.dtos.LoginUserResponse;
import br.com.frontier.rh_simplificado.user.infrastructure.queries.getbyemailandpassword.GetUserByEmailAndPasswordOutput;
import br.com.frontier.rh_simplificado.user.infrastructure.queries.getbyemailandpassword.GetUserByEmailAndPasswordUseCase;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthResource {

    private final CreateUserUseCase createUserUseCase;

    private final CreateEmployerUseCase createEmployerUseCase;

    private final GetUserByEmailAndPasswordUseCase getUserByEmailAndPasswordUseCase;

    private final JwtUtils jwtUtils;

    @PostMapping
    public UserID create(@RequestBody @Valid CreateUserWithEnployerRequest request) {

        CreateEmployerInput employerin = CreateEmployerInput.builder()
            .fantasyName(request.getEmployer().getFantasyName())
            .cnpj(request.getEmployer().getCnpj())
            .email(request.getEmployer().getEmail())
            .razaoSocial(request.getEmployer().getRazaoSocial())
            .build();

        EmployerID employerIDCreated = createEmployerUseCase.execute(employerin);

        CreateUserInput input = CreateUserInput.builder()
            .name(request.getUser().getName())
            .password(request.getUser().getPassword())
            .email(request.getUser().getEmail())
            .employerID(employerIDCreated)
            .build();

        return createUserUseCase.execute(input);
    }


    @PostMapping("/login")
    public LoginUserResponse login(@RequestBody @Valid LoginUserRequest request) {
        final GetUserByEmailAndPasswordOutput output = getUserByEmailAndPasswordUseCase.execute(
            request.email(),
            request.password()
        );

        String accessToken = jwtUtils.gerarAccessToken(
            output.name(),
            output.id(),
            output.employerId()
        );

        String refreshToken = jwtUtils.gerarRefreshToken(output.name(), output.id(),
            output.employerId());

        return LoginUserResponse.builder()
            .id(output.id())
            .name(output.name())
            .employeerId(output.employerId())
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginUserResponse> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refresh_token");

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try {
            Claims claims = jwtUtils.validarToken(refreshToken);
            String username = claims.getSubject();
            String userId = claims.get("userID", String.class);
            String employerId = claims.get("employerID", String.class);

            String newAccessToken = jwtUtils.gerarAccessToken(username, userId, employerId);

            return ResponseEntity.ok(
                LoginUserResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(refreshToken) // pode reusar o mesmo
                    .build()
            );
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
