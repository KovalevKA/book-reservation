package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import com.example.bookreservation.mapper.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.List;

@Transactional
public class CommonServiceImpl<Entity extends AbstractEntity,
        DTO extends AbstractDTO,
        Mapper extends AbstractMapper<Entity, DTO>>
        implements CommonService<Entity, DTO> {

  @Autowired
  private Mapper mapper;
  @Autowired
  EntityManager entityManager;

  private Class<Entity> clazz;

  public void setClazz(Class<Entity> clazzToSet) {
    this.clazz = clazzToSet;
  }

  @Override
  public List<DTO> getAll() {
    return mapper.toDTOs(entityManager.createQuery("FROM " + clazz.getName()).getResultList());
  }

  @Override
  public DTO getById(Long id) {
    return mapper.toDTO(
            entityManager.find(clazz, id)
    );
  }

  @Override
  public void deleteById(Long id) {
    entityManager.remove(getById(id));
  }

  @Override
  public DTO create(DTO dto) {
    entityManager.persist(mapper.toEntity(dto));
    return dto;
  }

  @Override
  public DTO editById(Long id, DTO dto) {
    Entity saveEntity = entityManager.find(clazz, id);

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
    return mapper.toDTO(saveEntity);
  }
}
