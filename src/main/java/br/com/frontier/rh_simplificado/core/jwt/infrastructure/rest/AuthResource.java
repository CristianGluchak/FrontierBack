package br.com.frontier.rh_simplificado.core.jwt.infrastructure.rest;

import br.com.frontier.rh_simplificado.core.jwt.infrastructure.dtos.CreateUserWithEnployerRequest;
import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerInput;
import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerUseCase;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.user.application.create.CreateUserInput;
import br.com.frontier.rh_simplificado.user.application.create.CreateUserUseCase;
import br.com.frontier.rh_simplificado.user.domain.entities.UserID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
