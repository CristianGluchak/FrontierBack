package br.com.frontier.rh_simplificado.payroll.domain.entities;

import br.com.frontier.rh_simplificado.shared.Identifier;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 10/05/2025
 */
public class PayrollID extends Identifier<UUID> {

    protected PayrollID(UUID id){
        super(id);
    }

    public static PayrollID unique(){
       return new PayrollID(UUID.randomUUID());
    }

    public static PayrollID from(final String id){
        return new PayrollID(UUID.fromString(id));
    }

    public static PayrollID from(final UUID id){
        return new PayrollID(id);
    }

}
