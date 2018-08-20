package com.starry.webflux.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.starry.webflux.Handler.EchoHandler;
/**
 * 
 * @ClassName  EchoRouter   
 * @Description Router类似@RequestMapping 
 * @author yafei.qin 
 * @date 2018年6月29日 下午2:20:13   
 *     
 * @Copyright: 2012-2018 www.hirain.com Inc. All rights reserved.
 */
@Configuration
public class EchoRouter {
    @Bean
    public RouterFunction<ServerResponse> monoRouterFunction(EchoHandler echoHandler) {
        return route(POST("/echo").and(accept(MediaType.APPLICATION_JSON)), echoHandler::echo)
                .andRoute(GET("/all").and(accept(MediaType.APPLICATION_STREAM_JSON)), echoHandler::allUser)
                .andRoute(GET("/user/{id}").and(accept(MediaType.APPLICATION_JSON)),echoHandler::findById);
    }
}
