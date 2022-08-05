package br.com.vendas.rest.dto;

import br.com.vendas.validation.NotEmptyList;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    @NotNull(message = "{field.client-code.required}")
    private Integer clientId;

    @NotNull(message = "{field.total-order.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.order-items.required}")
    private List<OrderItemDTO> items;

}
