package com.group7.clientsservice.controllers;

import com.group7.clientsservice.beans.PersonalClient;
import com.group7.clientsservice.repositories.PersonalClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients/personal")
@Slf4j
public class PersonalClientController {
    @Autowired
    private PersonalClientRepository repository;

    @GetMapping
    public Flux<PersonalClient> getAllPersonalClients(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<PersonalClient>> getPersonalClient(@PathVariable String id){
        return repository.findById(id)
                .map(client -> ResponseEntity.ok(client))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonalClient> savePersonalClient(@RequestBody PersonalClient client){
        return repository.insert(client);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<PersonalClient>> updatePersonalClient(@PathVariable String id,
                                                     @RequestBody PersonalClient client){
        return repository.findById(id)
                .flatMap(existingClient -> {
                    existingClient.setFirstName(client.getFirstName());
                    existingClient.setLastName(client.getLastName());
                    return repository.save(existingClient);
                })
                .map(updateClient -> ResponseEntity.ok(updateClient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deletePersonalClient(@PathVariable String id){
        return repository.findById(id)
                .flatMap(existingClient ->
                        repository.delete(existingClient)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllPersonalClients(){
        return repository.deleteAll();
    }
}
