package com.softwarehut.dataservice.demo.data;

import com.softwarehut.dataservice.demo.model.Fuel;
import com.softwarehut.dataservice.demo.repository.FuelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;

@Component
public class FuelDataGenerator {

    @Autowired
    FuelRepository fuelRepository;

    private final List<Fuel> fuel = new ArrayList<>();

    private final MathContext MATH_CONTEXT = new MathContext(2);

    private final Random random = new Random();

    public FuelDataGenerator() {
        this.fuel.add(new Fuel("PB95", 5.09));
        this.fuel.add(new Fuel("PB98", 5.37));
        this.fuel.add(new Fuel("LPG", 2.39));
        this.fuel.add(new Fuel("ON", 5.40));
    }

    private List<Fuel> generateNewPriceFuel(long interval) {
        return fuel.stream()
                .map(baseFuel -> {
                    BigDecimal priceChange = baseFuel.getPrice()
                            .multiply(new BigDecimal(0.05 * this.random.nextDouble()), this.MATH_CONTEXT);
                    Fuel result = new Fuel(baseFuel.getName(), baseFuel.getPrice().add(priceChange));
                    result.setTime(new Date().getTime());
                    return result;
                })
                .collect(Collectors.toList());
    }

    public Flux<Fuel> fetchFuelStream(Duration period) {
        return Flux.interval(period)
                .onBackpressureDrop()
                .map(this::generateNewPriceFuel)
                .flatMapIterable(fuelList -> fuelList)
                .log("com.softwarehut.dataservice.demo.data");
    }
}
