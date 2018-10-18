package com.starryfei.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描的Mapper类的包的路径,不需要在具体类上面添加@Mapper
//@MapperScan("com.starryfei.elastic.dao")
@SpringBootApplication
public class ElasticSearchApplication {
//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class, args);
    }
}
