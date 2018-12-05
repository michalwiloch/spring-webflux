package com.softwarehut.dataservice.demo.routing;

import com.softwarehut.dataservice.demo.controller.FuelController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class FuelRouting {

    @Bean
    public RouterFunction<ServerResponse> route(FuelController fuelController) {
        return RouterFunctions
                .route(GET("/routing/fuel/event").and(accept(APPLICATION_STREAM_JSON)), fuelController::fetchFuelStream);
    }

}
