package ifinit.com.vendas;

import ifinit.com.vendas.domain.entity.Client;
import ifinit.com.vendas.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository){
      return args -> {
          Client c1 = new Client();
          c1.setName("Saulo");
          clientRepository.save(c1);

          Client c2 = new Client();
          c2.setName("José");
          clientRepository.save(c2);

          Client c3 = new Client();
          c3.setName("Neco");
          clientRepository.save(c3);

          Client c4 = new Client();
          c4.setName("Capistrano");
          clientRepository.save(c4);


          List<Client> allClients = clientRepository.returnAll();
          allClients.forEach(System.out::println);
      };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
