package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.Client;
import ifinit.com.vendas.domain.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

  @GetMapping(value = "/api/client/{id}")
  @ResponseBody
  public ResponseEntity<Client> getClientById(@PathVariable  Integer id){

        Optional<Client> client = clientRepository.findById(id);

        if(client.isPresent()){
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
   }

  @PostMapping(value = "/api/client")
  @ResponseBody
  public ResponseEntity<Client> saveClient(@RequestBody Client newClient){
      Optional<Client> existingClient = clientRepository.findByNameLike(newClient.getName());

      if(existingClient.isPresent()){
          return ResponseEntity.status(HttpStatus.CONFLICT)
                  .body(existingClient.get());
      }else{
          Client savedClient = clientRepository.save(newClient);
          return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);

      }

  }


}
