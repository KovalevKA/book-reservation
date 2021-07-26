package com.example.bookreservation.service;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.dto.requestBodyParams.RequestBookSearchParam;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.AuthorRepository;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.GenreRepository;
import com.example.bookreservation.repository.TranslatorRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl
    extends AbstractServiceImpl<Book, BookDTO, BookRepository, AbstractMapper<Book, BookDTO>>
    implements BookService<Book, BookDTO> {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TranslatorRepository translatorRepository;
    @Autowired
    private AbstractMapper<Book, BookDTO> bookMapper;

    private final Gson gson = new Gson();

    private final RestHighLevelClient client;

    @Autowired
    public BookServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public List<BookDTO> search(RequestBookSearchParam params) throws Exception {
        List<BookDTO> result = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery()
            .filter(QueryBuilders.matchPhraseQuery("authors.name", params.getAuthors()))
            .filter(QueryBuilders.matchPhraseQuery("name", params.getName()))
        );
        searchRequest.source(searchSourceBuilder);
        SearchHits searchHits = client.search(searchRequest, RequestOptions.DEFAULT).getHits();
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> map = searchHit.getSourceAsMap();
            result.add(gson.fromJson(String.valueOf(map), BookDTO.class));
        }
        return result;
    }

    @Override
    public RestStatus add(RequestBookSearchParam params, BookDTO dto) throws Exception {
        IndexRequest indexRequest = new IndexRequest(params.getElIndex());
        indexRequest.source(gson.toJson(dto), XContentType.JSON);
        return client.index(indexRequest, RequestOptions.DEFAULT).status();
    }

    @Override
    public RestStatus update(RequestBookSearchParam params, BookDTO dto) throws Exception {
        UpdateRequest updateRequest = new UpdateRequest(params.getElIndex(), params.getElId());
        updateRequest.doc(gson.toJson(dto), XContentType.JSON);
        return client.update(updateRequest, RequestOptions.DEFAULT).status();
    }

    @Override
    public RestStatus delete(RequestBookSearchParam params) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(params.getElIndex()).id(params.getElId());
        return client.delete(deleteRequest, RequestOptions.DEFAULT).status();
    }


    @Override
    public BookDTO editById(Long id, BookDTO data) throws EntityNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (!data.getName().isEmpty()) {
            book.setName(data.getName());
        }
        if (!data.getPublishHouse().isEmpty()) {
            book.setPublishHouse(data.getPublishHouse());
        }
        if (data.getPublishYear() != 0) {
            book.setPublishYear(data.getPublishYear());
        }
        if (!data.getDescription().isEmpty()) {
            book.setDescription(data.getDescription());
        }
        return bookMapper.toDTO(bookRepository.saveAndFlush(book));
    }

    public List<BookDTO> findByParams(Boolean isReserved, String bookName,
        List<Long> listGenreId, List<Long> listAuthorId, List<Long> listTranslatorsId) {
        listAuthorId = listAuthorId.size() == 0 ? authorRepository.getAllIds() : listAuthorId;
        listGenreId = listGenreId.size() == 0 ? genreRepository.getAllIds() : listGenreId;
        listTranslatorsId =
            listTranslatorsId.size() == 0 ? translatorRepository.getAllIds() : listTranslatorsId;
        List<Book> books = bookRepository.getFreeByParams(bookName, listAuthorId, listGenreId,
            listTranslatorsId, new Date());
        if (!isReserved) {
            return bookMapper.toDTOs(books);
        }
        List<Book> resBooks = bookRepository.getReservByParams(bookName.toUpperCase(), listAuthorId,
            listGenreId, listTranslatorsId, new Date());
        books.addAll(resBooks);
        return bookMapper.toDTOs(books);
    }

}