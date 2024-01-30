package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {


}
