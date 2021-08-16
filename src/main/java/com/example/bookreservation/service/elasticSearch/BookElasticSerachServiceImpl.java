package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import org.hibernate.search.backend.elasticsearch.ElasticsearchExtension;
import org.hibernate.search.backend.elasticsearch.search.query.ElasticsearchSearchQuery;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    public List<BookDTO> search(String keyWords) {
        SearchSession searchSession = Search.session(entityManager);
        ElasticsearchSearchQuery<Book> query = searchSession.search(Book.class)
                .extension(ElasticsearchExtension.get())
                .where(f -> f.matchAll())
                .toQuery();
        ElasticsearchSearchQuery<Book> searchQuery = query.extension(ElasticsearchExtension.get());
        return mapper.toDTOs(searchQuery.fetchAll().hits());
    }

}
