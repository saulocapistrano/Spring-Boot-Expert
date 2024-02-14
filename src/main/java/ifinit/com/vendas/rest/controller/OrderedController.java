package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.Ordered;
import ifinit.com.vendas.rest.dto.OrderedDTO;
import ifinit.com.vendas.service.OrderedService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/ordereds")
public class OrderedController {
    private OrderedService orderedService;

    public OrderedController(OrderedService orderedService) {
        this.orderedService = orderedService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody OrderedDTO orderedDTO){
        Ordered ordered = orderedService.save(orderedDTO);
        return ordered.getId();
    }
}
