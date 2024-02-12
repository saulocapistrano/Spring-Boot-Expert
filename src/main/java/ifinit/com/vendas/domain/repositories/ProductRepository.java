package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * from Product p where p.name like :name", nativeQuery = true)
    Optional<Product> findByNameLike(@Param("name") String name);
}
