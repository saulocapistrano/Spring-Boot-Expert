package ifinit.com.vendas;

import ifinit.com.vendas.domain.entity.Client;
import ifinit.com.vendas.domain.entity.Ordered;
import ifinit.com.vendas.domain.repositories.ClientRepository;
import ifinit.com.vendas.domain.repositories.OrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository,
                                  @Autowired OrderedRepository orderedRepository)
    {
      return args -> {

          System.out.println("Save client.");
          Client  c1 = clientRepository.save(new Client("Saulo José"));
          Client  c2 = clientRepository.save(new Client("Karla Santana"));

          Ordered o1 = new Ordered();
          o1.setClient(c1);
          o1.setOrderDate(LocalDate.now());
          o1.setTotalOrder(BigDecimal.valueOf(100));

          orderedRepository.save(o1);


          ClientRepository client = clientRepository.findClientByFetchOrdereds(c1.getId());
          System.out.println(client);
          System.out.println(clientRepository.getReferenceById(c1.getId()));

          orderedRepository.findByClient(c1).forEach(System.out::println);

      };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
