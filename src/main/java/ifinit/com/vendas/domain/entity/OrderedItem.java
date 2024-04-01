package ifinit.com.vendas.domain.entity;

import javax.persistence.*;

@Entity
public class OrderedItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Ordered ordered;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity ;
    }

    @Override
    public String toString() {
        return "Ordered Item{" +
                "rdered=" + ordered +
                ", product=" + product.getName() +
                ", qauntity=" + quantity +
                '}';
    }
}
