package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.Product;
import ifinit.com.vendas.domain.repositories.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product newProduct){
        Optional<Product> extistingProduct = productRepository.findByNameLike(newProduct.getName());
        if (extistingProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(extistingProduct.get());
        } else{
            Product saveProduct = productRepository.save(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
        }
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


    @PutMapping(value = "{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product upProduct ){
        Optional<Product> existingProduct = productRepository.findById(id);
        if(existingProduct.isPresent()){
            Product productToUpdate = existingProduct.get();
            productToUpdate.setName(upProduct.getName());
            productRepository.save(productToUpdate);
            return ResponseEntity.ok(productToUpdate);
        }
        return ResponseEntity.notFound().build();
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
