package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.Product;
import ifinit.com.vendas.domain.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save( @RequestBody  @Valid Product product){
        return productRepository.save(product);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable @Valid Integer id, @RequestBody Product product){
        productRepository.findById(id).map( p ->{
            product.setId(p.getId());
            productRepository.save(product);
            return product;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product no existing"));

    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<Product> delete(@PathVariable Integer id){
        Optional<Product> extistingProduct = productRepository.findById(id);
        if (extistingProduct.isPresent()){
            productRepository.delete(extistingProduct.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Product getById(@PathVariable  Integer id){
        return productRepository
                .findById(id)
                .orElseThrow(
                        ()-> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Product no existing"));
    }


    @GetMapping
    public ResponseEntity findList(Product filter){

        Example example = Example.of(filter,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<Product> newList = productRepository.findAll(example);
        return ResponseEntity.ok(newList);

    }
}
