package br.com.frontier.rh_simplificado.employer.infrastructure.rest;

import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerUseCase;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.employer.infrastructure.dtos.CreateEmployerRequest;
import br.com.frontier.rh_simplificado.employer.infrastructure.dtos.GetEmployerByIDResponse;
import br.com.frontier.rh_simplificado.employer.infrastructure.queries.GetEmployerByIdOutput;
import br.com.frontier.rh_simplificado.employer.infrastructure.queries.GetEmployerByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/employer")
@RequiredArgsConstructor
public class EmployerResource {

    private final CreateEmployerUseCase createEmployerUseCase;

    private final GetEmployerByIdUseCase getEmployerByIdUseCase;

    @PostMapping
    public EmployerID create(@RequestBody @Valid CreateEmployerRequest request){
        return createEmployerUseCase.execute(CreateEmployerRequest.from(request));
    }

    @GetMapping("/{id}")
    public GetEmployerByIDResponse get(@PathVariable UUID id){
        final GetEmployerByIdOutput output = getEmployerByIdUseCase.execute(EmployerID.from(id));
        return GetEmployerByIDResponse.from(output);
    }

}
