package br.com.vendas.domain.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "{field.name.required}")
    private String name;

    @Column(name = "description")
    @NotEmpty(message = "{field.description.required}")
    private String description;

    @Column(name = "unit_price")
    @NotNull(message = "{field.price.required}")
    private BigDecimal unitPrice;

}
