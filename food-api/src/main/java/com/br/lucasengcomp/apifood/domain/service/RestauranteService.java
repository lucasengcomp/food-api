package com.br.lucasengcomp.apifood.domain.service;

import com.br.lucasengcomp.apifood.domain.exception.EntidadeNaoEncontradaException;
import com.br.lucasengcomp.apifood.domain.model.Cozinha;
import com.br.lucasengcomp.apifood.domain.model.Restaurante;
import com.br.lucasengcomp.apifood.domain.repository.CozinhaRepository;
import com.br.lucasengcomp.apifood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha" +
                        "com o código %d", cozinhaId)));

        restaurante.setCozinha(cozinha);

        return repository.salvar(restaurante);
    }
}
