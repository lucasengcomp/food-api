package com.br.lucasengcomp.apifood.domain.controller;

import com.br.lucasengcomp.apifood.domain.exception.EntidadeEmUsoException;
import com.br.lucasengcomp.apifood.domain.exception.EntidadeNaoEncontradaException;
import com.br.lucasengcomp.apifood.domain.model.Estado;
import com.br.lucasengcomp.apifood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private EstadoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estado> buscarEstadoPorId(@PathVariable Long id) {
        Optional<Estado> estadoEncontrado = service.buscarPorId(id);
        return estadoEncontrado.map(cozinha ->
                ResponseEntity.ok().body(cozinha)).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Estado> listar() {
        return service.buscarTodos();
    }

    @PostMapping
    public Estado salvar(Estado estado) {
        return service.adicionar(estado);
    }

    @DeleteMapping
    public void excluir(Long estadoId) {
        try {
            service.excluir(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
        }
    }
}
