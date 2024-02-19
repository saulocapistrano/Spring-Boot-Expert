package ifinit.com.vendas.service;

import ifinit.com.vendas.domain.entity.Ordered;
import ifinit.com.vendas.rest.dto.OrderedDTO;

import java.util.Optional;

public interface OrderedService {
    Ordered save(OrderedDTO dto);

    Optional<Ordered> receiveCompletOrdered(Integer id);


}
