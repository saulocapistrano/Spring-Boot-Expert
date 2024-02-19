package ifinit.com.vendas.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Ordered {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "client_name")
    private Client clientName;

    private LocalDate orderDate = LocalDate.now();

    // total order representa o somatório do valos dos items do pedido
    // esse valor deve ser calculado pelo sistema
    @Column(name = "total_order", precision = 20, scale = 2)
    private BigDecimal totalOrder;

    @OneToMany(mappedBy = "ordered")
    private List<OrderedItem> orderedItems ;


    public Integer getId() {
        return id;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClientName() {
        return clientName;
    }

    public void setClientName(Client clientName) {
        this.clientName = clientName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public String toString() {
        return "Ordered" +
                ", Client=" + client.getName() +
                ", Date =" + orderDate +
                ", Total=" + totalOrder +
                ", Items=" + orderedItems +
                '}';
    }
}
