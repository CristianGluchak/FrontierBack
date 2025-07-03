package br.com.frontier.rh_simplificado.employer.infrastructure.rest;

import br.com.frontier.rh_simplificado.employee.infrastructure.dtos.UpdateEmployeeRequest;
import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerUseCase;
import br.com.frontier.rh_simplificado.employer.application.update.UpdateEmployerUseCase;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.employer.infrastructure.dtos.CreateEmployerRequest;
import br.com.frontier.rh_simplificado.employer.infrastructure.dtos.GetEmployerByIDResponse;
import br.com.frontier.rh_simplificado.employer.infrastructure.dtos.UpdateEmployerRequest;
import br.com.frontier.rh_simplificado.employer.infrastructure.queries.GetEmployerByIdOutput;
import br.com.frontier.rh_simplificado.employer.infrastructure.queries.GetEmployerByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final UpdateEmployerUseCase updateEmployerUseCase;

    @PostMapping
    public EmployerID create(@RequestBody @Valid CreateEmployerRequest request){
        return createEmployerUseCase.execute(CreateEmployerRequest.from(request));
    }

    @GetMapping("/{id}")
    public GetEmployerByIDResponse get(@PathVariable UUID id){
        final GetEmployerByIdOutput output = getEmployerByIdUseCase.execute(EmployerID.from(id));
        return GetEmployerByIDResponse.from(output);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(name = "id") UUID id, @RequestBody @Valid UpdateEmployerRequest request) {
        updateEmployerUseCase.execute(UpdateEmployerRequest.from(request, id));
        return ResponseEntity.noContent().build();
    }
}
