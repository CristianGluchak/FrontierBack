package br.com.frontier.rh_simplificado.core.flyway.infrastructure.queries;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 18/05/2025
 */
@Service
@RequiredArgsConstructor
public class FlywayMigrationUseCase {

    private final Flyway flyway;

    public void migrate(){
        flyway.migrate();
    }

}
