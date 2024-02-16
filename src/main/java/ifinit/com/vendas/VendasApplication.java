package ifinit.com.vendas;

import ifinit.com.vendas.domain.entity.Client;
import ifinit.com.vendas.domain.entity.Product;
import ifinit.com.vendas.domain.repositories.ClientRepository;
import ifinit.com.vendas.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ProductRepository productRepository, @Autowired ClientRepository clientRepository){
        return  args ->{
            Product p1 = new Product(null, "IPhone 15 pro max" , new BigDecimal(10), " Smartphone");
            Product p2 = new Product(null," Mac M2" , new BigDecimal(7000), " MacBook");
            Product p3 = new Product(null, "Dell Inspirion i9 " ,new BigDecimal(5000), " Dell Inspirio i9 ");
            productRepository.save(p1);
            productRepository.save(p2);
            productRepository.save(p3);


            Client c1 = new Client("Saulo", null, "12345678901");
            Client c2 = new Client("Karla", null, "12345678901");
            clientRepository.save(c1);
            clientRepository.save(c2);

        };
    }

      public static void main(String[] args)
      {
        SpringApplication.run(VendasApplication.class, args);
    }

}
