package br.com.vendas.domain.repository;

import java.util.List;
import java.util.Optional;
import br.com.vendas.domain.entity.Client;
import br.com.vendas.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByClient(Client client);

    @Query(" select o from Order o left join fetch o.orderItemSet where o.id = :id ")
    Optional<Order> findByIdFetchItems(@Param("id") Integer id);
}
