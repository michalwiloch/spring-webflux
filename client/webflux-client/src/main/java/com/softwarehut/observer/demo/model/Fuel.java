package com.softwarehut.observer.demo.model;

import java.math.BigDecimal;
import java.math.MathContext;


public class Fuel {

    private static final MathContext MATH_CONTEXT = new MathContext(2);

    private String id;
    private String name;
    private BigDecimal price;
    private Long time;

    public Fuel() {}

    public Fuel(String name, BigDecimal price, Long time) {
        this.name = name;
        this.price = price;
        this.time = time;
    }

    public Fuel(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Fuel(String name, Double price) {
        this(name, new BigDecimal(price, MATH_CONTEXT));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
