package com.br.lucasengcomp.apifood.domain.repository;

import com.br.lucasengcomp.apifood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
