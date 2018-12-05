package com.softwarehut.dataservice.demo.controller;

import com.softwarehut.dataservice.demo.data.FuelDataGenerator;
import com.softwarehut.dataservice.demo.model.Fuel;
import com.softwarehut.dataservice.demo.repository.FuelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FuelRestController {

    @Autowired
    FuelDataGenerator fuelDataGenerator;

    @Autowired
    FuelRepository fuelRepository;

    @GetMapping(path = "/rest/generateddata/fuel/event", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Fuel> getFuelFromGeneratedData(){
        return fuelDataGenerator.fetchFuelStream(Duration.ofSeconds(5)).share();
    }

    @GetMapping(path = "/rest/mongodata/fuel/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Fuel> getFuelFromMongo(){
        return fuelRepository.findAll().sort((ob1,ob2)-> ob2.getId().compareTo(ob1.getId())).limitRequest(40).sort(Comparator.comparing(Fuel::getId));
    }

    @PostMapping("/rest/fuel/add")
    public Mono<Fuel> creteNewDocument(@RequestBody  Fuel fuel){
        return fuelRepository.save(fuel);
    }

    @PostMapping("/rest/fuel/addAll")
    public Flux<Fuel> creteNewDocuments(@RequestBody List<Fuel> fuel){
        return fuelRepository.saveAll(fuel);
    }
}
