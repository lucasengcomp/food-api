package com.br.lucasengcomp.apifood.domain.repository;

import com.br.lucasengcomp.apifood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
