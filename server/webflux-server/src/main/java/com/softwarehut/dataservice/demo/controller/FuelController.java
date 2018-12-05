package com.softwarehut.dataservice.demo.controller;

import com.softwarehut.dataservice.demo.data.FuelDataGenerator;
import com.softwarehut.dataservice.demo.model.Fuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.time.Duration;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class FuelController {

    @Autowired
    FuelDataGenerator fuelDataGenerator;

    public Mono<ServerResponse> fetchFuelStream(ServerRequest request) {
        return ok()
                .contentType(APPLICATION_STREAM_JSON)
                .body(fuelDataGenerator.fetchFuelStream(Duration.ofSeconds(5)).share(), Fuel.class);
    }

}
