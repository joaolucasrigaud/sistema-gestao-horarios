package com.sistema.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> index() {
        return Map.of(
                "name", "Sistema de Gestão de Horários",
                "mode", "API",
                "swagger", "/swagger-ui.html"
        );
    }
}
