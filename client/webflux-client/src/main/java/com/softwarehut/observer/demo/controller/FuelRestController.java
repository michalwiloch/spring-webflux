package com.softwarehut.observer.demo.controller;

import com.softwarehut.observer.demo.model.Fuel;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;

@RestController
public class FuelRestController {

    public FuelRestController() {
    }

    @GetMapping(path = "/rest/genereteddata/fuel/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Fuel> fetchQuotesStream() {
        return WebClient.create("http://localhost:9081")
                .get()
                .uri("/rest/generateddata/fuel/event")
                .accept(APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Fuel.class)
                .share()
                .log();
    }

    @GetMapping(path = "/rest/mongodata/fuel/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Fuel> fetchFuelStream() {
        return WebClient.create("http://localhost:9081")
                .get()
                .uri("/rest/mongodata/fuel/event")
                .accept(TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Fuel.class)
                .share()
                .log();
    }
}
