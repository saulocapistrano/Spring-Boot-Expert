package ifinit.com.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    public Client() {

    }

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Ordered> ordereds;


    public Client(String name, Integer id) {
        this.id = id;
        this.name = name;

    }

    public Client(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Ordered> getOrdereds() {
        return ordereds;
    }

    @JsonIgnore
    public void setOrdereds(List<Ordered> ordereds) {
        this.ordereds = ordereds;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
