package com.example.bookreservation.service;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.mapper.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AuthorService extends
        CommonServiceImpl<Author, AuthorDTO, AbstractMapper<Author, AuthorDTO>> {

    @Autowired
    EntityManager entityManager;
    @Autowired
    private AbstractMapper<Author, AuthorDTO> authorMapper;

    public AuthorService() {
        super();
        setClazz(Author.class);
    }

    @Override
    public List<AuthorDTO> getByNameLike(String name) {
        return authorMapper.toDTOs(entityManager
                .createQuery("FROM Author WHERE LOWER(name) LIKE LOWER(:name)")
                .setParameter("name", "%" + name + "%")
                .getResultList()
        );
    }

    @Override
    public Author getByName(String name) {
        return (Author) entityManager
                .createQuery("FROM Author WHERE LOWER(name) = LOWER(:name) ")
                .setParameter("name", name)
                .getSingleResult();


    }

}
