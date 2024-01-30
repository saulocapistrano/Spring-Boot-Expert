package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.Client;
import ifinit.com.vendas.domain.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered, Integer> {

    List<Ordered> findByClient(Client client);
}
