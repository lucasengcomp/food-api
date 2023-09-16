package com.br.lucasengcomp.apifood.domain.controller;

import com.br.lucasengcomp.apifood.domain.model.Restaurante;
import com.br.lucasengcomp.apifood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurante> listar() {
        return service.buscarTodos();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        Optional<Restaurante> restauranteEncontrado = service.buscarPorId(id);
        return restauranteEncontrado.map(restaurante ->
                ResponseEntity.ok().body(restaurante)).orElseGet(() ->
                ResponseEntity.notFound().build());
    }
}
