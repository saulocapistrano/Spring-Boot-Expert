package ifinit.com.vendas.rest.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public class InfoOrderedDTO {

    private Integer code;
    private String description;
    private String nameClient;
    private String cpfClient;
    private String orderedDate;
    private BigDecimal total;




    private List<InfoItemOrderedDTO> items;

    public InfoOrderedDTO(Integer code, String description, String nameClient, String cpfClient, String orderedDate, BigDecimal total, List<InfoItemOrderedDTO> items) {
        this.code = code;
        this.description = description;
        this.nameClient = nameClient;
        this.cpfClient = cpfClient;
        this.orderedDate = orderedDate;
        this.total = total;
        this.items = items;
    }

    public InfoOrderedDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<InfoItemOrderedDTO> getItems() {
        return items;
    }

    public void setItems(List<InfoItemOrderedDTO> items) {
        this.items = items;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }
}
