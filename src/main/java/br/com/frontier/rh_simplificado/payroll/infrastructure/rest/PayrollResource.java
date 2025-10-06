package br.com.frontier.rh_simplificado.payroll.infrastructure.rest;

import br.com.frontier.rh_simplificado.core.jwt.AuthenticatedUser;
import br.com.frontier.rh_simplificado.employee.domain.entities.EmployeeID;
import br.com.frontier.rh_simplificado.payroll.application.calculate.CalculatePayrollInput;
import br.com.frontier.rh_simplificado.payroll.application.calculate.CalculatePayrollUseCase;
import br.com.frontier.rh_simplificado.payroll.application.calculateall.CalculateAllPayrollInput;
import br.com.frontier.rh_simplificado.payroll.application.calculateall.CalculateAllPayrollUseCase;
import br.com.frontier.rh_simplificado.payroll.domain.entities.PayrollID;
import br.com.frontier.rh_simplificado.payroll.infrastructure.dtos.GetPayrollByIDResponse;
import br.com.frontier.rh_simplificado.payroll.infrastructure.queries.GetPayrollByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 12/05/2025
 */

@RestController
@RequestMapping(value = "/payroll")
@RequiredArgsConstructor
public class PayrollResource {

    private final CalculatePayrollUseCase calculatePayrollUseCase;

    private final CalculateAllPayrollUseCase calculateAllPayrollUseCase;

    private final GetPayrollByIdUseCase getPayrollByIDUseCase;

    private final AuthenticatedUser loggedUser;

    @PostMapping("/employee/{id}")
    public ResponseEntity<Void> calculateSigle(@PathVariable(name = "id") UUID id) {

        calculatePayrollUseCase.execute(CalculatePayrollInput.builder()
            .employerID(loggedUser.getEmployerID())
            .employeeID(EmployeeID.from(id))
            .build());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee/all")
    public ResponseEntity<Void> calculateAll() {
        calculateAllPayrollUseCase.execute(CalculateAllPayrollInput.builder()
            .employerID(loggedUser.getEmployerID())
            .build());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public GetPayrollByIDResponse getByID(@PathVariable(name = "id") UUID id) {
        return GetPayrollByIDResponse.from(getPayrollByIDUseCase.execute(PayrollID.from(id),
            loggedUser.getEmployerID()));
    }


}
