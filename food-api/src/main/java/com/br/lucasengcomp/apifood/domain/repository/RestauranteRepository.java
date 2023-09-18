package com.br.lucasengcomp.apifood.domain.repository;

import com.br.lucasengcomp.apifood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}