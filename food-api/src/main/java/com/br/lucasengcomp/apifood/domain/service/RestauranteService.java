package com.br.lucasengcomp.apifood.domain.service;

import com.br.lucasengcomp.apifood.domain.exception.EntidadeEmUsoException;
import com.br.lucasengcomp.apifood.domain.model.Cozinha;
import com.br.lucasengcomp.apifood.domain.model.Restaurante;
import com.br.lucasengcomp.apifood.domain.repository.CozinhaRepository;
import com.br.lucasengcomp.apifood.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Optional<Restaurante> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Restaurante> buscarTodos() {
        return repository.findAll();
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long idCozinha = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(idCozinha).get();

        if (idCozinha == null) {
            throw new EntidadeEmUsoException(String.format("Cozinha inexistente para esse codigo: %d", idCozinha));
        }

        restaurante.setCozinha(cozinha);

        return repository.save(restaurante);
    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteConsultado = repository.findById(id).get();

        if (restauranteConsultado != null) {
            BeanUtils.copyProperties(restaurante, restauranteConsultado, "id");
            repository.save(restaurante);
        }
        return restaurante;
    }
}
