package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import javax.persistence.EntityExistsException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AbstractService<Entity extends AbstractEntity, DTO extends AbstractDTO> {

    Flux<DTO> getAll();

    Mono<DTO> getById(Long id);

    void deleteById(Long id);

    Mono<DTO> create(DTO dto);

    Mono<DTO> editById(Long id, DTO dto);

    default Mono<Entity> getByName(String name) {
        throw new EntityExistsException("Can't found by name");
    }

    default Flux<DTO> getByNameLike(String string) {
        throw new EntityExistsException("Can't found by name like");
    }
}
