package ifinit.com.vendas.rest.dto;

import jakarta.validation.constraints.NotEmpty;

public class OrderedItemDTO {

    @NotEmpty(message = "{field.items-ordered.mandatory}")
    private Integer product;
    @NotEmpty(message = "{field.quantity.required}")
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
