package com.group7.clientsservice;

import com.group7.clientsservice.beans.BusinessClient;
import com.group7.clientsservice.beans.Client;
import com.group7.clientsservice.beans.PersonalClient;
import com.group7.clientsservice.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ClientsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientsServiceApplication.class, args);
    }

    /*@Bean
    CommandLineRunner init(ClientRepository repository) {
        return args -> {
            Flux<Client> productFlux = Flux.just(
                            new PersonalClient(null, "Renato", 71318995L, "renatogaraym@gmail.com"),
                            new BusinessClient(null, "Vanessa", 73682852L))
                    .flatMap(repository::save);

            productFlux.thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }*/

}
