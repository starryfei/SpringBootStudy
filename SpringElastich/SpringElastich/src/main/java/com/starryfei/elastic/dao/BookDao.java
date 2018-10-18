package com.starryfei.elastic.dao;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import com.starryfei.elastic.bean.Book;


public class BookDao {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    
    public List<Book> searchByName(String name) {
      //设置高亮显示
//        HighlightBuilder hiBuilder=new HighlightBuilder();
//        hiBuilder.preTags("<a style=\"color: #e4393c\">");
//        hiBuilder.postTags("</a>");
//        hiBuilder.field("message");
        
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(QueryBuilders.queryStringQuery(name))
        .withSort(SortBuilders.scoreSort())
        .withHighlightFields(new HighlightBuilder.Field(name))
        .build();
        List<Book> books = elasticsearchTemplate.queryForList(searchQuery, Book.class);
        return books;
    }
}
