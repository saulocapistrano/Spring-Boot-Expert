package ifinit.com.vendas.service.impl;

import ifinit.com.vendas.domain.entity.Client;
import ifinit.com.vendas.domain.entity.Ordered;
import ifinit.com.vendas.domain.entity.OrderedItem;
import ifinit.com.vendas.domain.entity.Product;
import ifinit.com.vendas.domain.repositories.ClientRepository;
import ifinit.com.vendas.domain.repositories.OrderedItemRepository;
import ifinit.com.vendas.domain.repositories.OrderedRepository;
import ifinit.com.vendas.domain.repositories.ProductRepository;
import ifinit.com.vendas.exception.RulerManagerException;
import ifinit.com.vendas.rest.dto.OrderedDTO;
import ifinit.com.vendas.rest.dto.OrderedItemDTO;
import ifinit.com.vendas.service.OrderedService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class OrderedServiceImpl implements OrderedService {
    private final OrderedRepository orderedRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderedItemRepository orderedItemRepository;

    public OrderedServiceImpl(OrderedRepository orderedRepository, ClientRepository clientRepository, ProductRepository productRepository, OrderedItemRepository orderedItemRepository) {
        this.orderedRepository = orderedRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderedItemRepository = orderedItemRepository;
    }
    @Override
    @Transactional
    public Ordered save(OrderedDTO orderedDTO) {
        Integer idClient =  orderedDTO.getClient();

        Client client = clientRepository.findById(idClient)
                .orElseThrow(()-> new RulerManagerException("Invalid code client: "+idClient));

        Ordered ordered = new Ordered();
        ordered.setTotalOrder(orderedDTO.getTotal());
        ordered.setOrderDate(LocalDate.now());
        ordered.setClient(client);

        List<OrderedItem> orderedItems = convetItems(ordered, orderedDTO.getOrderedItemDTOS());
        orderedRepository.save(ordered);
        orderedItemRepository.saveAll(orderedItems);
        ordered.setOrderedItems(orderedItems);
        return ordered;
    }

    private List<OrderedItem> convetItems(Ordered ordered, List<OrderedItemDTO> orderedItemDTOS){
        if(orderedItemDTOS.isEmpty()){
            throw new RulerManagerException("Impossible create ordered with inexisting itens.");
        }

        return orderedItemDTOS.stream().map(dtoItemOrdered ->{
                Integer idProduct = dtoItemOrdered.getProduct();
                Product product = productRepository.findById(idProduct)
                        .orElseThrow(
                                ()-> new RulerManagerException("Invalid code product: "+idProduct));

                OrderedItem orderedItem = new OrderedItem();
                orderedItem.setAmount(dtoItemOrdered.getQuantity());
                orderedItem.setOrdered(ordered);
                orderedItem.setProduct(product);
                return orderedItem;
        }).collect(Collectors.toList());
    }
}
