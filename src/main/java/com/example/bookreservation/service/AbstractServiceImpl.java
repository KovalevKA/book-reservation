package com.example.bookreservation.service;

import com.example.bookreservation.mapper.AbstractMapper;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Lazy
public class AbstractServiceImpl<Entity, DTO,
    Repository extends R2dbcRepository<Entity, Long>,
    Mapper extends AbstractMapper<Entity, DTO>>
    implements AbstractService<Entity, DTO> {

    @Autowired
    private Repository repository;
    @Autowired
    private Mapper mapper;


    @Override
    public Flux<DTO> getAll() {
        return repository.findAll().map(mapper::toDTO);
    }

    @Override
    public Mono<DTO> getById(Long id) throws EntityNotFoundException {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Mono<DTO> create(DTO dto) {
        Entity entity = mapper.toEntity(dto);
        return repository.exists(
            Example.of(entity)
        )
            .flatMap(aBoolean ->
                aBoolean ? Mono.error(new EntityExistsException("Entity is exist"))
                    : repository.save(entity))
            .map(mapper::toDTO)
            ;
    }
}
