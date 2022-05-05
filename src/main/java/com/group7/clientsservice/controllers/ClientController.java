package com.group7.clientsservice.controllers;

import com.group7.clientsservice.beans.Client;
import com.group7.clientsservice.factories.ClientFactory;
import com.group7.clientsservice.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public Flux<Client> getAllClients(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Client>> getClient(@PathVariable String id){
        return repository.findById(id)
                .map(client -> ResponseEntity.ok(ClientFactory.create(client)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Client> saveClient(@RequestBody Client client){
        return repository.save(ClientFactory.create(client));
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Client>> updateClient(@PathVariable String id,
                                                       @RequestBody Client client){
        return repository.findById(id)
                .flatMap(existingClient -> {
                    existingClient.setName(client.getName());
                    return repository.save(ClientFactory.create(existingClient));
                })
                .map(updateClient -> ResponseEntity.ok(updateClient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable String id){
        return repository.findById(id)
                .flatMap(existingClient ->
                        repository.delete(existingClient)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllClients(){
        return repository.deleteAll();
    }

}
