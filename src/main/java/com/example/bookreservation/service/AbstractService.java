package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import java.util.List;
import javax.persistence.EntityExistsException;

public interface AbstractService<Entity extends AbstractEntity, DTO extends AbstractDTO> {

  List<DTO> getAll();

  DTO getById(Long id);

  void deleteById(Long id);

  DTO create(DTO dto);

  DTO editById(Long id, DTO dto) throws IllegalAccessException;

  default Entity getByName(String name) {
    throw new EntityExistsException("Can't found by name");
  }

  default List<DTO> getByNameLike(String string) {
    throw new EntityExistsException("Can't found by name like");
  }
}
