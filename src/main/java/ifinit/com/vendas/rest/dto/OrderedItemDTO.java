package ifinit.com.vendas.rest.dto;

public class OrderedItemDTO {
    private Integer product;
    private Integer quantity;

    public OrderedItemDTO() {

    }
    public OrderedItemDTO(Integer product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
