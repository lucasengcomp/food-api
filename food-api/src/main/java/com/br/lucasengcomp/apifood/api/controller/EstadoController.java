package com.br.lucasengcomp.apifood.api.controller;

import com.br.lucasengcomp.apifood.domain.exception.EntidadeEmUsoException;
import com.br.lucasengcomp.apifood.domain.exception.EntidadeNaoEncontradaException;
import com.br.lucasengcomp.apifood.domain.model.Estado;
import com.br.lucasengcomp.apifood.domain.repository.EstadoRepository;
import com.br.lucasengcomp.apifood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private EstadoService service;

    @GetMapping
    public List<Estado> listar() {
        return repository.findAll();
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado = repository.findById(estadoId).get();

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return service.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId,
                                            @RequestBody Estado estado) {
        Estado estadoAtual = repository.findById(estadoId).get();

        if (estadoAtual != null) {
            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual = service.salvar(estadoAtual);
            return ResponseEntity.ok(estadoAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            service.excluir(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
