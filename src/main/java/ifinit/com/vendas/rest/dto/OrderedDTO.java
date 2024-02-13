package ifinit.com.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedDTO {
    private Integer client;
    private BigDecimal total;
    private List<OrderedItemDTO> orderedItemDTOS;
}
