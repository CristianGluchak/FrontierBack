package br.com.frontier.rh_simplificado.payroll.infrastructure.rest;

import br.com.frontier.rh_simplificado.employer.application.create.CreateEmployerUseCase;
import br.com.frontier.rh_simplificado.employer.application.update.UpdateEmployerUseCase;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import br.com.frontier.rh_simplificado.payroll.infrastructure.dtos.CreatePayrollRequest;
import br.com.frontier.rh_simplificado.payroll.infrastructure.queries.GetPayrollByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/payroll")
@RequiredArgsConstructor
public class PayrollResource {

    private final CreateEmployerUseCase createEmployerUseCase;

    private final GetPayrollByIdUseCase getEmployerByIdUseCase;

    private final UpdateEmployerUseCase updateEmployerUseCase;

    @PostMapping("/employer/{id}")
    public EmployerID calculateSigle(@RequestBody @Valid CreatePayrollRequest request){
        return createEmployerUseCase.execute(null);
    }




}
