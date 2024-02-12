package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.Product;
import ifinit.com.vendas.domain.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody()
    public ResponseEntity<Product> getProductById(@PathVariable  Integer id){

        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }



}
