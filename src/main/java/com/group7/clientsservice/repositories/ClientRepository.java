package com.group7.clientsservice.repositories;

import com.group7.clientsservice.beans.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<Client,String> {
}
