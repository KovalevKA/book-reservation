package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {

}
