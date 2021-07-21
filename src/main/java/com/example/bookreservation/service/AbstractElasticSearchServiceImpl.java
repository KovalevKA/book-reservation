package com.example.bookreservation.service;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.dto.requestBodyParams.AbstractRequestParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractElasticSearchServiceImpl<Params extends AbstractRequestParams, DTO extends AbstractDTO>
    implements AbstractElasticSearchService<Params, DTO> {

    private final ModelMapper mapper = new ModelMapper();
    private final RestHighLevelClient client;

    @Autowired
    public AbstractElasticSearchServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public RestStatus add(Params params, DTO dto) throws Exception {
        IndexRequest indexRequest = new IndexRequest(params.getIndex());
        indexRequest.id(dto.getId().toString());
        indexRequest.source(dto.toString(), XContentType.JSON);
        return client.index(indexRequest, RequestOptions.DEFAULT).status();
    }

    @Override
    public RestStatus update(Params params, DTO dto) {
        return null;
    }

    @Override
    public RestStatus delete(Params params, DTO dto) {
        return null;
    }

    @Override
    public List<DTO> search(Params params, DTO dto) throws Exception {
        List<DTO> result = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(params.getIndex());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchHits searchHits = client.search(searchRequest, RequestOptions.DEFAULT).getHits();
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> map = searchHit.getSourceAsMap();
            result.add((DTO) mapper.map(map, dto.getClass().getComponentType()));
        }
        return result;

    }
}
