package com.Practica1.ResourceServer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("api/pokemon")
public class PokemonController {

    @GetMapping("{pokemon}")
    public String getPokemon(@PathVariable("pokemon") String pokemon ){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <String> response = restTemplate.getForEntity("https://pokeapi.co/api/v2/pokemon/"+pokemon, String.class);
        return response.getBody();
    }
}

