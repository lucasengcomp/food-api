package com.br.lucasengcomp.apifood.domain.controller;

import com.br.lucasengcomp.apifood.domain.exception.EntidadeNaoEncontradaException;
import com.br.lucasengcomp.apifood.domain.model.Cozinha;
import com.br.lucasengcomp.apifood.domain.model.Restaurante;
import com.br.lucasengcomp.apifood.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = service.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante restauranteConsultado = service.atualizar(id, restaurante);

        if (restauranteConsultado != null) {
            BeanUtils.copyProperties(restaurante, restauranteConsultado, "id");
            service.salvar(restaurante);
            return ResponseEntity.ok(restauranteConsultado);
        }

        return ResponseEntity.notFound().build();
    }
}
