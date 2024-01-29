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

          System.out.println("Save client.");
          Client c1 = new Client();
          c1.setName("Saulo José");
          clientRepository.save(c1);

          Client c2 = new Client();
          c2.setName("Francisco José");
          clientRepository.save(c2);

          Client c3 = new Client();
          c3.setName("Karla Capistrano");
          clientRepository.save(c3);

          Client c4 = new Client();
          c4.setName("Joaquin Capistrano");
          clientRepository.save(c4);

          System.out.println("List client.");
          List<Client> allClients = clientRepository.returnAll();
          allClients.forEach(System.out::println);

          System.out.println("Updated client.");
          allClients.forEach( c -> {c.setName(c.getName() + " refreshed");
          clientRepository.update(c);

           });


          allClients = clientRepository.returnAll();
          allClients.forEach(System.out::println);

          System.out.println("Get client by step character.");
          clientRepository.getByName("Sau").forEach(System.out::println);
          clientRepository.getByName("Capis").forEach(System.out::println);


          System.out.println("Delete character.");
          clientRepository.returnAll().forEach(c ->{
              clientRepository.delete(c);
          });

          allClients = clientRepository.returnAll();

          if (allClients.isEmpty()) {
            System.out.println("No have client");
          }else{
              allClients.forEach(System.out::println);
          }

      };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
