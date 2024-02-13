package ifinit.com.vendas.service.impl;

import ifinit.com.vendas.domain.repositories.OrderedRepository;
import ifinit.com.vendas.service.OrderedService;
import org.springframework.stereotype.Service;

@Service
public class OrderedServiceImpl implements OrderedService {
    private OrderedRepository orderedRepository;

    public OrderedServiceImpl(OrderedRepository orderedRepository) {
        this.orderedRepository = orderedRepository;
    }
}
