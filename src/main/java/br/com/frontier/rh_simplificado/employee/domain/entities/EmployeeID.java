package br.com.frontier.rh_simplificado.employee.domain.entities;

import br.com.frontier.rh_simplificado.shared.Identifier;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
public class EmployeeID extends Identifier<UUID> {

    protected EmployeeID( UUID id){
        super(id);
    }

    public static EmployeeID unique(){
       return new EmployeeID(UUID.randomUUID());
    }

    public static EmployeeID from(final String id){
        return new EmployeeID(UUID.fromString(id));
    }

    public static EmployeeID from(final UUID id){
        return new EmployeeID(id);
    }

}
