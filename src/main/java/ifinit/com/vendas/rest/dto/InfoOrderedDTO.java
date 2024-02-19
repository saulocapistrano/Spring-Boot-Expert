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
    private String status;
    private BigDecimal total;


    private List<InfoItemOrderedDTO> items;



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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }
}
