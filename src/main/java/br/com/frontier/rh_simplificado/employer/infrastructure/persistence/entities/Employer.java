package br.com.frontier.rh_simplificado.employer.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Cristian Gluchak <cjgc4002@gmail.com>
 * @since 11/05/2025
 */

@Entity
@Getter
@Setter
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employer")
    private Integer id;

    @Column(name = "fantasyname")
    private String fantasyName;

    @Column(name = "razaosocial")
    private String razaoSocial;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "email")
    private String email;
}
