package br.com.vendas.rest.controller;

import br.com.vendas.domain.entity.Order;
import br.com.vendas.domain.enums.OrderStatus;
import br.com.vendas.rest.dto.OrderDTO;
import br.com.vendas.rest.dto.OrderInformationDTO;
import br.com.vendas.rest.dto.UpdateOrderStatusDTO;
import br.com.vendas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/pedido")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid OrderDTO dto){
        Order order = orderService.save(dto);
        return order.getId();
    }

    @GetMapping("{id}")
    public OrderInformationDTO getById( @PathVariable Integer id ){
        return orderService.getCompleteOrder(id)
                .map( p -> orderService.covert(p) )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus( @PathVariable Integer id, @RequestBody UpdateOrderStatusDTO statusDTO ){

        String newStatus = statusDTO.getNewStatus();
        orderService.updateStatus(id, OrderStatus.valueOf(newStatus));

    }

}
