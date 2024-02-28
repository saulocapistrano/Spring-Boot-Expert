package ifinit.com.vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "client")  // Adicionei a anotação @Table com o nome da tabela
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{field.name.required}")
    private String name;

    @NotEmpty(message = "{field.cpf.required}")
    @Column(length = 11)
    @CPF(message = "{field.cpf.invalid}")
    private String cpf;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Ordered> ordereds;

    public Client() {
    }

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
