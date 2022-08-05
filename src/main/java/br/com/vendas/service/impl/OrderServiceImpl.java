package br.com.vendas.service.impl;

import br.com.vendas.domain.entity.Client;
import br.com.vendas.domain.entity.Order;
import br.com.vendas.domain.entity.OrderItem;
import br.com.vendas.domain.entity.Product;
import br.com.vendas.domain.enums.OrderStatus;
import br.com.vendas.domain.repository.ClientRepository;
import br.com.vendas.domain.repository.OrderItemRepository;
import br.com.vendas.domain.repository.OrderRepository;
import br.com.vendas.domain.repository.ProductRepository;
import br.com.vendas.exception.BusinessRuleException;
import br.com.vendas.exception.OrderNotFoundException;
import br.com.vendas.rest.dto.OrderDTO;
import br.com.vendas.rest.dto.OrderInformationDTO;
import br.com.vendas.rest.dto.OrderItemDTO;
import br.com.vendas.rest.dto.OrderItemInformationDTO;
import br.com.vendas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order save(OrderDTO dto) {
        Integer clientId = dto.getClientId();
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow( () -> new BusinessRuleException("Invalid client code: " + clientId));

        Order order = new Order();
        order.setAmount(dto.getTotal());
        order.setOrderingDate(LocalDateTime.now());
        order.setClient(client);
        order.setStatus(OrderStatus.ORDERED);

        List<OrderItem> itemList = convertItems(order, dto.getItems());
        orderRepository.save(order);
        orderItemRepository.saveAll(itemList);
        order.setOrderItemSet(itemList);
        return order;
    }

    @Override
    public Optional<Order> getCompleteOrder(Integer id) {
        return orderRepository.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, OrderStatus orderStatus) {
        orderRepository
                .findById(id)
                .map( order -> {
                    order.setStatus(orderStatus);
                    return orderRepository.save(order);
                })
                .orElseThrow( () -> new OrderNotFoundException("Order not found."));
    }

    private List<OrderItem> convertItems(Order order, List<OrderItemDTO> items){
        if (items.isEmpty()){
            throw new BusinessRuleException("Order items not found.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer productId = dto.getProductId();
                    Product product = productRepository
                            .findById(productId)
                            .orElseThrow( () -> new BusinessRuleException("Invalid product code: " + productId));

                    OrderItem orderItem = new OrderItem();
                    orderItem.setAmount(dto.getAmount());
                    orderItem.setOrder(order);
                    orderItem.setProduct(product);
                    return orderItem;
                }).collect(Collectors.toList());
    }

    public OrderInformationDTO covert(Order order){
        return  OrderInformationDTO
                .builder()
                .code(order.getId())
                .orderDate(order.getOrderingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")))
                .cpf(order.getClient().getCpf())
                .clientName(order.getClient().getName())
                .total(order.getAmount())
                .status(order.getStatus().name())
                .items(covertList(order.getOrderItemSet()))
                .build();

    }

    private List<OrderItemInformationDTO> covertList(List<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItems)){
            return Collections.emptyList();
        }

        return orderItems.stream().map(
                orderItem -> OrderItemInformationDTO
                        .builder()
                        .productDescription(orderItem.getProduct().getDescription())
                        .unitPrice(orderItem.getProduct().getUnitPrice())
                        .amount(orderItem.getAmount())
                        .build()
        ).collect(Collectors.toList());
    }

}
