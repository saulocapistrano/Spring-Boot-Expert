package ifinit.com.vendas.domain.entity;

import jakarta.persistence.*;

@Entity
public class OrderedItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Ordered ordered;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ordered getOrdered() {
        return ordered;
    }

    public void setOrdered(Ordered ordered) {
        this.ordered = ordered;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ordered Item{" +
                "rdered=" + ordered +
                ", product=" + product.getName() +
                ", amount=" + amount +
                '}';
    }
}
