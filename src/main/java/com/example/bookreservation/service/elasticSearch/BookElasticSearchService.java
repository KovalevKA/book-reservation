package com.example.bookreservation.service.elasticSearch;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.dto.requestBodyParams.RequestBookSearchParam;
import com.example.bookreservation.entity.Book;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

@Service
public class BookElasticSearchService implements
    AbstractElasticSearchService<RequestBookSearchParam, BookDTO> {

    private final Gson gson = new Gson();

    private final RestHighLevelClient client;

    public BookElasticSearchService(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public List<BookDTO> getWithPagination(Integer countInPage, Integer page) throws IOException {
        if (countInPage <= 0 || page <= 0) {
            throw new IOException("Error count or page count");
        }
        SearchRequest searchRequest = new SearchRequest("books");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(countInPage * (page - 1));
        searchSourceBuilder.size(countInPage);
        searchRequest.source(searchSourceBuilder);
        SearchHits searchHits = client.search(searchRequest, RequestOptions.DEFAULT).getHits();
        return mapping(searchHits);
    }

    @Override
    public RestStatus add(BookDTO BookDTO)
        throws Exception {
        IndexRequest indexRequest = new IndexRequest(Book.INDEX);
        indexRequest.source(gson.toJson(BookDTO), XContentType.JSON);
        return client.index(indexRequest, RequestOptions.DEFAULT).status();
    }

    @Override
    public RestStatus update(String id, BookDTO BookDTO)
        throws Exception {
        UpdateRequest updateRequest = new UpdateRequest(Book.INDEX, id);
        updateRequest.doc(gson.toJson(BookDTO), XContentType.JSON);
        return client.update(updateRequest, RequestOptions.DEFAULT).status();
    }

    @Override
    public RestStatus delete(String id) throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest(Book.INDEX).id(id);
        return client.delete(deleteRequest, RequestOptions.DEFAULT).status();
    }

    @Override
    public List<BookDTO> search(RequestBookSearchParam params) throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery()
            .filter(QueryBuilders.matchPhraseQuery("authors.name", params.getAuthors()))
            .filter(QueryBuilders.matchPhraseQuery("name", params.getName()))
        );
        searchRequest.source(searchSourceBuilder);
        return mapping(client.search(searchRequest, RequestOptions.DEFAULT).getHits());
    }

    private List<BookDTO> mapping(SearchHits searchHits) {
        List<BookDTO> result = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> map = searchHit.getSourceAsMap();
            result.add(gson.fromJson(String.valueOf(map), BookDTO.class));
        }
        return result;
    }

}
