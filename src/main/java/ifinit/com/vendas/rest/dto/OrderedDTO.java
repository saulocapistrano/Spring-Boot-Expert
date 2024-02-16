package ifinit.com.vendas.rest.dto;

import ifinit.com.vendas.domain.entity.Client;

import java.math.BigDecimal;
import java.util.List;


public class OrderedDTO {
    private Integer client;
    private BigDecimal total;
    private Client name;
    private List<OrderedItemDTO> orderedItemList;


    public OrderedDTO(){

    }
    public OrderedDTO(Integer client, BigDecimal total, List<OrderedItemDTO> orderedItemList, Client name) {
        this.client = client;
        this.name = name;
        this.total = total;
        this.orderedItemList = orderedItemList;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Client getName() {
        return name;
    }

    public void setName(Client name) {
        this.name = name;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderedItemDTO> getOrderedItemList() {
        return orderedItemList;
    }

    public void setOrderedItemList(List<OrderedItemDTO> orderedItemList) {
        this.orderedItemList = orderedItemList;
    }

//    public BigDecimal calculatorAmount(BigDecimal amount) {
//
//        if (orderedItemList == null|| orderedItemList.isEmpty()) {
//            throw new RulerManagerException("No have items to provide calculo.");
//        }
//        BigDecimal calc = orderedItemList.stream()
//                .map(OrderedItemDTO::getAmount)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        return calc;
//    }

}
