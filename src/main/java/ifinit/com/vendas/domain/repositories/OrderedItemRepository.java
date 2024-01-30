package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {
}
