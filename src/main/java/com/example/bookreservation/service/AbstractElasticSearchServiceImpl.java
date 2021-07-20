package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.entity.AbstractEntity;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractElasticSearchServiceImpl<Entity extends AbstractEntity, DTO extends AbstractDTO>
    implements AbstractElasticSearchService<Entity, DTO> {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public List<DTO> getAll() {
        return null;
    }

    @Override
    public DTO getById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public DTO create(DTO dto) {
        return null;
    }

    @Override
    public DTO editById(Long id, DTO dto) {
        return null;
    }

    @Override
    public List<DTO> search(String title, Map<String, ?> params) throws Exception {
        SearchRequest searchRequest = new SearchRequest(title);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        params.keySet().forEach(s -> {
            searchSourceBuilder.query(QueryBuilders.matchQuery(s, params.get(s)));
        });
        searchRequest.source(searchSourceBuilder);
        SearchHits searchHits = client.search(searchRequest, RequestOptions.DEFAULT).getHits();
        return null;
    }
}
