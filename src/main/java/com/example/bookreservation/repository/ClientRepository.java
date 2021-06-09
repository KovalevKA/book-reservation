package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ClientRepository extends ReactiveCrudRepository<Client, Long> {

    Mono<Client> findByClientId(Long id);

}
