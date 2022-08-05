package br.com.vendas.domain.repository;

import br.com.vendas.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>  {
}
