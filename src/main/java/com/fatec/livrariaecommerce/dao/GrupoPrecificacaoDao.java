package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.GrupoPrecificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface GrupoPrecificacaoDao extends JpaRepository<GrupoPrecificacao, Integer> {
}
