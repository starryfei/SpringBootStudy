package com.starry.webflux.Handler;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.starry.webflux.bean.User;
import com.starry.webflux.dao.UserDao;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @ClassName  EchoHandler   
 * @Description 类似Service \\ Controller   
 * @author yafei.qin 
 * @date 2018年6月29日 下午2:19:33   
 *     
 */
@Component
public class EchoHandler{
    @Autowired
    private UserDao userDao;
    public Mono<ServerResponse> echo(ServerRequest request) {
        return ServerResponse.ok().body(request.bodyToMono(String.class), String.class);
    }
    
    public Mono<ServerResponse> allUser(ServerRequest request) {
        return ServerResponse.ok().body(Flux.fromIterable(userDao.findAll()) , User.class);
    }
    public Mono<ServerResponse> findById(ServerRequest request) {
        return Mono.justOrEmpty(userDao.findById(Long.valueOf(request.pathVariable("id"))))
                .flatMap(user -> ServerResponse.ok().body(Mono.just(user), User.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
