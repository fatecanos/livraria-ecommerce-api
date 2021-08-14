package com.fatec.livrariaecommerce.dao;

import com.fatec.livrariaecommerce.models.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeDao extends JpaRepository<Cidade, Integer> {

    Optional<Cidade> findByNome(String name);
}
