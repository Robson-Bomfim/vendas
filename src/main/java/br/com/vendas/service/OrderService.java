package br.com.vendas.service;

import br.com.vendas.domain.entity.Order;
import br.com.vendas.domain.enums.OrderStatus;
import br.com.vendas.rest.dto.OrderDTO;
import br.com.vendas.rest.dto.OrderInformationDTO;

import java.util.Optional;

public interface OrderService {
    Order save(OrderDTO dto );
    OrderInformationDTO covert(Order order);
    Optional<Order> getCompleteOrder(Integer id);
    void updateStatus(Integer id, OrderStatus orderStatus);
}
