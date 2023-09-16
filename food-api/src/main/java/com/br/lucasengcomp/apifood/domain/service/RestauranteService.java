package com.br.lucasengcomp.apifood.domain.service;

import com.br.lucasengcomp.apifood.domain.model.Restaurante;
import com.br.lucasengcomp.apifood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public Optional<Restaurante> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
