package ifinit.com.vendas.domain.repositories;

import ifinit.com.vendas.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ClientRepository {

    private static String INSERT = "insert into client (name) values (?)";
    private static String SELECT_ALL = "select * from client";

    private static String UPDATE = "update client set name =? where id = ? ";

    private static String DELETE = "delete from client  where id = ? ";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Client save(Client client){

        jdbcTemplate.update(INSERT, new Object[]{client.getName()} );
        return client;
    }


    public Client update(Client client){

        jdbcTemplate.update(UPDATE, new Object[]{client.getName(), client.getId()});
        return client;
    }

    public Client delete(Client client){
        delete(client.getId());
    return null;
    }

    public Client delete(Integer id){
    jdbcTemplate.update(DELETE, new Object[]{id});
        return null;
    }

    public List<Client> getByName(String name){
        return jdbcTemplate.query(SELECT_ALL.concat(" where name like ?"),
                new Object[]{"%"+name+"%"},
                returnMaper());
    }

    public List<Client> returnAll(){
        return jdbcTemplate.query(SELECT_ALL, returnMaper());
    }

    private static RowMapper<Client> returnMaper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet rs, int i) throws SQLException {

                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                return new Client(name, id);
            }
        };
    }
}
