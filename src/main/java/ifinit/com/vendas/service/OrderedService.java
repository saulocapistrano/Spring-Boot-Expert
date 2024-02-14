package ifinit.com.vendas.service;

import ifinit.com.vendas.domain.entity.Ordered;
import ifinit.com.vendas.rest.dto.OrderedDTO;

public interface OrderedService {
    Ordered save(OrderedDTO orderedDTO);
}
