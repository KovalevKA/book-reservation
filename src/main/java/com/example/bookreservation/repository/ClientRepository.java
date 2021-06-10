package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Client;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ClientRepository extends R2dbcRepository<Client, Long> {

    Mono<Client> findByClientId(Long id);

}
