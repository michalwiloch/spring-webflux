package com.softwarehut.observer.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuelController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/fuel")
    public String quotes() {
        return "fuel";
    }

    @GetMapping("/fuelmongo")
    public String fuels() {
        return "fuelmongo";
    }
}
