package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "select * from Client c where c.name like :name", nativeQuery = true)
    Optional<Client> findByNameLike(@Param("name") String name);

    @Query("delete from Client c where c.name = :name")
    @Modifying
    void deleteClientsByName(@Param("name") String name);

    boolean existsByName(String name);

    @Query("select c from Client c left join fetch c.ordereds where c.id = :id")
    Client findClientByFetchOrdereds(@Param("id") Integer id);
}
