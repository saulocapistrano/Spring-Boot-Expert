package ifinit.com.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Field name is required")
    private String name;
    @NotEmpty(message = "Field cpf is required")
    @Column(length = 11)
    private String cpf;

    public Client() {

    }


    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Ordered> ordereds;



    public Client(String name, Integer id, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
