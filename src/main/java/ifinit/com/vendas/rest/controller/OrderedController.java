package ifinit.com.vendas.rest.controller;


import ifinit.com.vendas.domain.entity.Ordered;
import ifinit.com.vendas.domain.entity.OrderedItem;
import ifinit.com.vendas.rest.dto.InfoItemOrderedDTO;
import ifinit.com.vendas.rest.dto.InfoOrderedDTO;
import ifinit.com.vendas.rest.dto.OrderedDTO;
import ifinit.com.vendas.service.OrderedService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InfoOrderedDTO getById(@PathVariable Integer id){
        return orderedService
                .receiveCompletOrdered(id)
                .map(p -> convertTo(p))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordered not ..."));
    }

    private InfoOrderedDTO convertTo(Ordered ordered){
            return InfoOrderedDTO
                    .builder()
                    .code(ordered.getId())
                    .orderedDate(ordered.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .nameClient(ordered.getClient().getName())
                    .cpfClient(ordered.getClient().getCpf())
                    .total(ordered.getTotalOrder())
                    .items(convert(ordered.getOrderedItems()))
                    .build();

    }


    private List<InfoItemOrderedDTO> convert(List<OrderedItem> orderedItems){

    if(CollectionUtils.isEmpty(orderedItems)){
        return Collections.emptyList();
    }
    return orderedItems.stream()
            .map(item -> InfoItemOrderedDTO
                    .builder().descriptionItem(item.getProduct().getDescription())
                    .unitPrice(item.getProduct().getPrice())
                    .quantity(item.getQuantity())
                    .build()
    ).collect(Collectors.toList());

    }


}
