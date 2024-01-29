package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class ClientRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Client save(Client client){

        entityManager.persist(client);
        return client;
    }


    @Transactional
    public Client update(Client client){
    entityManager.merge(client);
    return client;
    }

    @Transactional
    public void delete(Client client){

        if(!entityManager.contains(client)){
            client = entityManager.merge(client);
        }
        entityManager.remove(client);

    }

    @Transactional
    public void delete(Integer id){
    Client client =  entityManager.find(Client.class, id);
        delete(client);
    }

    @Transactional(readOnly = true)
    public List<Client> getByName(String name){
    String jpql = "select c from Client c where c.name like :name";
     TypedQuery<Client> query  = entityManager.createQuery(jpql, Client.class);
     query.setParameter("name", "%"+name+"%");
     return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Client> returnAll(){
        return entityManager.createQuery("from Client", Client.class).getResultList();
    }


}