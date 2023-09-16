package com.br.lucasengcomp.apifood.domain.service;

import com.br.lucasengcomp.apifood.domain.exception.EntidadeEmUsoException;
import com.br.lucasengcomp.apifood.domain.exception.EntidadeNaoEncontradaException;
import com.br.lucasengcomp.apifood.domain.model.Estado;
import com.br.lucasengcomp.apifood.domain.repository.CidadeRepository;
import com.br.lucasengcomp.apifood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Estado adicionar(Estado estado) {
        Long idEstado = estado.getId();
        Estado estadoEncontrado = repository.findById(idEstado).get();

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de estado com código %d", idEstado));
        }

        return repository.save(estadoEncontrado);
    }

    public void excluir(Long EstadoId) {
        try {
            repository.deleteById(EstadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de Estado com código %d", EstadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso", EstadoId));
        }
    }

    public List<Estado> buscarTodos() {
        return repository.findAll();
    }

    public Optional<Estado> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
