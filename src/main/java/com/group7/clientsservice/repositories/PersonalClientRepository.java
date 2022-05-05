package com.group7.clientsservice.repositories;

import com.group7.clientsservice.beans.PersonalClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonalClientRepository extends ReactiveMongoRepository<PersonalClient,String> {
}
