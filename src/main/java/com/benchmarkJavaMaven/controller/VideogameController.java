package com.benchmarkJavaMaven.controller;

import com.benchmarkJavaMaven.application.VideogameApplication;
import com.benchmarkJavaMaven.entitiy.VideogameEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("videogame")
public class VideogameController {

    @Autowired
    private VideogameApplication videogameApplication;

    @GetMapping("list")
    private Iterable<VideogameEntity> getVideogames() {
        return videogameApplication.getVideogames();
    }

    @GetMapping("hello-world")
    private String helloWorld() {
        return videogameApplication.helloWorld();
    }
}
