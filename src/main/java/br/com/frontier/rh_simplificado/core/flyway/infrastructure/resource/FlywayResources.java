package br.com.frontier.rh_simplificado.core.flyway.infrastructure.resource;

import br.com.frontier.rh_simplificado.core.flyway.infrastructure.queries.FlywayMigrationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@RestController
@RequestMapping("/admin/rh")
@RequiredArgsConstructor
public class FlywayResources {

    private final FlywayMigrationUseCase flywayMigrationUseCase;

    @PostMapping("/migrate")
    public String migrate(){
        flywayMigrationUseCase.migrate();
        return "Migração executada com sucesso!";
    }
}
