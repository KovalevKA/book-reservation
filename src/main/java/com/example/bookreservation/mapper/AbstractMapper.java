package com.example.bookreservation.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface AbstractMapper<Entity, DTO> {

    DTO toDTO(Entity entity);

    Entity toEntity(DTO dto);

    default List<DTO> toDTOs(List<Entity> entityes) {
        List<DTO> ts = new ArrayList<>();
        entityes.stream().map(entity -> ts.add(toDTO(entity)))
                .collect(Collectors.toList());
        return ts;
    }

    default List<Entity> toEntities(List<DTO> dtos) {
        List<Entity> ts = new ArrayList<>();
        dtos.stream().map(dto -> ts.add(toEntity(dto)))
                .collect(Collectors.toList());
        return ts;
    }

}
