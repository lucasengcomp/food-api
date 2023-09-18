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

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Estado salvar(Estado estado) {
        return repository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            repository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
        }
    }
}
