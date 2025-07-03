package br.com.frontier.rh_simplificado.employer.infrastructure.persistence.entities;

import br.com.frontier.rh_simplificado.employer.domain.entities.Employer;
import br.com.frontier.rh_simplificado.employer.domain.entities.EmployerID;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 11/05/2025
 */

@Entity
@Getter
@Setter
@Table(name = "employer")
public class EmployerJpaEntity {

    @Id
    @Column(name = "id_employer")
    private UUID id;

    @Column(name = "fantasyname")
    private String fantasyName;

    @Column(name = "razaosocial")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "email")
    private String email;

    public static EmployerJpaEntity from(final UUID id){
        EmployerJpaEntity orm = new EmployerJpaEntity();
        orm.setId(id);
        return orm;
    }

    public static EmployerJpaEntity from(final EmployerID id){
        EmployerJpaEntity orm = new EmployerJpaEntity();
        orm.setId(id.getValue());
        return orm;
    }


    public static EmployerJpaEntity from(final Employer dto){
        EmployerJpaEntity orm = new EmployerJpaEntity();
        orm.setId(dto.getId().getValue());
        orm.setRazaoSocial(dto.getRazaoSocial());
        orm.setFantasyName(dto.getNomeFantasia());
        orm.setCnpj(dto.getCnpj());
        orm.setEmail(dto.getEmail());

        return orm;
    }

    public Employer toDomain(){
        return Employer.builder()
                .id(EmployerID.from(id))
                .razaoSocial(razaoSocial)
                .nomeFantasia(fantasyName)
                .cnpj(cnpj)
                .email(email)
                .build();
    }
}
