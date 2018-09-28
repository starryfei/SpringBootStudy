package com.starry.elasticsearch;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
/**
 * 
 * @ClassName  FileSearch   
 * @Description 基于elasticsearch的搜索demo   
 * @author yafei.qin 
 *
 */
public class FileSearch {
    public static void main(String[] args) throws IOException {
        Settings setting = Settings.builder()
                .put("cluster.name", "application")
                .put("client.transport.sniff", true)// 支持中文简体
                .build();
        @SuppressWarnings("resource")
        TransportClient client = new PreBuiltTransportClient(setting)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress
                        .getByName("localhost"), 9300));
        System.out.println("连接成功");
        // 创建索引
        // _index 文档在哪存放,一个 索引 应该是因共同的特性被分组到一起的文档集合
        // _type 文档表示的对象类别，数据可能在索引中只是松散的组合在一起，但是通常明确定义一些数据中的子分区是很有用的
        // _id 文档唯一标识，创建一个新的文档，要么提供自己的 _id ，要么让 Elasticsearch 帮你生成
        // use map
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "starry");
        json.put("postDate", new Date());
        json.put("message", "study Elasticsearch");
        IndexResponse index1 = client.prepareIndex("test", "hello", "2")
                .setSource(json).get();
        System.out.println(index1.getId());
        IndexResponse index = client
                .prepareIndex("test", "hello", "1")
                .setSource(jsonBuilder().startObject().field("user", "kimchy")
                                .field("postDate", new Date())
                                .field("message", "trying out Elasticsearch")
                                .endObject()).get();
        System.out.println(index.getIndex());
        // 添加索引:传入json字符串
        String jsonStr = "{" + "\"userName\":\"张三\","
                + "\"postDate\":\"2017-11-30\","
                + "\"message\":\"你好李四\"" + "}";
        IndexResponse response = client.prepareIndex("test", "hello")
                .setSource(jsonStr, XContentType.JSON).get();
        System.out.println(response.getVersion());
        //获取索引
        GetResponse getResponse = client.prepareGet("test", "hello", "1").get();
        System.out.println(getResponse.getSourceAsString());
        
        
        //设置查询条件
        //单字段搜索
        QueryBuilder query = matchQuery("message","Elasticsearch");

        //设置高亮显示
        HighlightBuilder hiBuilder=new HighlightBuilder();
        hiBuilder.preTags("<a style=\"color: #e4393c\">");
        hiBuilder.postTags("</a>");
        hiBuilder.field("message");
        //搜索
        SearchResponse searchResponse = client.prepareSearch("test")
                .setTypes("hello")
                .highlighter(hiBuilder) //设置高亮显示
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(query)
                .setFrom(0).setSize(5).setExplain(true)  //从第几个开始，显示size个数据
                .addSort("", SortOrder.ASC)
                .get();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit searchHit : hits) {
            System.out.println(searchHit.getSourceAsString());
        }
        
        client.close();
    }
}
