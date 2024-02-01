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
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/api/client")
    @ResponseBody
    public ResponseEntity<Client> saveClient(@RequestBody Client newClient) {
        Optional<Client> existingClient = clientRepository.findByNameLike(newClient.getName());

        if (existingClient.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(existingClient.get());
        } else {
            Client savedClient = clientRepository.save(newClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);

        }

    }

    @DeleteMapping(value = "/api/client/remove/{id}")
    @ResponseBody
    public ResponseEntity<Client> delete(@PathVariable Integer id) {
        Optional<Client> existingClient = clientRepository.findById(id);

        if (existingClient.isPresent()) {
            clientRepository.delete(existingClient.get());
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping(value = "/api/client/{id}")
    @ResponseBody
    public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody  Client upClient){

        Optional<Client> existingClient = clientRepository.findById(id);

        if (existingClient.isPresent()) {
            Client clientToUpdate = existingClient.get();
            clientToUpdate.setName(upClient.getName());
            clientRepository.save(clientToUpdate);
            return ResponseEntity.ok(clientToUpdate);
        }
        return ResponseEntity.notFound().build();
    }

//    @PutMapping(value = "/api/client/{id}")
//    @ResponseBody
//    public ResponseEntity update(@PathVariable Integer id, @RequestBody  Client upClient){
//
//        return clientRepository.findById(id).map(existingClient -> {
//            upClient.setId(existingClient.getId());
//            clientRepository.save(upClient);
//            return ResponseEntity.noContent().build();
//        }).orElseGet(()-> ResponseEntity.notFound().build());
//
// código implementado pelo professor, aparentemente tem melhor performance que o método que eu criei
//    }


}
