package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import org.elasticsearch.rest.RestStatus;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class BookElasticSerachServiceImpl implements CommonElasticSearchService<BookDTO> {

    @Autowired
    private AbstractMapper<Book, BookDTO> mapper;
   
    private final EntityManager entityManager;

    @Autowired
    public BookElasticSerachServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            SearchSession searchSession = Search.session(entityManager);
            MassIndexer indexer = searchSession.massIndexer(Book.class)
                    .threadsToLoadObjects(7);
            indexer.startAndWait();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException("Cant create indexer");
        }

    }

    @Override
    public RestStatus add(BookDTO dto) throws Exception {
        entityManager.persist(mapper.toEntity(dto));
        return RestStatus.OK;
    }

    @Override
    public RestStatus update(String id, BookDTO dto) throws Exception {
        return null;
    }

    @Override
    public RestStatus delete(String id) throws Exception {
        return null;
    }

    @Override
    public List<BookDTO> search(String keyWords) {
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Book> result = searchSession.search(Book.class)
                .where(searchPredicateFactory ->
                        searchPredicateFactory.match()
                                .field("name")
                                .matching("book124")
                ).fetchAll();
        return mapper.toDTOs(result.hits());
    }

    @Override
    public List<BookDTO> getWithPagination(Integer countInPage, Integer page) throws IOException {
        return null;
    }
}
