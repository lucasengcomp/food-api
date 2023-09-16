package com.br.lucasengcomp.apifood.domain.controller;

import com.br.lucasengcomp.apifood.domain.model.Cozinha;
import com.br.lucasengcomp.apifood.domain.model.CozinhasXmlWrapper;
import com.br.lucasengcomp.apifood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(repository.findAll());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listar() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cozinha> buscarCozinhaPorId(@PathVariable Long id) {
        Optional<Cozinha> cozinhaEncontrada = repository.findById(id);
        return cozinhaEncontrada.map(cozinha ->
                ResponseEntity.ok().body(cozinha)).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return repository.save(cozinha);
    }
}