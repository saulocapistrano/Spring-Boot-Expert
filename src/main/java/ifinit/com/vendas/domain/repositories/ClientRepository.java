package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "select * from Client c where c.name like '%:name%'", nativeQuery = true)
    List<Client> findByNameLike(@Param("name") String name);

    @Query("delete from Client c where c.name =:name")
    @Modifying
    void deleteClientsByName(String name);
    boolean existsByName(String name);

    @Query("select  c from Client c left join fetch c.ordereds where c.id =:id ")
    ClientRepository findClientByFetchOrdereds(@Param( "id") Integer id);
}