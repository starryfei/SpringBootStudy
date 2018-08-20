package com.starry.webflux.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.starry.webflux.bean.User;
/**
 * 
 * @ClassName  Client   
 * @Description 连接server端
 * @author yafei.qin 
 * @date 2018年6月29日 下午3:28:37   
 *     
 * @Copyright: 2012-2018 www.hirain.com Inc. All rights reserved.
 */
public class Client {
    public static void main(String[] args) {
        //
        ClientResponse response = WebClient.create("http://localhost:8080")
                .get().uri("/echo").accept(MediaType.APPLICATION_JSON)
                .exchange().block();
        assert response.statusCode().value() == 200;
        
        User user = WebClient.create("http://localhost:8080").get().uri("/user/1")
                .accept(MediaType.APPLICATION_JSON).exchange().flatMap(resp -> resp.bodyToMono(User.class)).block();
        assert user.getId() == 1;
        System.out.println(user);

    }
}
