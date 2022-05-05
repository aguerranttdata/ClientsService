package com.group7.clientsservice.repositories;

import com.group7.clientsservice.beans.BusinessClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BusinessClientRepository extends ReactiveMongoRepository<BusinessClient,String> {
}
