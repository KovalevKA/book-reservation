package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import com.example.bookreservation.mapper.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;

/*
@Service
@Lazy
*/
public class AbstractServiceImpl<Entity extends AbstractEntity, DTO extends AbstractDTO, Repository extends JpaRepository<Entity, Long>, Mapper extends AbstractMapper<Entity, DTO>>
        implements AbstractService<Entity, DTO> {

    @Autowired
    private Repository repository;
    @Autowired
    private Mapper mapper;

    @Override
    public List<DTO> getAll() {
        return mapper.toDTOs(repository.findAll());
    }

    @Override
    public DTO getById(Long id) {
        return mapper.toDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public DTO getByName(String name) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public DTO create(DTO dto) {
        Entity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.saveAndFlush(entity));
    }

    @Override
    public DTO editById(Long id, DTO dto) {
        Entity saveEntity = repository.findById(id)
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
        return mapper.toDTO(repository.saveAndFlush(saveEntity));
    }
}
