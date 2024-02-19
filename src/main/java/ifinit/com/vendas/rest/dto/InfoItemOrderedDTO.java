package ifinit.com.vendas.rest.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class InfoItemOrderedDTO {
    private String descriptionItem;
    private BigDecimal unitPrice;
    private Integer quantity;


    public InfoItemOrderedDTO() {
    }

    public InfoItemOrderedDTO(String descriptionItem, BigDecimal unitPrice, Integer quantity) {
        this.descriptionItem = descriptionItem;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getDescriptionItem() {
        return descriptionItem;
    }

    public void setDescriptionItem(String descriptionItem) {
        this.descriptionItem = descriptionItem;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
