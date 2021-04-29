package com.example.bookreservation.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    default Set<DTO> toDTOs(Set<Entity> entityes) {
        Set<DTO> ts = new HashSet<>();
        entityes.stream().map(entity -> ts.add(toDTO(entity)))
                .collect(Collectors.toList());
        return ts;
    }

    default Set<Entity> toEntities(Set<DTO> dtos) {
        Set<Entity> ts = new HashSet<>();
        dtos.stream().map(dto -> ts.add(toEntity(dto)))
                .collect(Collectors.toList());
        return ts;
    }

}
