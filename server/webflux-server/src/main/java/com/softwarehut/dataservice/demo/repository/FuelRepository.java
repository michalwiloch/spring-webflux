package com.softwarehut.dataservice.demo.repository;

import com.softwarehut.dataservice.demo.model.Fuel;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FuelRepository extends ReactiveMongoRepository<Fuel, String> {
}
