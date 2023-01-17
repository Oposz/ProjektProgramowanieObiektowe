package com.example.programowanieobiektoweprojekt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello.html";
    }
}
