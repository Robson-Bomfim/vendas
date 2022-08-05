package br.com.vendas.rest.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDTO {

    private Integer productId;
    private Integer amount;

}
