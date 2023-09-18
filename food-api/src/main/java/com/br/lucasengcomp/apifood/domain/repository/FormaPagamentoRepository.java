package com.br.lucasengcomp.apifood.domain.repository;

import com.br.lucasengcomp.apifood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}