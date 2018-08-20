package com.starry.webflux.controller;

import io.netty.util.internal.ThreadLocalRandom;

import java.time.Duration;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

@RestController
public class ServerSendEventsController {
    @SuppressWarnings("rawtypes")
    @RequestMapping("/server")
    @ResponseBody
    public Flux<ServerSentEvent> sendInfo() {
        return Flux.interval(Duration.ofMillis(10))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }
}
