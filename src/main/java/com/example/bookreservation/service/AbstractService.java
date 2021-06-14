package com.example.bookreservation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AbstractService<Entity, DTO> {

    Flux<DTO> getAll();

    Mono<DTO> getById(Long id);

    void deleteById(Long id);

    Mono<DTO> create(DTO dto);

    default Mono<DTO> editById(Long id, DTO dto) {
        throw new AbstractMethodError("Must be override");
    }

    default Mono<Entity> getByName(String name) {
        throw new AbstractMethodError("Must be override");
    }

    default Flux<DTO> getByNameLike(String string) {
        throw new AbstractMethodError("Must be override");
    }

    default Flux<DTO> getByBookId (Long id){
        throw new AbstractMethodError("Must be override");
    }
}
