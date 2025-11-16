package br.com.frontier.user.infrastructure.rest;

import br.com.frontier.core.jwt.AuthenticatedUser;
import br.com.frontier.user.application.create.CreateUserInput;
import br.com.frontier.user.application.create.CreateUserUseCase;
import br.com.frontier.user.application.update.UpdateUserUseCase;
import br.com.frontier.user.domain.entities.UserID;

import br.com.frontier.user.infrastructure.dtos.CreateUserRequest;
import br.com.frontier.user.infrastructure.dtos.FindAllUserResponse;
import br.com.frontier.user.infrastructure.dtos.GetUserByIDResponse;
import br.com.frontier.user.infrastructure.dtos.UpdateUserRequest;
import br.com.frontier.user.infrastructure.queries.findAll.FindAllUserUseCase;
import br.com.frontier.user.infrastructure.queries.getbyid.GetUserByIdOutput;
import br.com.frontier.user.infrastructure.queries.getbyid.GetUserByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserResource {

    private final CreateUserUseCase createUserUseCase;

    private final UpdateUserUseCase updateUserUseCase;

    private final GetUserByIdUseCase getUserByIdUseCase;

    private final AuthenticatedUser loggedUser;

    private final FindAllUserUseCase findAllUserUseCase;


    @PostMapping
    public UserID create(@RequestBody @Valid CreateUserRequest request) {

        CreateUserInput input = CreateUserInput.builder()
            .name(request.getName())
            .password(request.getPassword())
            .email(request.getEmail())
            .employerID(loggedUser.getEmployerID())
            .build();

        return createUserUseCase.execute(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id,
        @RequestBody @Valid UpdateUserRequest request) {
        updateUserUseCase.execute(UpdateUserRequest.from(request, id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public GetUserByIDResponse get(@PathVariable UUID id) {
        final GetUserByIdOutput output = getUserByIdUseCase.execute(UserID.from(id));
        return GetUserByIDResponse.from(output);
    }

    @GetMapping
    @Operation(summary = "List users (paginated, filter by name, implicit employer ID)")
    public Page<FindAllUserResponse> findAll(
        @RequestParam(required = false) String name,
        Pageable pageable
    ) {

        return findAllUserUseCase.execute(name, loggedUser.getEmployerID().getValue(), pageable)
            .map(FindAllUserResponse::from);
    }

}
