package com.benchmarkJavaMaven.application;

import com.benchmarkJavaMaven.entitiy.VideogameEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class VideogameApplication {

    public String helloWorld() {
        return "Hello world";
    }
    public Iterable<VideogameEntity> getVideogames() {
        ArrayList<VideogameEntity> listVideogames = new ArrayList<VideogameEntity>();
        VideogameEntity videogameEntity = new VideogameEntity();
        videogameEntity.setId("hashID");
        videogameEntity.setName("Spiderman");
        videogameEntity.setDeveloper("Insomiac");
        videogameEntity.setPrice(1200d);

        listVideogames.add(videogameEntity);

        videogameEntity.setId("hashID2");
        videogameEntity.setName("Spiderman 2");
        videogameEntity.setDeveloper("Insomiac 2");
        videogameEntity.setPrice(1200d);
        return listVideogames;
    }
}
