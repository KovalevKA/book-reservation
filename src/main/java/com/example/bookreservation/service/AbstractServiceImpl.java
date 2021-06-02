package com.example.bookreservation.service;

import com.example.bookreservation.mapper.AbstractMapper;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Lazy
public class AbstractServiceImpl<Entity, DTO,
    Repository extends ReactiveCrudRepository<Entity, Long>,
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
        return repository.findAll()
            .switchOnFirst((signal, entityFlux) -> {
                if (signal.hasValue()) {
                    return entityFlux.filter(entity -> entity.equals(mapper.toEntity(dto)));
                }
                return entityFlux;
            })
            .next()
            .switchIfEmpty(repository.save(mapper.toEntity(dto)))
            .map(mapper::toDTO)
            ;
    }

    /*TODO: переписать/исправить*/
    @Override
    public Mono<DTO> editById(Long id, DTO dto) {
        /*return repository.save(
            repository.findById(id)
                .map(entity -> {
                    Arrays.stream(dto.getClass().getDeclaredFields()).forEach(
                        field -> {
                            field.setAccessible(true);
                            Field saveField = entity.getClass().getDeclaredField(field.getName());
                            saveField.setAccessible(true);
                            saveField.set(entity, field.get(dto));
                            saveField.setAccessible(false);
                        }
                    );
                    return entity;
                })
        );*/
        return null;
    }
}
