package br.com.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 100)
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "{field.cpf.required}")
    @CPF(message = "{field.cpf.invalid}")
    private String cpf;

    @JsonIgnore
    @OneToMany( mappedBy = "client", fetch = FetchType.LAZY )
    private List<Order> orderList;

}
