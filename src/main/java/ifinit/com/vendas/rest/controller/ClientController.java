package ifinit.com.vendas.rest.controller;

import ifinit.com.vendas.domain.entity.Client;
import ifinit.com.vendas.domain.repositories.ClientRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/clients")

public class ClientController {

    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping(value = "/{id}")

    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {

        Optional<Client> client = clientRepository.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
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

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Client> delete(@PathVariable Integer id) {
        Optional<Client> existingClient = clientRepository.findById(id);

        if (existingClient.isPresent()) {
            clientRepository.delete(existingClient.get());
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping(value = "{id}")
    public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody  Client upClient){

        Optional<Client> existingClient = clientRepository.findById(id);

        if (existingClient.isPresent()) {
            Client clientToUpdate = existingClient.get();
            clientToUpdate.setName(upClient.getName());
            clientToUpdate.setCpf(upClient.getCpf());
            clientToUpdate.setBirthDate(upClient.getBirthDate());
            clientToUpdate.setEmail(upClient.getEmail());
            clientToUpdate.setPhoneNumber(upClient.getPhoneNumber());
            clientToUpdate.setGender(upClient.getGender());
            clientToUpdate.setOrdereds(upClient.getOrdereds());

            clientToUpdate.setCreatedBy(upClient.getCreatedBy());
            clientToUpdate.setCreatedAt(upClient.getCreatedAt());
            clientToUpdate.setLastModifiedAt(upClient.getLastModifiedAt());
            clientToUpdate.setLastModifiedBy(upClient.getLastModifiedBy());

            clientToUpdate.setPassword(upClient.getPassword());
            clientToUpdate.setUsername(upClient.getUsername());

            clientRepository.save(clientToUpdate);
            return ResponseEntity.ok(clientToUpdate);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity findList( Client filter){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );


        Example example = Example.of(filter, matcher);
        List<Client> newList = clientRepository.findAll(example);
        return ResponseEntity.ok(newList);
    }

}
