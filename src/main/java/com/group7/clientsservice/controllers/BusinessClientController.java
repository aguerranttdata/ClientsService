package com.group7.clientsservice.controllers;

import com.group7.clientsservice.beans.BusinessClient;
import com.group7.clientsservice.beans.BusinessClient;
import com.group7.clientsservice.repositories.BusinessClientRepository;
import com.group7.clientsservice.repositories.BusinessClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients/business")
@Slf4j
public class BusinessClientController {
    @Autowired
    private BusinessClientRepository repository;

    @GetMapping
    public Flux<BusinessClient> getAllBusinessClients(){
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<BusinessClient>> getBusinessClient(@PathVariable String id){
        return repository.findById(id)
                .map(client -> ResponseEntity.ok(client))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BusinessClient> saveBusinessClient(@RequestBody BusinessClient client){
        return repository.insert(client);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<BusinessClient>> updateBusinessClient(@PathVariable String id,
                                                                     @RequestBody BusinessClient client){
        return repository.findById(id)
                .flatMap(existingClient -> {
                    existingClient.setSocialName(client.getSocialName());
                    return repository.save(existingClient);
                })
                .map(updateClient -> ResponseEntity.ok(updateClient))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteBusinessClient(@PathVariable String id){
        return repository.findById(id)
                .flatMap(existingClient ->
                        repository.delete(existingClient)
                                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllBusinessClients(){
        return repository.deleteAll();
    }
}
