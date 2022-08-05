package br.com.vendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    @NotEmpty(message = "{field.login.required}")
    private String login;
    @Column
    @NotEmpty(message = "{field.password.required}")
    private String senha;
    @Column
    private boolean admin;

}
