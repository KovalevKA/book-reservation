package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import com.example.bookreservation.mapper.AbstractMapper;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
@Service
@Lazy
*/
public class AbstractServiceImpl<Entity extends AbstractEntity,
    DTO extends AbstractDTO, Repository extends ReactiveCrudRepository<Entity, Long>,
    Mapper extends AbstractMapper<Entity, DTO>>
    implements AbstractService<Entity, DTO> {

    @Autowired
    private Repository repository;
    @Autowired
    private Mapper mapper;


    @Override
    public Flux<DTO> getAll() {
        //return mapper.toDTOs(repository.findAll());
        return null;
    }

    @Override
    public Mono<DTO> getById(Long id) throws EntityNotFoundException {
        //return mapper.toDTO(repository.findById(id).orElseThrow(EntityNotFoundException::new));
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Mono<DTO> create(DTO dto) {
        Entity entity = mapper.toEntity(dto);
        //return mapper.toDTO(repository.save(entity));
        return null;
    }

    @Override
    public Mono<DTO> editById(Long id, DTO dto) {
        /*Entity saveEntity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException());

        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Field saveField;
            try {
                saveField = saveEntity.getClass().getDeclaredField(field.getName());
                saveField.setAccessible(true);
                saveField.set(saveEntity, field.get(dto));
                saveField.setAccessible(false);
            } catch (Exception e) {
                throw new IllegalArgumentException("Field not found");
            }
            field.setAccessible(false);
        }
        return mapper.toDTO(repository.saveAndFlush(saveEntity));*/
        return null;
    }
}
