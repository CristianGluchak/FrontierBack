package br.com.frontier.rh_simplificado.employer.domain.entities;

import br.com.frontier.rh_simplificado.shared.Identifier;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
public class EmployerID extends Identifier<UUID> {

    protected EmployerID(UUID id){
        super(id);
    }

    public static EmployerID unique(){
       return new EmployerID(UUID.randomUUID());
    }

    public static EmployerID from(final String id){
        return new EmployerID(UUID.fromString(id));
    }

    public static EmployerID from(final UUID id){
        return new EmployerID(id);
    }

}
