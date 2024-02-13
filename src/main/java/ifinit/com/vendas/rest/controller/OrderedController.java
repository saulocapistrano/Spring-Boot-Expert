package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.service.OrderedService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ordereds")
public class OrderedController {
    private OrderedService orderedService;

    public OrderedController(OrderedService orderedService) {
        this.orderedService = orderedService;
    }
}
