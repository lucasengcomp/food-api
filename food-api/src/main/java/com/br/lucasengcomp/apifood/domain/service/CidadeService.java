package com.br.lucasengcomp.apifood.domain.service;

import com.br.lucasengcomp.apifood.domain.exception.EntidadeEmUsoException;
import com.br.lucasengcomp.apifood.domain.exception.EntidadeNaoEncontradaException;
import com.br.lucasengcomp.apifood.domain.model.Cidade;
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
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return repository.findAll();
    }

    public Optional<Cidade> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Cidade adicionar(Cidade cidade) {
        Long idEstado = cidade.getEstado().getId();
        Estado estado = estadoRepository.findById(idEstado).get();

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro dessa cidade com código %d", idEstado));
        }
        cidade.setEstado(estado);

        return repository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            repository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", cidadeId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
        }
    }
}
